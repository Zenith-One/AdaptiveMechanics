package zenithmods.AdaptiveMechanics.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * Actuator - zenith
 * Created using Tabula 4.1.1
 */
public class ModelMechanicalReceiver extends ModelBase implements AMModel{
    public ModelRenderer LinkageBase;
    public ModelRenderer LinkageFront;
    public ModelRenderer LinkageBack;
    public ModelRenderer RearRightLeg;
    public ModelRenderer RearLeftLeg;
    public ModelRenderer FrontRightLeg;
    public ModelRenderer FrontLeftLeg;
    public ModelRenderer FrontPanel;
    public ModelRenderer RearPanel;
    public ModelRenderer LeftPanel;
    public ModelRenderer BottomPanel;
    public ModelRenderer TopPanel;
    public ModelRenderer RightPanelBottom;
    public ModelRenderer RightPanelTop;
    public ModelRenderer RightPanelRight;
    public ModelRenderer RightPanelLeft;
    public ModelRenderer GearboxBottom;
    public ModelRenderer GearboxTop;
    public ModelRenderer GearboxRight;
    public ModelRenderer GearboxLeft;
    public ModelRenderer GearboxRear;
    public ModelRenderer GearConnector;
    public ModelRenderer GearConnectorRightPin;
    public ModelRenderer GearConnectorLeft;
    public ModelRenderer GearConnectorLeftPin;

