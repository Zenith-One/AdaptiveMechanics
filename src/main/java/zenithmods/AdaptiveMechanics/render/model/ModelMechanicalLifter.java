package zenithmods.AdaptiveMechanics.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import zenithmods.AdaptiveMechanics.utility.AMMathHelper;

/**
 * ModelMechanicalLifter - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelMechanicalLifter extends ModelBase implements AMModel{
    public ModelRenderer FrontPanelLeft;
    public ModelRenderer BottomPanel;
    public ModelRenderer InnerRear;
    public ModelRenderer InnerTopLTR;
    public ModelRenderer InnerLeftFront;
    public ModelRenderer FrontPanelRight;
    public ModelRenderer LinkageBase;
    public ModelRenderer LeftPanelBottom;
    public ModelRenderer InnerFrontLeft;
    public ModelRenderer RightPanelLeft;
    public ModelRenderer LeftPanelTop;
    public ModelRenderer RightPanelTop;
    public ModelRenderer RearPanel;
    public ModelRenderer RightPanelRight;
    public ModelRenderer GearBar;
    public ModelRenderer TopPanel;
    public ModelRenderer RightPanelBottom;
    public ModelRenderer FulcrumLower;
    public ModelRenderer GearMain;
    public ModelRenderer InnerFrontRight;
    public ModelRenderer FrontLeftLeg;
    public ModelRenderer LeftPanelRight;
    public ModelRenderer FrontPanelBottom;
    public ModelRenderer LeftPanelLeft;
    public ModelRenderer InnerRightFront;
    public ModelRenderer InnerBottomLTR;
    public ModelRenderer FrontRightLeg;
    public ModelRenderer FrontPanelTop;
    public ModelRenderer FulcrumUpper;
    public ModelRenderer RearRightLeg;
    public ModelRenderer RearLeftLeg;
    public ModelRenderer LinkageBaseChild;
    public ModelRenderer LinkageBaseChild_1;
    public ModelRenderer LeverArm;
    public ModelRenderer LeverArmProng1;
    public ModelRenderer LeverArmProng2;
    public ModelRenderer GearMainChild;
    public ModelRenderer GearMainChild_1;

    public ModelMechanicalLifter() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.LeverArm = new ModelRenderer(this, 41, 0);
        this.LeverArm.setRotationPoint(-1.0F, 0.7F, 0.3F);
        this.LeverArm.addBox(-0.5F, -0.5F, -9.4F, 1, 1, 10, 0.0F);
        this.setRotateAngle(LeverArm, -0.2408554367752175F, 0.0F, 0.0F);
        this.BottomPanel = new ModelRenderer(this, 65, 62);
        this.BottomPanel.setRotationPoint(-5.900000095367432F, 13.0F, 5.900000095367432F);
        this.BottomPanel.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
        this.setRotateAngle(BottomPanel, 3.1415927410125732F, 0.0F, 0.0F);
        this.GearBar = new ModelRenderer(this, 26, 10);
        this.GearBar.setRotationPoint(1.2000000476837158F, 5.699999809265137F, 2.450000047683716F);
        this.GearBar.addBox(-0.5F, 0.0F, -0.20000000298023224F, 1, 4, 1, 0.0F);
        this.InnerBottomLTR = new ModelRenderer(this, 84, 106);
        this.InnerBottomLTR.setRotationPoint(-5.5F, 8.399999618530273F, -5.5F);
        this.InnerBottomLTR.addBox(0.0F, 0.0F, 0.0F, 11, 1, 10, 0.0F);
        this.LeftPanelBottom = new ModelRenderer(this, 10, 63);
        this.LeftPanelBottom.setRotationPoint(-6.5F, 8.399999618530273F, -5.400000095367432F);
        this.LeftPanelBottom.addBox(0.0F, 0.0F, 0.0F, 1, 5, 11, 0.0F);
        this.LinkageBaseChild_1 = new ModelRenderer(this, 108, 0);
        this.LinkageBaseChild_1.setRotationPoint(-1.5F, 2.4000000953674316F, 0.5F);
        this.LinkageBaseChild_1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(LinkageBaseChild_1, 3.1415927410125732F, 0.0F, 1.5707963705062866F);
        this.InnerRear = new ModelRenderer(this, 64, 108);
        this.InnerRear.setRotationPoint(-5.5F, 3.0F, 3.5999999046325684F);
        this.InnerRear.addBox(0.0F, 0.0F, 0.0F, 11, 6, 1, 0.0F);
        this.LinkageBaseChild = new ModelRenderer(this, 108, 0);
        this.LinkageBaseChild.setRotationPoint(1.5F, 2.4000000953674316F, -0.5F);
        this.LinkageBaseChild.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.setRotateAngle(LinkageBaseChild, 0.0F, 0.0F, 1.5707963705062866F);
        this.FrontRightLeg = new ModelRenderer(this, 0, 69);
        this.FrontRightLeg.setRotationPoint(5.0F, 0.0F, -7.0F);
        this.FrontRightLeg.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.LinkageBase = new ModelRenderer(this, 91, 20);
        this.LinkageBase.setRotationPoint(0.0F, 12.399999618530273F, 0.0F);
        this.LinkageBase.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        this.RightPanelLeft = new ModelRenderer(this, 0, 36);
        this.RightPanelLeft.setRotationPoint(5.5F, 3.4000000953674316F, -5.400000095367432F);
        this.RightPanelLeft.addBox(0.0F, 0.0F, 0.0F, 1, 5, 5, 0.0F);
        this.InnerRightFront = new ModelRenderer(this, 84, 83);
        this.InnerRightFront.setRotationPoint(2.5999999046325684F, 3.4000000953674316F, -1.5F);
        this.InnerRightFront.addBox(0.0F, 0.0F, 0.0F, 3, 5, 1, 0.0F);
        this.GearMain = new ModelRenderer(this, 7, 0);
        this.GearMain.setRotationPoint(3.0F, 6.400000095367432F, 2.799999952316284F);
        this.GearMain.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
        this.InnerFrontRight = new ModelRenderer(this, 99, 83);
        this.InnerFrontRight.setRotationPoint(2.5999999046325684F, 3.4000000953674316F, -5.5F);
        this.InnerFrontRight.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4, 0.0F);
        this.FrontPanelLeft = new ModelRenderer(this, 44, 49);
        this.FrontPanelLeft.setRotationPoint(-5.400000095367432F, 3.4000000953674316F, -6.5F);
        this.FrontPanelLeft.addBox(0.0F, 0.0F, 0.0F, 3, 5, 1, 0.0F);
        this.LeverArmProng1 = new ModelRenderer(this, 0, 10);
        this.LeverArmProng1.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.LeverArmProng1.addBox(-0.5F, -0.5F, -10.2F, 1, 1, 2, 0.0F);
        this.LeftPanelRight = new ModelRenderer(this, 0, 36);
        this.LeftPanelRight.setRotationPoint(-6.5F, 3.4000000953674316F, -5.400000095367432F);
        this.LeftPanelRight.addBox(0.0F, 0.0F, 0.0F, 1, 5, 5, 0.0F);
        this.InnerFrontLeft = new ModelRenderer(this, 99, 83);
        this.InnerFrontLeft.setRotationPoint(-3.5F, 3.4000000953674316F, -5.5F);
        this.InnerFrontLeft.addBox(0.0F, 0.0F, 0.0F, 1, 5, 4, 0.0F);
        this.GearMainChild_1 = new ModelRenderer(this, 36, 0);
        this.GearMainChild_1.setRotationPoint(0.0F, 0.0F, 0.30000001192092896F);
        this.GearMainChild_1.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
        this.setRotateAngle(GearMainChild_1, 0.0F, 0.0F, 0.7853981852531433F);
        this.LeftPanelTop = new ModelRenderer(this, 0, 20);
        this.LeftPanelTop.setRotationPoint(-6.5F, 0.4000000059604645F, -5.400000095367432F);
        this.LeftPanelTop.addBox(0.0F, 0.0F, 0.0F, 1, 3, 11, 0.0F);
        this.FrontPanelRight = new ModelRenderer(this, 57, 49);
        this.FrontPanelRight.setRotationPoint(2.5999999046325684F, 3.4000000953674316F, -6.5F);
        this.FrontPanelRight.addBox(0.0F, 0.0F, 0.0F, 3, 5, 1, 0.0F);
        this.FrontPanelTop = new ModelRenderer(this, 44, 42);
        this.FrontPanelTop.setRotationPoint(-5.400000095367432F, 0.4000000059604645F, -6.5F);
        this.FrontPanelTop.addBox(0.0F, 0.0F, 0.0F, 11, 3, 1, 0.0F);
        this.TopPanel = new ModelRenderer(this, 65, 0);
        this.TopPanel.setRotationPoint(-5.900000095367432F, 1.7999999523162842F, 5.900000095367432F);
        this.TopPanel.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12, 0.0F);
        this.setRotateAngle(TopPanel, 3.1415927410125732F, 0.0F, 0.0F);
        this.LeftPanelLeft = new ModelRenderer(this, 0, 20);
        this.LeftPanelLeft.setRotationPoint(-6.5F, 3.4000000953674316F, 3.5999999046325684F);
        this.LeftPanelLeft.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
        this.InnerLeftFront = new ModelRenderer(this, 84, 83);
        this.InnerLeftFront.setRotationPoint(-5.5F, 3.4000000953674316F, -1.5F);
        this.InnerLeftFront.addBox(0.0F, 0.0F, 0.0F, 3, 5, 1, 0.0F);
        this.FulcrumUpper = new ModelRenderer(this, 84, 100);
        this.FulcrumUpper.mirror = true;
        this.FulcrumUpper.setRotationPoint(-2.700000047683716F, 4.670000076293945F, -0.6000000238418579F);
        this.FulcrumUpper.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1, 0.0F);
        this.setRotateAngle(FulcrumUpper, -3.1415927410125732F, 0.0F, 0.0F);
        this.FrontLeftLeg = new ModelRenderer(this, 0, 69);
        this.FrontLeftLeg.setRotationPoint(-7.0F, 0.0F, -7.0F);
        this.FrontLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 2, 16, 2, 0.0F);
        this.setRotateAngle(FrontLeftLeg, 0.0F, 3.1415927410125732F, 0.0F);
        this.FrontPanelBottom = new ModelRenderer(this, 43, 58);
        this.FrontPanelBottom.setRotationPoint(-5.400000095367432F, 8.399999618530273F, -6.5F);
        this.FrontPanelBottom.addBox(0.0F, 0.0F, 0.0F, 11, 5, 1, 0.0F);
        this.InnerTopLTR = new ModelRenderer(this, 84, 106);
        this.InnerTopLTR.setRotationPoint(-5.5F, 2.4000000953674316F, -5.5F);
        this.InnerTopLTR.addBox(0.0F, 0.0F, 0.0F, 11, 1, 10, 0.0F);
        this.RearLeftLeg = new ModelRenderer(this, 0, 69);
        this.RearLeftLeg.setRotationPoint(-7.0F, 0.0F, 5.0F);
        this.RearLeftLeg.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.RightPanelTop = new ModelRenderer(this, 0, 20);
        this.RightPanelTop.setRotationPoint(5.5F, 0.4000000059604645F, -5.400000095367432F);
        this.RightPanelTop.addBox(0.0F, 0.0F, 0.0F, 1, 3, 11, 0.0F);
        this.RearRightLeg = new ModelRenderer(this, 0, 69);
        this.RearRightLeg.setRotationPoint(5.0F, 0.0F, 5.0F);
        this.RearRightLeg.addBox(-2.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.setRotateAngle(RearRightLeg, 0.0F, 1.5707963705062866F, 0.0F);
        this.RightPanelBottom = new ModelRenderer(this, 10, 63);
        this.RightPanelBottom.setRotationPoint(5.5F, 8.399999618530273F, -5.400000095367432F);
        this.RightPanelBottom.addBox(0.0F, 0.0F, 0.0F, 1, 5, 11, 0.0F);
        this.RightPanelRight = new ModelRenderer(this, 0, 20);
        this.RightPanelRight.setRotationPoint(5.5F, 3.4000000953674316F, 3.5999999046325684F);
        this.RightPanelRight.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
        this.FulcrumLower = new ModelRenderer(this, 84, 100);
        this.FulcrumLower.setRotationPoint(-2.700000047683716F, 6.25F, -1.600000023841858F);
        this.FulcrumLower.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1, 0.0F);
        this.GearMainChild = new ModelRenderer(this, 7, 0);
        this.GearMainChild.setRotationPoint(0.0F, 0.0F, 0.029999999329447746F);
        this.GearMainChild.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
        this.setRotateAngle(GearMainChild, 0.0F, 0.0F, 0.7853981852531433F);
        this.LeverArmProng2 = new ModelRenderer(this, 0, 10);
        this.LeverArmProng2.setRotationPoint(-1.0F, 0.0F, 0.0F);
        this.LeverArmProng2.addBox(-0.5F, -0.5F, -10.2F, 1, 1, 2, 0.0F);
        this.RearPanel = new ModelRenderer(this, 31, 25);
        this.RearPanel.setRotationPoint(-5.5F, 0.4000000059604645F, 5.5F);
        this.RearPanel.addBox(0.0F, 0.0F, 0.0F, 11, 13, 1, 0.0F);
        this.GearBar.addChild(this.LeverArm);
        this.LinkageBase.addChild(this.LinkageBaseChild_1);
        this.LinkageBase.addChild(this.LinkageBaseChild);
        this.LeverArm.addChild(this.LeverArmProng1);
        this.GearMain.addChild(this.GearMainChild_1);
        this.GearMain.addChild(this.GearMainChild);
        this.LeverArm.addChild(this.LeverArmProng2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.BottomPanel.render(f5);
        this.InnerBottomLTR.render(f5);
        this.LeftPanelBottom.render(f5);
        this.InnerRear.render(f5);
        this.FrontRightLeg.render(f5);
        this.RightPanelLeft.render(f5);
        this.InnerRightFront.render(f5);
        this.InnerFrontRight.render(f5);
        this.FrontPanelLeft.render(f5);
        this.LeftPanelRight.render(f5);
        this.InnerFrontLeft.render(f5);
        this.LeftPanelTop.render(f5);
        this.FrontPanelRight.render(f5);
        this.FrontPanelTop.render(f5);
        this.TopPanel.render(f5);
        this.LeftPanelLeft.render(f5);
        this.InnerLeftFront.render(f5);
        this.FulcrumUpper.render(f5);
        this.FrontLeftLeg.render(f5);
        this.FrontPanelBottom.render(f5);
        this.InnerTopLTR.render(f5);
        this.RearLeftLeg.render(f5);
        this.RightPanelTop.render(f5);
        this.RearRightLeg.render(f5);
        this.RightPanelBottom.render(f5);
        this.RightPanelRight.render(f5);
        this.FulcrumLower.render(f5);
        this.RearPanel.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


    public void renderMainGear(float rotation, float f5){
        this.setRotateAngle(this.GearMain, 0, 0, rotation);
        this.GearMain.render(f5);
    }

    public void renderLinkage(float rotation, float f5){
        this.setRotateAngle(this.LinkageBase, 0, rotation, 0);
        this.LinkageBase.render(f5);
    }

    public void renderGearBar(float leverArmRotation, float f5){
        setRotateAngle(LeverArm, leverArmRotation, 0, 0);
        this.GearBar.render(f5);
    }

    public void renderAll(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
        this.render(entity, f, f1, f2, f3, f4, f5);
        this.renderMainGear(0, f5);
        this.renderGearBar((float)Math.toRadians(18.8), f5);
    }
}
