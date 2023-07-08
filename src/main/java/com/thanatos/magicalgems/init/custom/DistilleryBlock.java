package com.thanatos.magicalgems.init.custom;

import com.thanatos.magicalgems.TileEntities.DistilleryTile;
import com.thanatos.magicalgems.TileEntities.ModTileEntities;
import com.thanatos.magicalgems.container.DistilleryContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DistilleryBlock extends HorizontalBlock {
    public DistilleryBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(1, 2, 1, 15, 8, 15),
            Block.makeCuboidShape(2, 8, 2, 14, 14, 14),
            Block.makeCuboidShape(-4, 14, 6, 9, 16, 10),
            Block.makeCuboidShape(-6, 11, 6, -4, 14, 10),
            Block.makeCuboidShape(-6, 9, 7, -4, 11, 9),
            Block.makeCuboidShape(-5.5, 8, 7.5, -4.5, 9, 8.5),
            Block.makeCuboidShape(9, 14, 6, 11, 15, 10),
            Block.makeCuboidShape(-5, 15, 6, -4, 16, 10),
            Block.makeCuboidShape(-6, 14, 6, -4, 15, 10),
            Block.makeCuboidShape(9, 15, 6, 10, 16, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(1, 2, 1, 15, 8, 15),
            Block.makeCuboidShape(2, 8, 2, 14, 14, 14),
            Block.makeCuboidShape(6, 14, 7, 10, 16, 20),
            Block.makeCuboidShape(6, 11, 20, 10, 14, 22),
            Block.makeCuboidShape(7, 9, 20, 9, 11, 22),
            Block.makeCuboidShape(7.5, 8, 20.5, 8.5, 9, 21.5),
            Block.makeCuboidShape(6, 14, 5, 10, 15, 7),
            Block.makeCuboidShape(6, 15, 20, 10, 16, 21),
            Block.makeCuboidShape(6, 14, 20, 10, 15, 22),
            Block.makeCuboidShape(6, 15, 6, 10, 16, 7)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(1, 2, 1, 15, 8, 15),
            Block.makeCuboidShape(2, 8, 2, 14, 14, 14),
            Block.makeCuboidShape(7, 14, 6, 20, 16, 10),
            Block.makeCuboidShape(20, 11, 6, 22, 14, 10),
            Block.makeCuboidShape(20, 9, 7, 22, 11, 9),
            Block.makeCuboidShape(20.5, 8, 7.5, 21.5, 9, 8.5),
            Block.makeCuboidShape(5, 14, 6, 7, 15, 10),
            Block.makeCuboidShape(20, 15, 6, 21, 16, 10),
            Block.makeCuboidShape(20, 14, 6, 22, 15, 10),
            Block.makeCuboidShape(6, 15, 6, 7, 16, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
            Block.makeCuboidShape(1, 2, 1, 15, 8, 15),
            Block.makeCuboidShape(2, 8, 2, 14, 14, 14),
            Block.makeCuboidShape(6, 14, -4, 10, 16, 9),
            Block.makeCuboidShape(6, 11, -6, 10, 14, -4),
            Block.makeCuboidShape(7, 9, -6, 9, 11, -4),
            Block.makeCuboidShape(7.5, 8, -5.5, 8.5, 9, -4.5),
            Block.makeCuboidShape(6, 14, 9, 10, 15, 11),
            Block.makeCuboidShape(6, 15, -5, 10, 16, -4),
            Block.makeCuboidShape(6, 14, -6, 10, 15, -4),
            Block.makeCuboidShape(6, 15, 9, 10, 16, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(HORIZONTAL_FACING)){
            case NORTH: return SHAPE_N;
            case SOUTH: return SHAPE_S;
            case EAST: return SHAPE_E;
            case WEST: return SHAPE_W;
            default: return SHAPE_N;

        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if(!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof DistilleryTile) {
                INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);
                NetworkHooks.openGui(((ServerPlayerEntity)player), containerProvider, tileEntity.getPos());
             } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.magicalgems.distillery");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new DistilleryContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.DISTILLERY_TILE.get().create();

    }



    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock())

        {
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof DistilleryTile)
            {
                DistilleryTile core = (DistilleryTile)te;
                List<ItemStack> drops = new ArrayList<>();
                double x = pos.getX() + 0.5D;
                double y = pos.getY() + 0.5D;
                double z = pos.getZ() + 0.5D;
                core.addSlotContentToArray(drops);
                for (ItemStack drop : drops)
                {
                    InventoryHelper.spawnItemStack(world, x, y, z, drop);
                }


            }
            super.onReplaced(state, world, pos, newState, isMoving);
        }
    }
}