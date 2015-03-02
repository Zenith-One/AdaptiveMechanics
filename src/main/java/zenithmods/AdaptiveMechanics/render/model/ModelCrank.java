package zenithmods.AdaptiveMechanics.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Crank - zenith
 * Created using Tabula 4.1.1
 */
public class ModelCrank extends ModelBase implements AMModel{
    public ModelRenderer CrankBase;
    public ModelRenderer CrankUp;
    public ModelRenderer CrankHandle;

   public ModelCrank() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.CrankUp = new ModelRenderer(this, 0, 0);
        this.CrankUp.setRotationPoint(3.5F, 0.5F, 0.0F);
        this.CrankUp.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
        this.setRotateAngle(CrankUp, 0.0F, 0.0F, -1.5707963705062866F);
        this.CrankHandle = new ModelRenderer(this, 0, 0);
        this.CrankHandle.setRotationPoint(4.0F, -3.5F, -0.5F);
        this.CrankHandle.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.CrankBase = new ModelRenderer(this, 0, 0);
        this.CrankBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CrankBase.addBox(0.0F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
        this.setRotateAngle(CrankBase, 0.0F, 1.5707963267948966F, 0.0F);
        this.CrankBase.addChild(this.CrankUp);
        this.CrankBase.addChild(this.CrankHandle);
    }

    @Override
    public void renderAll(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.render(entity, f, f1, f2, f3, f4, f5);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.CrankBase.render(f5);
    }

    public void renderRotation(float crankRotation, float f5){
        setRotateAngle(CrankBase, crankRotation, 0, 0);
        this.CrankBase.render(f5);
        setRotateAngle(CrankBase, 0, 0, 0);
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
