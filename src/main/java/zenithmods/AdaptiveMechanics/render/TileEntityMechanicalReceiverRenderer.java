package zenithmods.AdaptiveMechanics.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import zenithmods.AdaptiveMechanics.api.item.IItemGearbox;
import zenithmods.AdaptiveMechanics.blocks.AMBlocks;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.model.ModelCrankSocket;
import zenithmods.AdaptiveMechanics.render.model.ModelMechanicalReceiver;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalReceiver;

public class TileEntityMechanicalReceiverRenderer extends TileEntitySpecialRenderer{

    private ModelMechanicalReceiver model;
    public static ResourceLocation texture = new ResourceLocation(Constants.MOD_ID + ":textures/models/MechanicalReceiver.png");

    public TileEntityMechanicalReceiverRenderer(ModelMechanicalReceiver model){
        this.model = model;
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) {
        TileEntityMechanicalReceiver receiver = (TileEntityMechanicalReceiver) tile;
        GL11.glPushMatrix();
        //This will move our renderer so that it will be on proper place in the world
        GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z - 0.5F);
        /*Note that true tile entity coordinates (tileEntity.xCoord, etc) do not match to render coordinates (d, etc) that are calculated as [true coordinates] - [player coordinates (camera coordinates)]*/
        renderBlockMechanicalReceiver(receiver, receiver.getWorldObj(), receiver.xCoord, receiver.yCoord, receiver.zCoord, AMBlocks.mechanicalReceiver);
        GL11.glPopMatrix();
    }


    public void renderBlockMechanicalReceiver(TileEntityMechanicalReceiver tileEntityMechanicalReceiver, World world, int x, int y, int z, Block block) {
        Tessellator tessellator = Tessellator.instance;
        //This will make your block brightness dependent from surroundings lighting.
        float f = block.getMixedBrightnessForBlock(world, x, y, z);
        int l = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
        int l1 = l % 65536;
        int l2 = l / 65536;
        tessellator.setColorOpaque_F(f, f, f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) l2);

        int meta = world.getBlockMetadata(x, y, z);

        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 0, 0.5F);

        // Because we don't want the model upside-down
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        // rotate properly based on meta and reposition
        switch (meta){
            case 2:
                GL11.glTranslatef (1.0F, 0, 1.0F);
                break;
            case 5:
                GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef (0, 0, 1.0F);
                break;
            case 3:
                GL11.glRotatef(180F, 0F, 1.0F, 0.0F);
                break;
            case 4:
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0F);
                GL11.glTranslatef(1.0F, 0, 0);
                break;
            default: break;
        }


        GL11.glTranslatef(-0.5F, -1.0F, -0.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        // render the stationary bits
        //this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        IItemGearbox itemGearbox = tileEntityMechanicalReceiver.getGearbox();
        if (itemGearbox != null){
            ModelBase gearboxModel = itemGearbox.getGearboxModel();
            gearboxModel.render((Entity) null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625F);
        }

        // render the rotation of the linkage and the gearbox
        float rotationAngle = tileEntityMechanicalReceiver.getOutputRotationAnglef();
        this.model.renderLinkage(rotationAngle, 0.0625F);
        this.model.renderGearConnector(rotationAngle, 0.0625F);

        GL11.glPopMatrix();
    }
}
