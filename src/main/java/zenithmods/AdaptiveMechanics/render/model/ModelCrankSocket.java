package zenithmods.AdaptiveMechanics.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Actuator - zenith
 * Created using Tabula 4.1.1
 */
public class ModelCrankSocket extends ModelBase {
    public ModelRenderer CrankSocket;

    public ModelCrankSocket() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.CrankSocket = new ModelRenderer(this, 105, 64);
        this.CrankSocket.setRotationPoint(4.4F, 2.3F, -2.0F);
        this.CrankSocket.addBox(0.0F, 0.0F, 0.0F, 2, 4, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.CrankSocket.render(f5);
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
