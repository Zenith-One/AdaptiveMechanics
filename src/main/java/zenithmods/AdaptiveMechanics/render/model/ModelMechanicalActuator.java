package zenithmods.AdaptiveMechanics.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ActuatorMachine - zenith
 * Created using Tabula 4.1.1
 */
public class ModelMechanicalActuator extends ModelBase implements AMModel{
    public ModelRenderer LinkageBase;
    public ModelRenderer ActuatorArm;
    public ModelRenderer RightPanel;
    public ModelRenderer ActuatorArmInner;
    public ModelRenderer RearRightLeg;
    public ModelRenderer RearLeftLeg;
    public ModelRenderer FrontRightLeg;
    public ModelRenderer FrontLeftLeg;
    public ModelRenderer RearPanelInner;
    public ModelRenderer TopPanel;
    public ModelRenderer BottomPanel;
    public ModelRenderer FrontPanel;
    public ModelRenderer LeftPanel;
    public ModelRenderer FrontPanelBottom;
    public ModelRenderer FrontPanelLeft;
    public ModelRenderer FrontPanelRight;
    public ModelRenderer FrontPanelTop;

    public ModelRenderer RearRightTopStrut;
    public ModelRenderer RearRightBottomStrut;
    public ModelRenderer RearLeftTopStrut;
    public ModelRenderer RearLeftBottomStrut;

    private final float LINKAGE_OFFSET_Z_DEFAULT = 3.2F;
    public final float ACTUATOR_ARM_OFFSET_DEFAULT = 0.3F;
    public final float ACTUATOR_ARM_INNER_OFFSET_DEFAULT = 0.7F;

    public ModelMechanicalActuator() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.RearLeftLeg = new ModelRenderer(this, 0, 69);
        this.RearLeftLeg.setRotationPoint(-7.0F, 0.0F, 5.0F);
        this.RearLeftLeg.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.FrontPanelRight = new ModelRenderer(this, 57, 49);
        this.FrontPanelRight.setRotationPoint(0.8F, 3.1F, 5.5F);
        this.FrontPanelRight.addBox(0.0F, 0.0F, 0.0F, 5, 5, 1, 0.0F);
        this.FrontPanelBottom = new ModelRenderer(this, 43, 58);
        this.FrontPanelBottom.setRotationPoint(-5.4F, 8.1F, 5.5F);
        this.FrontPanelBottom.addBox(0.0F, 0.0F, 0.0F, 11, 2, 1, 0.0F);
        this.FrontPanel = new ModelRenderer(this, 31, 25);
        this.FrontPanel.setRotationPoint(-5.5F, 0.1F, -6.5F);
        this.FrontPanel.addBox(0.0F, 0.0F, 0.0F, 11, 10, 1, 0.0F);
        this.RearPanelInner = new ModelRenderer(this, 61, 25);
        this.RearPanelInner.setRotationPoint(-5.5F, 0.4F, 5.0F);
        this.RearPanelInner.addBox(0.0F, 0.0F, 0.0F, 11, 9, 1, 0.0F);
        this.FrontLeftLeg = new ModelRenderer(this, 0, 69);
        this.FrontLeftLeg.setRotationPoint(-7.0F, 0.0F, -7.0F);
        this.FrontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(FrontLeftLeg, 0.0F, 3.141592653589793F, 0.0F);
        this.LinkageBase = new ModelRenderer(this, 91, 20);
        this.LinkageBase.setRotationPoint(0.15F, 4.1F, LINKAGE_OFFSET_Z_DEFAULT);
        this.LinkageBase.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.setRotateAngle(LinkageBase, 1.5707963267948966F, 0.0F, 0.0F);
        this.LeftPanel = new ModelRenderer(this, 31, 25);
        this.LeftPanel.setRotationPoint(-6.5F, 0.1F, 5.0F);
        this.LeftPanel.addBox(0.0F, 0.0F, 0.0F, 11, 10, 1, 0.0F);
        this.setRotateAngle(LeftPanel, 0.0F, 1.5707963267948966F, 0.0F);
        this.BottomPanel = new ModelRenderer(this, 65, 62);
        this.BottomPanel.setRotationPoint(-5.9F, 9.8F, 5.9F);
        this.BottomPanel.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
        this.setRotateAngle(BottomPanel, 3.141592653589793F, 0.0F, 0.0F);
        this.RearRightLeg = new ModelRenderer(this, 0, 69);
        this.RearRightLeg.setRotationPoint(5.0F, 0.0F, 5.0F);
        this.RearRightLeg.addBox(-2.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.setRotateAngle(RearRightLeg, 0.0F, 1.5707963267948966F, 0.0F);
        this.TopPanel = new ModelRenderer(this, 65, 0);
        this.TopPanel.setRotationPoint(-5.9F, 1.2F, 5.9F);
        this.TopPanel.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
        this.setRotateAngle(TopPanel, 3.141592653589793F, 0.0F, 0.0F);
        this.RightPanel = new ModelRenderer(this, 31, 25);
        this.RightPanel.setRotationPoint(5.6F, 0.1F, 5.0F);
        this.RightPanel.addBox(0.0F, 0.0F, 0.0F, 11, 10, 1, 0.0F);
        this.setRotateAngle(RightPanel, 0.0F, 1.5707963267948966F, 0.0F);
        this.FrontRightLeg = new ModelRenderer(this, 0, 69);
        this.FrontRightLeg.setRotationPoint(5.0F, 0.0F, -7.0F);
        this.FrontRightLeg.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2, 0.0F);
        this.FrontPanelLeft = new ModelRenderer(this, 44, 49);
        this.FrontPanelLeft.setRotationPoint(-5.5F, 3.1F, 5.5F);
        this.FrontPanelLeft.addBox(0.0F, 0.0F, 0.0F, 5, 5, 1, 0.0F);
        this.FrontPanelTop = new ModelRenderer(this, 44, 42);
        this.FrontPanelTop.setRotationPoint(-5.4F, 0.10000000000000003F, 5.5F);
        this.FrontPanelTop.addBox(0.0F, 0.0F, 0.0F, 11, 3, 1, 0.0F);

