package zenithmods.AdaptiveMechanics.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineReceiver;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineTransmitter;

public class TileEntityMechanicalReceiver extends TileEntity implements IAdaptiveMachineTransmitter, IAdaptiveMachineReceiver {

    private int rotationTicks = 0;
    private int inputTicks = 0;
    private int rotationPerTick = 0;
    private int ticksPerRotation = 0;
    private int ticksTillRotate = 0;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (inputTicks > 0){
            inputTicks--;
            if (rotationPerTick > 0){
                for (int i = 0; i < rotationPerTick; i ++){
                    rotationTicks++;
                }
            } else if (ticksPerRotation > 0){
                if (ticksTillRotate <= 0){
                    rotationTicks++;
                    ticksTillRotate = ticksPerRotation;
                } else {
                    ticksTillRotate--;
                }
            }
        } else {
            if (rotationPerTick > 0){
                rotationPerTick = 0;
            }
            if (ticksPerRotation > 0){
                ticksPerRotation = 0;
            }
            if (ticksTillRotate > 0){
                ticksTillRotate = 0;
            }
        }

    }

    public int getRotationTicks(){
        return rotationTicks;
    }

    @Override
    public boolean[] getInputSides() {
        boolean[] sides = new boolean[6];
        for (int i = 0; i < 6; i++){
            ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata());
            ForgeDirection right = dir.getRotation(ForgeDirection.DOWN);
            int side = right.ordinal();
            if (side == i){
                clientPrint(i + ": true"  );
                sides[i] = true;
                //worldObj.setBlock(xCoord + right.offsetX, yCoord + right.offsetY, zCoord + right.offsetZ, Blocks.cobblestone);
            } else {
                clientPrint(i + ": false"  );
                sides[i] = false;
            }


        }

        System.out.println(sides);
        return sides;
    }

    @Override
    public int getOutputRotationAngle() {
        return this.getRotationTicks();
    }

    @Override
    public float getOutputRotationAnglef() {
        return (float) (this.getRotationTicks() / 360F * 2 * (float)Math.PI);
    }

    @Override
    public boolean canOutputToSide(ForgeDirection side) {
        if (side == ForgeDirection.UP){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean needsInputRotation(){
        return (this.getBlockMetadata() == 2 || this.blockMetadata == 3);
    }

    @Override
    public void inputRotationalEnergy(int amount, int ticks) {
        float energyInput = (float) amount;
        if (energyInput / ticks >= 1){
            rotationPerTick = amount/ticks;
        } else {
            ticksPerRotation = ticks / amount;
        }
        inputTicks = ticks;
    }

    private void clientPrint(String msg){
        if (!worldObj.isRemote){
            System.out.println(msg);
        }
    }
}
