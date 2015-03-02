package zenithmods.AdaptiveMechanics.api.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public abstract class BlockAdaptiveMachine extends BlockContainer {

    public BlockAdaptiveMachine(Material material){
        super(material);
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta){

    }
}
