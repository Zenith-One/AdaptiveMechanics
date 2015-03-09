package zenithmods.AdaptiveMechanics.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelSplittingHead - zenith
 * Created using Tabula 4.1.1
 */
public class ModelSplittingHead extends ModelBase {
    public ModelRenderer SplittingHeadRight;
    public ModelRenderer SplittingHeadLeft;
    public ModelRenderer SplittingHeadTop;

    public ModelSplittingHead() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.SplittingHeadRight = new ModelRenderer(this, 0, 0);
        this.SplittingHeadRight.setRotationPoint(-1.9F, 12.7F, 4.5F);
        this.SplittingHeadRight.addBox(0.0F, 0.0F, 0.0F, 9, 3, 1, 0.0F);
        this.setRotateAngle(SplittingHeadRight, 0.7853981633974483F, 1.5707963267948966F, 0.0F);
        this.SplittingHeadTop = new ModelRenderer(this, 0, 11);
        this.SplittingHeadTop.setRotationPoint(0.5F, -0.1F, 1.65F);
        this.SplittingHeadTop.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.setRotateAngle(SplittingHeadTop, -0.7853981633974483F, 0.0F, 0.0F);
        this.SplittingHeadLeft = new ModelRenderer(this, 0, 6);
        this.SplittingHeadLeft.setRotationPoint(0.0F, 2.0F, 3.0F);
        this.SplittingHeadLeft.addBox(0.0F, 0.0F, 0.0F, 9, 2, 1, 0.0F);
        this.setRotateAngle(SplittingHeadLeft, -1.5707963267948966F, 0.0F, 0.0F);
        this.SplittingHeadRight.addChild(this.SplittingHeadTop);
        this.SplittingHeadRight.addChild(this.SplittingHeadLeft);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.SplittingHeadRight.render(f5);
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
