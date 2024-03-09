package embersified;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import embersified.blocks.tiles.TileEmitter;
import embersified.blocks.tiles.TileReceptor;
import embersified.blocks.tiles.TileEjector;
import embersified.blocks.tiles.TileFunnel;
import embersified.init.ModConfig;
import embersified.init.ModGlobals;

@Mod(modid = ModGlobals.MODID, name = ModGlobals.NAME, version = ModGlobals.VERSION, acceptedMinecraftVersions = "1.12.2", dependencies = ModGlobals.DEPENDENCIES)
public class Embersified {

	@Instance(ModGlobals.MODID)
	public static Embersified INSTANCE;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ModConfig.getInstance().load();
		GameRegistry.registerTileEntity(TileEmitter.class, new ResourceLocation(ModGlobals.MODID, "tile_entity_emitter"));
		GameRegistry.registerTileEntity(TileReceptor.class, new ResourceLocation(ModGlobals.MODID, "tile_entity_receiver"));
		GameRegistry.registerTileEntity(TileEjector.class, new ResourceLocation(ModGlobals.MODID, "tile_entity_pulser"));
		GameRegistry.registerTileEntity(TileFunnel.class, new ResourceLocation(ModGlobals.MODID, "tile_entity_funnel"));
	}

}