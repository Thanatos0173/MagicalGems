package com.thanatos.magicalgems.TileEntities;

import com.thanatos.magicalgems.container.DistilleryContainer;
import com.thanatos.magicalgems.init.ModItems;
import com.thanatos.magicalgems.main;
import net.minecraft.block.BlockState;
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

public class DistilleryTile extends TileEntity implements ITickableTileEntity {
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private int ticking;
    private int FE;
    private int resultSec;
    private int FEtick;

    private Item itemInSlot1;

    private Item[] slot1 = {Items.DIAMOND_PICKAXE,ModItems.dolphins_scute.get(),Items.HEART_OF_THE_SEA};

    private String[] slot2 = {"item.minecraft.potion.effect.swiftness","item.minecraft.potion.effect.swiftness","item.minecraft.potion.effect.regeneration"};

    private Item[] slot3 = {ModItems.haste_essence.get(),ModItems.dolphins_grace_essence.get(),ModItems.health_boost_gem.get()};
    public DistilleryTile() {
        this(ModTileEntities.DISTILLERY_TILE.get());
    }
    public DistilleryTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }


    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.FE = nbt.getInt("FE");
        this.resultSec = nbt.getInt("resultSec");
        this.FEtick = nbt.getInt("FEtick");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.putInt("FE",FE);
        compound.putInt("resultSec",resultSec);
        compound.putInt("FEtick",FEtick);
        return super.write(compound);
    }


    private ItemStackHandler createHandler() {
        return new ItemStackHandler(DistilleryContainer.TE_INVENTORY_SLOT_COUNT) {

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:
                        return stack.getItem() == ModItems.upgraded_blaze_powder.get();
                    case 1:
                        return stack.getItem() == Items.DIAMOND_PICKAXE || stack.getItem() == ModItems.dolphins_scute.get() || stack.getItem() == Items.HEART_OF_THE_SEA;
                    case 2:
                        return stack.getTranslationKey().equalsIgnoreCase("item.minecraft.potion.effect.swiftness") || stack.getTranslationKey().equalsIgnoreCase("item.minecraft.potion.effect.regeneration");
                    case 3:
                        return stack.getItem() == ModItems.empty_vial.get() || stack.getItem() == ModItems.haste_essence.get() || stack.getItem() == ModItems.dolphins_grace_essence.get() || stack.getItem() == ModItems.health_boost_essence.get();
                    default:
                        return false;
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

    private boolean allSlotsAreCompatibles(Item item1, String item2) {
        for(int i = 0; i < slot1.length; i++){
            if(slot1[i] == item1) return item2.equalsIgnoreCase(slot2[i]);
        }
        return false;
    }

    private boolean isSlotNotEmpty(int slot){
        return this.itemHandler.getStackInSlot(slot).getCount() > 0;
    }

    private boolean allSlotsAreNotEmpty() {
        return isSlotNotEmpty(0) && isSlotNotEmpty(1) && isSlotNotEmpty(2);
    }

    private boolean isItemOnSlot(Item item, int slot){return item == this.itemHandler.getStackInSlot(slot).getItem();}

    private void removeOfSlotOneAndTwo() {
        this.itemHandler.getStackInSlot(1).shrink(1);
        this.itemHandler.getStackInSlot(2).shrink(1);
    }

    private Item generateVial(Item item1){
        for(int i = 0; i < slot1.length; i++){
            if(slot1[i] == item1) return slot3[i];
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
        nbt.putInt("FEtick",FEtick);
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
        if(tag.contains("FEtick")){
            FEtick = tag.getInt("FEtick");
            this.getTileData().putInt("FEtick",FEtick);
        }

    }

    @Override
    public void tick() {
        if(allSlotsAreNotEmpty() && isItemOnSlot(ModItems.empty_vial.get(), 3) && allSlotsAreCompatibles(itemHandler.getStackInSlot(1).getItem(),itemHandler.getStackInSlot(2).getTranslationKey())) {
            itemInSlot1 = itemHandler.getStackInSlot(1).getItem();
            if(ticking%2 == 0) {
                this.FEtick++;
                assert world != null;
                world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                this.markDirty();
            }
            if (ticking > 20) {
                this.ticking = 0;
                this.FE++;
                this.resultSec ++;
                if(((FE % 74) == 0) && (this.itemHandler.getStackInSlot(0).getCount() > 0)) this.itemHandler.getStackInSlot(0).shrink(1);
                if(Math.round(resultSec/21) >= 1){
                    this.resultSec = 0;
                    this.itemHandler.getStackInSlot(3).shrink(1);
                    removeOfSlotOneAndTwo();
                    this.itemHandler.insertItem(3, new ItemStack(generateVial(itemInSlot1 /* Possible because possible items in slot 1 are all different (as a primary key in SQL) */),1), false);
                }
                assert world != null;
                world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                this.markDirty();
            }
            else ticking++;
        }
        if(!isSlotNotEmpty(0) && (FE != 0 || FEtick != 0)) {
            FE = 0;
            FEtick = 0;
            assert world != null;
            world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
            this.markDirty();
        }
        if((!isSlotNotEmpty(1) || !isSlotNotEmpty(2) || !isSlotNotEmpty(3)) && resultSec != 0) {
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

