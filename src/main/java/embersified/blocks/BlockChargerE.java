package embersified.blocks;
import teamroots.embers.block.BlockCharger;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamroots.embers.tileentity.TileEntityCharger;
import teamroots.embers.util.Misc;
import embersified.blocks.tiles.TileCharger;
public class BlockChargerE extends BlockCharger{
	
	public BlockChargerE() {
		super(Material.ROCK,"charger",true);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn,int meta) {
		return new TileCharger();
	}
	
}