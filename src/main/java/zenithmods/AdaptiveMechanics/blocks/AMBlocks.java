package zenithmods.AdaptiveMechanics.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public final class AMBlocks {

    public static Block mechanicalReceiver;
    public static Block mechanicalLifter;
    public static Block mechanicalActuator;
    public static Block crank;

    public static void registerBlocks(){
        mechanicalReceiver = new BlockMechanicalReceiver();
        GameRegistry.registerBlock(mechanicalReceiver, BlockMechanicalReceiver.NAME);

        mechanicalLifter = new BlockMechanicalLifter();
        GameRegistry.registerBlock(mechanicalLifter, BlockMechanicalLifter.NAME);

        mechanicalActuator = new BlockMechanicalActuator();
        GameRegistry.registerBlock(mechanicalActuator, BlockMechanicalActuator.NAME);

        crank = new BlockCrank();
        GameRegistry.registerBlock(crank, BlockCrank.NAME);

    }
}
