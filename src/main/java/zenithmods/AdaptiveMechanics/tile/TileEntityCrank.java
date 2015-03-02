package zenithmods.AdaptiveMechanics.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineReceiver;

public class TileEntityCrank extends TileEntity {

    private int rotationTicks = 0;
    private boolean turning = false;
    private boolean turningQueued = false;
    private final int OUTPUT_TICKS = 30;
    private final int OUTPUT_AMOUNT = OUTPUT_TICKS * 3;

    public void updateEntity() {
        super.updateEntity();
        if (turning){
            if (rotationTicks < 360){
                for (int i = 0; i < 12; i++){
                    rotationTicks++;
                }
            } else {
                rotationTicks = 0;
                turning = false;
            }
        } else if (turningQueued){
            turningQueued = false;
            turning = true;
        }
    }

    public boolean isTurning(){
        return turning;
    }

    private TileEntity getTargetTile(){
        ForgeDirection d = ForgeDirection.getOrientation(blockMetadata).getOpposite();
        return worldObj.getTileEntity(xCoord + d.offsetX, yCoord + d.offsetY, zCoord + d.offsetZ);
    }

    private boolean sendTargetRotations(){
        TileEntity te = getTargetTile();
        if (te != null && te instanceof IAdaptiveMachineReceiver){
            IAdaptiveMachineReceiver iamr = (IAdaptiveMachineReceiver) te;
            iamr.inputRotationalEnergy(OUTPUT_AMOUNT, OUTPUT_TICKS);
            return true;
        }
        return false;
    }


    public void rightClicked(){
        if (!turning){
            turning = sendTargetRotations();
        } else if (rotationTicks > 350){
            turningQueued = true;
        }
    }


    public float getOutputRotationAnglef() {
        return (float) ((float) this.rotationTicks / 360F * 2 * (float)Math.PI);
    }
}
