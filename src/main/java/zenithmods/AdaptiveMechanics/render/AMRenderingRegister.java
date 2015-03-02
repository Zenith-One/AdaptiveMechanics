package zenithmods.AdaptiveMechanics.render;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class AMRenderingRegister {

    public static int mechanicalReceiverRenderID;

    public static void init(){
        mechanicalReceiverRenderID = RenderingRegistry.getNextAvailableRenderId();
    }
}
