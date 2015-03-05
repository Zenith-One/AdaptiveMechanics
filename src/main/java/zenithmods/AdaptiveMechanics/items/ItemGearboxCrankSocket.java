package zenithmods.AdaptiveMechanics.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zenithmods.AdaptiveMechanics.AdaptiveMechanics;
import zenithmods.AdaptiveMechanics.api.item.IItemGearbox;
import zenithmods.AdaptiveMechanics.api.item.ItemGearbox;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.model.ModelCrankSocket;

public class ItemGearboxCrankSocket extends ItemGearbox implements IItemGearbox {

    private String name = "itemGearboxCrankSocket";

    @SideOnly(Side.CLIENT)
    public static final ModelBase crankSocketModel = new ModelCrankSocket();

    public ItemGearboxCrankSocket(){
        super(crankSocketModel);
        setUnlocalizedName(Constants.MOD_ID + ":" + name);
        setCreativeTab(AdaptiveMechanics.tab);
        GameRegistry.registerItem(this, name);
    }

    @Override
    public int getMaximumInputRate() {
        return ItemGearbox.TIER_ONE_RATE;
    }
}
