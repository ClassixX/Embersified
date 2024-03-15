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
		//Options.ae2EnergyCanGenerateEmbers = CONFIG.get(Configuration.CATEGORY_GENERAL, "AE2 Can Produce Embers", false, "AE2 energy generating blocks can produce Embers energy").getBoolean();
		//Options.embersEnergyCanGenerateAE2Energy = CONFIG.get(Configuration.CATEGORY_GENERAL, "Embers Can Produce AE2", true, "Embers energy generating blocks can produce AE2 Energy").getBoolean();
		//Options.rfEnergyCanGenerateEmbers = CONFIG.get(Configuration.CATEGORY_GENERAL, "RF Can Produce Embers", false, "RF energy generating blocks can produce Embers energy").getBoolean();
		//Options.embersEnergyCanGenerateRFEnergy = CONFIG.get(Configuration.CATEGORY_GENERAL, "Embers Can Produce RF", true, "Embers energy generating blocks can produce RF Energy").getBoolean();
		Options.chargerCanGenerateForge = CONFIG.get(Configuration.CATEGORY_GENERAL, "Charger can charge FE", true, "Adds embersified functionality to the Copper Charger").getBoolean();
		Options.pipesCanGenerateForge = CONFIG.getBoolean(Configuration.CATEGORY_GENERAL, "Conduits can generate FE", true, "Adds embersified functionality to the Volatile Ember Conduit");
		Options.embersCanGenerateMana = CONFIG.getBoolean(Configuration.CATEGORY_GENERAL, "Embers can produce Mana", true, "Embers energy generating blocks can produce Botania Mana");
		Options.manaMultiplier = CONFIG.get(Configuration.CATEGORY_GENERAL, "Mana Multiplier", 10D, "Mana produced equals Ember amount times this number", 1D, Double.MAX_VALUE).getDouble();
		if (CONFIG.hasChanged()) {
			CONFIG.save();
		}
	}

	public static class Options {

		public static double mulitiplier = 100D;
		public static boolean forgeEnergyCanGenerateEmbers = false;
		public static boolean embersEnergyCanGenerateForgeEnergy = true;
		public static boolean ae2EnergyCanGenerateEmbers = false;
		public static boolean embersEnergyCanGenerateAE2Energy = true;
		public static boolean rfEnergyCanGenerateEmbers = false;
		public static boolean embersEnergyCanGenerateRFEnergy = true;
		public static boolean chargerCanGenerateForge =true;
		public static boolean pipesCanGenerateForge =true;
		public static double manaMultiplier = 10D;
		public static boolean embersCanGenerateMana = true;

	}

}
