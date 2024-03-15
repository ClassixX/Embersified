package embersified.init;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

/**
 * @author p455w0rd
 *
 */
public class ModConfig {

	public static Configuration CONFIG = new Configuration(new File("config/" + ModGlobals.CONFIG_FILE));
	private static final ModConfig INSTANCE = new ModConfig();

	public static ModConfig getInstance() {
		return INSTANCE;
	}

	public void load() {
		CONFIG.load();
		Options.mulitiplier = CONFIG.get(Configuration.CATEGORY_GENERAL, "Forge Multiplier", 100D, "Forge Energy amount equals Ember amount times this number", 1D, Double.MAX_VALUE).getDouble();
		Options.forgeEnergyCanGenerateEmbers = CONFIG.get(Configuration.CATEGORY_GENERAL, "FE Can Produce Embers", false, "FE generating blocks can produce Embers energy").getBoolean();
		Options.embersEnergyCanGenerateForgeEnergy = CONFIG.get(Configuration.CATEGORY_GENERAL, "Embers Can Produce FE", true, "Embers energy generating blocks can produce Forge Energy").getBoolean();
		Options.chargerCanGenerateForge = CONFIG.get(Configuration.CATEGORY_GENERAL, "Charger can charge FE", true, "Adds embersified functionality to the Copper Charger").getBoolean();
		Options.pipesCanGenerateForge = CONFIG.getBoolean(Configuration.CATEGORY_GENERAL, "Conduits can generate FE", true, "Adds embersified functionality to the Volatile Ember Conduit");
		Options.embersCanGenerateMana = CONFIG.getBoolean(Configuration.CATEGORY_GENERAL, "Embers can produce Mana", true, "Embers energy generating blocks can produce Botania Mana");
		Options.manaMultiplier = CONFIG.get(Configuration.CATEGORY_GENERAL, "Mana Multiplier", 10D, "Mana produced equals Ember amount times this number", 1D, Double.MAX_VALUE).getDouble();
		Options.enableOverflowBuffer = CONFIG.getBoolean(Configuration.CATEGORY_GENERAL, "Enable Overflow Buffer", true, "Should Ember Receptors and Funnels contain a buffer for Ember overflow? This will prevent the loss of energy due to the delay of Ember travelling through the air.");
		if (CONFIG.hasChanged()) {
			CONFIG.save();
		}
	}

	public static class Options {

		public static double mulitiplier = 100D;
		public static boolean forgeEnergyCanGenerateEmbers = false;
		public static boolean embersEnergyCanGenerateForgeEnergy = true;
		public static boolean chargerCanGenerateForge =true;
		public static boolean pipesCanGenerateForge =true;
		public static double manaMultiplier = 10D;
		public static boolean embersCanGenerateMana = true;
		public static boolean enableOverflowBuffer = true;

	}

}
