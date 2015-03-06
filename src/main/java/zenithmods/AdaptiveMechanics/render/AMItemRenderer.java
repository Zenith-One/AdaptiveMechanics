package zenithmods.AdaptiveMechanics.render;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import zenithmods.AdaptiveMechanics.render.model.AMModel;

public class AMItemRenderer implements IItemRenderer {

    private AMModel model;
    private ResourceLocation texture;

    public AMItemRenderer(AMModel model, ResourceLocation texture ){
        this.model = model;
        this.texture = texture;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        if (type == ItemRenderType.EQUIPPED) {
            EntityClientPlayerMP entity = (EntityClientPlayerMP) data[1];
            GL11.glTranslatef(-0.5F, -1.15f, 0.5F);

            model.renderAll(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        } else if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef(0F, -1.25F, 0.50F);
            model.renderAll((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        } else {
            GL11.glTranslatef(0F, -0.50F, 0F);
            model.renderAll((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        }
        GL11.glPopMatrix();
    }


}