        this.ActuatorArm = new ModelRenderer(this, 0, 51);
        this.ActuatorArm.setRotationPoint(0.1F, ACTUATOR_ARM_OFFSET_DEFAULT, 0.0F);
        this.ActuatorArm.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F);
        this.ActuatorArmInner = new ModelRenderer(this, 10, 51);
        this.ActuatorArmInner.setRotationPoint(0.0F, ACTUATOR_ARM_INNER_OFFSET_DEFAULT, 0.0F);
        this.ActuatorArmInner.addBox(-0.5F, 0.0F, -0.5F, 1, 11, 1, 0.0F);
        this.ActuatorArm.addChild(this.ActuatorArmInner);

        this.RearLeftTopStrut = new ModelRenderer(this, 0, 69);
        this.RearLeftTopStrut.setRotationPoint(-12.0F, 0.0F, 0.0F);
        this.RearLeftTopStrut.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(RearLeftTopStrut, 0.0F, 1.5707963267948966F, 0.0F);
        this.RearLeftBottomStrut = new ModelRenderer(this, 0, 69);
        this.RearLeftBottomStrut.setRotationPoint(-12.0F, 9.0F, 0.0F);
        this.RearLeftBottomStrut.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(RearLeftBottomStrut, 0.0F, 1.5707963267948966F, 0.0F);
        this.RearRightTopStrut = new ModelRenderer(this, 0, 69);
        this.RearRightTopStrut.setRotationPoint(5.0F, 0.0F, 7.0F);
        this.RearRightTopStrut.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.RearRightBottomStrut = new ModelRenderer(this, 0, 69);
        this.RearRightBottomStrut.setRotationPoint(0.0F, 9.0F, 0.0F);
        this.RearRightBottomStrut.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.RearRightTopStrut.addChild(this.RearLeftTopStrut);
        this.RearRightTopStrut.addChild(this.RearLeftBottomStrut);
        this.RearRightTopStrut.addChild(this.RearRightBottomStrut);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.RearLeftLeg.render(f5);
        this.FrontPanelRight.render(f5);
        this.FrontPanelBottom.render(f5);
        this.FrontPanel.render(f5);
        this.FrontLeftLeg.render(f5);
        this.LeftPanel.render(f5);
        this.BottomPanel.render(f5);
        this.RearRightLeg.render(f5);
        this.TopPanel.render(f5);
        this.RightPanel.render(f5);
        this.FrontRightLeg.render(f5);
        this.FrontPanelLeft.render(f5);
        this.FrontPanelTop.render(f5);
        this.RearPanelInner.render(f5);
    }

    public void renderLinkage(float f5){
        this.LinkageBase.render(f5);
    }

    public void renderLinkage(float offset, float f5){

        this.LinkageBase.setRotationPoint(LinkageBase.rotationPointX, LinkageBase.rotationPointY, offset);
        renderLinkage(f5);
        this.LinkageBase.setRotationPoint(LinkageBase.rotationPointX, LinkageBase.rotationPointY, LINKAGE_OFFSET_Z_DEFAULT);
    }

    public void renderStruts(float f5){
        this.RearRightTopStrut.render(f5);
    }

    public void renderActuatorArm(float f5){
        this.ActuatorArm.render(f5);
    }

    public void renderActuatorArm(float outerOffset, float innerOffset, float f5){
        float oldInnterY = this.ActuatorArmInner.rotationPointY;
        float oldOuterY = this.ActuatorArm.rotationPointY;
        this.ActuatorArmInner.setRotationPoint(ActuatorArmInner.rotationPointX, innerOffset, ActuatorArmInner.rotationPointZ);
        this.ActuatorArm.setRotationPoint(ActuatorArm.rotationPointX, outerOffset, ActuatorArm.rotationPointZ);
        renderActuatorArm(f5);
        this.ActuatorArmInner.setRotationPoint(ActuatorArmInner.rotationPointX, oldInnterY, ActuatorArmInner.rotationPointZ);
        this.ActuatorArm.setRotationPoint(ActuatorArm.rotationPointX, oldOuterY, ActuatorArm.rotationPointZ);
    }

    @Override
    public void renderAll(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        render(entity, f, f1, f2, f3, f4, f5);
        renderLinkage(f5);
        renderActuatorArm(f5);
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
