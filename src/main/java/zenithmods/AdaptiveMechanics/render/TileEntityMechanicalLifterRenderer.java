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
import org.lwjgl.opengl.GL11;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineTransmitter;
import zenithmods.AdaptiveMechanics.blocks.AMBlocks;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.model.ModelMechanicalLifter;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalLifter;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalReceiver;
import zenithmods.AdaptiveMechanics.utility.AMMathHelper;

public class TileEntityMechanicalLifterRenderer extends TileEntitySpecialRenderer{

    private ModelMechanicalLifter model;
    public static ResourceLocation texture = new ResourceLocation(Constants.MOD_ID + ":textures/models/MechanicalLifter.png");

    public TileEntityMechanicalLifterRenderer(ModelMechanicalLifter model){
        this.model = model;
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) {
        TileEntityMechanicalLifter lifter = (TileEntityMechanicalLifter) tile;
        GL11.glPushMatrix();
        //This will move our renderer so that it will be on proper place in the world
        GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z - 0.5F);
        /*Note that true tile entity coordinates (tileEntity.xCoord, etc) do not match to render coordinates (d, etc) that are calculated as [true coordinates] - [player coordinates (camera coordinates)]*/
        renderBlockMechanicalLifter(lifter, lifter.getWorldObj(), lifter.xCoord, lifter.yCoord, lifter.zCoord, AMBlocks.mechanicalReceiver);
        GL11.glPopMatrix();
    }


    public void renderBlockMechanicalLifter(TileEntityMechanicalLifter tileEntityMechanicalLifter, World world, int x, int y, int z, Block block) {
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

        // render the stationary bits
        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        if (tileEntityMechanicalLifter.hasInputBelow()){
            // render the rotation of the linkage and the gearbox
            IAdaptiveMachineTransmitter iamt = (IAdaptiveMachineTransmitter) tileEntityMechanicalLifter.getTileEntityBelow();
            float linkageRotationOffset = 0;
            float rotationAngle = tileEntityMechanicalLifter.getOutputRotationAnglef();
            boolean needsRotation = iamt.needsInputRotation();
            if ( ( needsRotation && (meta == 4 || meta == 5))

                    ||
              ( !needsRotation && ( meta == 2 || meta == 3) )
            ){
                linkageRotationOffset = 90F;
            }
            GL11.glRotatef(linkageRotationOffset, 0, 1, 0);
            this.model.renderLinkage(rotationAngle, 0.0625F);
            GL11.glRotatef(-1 * linkageRotationOffset, 0, 1, 0);
        }

        double mainGearDistance = 0;
        int extended = tileEntityMechanicalLifter.getExtendedTicks();

        if (extended > 0){
            mainGearDistance = -1 * extended * 0.027F / 4;
        }

        GL11.glTranslated(0, 0, mainGearDistance);
        this.model.renderMainGear(tileEntityMechanicalLifter.getGearRotationAnglef(),  0.0625F);

        GL11.glTranslated(0, 0, -1 * mainGearDistance);

        double gearBarHeightOffset = -1 * ((double) tileEntityMechanicalLifter.getGearBarOffset() / (double) tileEntityMechanicalLifter.MAX_LIFT_TIME) * 0.12D;

        GL11.glTranslated(0, gearBarHeightOffset, 0);
        this.model.renderGearBar(getLeverArmRotation(tileEntityMechanicalLifter.getGearBarOffset(), tileEntityMechanicalLifter.MAX_LIFT_TIME), 0.0625F);
        GL11.glTranslated(0, -1 * gearBarHeightOffset, 0);

        GL11.glPopMatrix();
    }


    public static float getLeverArmRotation(int tick, int tickMax){
        // down: 18.8 deg
        // up:  -13.8 deg
        // difference = 32.6
        float tickPercent = (float) tick / (float) tickMax;
        float degrees = tickPercent * 32.6F - 13.8F;
        float rads = (float) Math.toRadians(degrees);

        return rads;
    }

}
