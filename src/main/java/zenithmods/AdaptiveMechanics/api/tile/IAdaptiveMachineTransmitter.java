package zenithmods.AdaptiveMechanics.api.tile;

import net.minecraftforge.common.util.ForgeDirection;

public interface IAdaptiveMachineTransmitter {

    public boolean[] getInputSides();
    public int getOutputRotationAngle();
    public float getOutputRotationAnglef();
    public boolean canOutputToSide(ForgeDirection side);
    public boolean needsInputRotation();

}
