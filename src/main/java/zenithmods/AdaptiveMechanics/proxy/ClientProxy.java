package zenithmods.AdaptiveMechanics.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import zenithmods.AdaptiveMechanics.blocks.AMBlocks;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.AMItemRenderer;
import zenithmods.AdaptiveMechanics.render.TileEntityCrankRenderer;
import zenithmods.AdaptiveMechanics.render.TileEntityMechanicalLifterRenderer;
import zenithmods.AdaptiveMechanics.render.TileEntityMechanicalReceiverRenderer;
import zenithmods.AdaptiveMechanics.render.model.AMModel;
import zenithmods.AdaptiveMechanics.render.model.ModelCrank;
import zenithmods.AdaptiveMechanics.render.model.ModelMechanicalLifter;
import zenithmods.AdaptiveMechanics.render.model.ModelMechanicalReceiver;
import zenithmods.AdaptiveMechanics.tile.TileEntityCrank;
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

    }
}
