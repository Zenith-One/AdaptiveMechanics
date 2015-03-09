package zenithmods.AdaptiveMechanics.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * SmashingHeadModel - zenith
 * Created using Tabula 4.1.1
 */
public class ModelSmashingHead extends ModelBase {
    public ModelRenderer SmashingHead;

    public ModelSmashingHead() {
        this.textureWidth = 16;
        this.textureHeight = 16;
        this.SmashingHead = new ModelRenderer(this, 0, 0);
        this.SmashingHead.setRotationPoint(-4.5F, 15.5F, 4.5F);
        this.SmashingHead.addBox(0.0F, 0.0F, 0.0F, 9, 5, 9, 0.0F);
        this.setRotateAngle(SmashingHead, 3.141592653589793F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.SmashingHead.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
