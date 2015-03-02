package zenithmods.AdaptiveMechanics.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zenithmods.AdaptiveMechanics.lib.Constants;

public class ItemGear extends Item {

    private String name = "itemGear";

    public ItemGear(){
        setUnlocalizedName(Constants.MOD_ID + "_" + name);
        setCreativeTab(CreativeTabs.tabMaterials);
        GameRegistry.registerItem(this, name);
    }
}
