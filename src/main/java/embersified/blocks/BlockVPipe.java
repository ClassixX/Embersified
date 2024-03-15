package embersified.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import embersified.blocks.tiles.TileVPipe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.math.AxisAlignedBB;
import teamroots.embers.block.BlockTEBase;
import teamroots.embers.util.EnumPipeConnection;
import teamroots.embers.util.Misc;

public class BlockVPipe extends BlockTEBase{
	
	public BlockVPipe(Material material,String name,boolean addToTab) {
		super(material,name,addToTab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileVPipe();
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos){
		TileVPipe p = (TileVPipe)world.getTileEntity(pos);
		p.updateNeighbors(world);
		p.markDirty();
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		if (world.getTileEntity(pos) instanceof TileVPipe){
			((TileVPipe)world.getTileEntity(pos)).updateNeighbors(world);
			world.getTileEntity(pos).markDirty();
		}
	}
	
	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side){
		return false;
	}

	@Override
	public RayTraceResult collisionRayTrace(IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Vec3d start, @Nonnull Vec3d end) {
		List<AxisAlignedBB> subBoxes = new ArrayList<>();

		subBoxes.add(new AxisAlignedBB(0.375, 0.375, 0.375, 0.625, 0.625, 0.625));

		if (world.getTileEntity(pos) instanceof TileVPipe) {
			TileVPipe pipe = ((TileVPipe) world.getTileEntity(pos));

			if (pipe.getInternalConnection(EnumFacing.UP) != EnumPipeConnection.NONE)
				subBoxes.add(new AxisAlignedBB(0.375, 0.625, 0.375, 0.625, 1.0, 0.625));
			if (pipe.getInternalConnection(EnumFacing.DOWN) != EnumPipeConnection.NONE)
				subBoxes.add(new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 0.375, 0.625));
			if (pipe.getInternalConnection(EnumFacing.NORTH) != EnumPipeConnection.NONE)
				subBoxes.add(new AxisAlignedBB(0.375, 0.375, 0.0, 0.625, 0.625, 0.375));
			if (pipe.getInternalConnection(EnumFacing.SOUTH) != EnumPipeConnection.NONE)
				subBoxes.add(new AxisAlignedBB(0.375, 0.375, 0.625, 0.625, 0.625, 1.0));
			if (pipe.getInternalConnection(EnumFacing.WEST) != EnumPipeConnection.NONE)
				subBoxes.add(new AxisAlignedBB(0.0, 0.375, 0.375, 0.375, 0.625, 0.625));
			if (pipe.getInternalConnection(EnumFacing.EAST) != EnumPipeConnection.NONE)
				subBoxes.add(new AxisAlignedBB(0.625, 0.375, 0.375, 1.0, 0.625, 0.625));
		}

		return Misc.raytraceMultiAABB(subBoxes, pos, start, end);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		double x1 = 0.375;
		double y1 = 0.375;
		double z1 = 0.375;
		double x2 = 0.625;
		double y2 = 0.625;
		double z2 = 0.625;

		if (source.getTileEntity(pos) instanceof TileVPipe) {
			TileVPipe pipe = ((TileVPipe) source.getTileEntity(pos));
			if (pipe.getInternalConnection(EnumFacing.UP) != EnumPipeConnection.NONE) {
				y2 = 1;
			}
			if (pipe.getInternalConnection(EnumFacing.DOWN) != EnumPipeConnection.NONE) {
				y1 = 0;
			}
			if (pipe.getInternalConnection(EnumFacing.NORTH) != EnumPipeConnection.NONE) {
				z1 = 0;
			}
			if (pipe.getInternalConnection(EnumFacing.SOUTH) != EnumPipeConnection.NONE) {
				z2 = 1;
			}
			if (pipe.getInternalConnection(EnumFacing.WEST) != EnumPipeConnection.NONE) {
				x1 = 0;
			}
			if (pipe.getInternalConnection(EnumFacing.EAST) != EnumPipeConnection.NONE) {
				x2 = 1;
			}
		}

		return new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
	}
}
