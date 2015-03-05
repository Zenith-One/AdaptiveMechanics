package zenithmods.AdaptiveMechanics.api.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;

public abstract class ItemGearbox extends Item implements IItemGearbox{

    public static final int TIER_ONE_RATE = 3;

    @SideOnly(Side.CLIENT)
    ModelBase model = null;

    public ItemGearbox(ModelBase model){
        this.model = model;
    }

    @Override
    public ModelBase getGearboxModel() {
        return this.model;
    }

    @Override
    public int getMaximumInputRate() {
        return TIER_ONE_RATE;
    }
}
