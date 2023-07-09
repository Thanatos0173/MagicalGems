package com.thanatos.magicalgems.TileEntities;

import com.thanatos.magicalgems.container.AlchemyTableContainer;
import com.thanatos.magicalgems.data.recipes.ModRecipeTypes;
import com.thanatos.magicalgems.data.recipes.alchemy_table.AlchemyTableRecipe;
import com.thanatos.magicalgems.data.recipes.distillery.DistilleryRecipe;
import com.thanatos.magicalgems.init.ModItems;
import com.thanatos.magicalgems.main;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class AlchemyTableTile extends TileEntity implements ITickableTileEntity {
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    private Item FUEL = ModItems.upgraded_blaze_powder.get();
    private int ticking;
    private int FE;
    private int resultSec;
    private String firstPotion = "";

   private Item[] specialGemList = {ModItems.poison_gem.get(),ModItems.slowness_gem.get(),ModItems.strength_gem.get(),ModItems.fire_resistance_gem.get(),ModItems.slow_falling_gem.get(),ModItems.jump_boost_gem.get(),
            ModItems.regeneration_gem.get(),ModItems.water_breathing_gem.get(),ModItems.speed_gem.get(),ModItems.night_vision_gem.get(),ModItems.haste_gem.get(),ModItems.dolphins_grace_gem.get(),ModItems.health_boost_gem.get()};

    private String[] potionList = {"item.minecraft.potion.effect.poison","item.minecraft.potion.effect.slowness","item.minecraft.potion.effect.strength",
            "item.minecraft.potion.effect.fire_resistance","item.minecraft.potion.effect.slow_falling","item.minecraft.potion.effect.leaping",
            "item.minecraft.potion.effect.regeneration", "item.minecraft.potion.effect.water_breathing",
            "item.minecraft.potion.effect.swiftness","item.minecraft.potion.effect.night_vision","item.magicalgems.haste_essence.json","item.magicalgems.dolphins_grace_essence","item.magicalgems.health_boost_essence"};

    public AlchemyTableTile() {
        this(ModTileEntities.ALCHEMY_TABLE_TILE.get());
    }
    public AlchemyTableTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.FE = nbt.getInt("FE");
        this.resultSec = nbt.getInt("resultSec");
        this.firstPotion = nbt.getString("firstPotion");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.putInt("FE",FE);
        compound.putInt("resultSec",resultSec);
        compound.putString("firstPotion",firstPotion);
        return super.write(compound);
    }
    private boolean isInListItemList(ItemStack stack, Item[] list){
        for (Item item : list) {
            if (item == stack.getItem()) return true;
        }
        return false;
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(AlchemyTableContainer.TE_INVENTORY_SLOT_COUNT) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:  return stack.getItem() == FUEL;
                    case 1:  return stack.getItem() == Items.POTION || stack.getItem() == ModItems.haste_essence.get() || stack.getItem() == ModItems.dolphins_grace_essence.get() || stack.getItem() == ModItems.health_boost_essence.get();
                    case 2:  return stack.getItem() == ModItems.gem.get();
                    case 3:  return isInListItemList(stack,specialGemList);
                    default: return false;
                }
            }
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private boolean isSlotNotEmpty(int slot){
        return this.itemHandler.getStackInSlot(slot).getCount() > 0;
    }



    public void removeOfSlotOneAndTwo() {
        this.itemHandler.getStackInSlot(1).shrink(1);
        this.itemHandler.getStackInSlot(2).shrink(1);
    }



    private boolean allSlotsAreNotEmpty() {
        return isSlotNotEmpty(0) && isSlotNotEmpty(1) && isSlotNotEmpty(2);
    }



    private Item generateGem(String potion){
        for(int i = 0; i < potionList.length;i++){
            System.out.println(potion + "was compared to " + potionList[i] + " and the string are " + (potionList[i].equalsIgnoreCase(potion)? "" : "not ") + "equals");
            if(potionList[i].equalsIgnoreCase(potion)){
                return specialGemList[i];
            }
        }
        return null;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("FE",FE);
        nbt.putInt("resultSec",resultSec);
        nbt.putString("firstPotion",firstPotion);

        return new SUpdateTileEntityPacket(getPos(),-1,nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT tag = pkt.getNbtCompound();
        if(tag.contains("FE")){
            FE = tag.getInt("FE");
            this.getTileData().putInt("FE",FE);
        }
        if(tag.contains("resultSec")){
            resultSec = tag.getInt("resultSec");
            this.getTileData().putInt("resultSec",resultSec);
        }
        if(tag.contains("firstPotion")){
            firstPotion = tag.getString("firstPotion");
            this.getTileData().putString("firstPotion",firstPotion);
        }

    }
   @Override
   public void tick() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemHandler.getStackInSlot(i));
        }

        Optional<AlchemyTableRecipe> recipe = world.getRecipeManager()
                .getRecipe(ModRecipeTypes.ALCHEMY_TABLE_RECIPE, inv, world);

        recipe.ifPresent(iRecipe -> {

            ItemStack output = iRecipe.getRecipeOutput();
            if (this.itemHandler.getStackInSlot(3).getCount() == 0 || itemHandler.getStackInSlot(1).getTranslationKey().equalsIgnoreCase(firstPotion)) {
                if (this.itemHandler.getStackInSlot(3).getCount() == 0)
                    firstPotion = itemHandler.getStackInSlot(1).getTranslationKey();
                if (ticking > 20) {
                    this.ticking = 0;
                    this.FE++;
                    this.resultSec++;
                    if (((FE % 74) == 0) && (this.itemHandler.getStackInSlot(0).getCount() > 0))
                        this.itemHandler.getStackInSlot(0).shrink(1);
                    if (Math.round(resultSec / 19) >= 1) {
                        this.resultSec = 0;
                        removeOfSlotOneAndTwo();
                        this.itemHandler.insertItem(3, output, false);
                    }
                    assert world != null;
                    world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                    this.markDirty();
                } else ticking++;
            }
            this.markDirty();
        });

        if(!isSlotNotEmpty(0) && FE != 0) {
            FE = 0;
            assert world != null;
            world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
            this.markDirty();
        }
        if((!isSlotNotEmpty(1) || !isSlotNotEmpty(2)) && resultSec != 0) {
            resultSec = 0;
            assert world != null;
            world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
            this.markDirty();
        }
   }


    public void addSlotContentToArray(List<ItemStack> arrayList){
        for(int i = 0; i <= 3; i++) arrayList.add(itemHandler.getStackInSlot(i));
    }
}

