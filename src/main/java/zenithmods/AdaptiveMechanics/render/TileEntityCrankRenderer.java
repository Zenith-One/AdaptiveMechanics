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
import zenithmods.AdaptiveMechanics.blocks.AMBlocks;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.model.ModelCrank;
import zenithmods.AdaptiveMechanics.tile.TileEntityCrank;

public class TileEntityCrankRenderer extends TileEntitySpecialRenderer{

    private float pixel = 1f / 16f;

    private ModelCrank model;
    public static ResourceLocation texture = new ResourceLocation(Constants.MOD_ID + ":textures/models/Crank.png");

    public TileEntityCrankRenderer(ModelCrank model){
        this.model = model;
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) {
        TileEntityCrank crank = (TileEntityCrank) tile;
        GL11.glPushMatrix();
        //This will move our renderer so that it will be on proper place in the world
        GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z - 0.5F);
        /*Note that true tile entity coordinates (tileEntity.xCoord, etc) do not match to render coordinates (d, etc) that are calculated as [true coordinates] - [player coordinates (camera coordinates)]*/
        renderBlockCrank(crank, crank.getWorldObj(), crank.xCoord, crank.yCoord, crank.zCoord, AMBlocks.mechanicalReceiver);
        GL11.glPopMatrix();
    }


    public void renderBlockCrank(TileEntityCrank tileEntityCrank, World world, int x, int y, int z, Block block) {
        Tessellator tessellator = Tessellator.instance;
        //This will make your block brightness dependent from surroundings lighting.
        float f = block.getMixedBrightnessForBlock(world, x, y, z);
        int l = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
        int l1 = l % 65536;
        int l2 = l / 65536;
        //tessellator.setColorOpaque_F(f, f, f);
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) l2);

        int meta = world.getBlockMetadata(x, y, z);

        GL11.glPushMatrix();

        // Because we don't want the model upside-down
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0f, -0.8f + pixel, 0);

        // rotate properly based on meta and reposition
        // mostly because I suck and didn't center the render point correctly. -Z
        switch (meta){
            case 2:
                GL11.glTranslatef (-1.5F - (2 * pixel), 0, 0.0F);
                break;
            case 5:
                GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef (-0.5f - (2 * pixel), 0, -1.0F);
                break;
            case 3:
                GL11.glRotatef(180F, 0F, 1.0F, 0.0F);
                GL11.glTranslatef(0.5F - (2 * pixel), 0F, 0F);
                break;
            case 4:
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0F);
                GL11.glTranslatef(-0.5F - (2 * pixel), 0, 1);
                break;
            default: break;
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        // render the stationary bits
        this.model.renderRotation(-1 * tileEntityCrank.getOutputRotationAnglef(), 0.0625F);

        GL11.glPopMatrix();
    }
}