    public ModelMechanicalReceiver() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.GearConnectorLeftPin = new ModelRenderer(this, 18, 12);
        this.GearConnectorLeftPin.setRotationPoint(0.0F, 0.0F, -5.7F);
        this.GearConnectorLeftPin.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.LinkageFront = new ModelRenderer(this, 91, 0);
        this.LinkageFront.setRotationPoint(-1.5F, -0.4F, -1.5F);
        this.LinkageFront.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.LeftPanel = new ModelRenderer(this, 45, 44);
        this.LeftPanel.setRotationPoint(-6.6F, 0.4F, -5.4F);
        this.LeftPanel.addBox(0.0F, 0.0F, 0.0F, 1, 14, 11, 0.0F);
        this.LinkageBase = new ModelRenderer(this, 91, 20);
        this.LinkageBase.setRotationPoint(0.0F, 0.4F, 0.0F);
        this.LinkageBase.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
        this.RightPanelTop = new ModelRenderer(this, 0, 32);
        this.RightPanelTop.setRotationPoint(5.5F, 0.3000000000000135F, -5.5F);
        this.RightPanelTop.addBox(0.0F, 0.0F, 0.0F, 1, 2, 11, 0.0F);
        this.RightPanelLeft = new ModelRenderer(this, 0, 62);
        this.RightPanelLeft.setRotationPoint(5.5F, 2.3000000000000216F, -5.5F);
        this.RightPanelLeft.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.GearboxRear = new ModelRenderer(this, 0, 18);
        this.GearboxRear.setRotationPoint(1.51F, 1.4000000000000004F, -3.5F);
        this.GearboxRear.addBox(0.0F, 0.0F, 0.0F, 1, 6, 7, 0.0F);
        this.LinkageBack = new ModelRenderer(this, 91, 0);
        this.LinkageBack.setRotationPoint(-1.5F, -0.4F, 0.5F);
        this.LinkageBack.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.GearboxBottom = new ModelRenderer(this, 51, 0);
        this.GearboxBottom.setRotationPoint(2.51F, 6.300000000000001F, -2.5F);
        this.GearboxBottom.addBox(0.0F, 0.0F, 0.0F, 3, 1, 5, 0.0F);
        this.GearboxRight = new ModelRenderer(this, 65, 17);
        this.GearboxRight.setRotationPoint(2.51F, 1.4000000000000004F, 2.5F);
        this.GearboxRight.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1, 0.0F);
        this.GearboxTop = new ModelRenderer(this, 21, 0);
        this.GearboxTop.setRotationPoint(2.51F, 1.4000000000000004F, -2.5F);
        this.GearboxTop.addBox(0.0F, 0.0F, 0.0F, 3, 1, 5, 0.0F);
        this.BottomPanel = new ModelRenderer(this, 65, 0);
        this.BottomPanel.setRotationPoint(-5.9F, 13.0F, -5.9F);
        this.BottomPanel.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
        this.RearLeftLeg = new ModelRenderer(this, 0, 0);
        this.RearLeftLeg.setRotationPoint(-7.0F, 0.0F, 5.0F);
        this.RearLeftLeg.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.TopPanel = new ModelRenderer(this, 65, 62);
        this.TopPanel.setRotationPoint(-5.9F, 1.1F, -5.9F);
        this.TopPanel.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
        this.FrontRightLeg = new ModelRenderer(this, 0, 0);
        this.FrontRightLeg.setRotationPoint(5.0F, 0.0F, -7.0F);
        this.FrontRightLeg.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.FrontPanel = new ModelRenderer(this, 13, 54);
        this.FrontPanel.setRotationPoint(-5.4F, 0.4F, -6.5F);
        this.FrontPanel.addBox(0.0F, 0.0F, 0.0F, 11, 14, 1, 0.0F);
        this.GearConnector = new ModelRenderer(this, 16, 0);
        this.GearConnector.setRotationPoint(4.1F, 4.4F, 3.2F);
        this.GearConnector.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, 0.0F);
        this.RearPanel = new ModelRenderer(this, 31, 25);
        this.RearPanel.setRotationPoint(-5.5F, 0.4F, 5.5F);
        this.RearPanel.addBox(0.0F, 0.0F, 0.0F, 11, 14, 1, 0.0F);
        this.RightPanelRight = new ModelRenderer(this, 0, 51);
        this.RightPanelRight.setRotationPoint(5.5F, 2.3000000000000216F, 2.5F);
        this.RightPanelRight.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
        this.GearboxLeft = new ModelRenderer(this, 65, 17);
        this.GearboxLeft.setRotationPoint(2.51F, 1.4000000000000004F, -3.5F);
        this.GearboxLeft.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1, 0.0F);
        this.RearRightLeg = new ModelRenderer(this, 0, 0);
        this.RearRightLeg.setRotationPoint(5.0F, 0.0F, 5.0F);
        this.RearRightLeg.addBox(-2.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.setRotateAngle(RearRightLeg, 0.0F, 1.5707963267948966F, 0.0F);
        this.GearConnectorLeft = new ModelRenderer(this, 16, 0);
        this.GearConnectorLeft.setRotationPoint(0.0F, 0.0F, -5.4F);
        this.GearConnectorLeft.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, 0.0F);
        this.GearConnectorRightPin = new ModelRenderer(this, 18, 12);
        this.GearConnectorRightPin.setRotationPoint(0.0F, 0.0F, -0.7F);
        this.GearConnectorRightPin.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.FrontLeftLeg = new ModelRenderer(this, 0, 0);
        this.FrontLeftLeg.setRotationPoint(-7.0F, 0.0F, -7.0F);
        this.FrontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 2, 16, 2, 0.0F);
        this.setRotateAngle(FrontLeftLeg, 0.0F, 3.141592653589793F, 0.0F);
        this.RightPanelBottom = new ModelRenderer(this, 31, 0);
        this.RightPanelBottom.setRotationPoint(5.5F, 6.2000000000000295F, -5.5F);
        this.RightPanelBottom.addBox(0.0F, 0.0F, 0.0F, 1, 8, 11, 0.0F);
        this.GearConnector.addChild(this.GearConnectorLeftPin);
        this.LinkageBase.addChild(this.LinkageFront);
        this.LinkageBase.addChild(this.LinkageBack);
        this.GearConnector.addChild(this.GearConnectorLeft);
        this.GearConnector.addChild(this.GearConnectorRightPin);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.LeftPanel.render(f5);
        this.RightPanelTop.render(f5);
        this.RightPanelLeft.render(f5);
        this.GearboxRear.render(f5);
        this.GearboxBottom.render(f5);
        this.GearboxRight.render(f5);
        this.GearboxTop.render(f5);
        this.BottomPanel.render(f5);
        this.RearLeftLeg.render(f5);
        this.TopPanel.render(f5);
        this.FrontRightLeg.render(f5);
        this.FrontPanel.render(f5);
        this.RearPanel.render(f5);
        this.RightPanelRight.render(f5);
        this.GearboxLeft.render(f5);
        this.RearRightLeg.render(f5);
        this.FrontLeftLeg.render(f5);
        this.RightPanelBottom.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void renderLinkage(float rotationAngle, float f5) {
        setRotateAngle(this.LinkageBase, 0, rotationAngle, 0);
        this.LinkageBase.render(f5);
    }

    public void renderGearConnector (float rotationAngle, float f5){
        setRotateAngle(this.GearConnector, 0, 0, rotationAngle * 2);
        this.GearConnector.render(f5);
    }

    public void renderAll (Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
        this.render(entity, f, f1, f2, f3, f4, f5);
        this.renderLinkage(0, f5);
        this.renderGearConnector(0, f5);
    }
}
