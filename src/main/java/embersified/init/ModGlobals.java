package embersified.init;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.embers.RegistryManager;
/**
 * @author p455w0rd & HelixPHD
 *
 */
public class ModGlobals {

	public static final String MODID = "embersifiedextended";
	public static final String NAME = "EmbersifiedExtended";
	public static final String VERSION = "1.2.1";
	public static final String DEPENDENCIES = "after:embers@[1.8,);";
	public static final String CONFIG_FILE = NAME + ".cfg";
	
	public static CreativeTabs tab = new CreativeTabs(MODID) {
    	@Override
    	public String getTabLabel(){
    		return MODID;
    	}
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem(){
			return new ItemStack(RegistryManager.ember_cluster,1);
		}
	};
}
