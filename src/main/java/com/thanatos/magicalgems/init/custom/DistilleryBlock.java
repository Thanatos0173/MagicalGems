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

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(6, 4, 6, 10, 7, 10),
            Block.makeCuboidShape(5, 0, 5, 11, 4, 11),
            Block.makeCuboidShape(7, 7, 7, 14, 8, 9),
            Block.makeCuboidShape(13, 5, 7.5, 14, 7, 8.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(6, 4, 6, 10, 7, 10),
            Block.makeCuboidShape(5, 0, 5, 11, 4, 11),
            Block.makeCuboidShape(7, 7, 7, 9, 8, 14),
            Block.makeCuboidShape(7.5, 5, 13, 8.5, 7, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(6, 4, 6, 10, 7, 10),
            Block.makeCuboidShape(5, 0, 5, 11, 4, 11),
            Block.makeCuboidShape(2, 7, 7, 9, 8, 9),
            Block.makeCuboidShape(2, 5, 7.5, 3, 7, 8.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(6, 4, 6, 10, 7, 10),
            Block.makeCuboidShape(5, 0, 5, 11, 4, 11),
            Block.makeCuboidShape(7, 7, 2, 9, 8, 9),
            Block.makeCuboidShape(7.5, 5, 2, 8.5, 7, 3)
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
            if(!player.isCrouching()) {
                if(tileEntity instanceof DistilleryTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);
                    NetworkHooks.openGui(((ServerPlayerEntity)player), containerProvider, tileEntity.getPos());

                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
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