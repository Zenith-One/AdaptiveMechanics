package zenithmods.AdaptiveMechanics.api.item;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import zenithmods.AdaptiveMechanics.codechicken.lib.vec.Cuboid6;

public interface IItemMechanicalToolHead {
    public ModelBase getModel();
    public Cuboid6 getCuboid(int meta);
    public boolean canApplyTo(Block block);
    public void apply(Block block);
}
