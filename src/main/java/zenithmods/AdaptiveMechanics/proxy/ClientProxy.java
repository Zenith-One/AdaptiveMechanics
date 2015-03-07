package zenithmods.AdaptiveMechanics.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import zenithmods.AdaptiveMechanics.blocks.AMBlocks;
import zenithmods.AdaptiveMechanics.items.AMItems;
import zenithmods.AdaptiveMechanics.items.ItemGearboxCrankSocket;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.*;
import zenithmods.AdaptiveMechanics.render.model.*;
import zenithmods.AdaptiveMechanics.tile.TileEntityCrank;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalActuator;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalLifter;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalReceiver;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {

        ModelMechanicalReceiver receiverModel = new ModelMechanicalReceiver();
        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityMechanicalReceiver.class,
                new TileEntityMechanicalReceiverRenderer(receiverModel)
        );
        MinecraftForgeClient.registerItemRenderer(
                Item.getItemFromBlock(AMBlocks.mechanicalReceiver),
                new AMItemRenderer((AMModel) receiverModel, TileEntityMechanicalReceiverRenderer.texture)
        );

        ModelMechanicalLifter lifterModel = new ModelMechanicalLifter();
        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityMechanicalLifter.class,
                new TileEntityMechanicalLifterRenderer(lifterModel)
        );
        MinecraftForgeClient.registerItemRenderer(
                Item.getItemFromBlock(AMBlocks.mechanicalLifter),
                new AMItemRenderer((AMModel) lifterModel, TileEntityMechanicalLifterRenderer.texture)
        );

        ModelCrank crankModel = new ModelCrank();
        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityCrank.class,
                new TileEntityCrankRenderer(crankModel)
        );
        MinecraftForgeClient.registerItemRenderer(
                Item.getItemFromBlock(AMBlocks.crank),
                new AMItemRenderer((AMModel) crankModel, TileEntityCrankRenderer.texture)
        );

        MinecraftForgeClient.registerItemRenderer(
                AMItems.itemGearboxCrankSocket,
                new AMItemRenderer((AMModel) ItemGearboxCrankSocket.crankSocketModel, TileEntityMechanicalReceiverRenderer.texture)
        );

        ModelMechanicalActuator actuatorModel = new ModelMechanicalActuator();
        ClientRegistry.bindTileEntitySpecialRenderer(
                TileEntityMechanicalActuator.class,
                new TileEntityMechanicalActuatorRenderer(actuatorModel)
        );
        MinecraftForgeClient.registerItemRenderer(
                Item.getItemFromBlock(AMBlocks.mechanicalActuator),
                new AMItemRenderer((AMModel) actuatorModel, TileEntityMechanicalActuatorRenderer.texture)
        );
    }
}
