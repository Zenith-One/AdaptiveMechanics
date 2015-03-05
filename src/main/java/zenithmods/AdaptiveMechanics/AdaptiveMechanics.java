package zenithmods.AdaptiveMechanics;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import zenithmods.AdaptiveMechanics.blocks.AMBlocks;
import zenithmods.AdaptiveMechanics.items.AMItems;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.proxy.CommonProxy;
import zenithmods.AdaptiveMechanics.render.AMRenderingRegister;
import zenithmods.AdaptiveMechanics.tile.AMTiles;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION)
public class AdaptiveMechanics {

    public static FMLEventChannel channel;

    public static CreativeTabs tab;

    @SidedProxy(clientSide = "zenithmods.AdaptiveMechanics.proxy.ClientProxy", serverSide = "zenithmods.AdaptiveMechanics.proxy.ServerProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        tab = new AMTab(CreativeTabs.getNextID(), "adaptivemechanics", false).setBackgroundImageName("item_search.png");

        AMBlocks.registerBlocks();
        AMItems.registerItems();
        AMTiles.registerTileEntities();

        System.out.println(Constants.MOD_NAME + " PreInitialization complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(Constants.MOD_ID);

        AMRenderingRegister.init();
        proxy.registerRenderers();
        System.out.println(Constants.MOD_NAME + " Initialization complete");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(AMBlocks.mechanicalReceiver);
        System.out.println(Constants.MOD_NAME + " PostInitialization complete");
    }


    private class AMTab extends CreativeTabs {
        boolean isToolTab;

        public AMTab(int par1, String par2Str, boolean toolTab) {
            super(par1, par2Str);
            isToolTab = toolTab;
        }


        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            if (isToolTab)
                return AMItems.itemGear;
            return new ItemStack(AMBlocks.mechanicalReceiver).getItem();
        }

        @Override
        public boolean hasSearchBar() {
            return !isToolTab;
        }
    }


}
