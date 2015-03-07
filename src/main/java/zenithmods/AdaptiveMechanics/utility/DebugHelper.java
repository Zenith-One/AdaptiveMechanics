package zenithmods.AdaptiveMechanics.utility;

import net.minecraft.world.World;

public class DebugHelper {

    public static void clientPrint(World world, String msg){
        if (world.isRemote){
            System.out.println(msg);
        }
    }

    public static void serverPrint(World world, String msg){
        if (!world.isRemote){
            System.out.println(msg);
        }
    }

}
