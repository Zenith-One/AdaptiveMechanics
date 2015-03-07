package zenithmods.AdaptiveMechanics.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import zenithmods.AdaptiveMechanics.blocks.AMBlocks;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.model.ModelMechanicalActuator;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalActuator;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalLifter;

public class TileEntityMechanicalActuatorRenderer extends TileEntitySpecialRenderer{

    private ModelMechanicalActuator model;
    public static ResourceLocation texture = new ResourceLocation(Constants.MOD_ID + ":textures/models/MechanicalActuator.png");

    public TileEntityMechanicalActuatorRenderer(ModelMechanicalActuator model){
        this.model = model;
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) {
        TileEntityMechanicalActuator actuator = (TileEntityMechanicalActuator) tile;
        GL11.glPushMatrix();
        //This will move our renderer so that it will be on proper place in the world
        GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z - 0.5F);
        /*Note that true tile entity coordinates (tileEntity.xCoord, etc) do not match to render coordinates (d, etc) that are calculated as [true coordinates] - [player coordinates (camera coordinates)]*/
        renderBlockMechanicalActuator(actuator, actuator.getWorldObj(), actuator.xCoord, actuator.yCoord, actuator.zCoord, AMBlocks.mechanicalReceiver);
        GL11.glPopMatrix();
    }


    public void renderBlockMechanicalActuator(TileEntityMechanicalActuator actuator, World world, int x, int y, int z, Block block) {
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
        // mostly because I suck and didn't center the render point correctly. -Z
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

        float f5 = 0.0625F;

        // render the stationary bits
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, f5 );

        // p = (cp * x - (r * cos(a)), cp * y + (r * sin(a))
        // cp = center point, a = angle (in degrees), r = 1 radian?

        // (s, t) ends up at (u, v) where u = s * cos(a) + t * sin(a) and v = s * sin(a) + t * cos(a)

        double r = 10.4d;

        if (actuator.isConnectedToLifter()) {
            TileEntityMechanicalLifter lifter = actuator.getInputLifter();
            float lifterRotation = TileEntityMechanicalLifterRenderer.getLeverArmRotation(lifter.getGearBarOffset(),lifter.MAX_LIFT_TIME);
            double offsetX = 9.4 * Math.cos(lifterRotation) - 9.12;

            double height = ((double) lifter.getGearBarOffset() / (double) lifter.MAX_LIFT_TIME) * 0.22D;

            GL11.glTranslated(0, height, 0);
            this.model.renderLinkage(5.6F - (float) offsetX, f5);
            GL11.glTranslated(0, -1 * height, 0);

        } else {
            this.model.renderLinkage(f5);
        }

        GL11.glPopMatrix();
    }

}

