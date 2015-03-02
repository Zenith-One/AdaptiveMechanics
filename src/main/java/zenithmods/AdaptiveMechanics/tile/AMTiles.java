package zenithmods.AdaptiveMechanics.tile;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;
import zenithmods.AdaptiveMechanics.lib.Constants;

public class AMTiles {

    public static void registerTileEntities(){
        registerTileEntity(TileEntityMechanicalReceiver.class, "mechanicalReceiver");
        registerTileEntity(TileEntityMechanicalLifter.class, "mechanicalLifter");
    }

    private static void registerTileEntity(Class<? extends TileEntity> thing, String key) {
        GameRegistry.registerTileEntityWithAlternatives(thing, Constants.MOD_ID + ":" + key);
    }

}
