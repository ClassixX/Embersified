package embersified.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import embersified.blocks.*;
import teamroots.embers.block.IBlock;
import teamroots.embers.block.IModeledBlock;

/**
 * @author p455w0rd
 *
 */
public class ModBlocks {

	public static final Block EMITTER = new BlockEmitter().setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(0.6f);
	public static final Block RECEPTOR = new BlockReceptor().setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(0.6f);	
	public static final Block EJECTOR = new BlockEjector().setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(0.6f);
	public static final Block FUNNEL = new BlockFunnel().setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.6f);
	public static final Block CHARGER = new BlockChargerE().setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.6f);
	public static final Block PIPE = new BlockVPipe(Material.IRON,"ember_pipe",true).setIsFullCube(false).setIsOpaqueCube(false).setHarvestProperties("pickaxe", 0).setHardness(1.6f);

	private static final Block[] BLOCK_ARRAY = new Block[] {
			EMITTER, RECEPTOR, EJECTOR, FUNNEL, CHARGER, PIPE
	};

	public static final Block[] getArray() {
		return BLOCK_ARRAY;
	}

	public static final Item[] getItemBlockArray() {
		Item[] itemBlockArray = new Item[getArray().length];
		for (int i = 0; i < itemBlockArray.length; i++) {
			itemBlockArray[i] = ((IBlock) getArray()[i]).getItemBlock();
		}
		return itemBlockArray;
	}

	public static final void registerModels() {
		for (Block block : getArray()) {
			if (block instanceof IModeledBlock) {
				((IModeledBlock) block).initModel();
			}
		}
	}

}
