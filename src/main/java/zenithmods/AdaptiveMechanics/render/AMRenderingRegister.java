package zenithmods.AdaptiveMechanics.render;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class AMRenderingRegister {

    public static int mechanicalReceiverRenderID;
    public static int mechanicalLifterRenderID;
    public static int crankRenderID;

    public static void init(){
        mechanicalReceiverRenderID = RenderingRegistry.getNextAvailableRenderId();
        mechanicalLifterRenderID= RenderingRegistry.getNextAvailableRenderId();
        crankRenderID = RenderingRegistry.getNextAvailableRenderId();

    }
}
