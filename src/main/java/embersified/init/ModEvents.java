package embersified.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import embersified.blocks.tiles.TileEmitter;
import embersified.blocks.tiles.TileEjector;
import embersified.blocks.tiles.TileCharger;
import embersified.blocks.tiles.TileVPipe;
import embersified.client.render.TESREmitter;
import embersified.client.render.TESREjector;
import embersified.client.render.TESRCharger;
import embersified.client.render.TESRVPipe;
import teamroots.embers.RegistryManager;
/**
 * @author p455w0rd
 *
 */
@EventBusSubscriber(modid = ModGlobals.MODID)
public class ModEvents {
	public static ResourceLocation getRL(String s){
    	return new ResourceLocation("embers",s);
    }
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onBlockRegistryReady(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.getArray());
		RegistryManager.ember_emitter = ModBlocks.EMITTER;
		RegistryManager.ember_receiver = ModBlocks.RECEPTOR;
		RegistryManager.ember_pulser = ModBlocks.EJECTOR;
		RegistryManager.ember_funnel = ModBlocks.FUNNEL;
		RegistryManager.charger = ModBlocks.CHARGER;
		RegistryManager.ember_pipe = ModBlocks.PIPE;
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onItemRegistryReady(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModBlocks.getItemBlockArray());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegistryReady(ModelRegistryEvent event) {
		ModBlocks.registerModels();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEmitter.class, new TESREmitter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEjector.class, new TESREjector());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCharger.class, new TESRCharger());
		ClientRegistry.bindTileEntitySpecialRenderer(TileVPipe.class, new TESRVPipe());
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRecipeRegistryReady(RegistryEvent.Register<IRecipe> event) {
		//@formatter:off
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_receiver"), new ItemStack(ModBlocks.RECEPTOR, 4),
				"I I",
				"CPC",
				'I', "ingotIron",
				'C', "ingotCopper",
				'P', RegistryManager.plate_caminite
				).setRegistryName(getRL("ember_receiver")));

		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_emitter"), new ItemStack(Item.getItemFromBlock(ModBlocks.EMITTER), 3),
						" a ", 
						" a ", 
						"bcb",
						'b', "ingotIron",
						'a', "ingotCopper",
						'c', RegistryManager.plate_caminite
						).setRegistryName(getRL("ember_emitter")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_funnel"),new ItemStack(RegistryManager.ember_funnel,1),true,new Object[]{
				"D D",
				"CRC",
				" D ",
				'R', RegistryManager.ember_receiver,
				'C', "ingotCopper",
				'D', "plateDawnstone"}).setRegistryName(getRL("ember_funnel")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("ember_pulser"),new ItemStack(RegistryManager.ember_pulser,1),true,new Object[]{
				"D",
				"E",
				"I",
				'E', RegistryManager.ember_emitter,
				'I', "ingotIron",
				'D', "plateDawnstone"}).setRegistryName(getRL("ember_pulser")));
		event.getRegistry().register(new ShapedOreRecipe(getRL("charger"),new ItemStack(RegistryManager.charger,1),true,new Object[]{
				" X ",
				"DCD",
				"IPI",
				'D', "ingotDawnstone",
				'P', "plateCopper",
				'C', "ingotCopper",
				'I', "ingotIron",
				'X', "plateIron"}).setRegistryName(getRL("charger")));
		//@formatter:on
	}

}
