package zenithmods.AdaptiveMechanics.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineTransmitter;

public class TileEntityMechanicalLifter extends TileEntity implements IAdaptiveMachineTransmitter {

    public static final int MAX_EXTENDED_TICKS = 10;
    public static final int MAX_DROP_TICKS = 5;
    public static final int MAX_LIFT_TIME = 90;
    public static final int MAX_DROP_WAIT = MAX_DROP_TICKS * 2;

    private final int EXTEND_START = 90;
    private final int DROP_START = 100;
    private final int RETRACT_START = 110;
    private final int RESET = 120;

    private int workingTicks = 0;
    private int passesInQueue = 0;
    private int remainder = 0;

    private boolean initialized = false;
    private int inputRotationTicks = 0;

    private int gearRotationTicks = 0;

    private int extendedTicks = 0;

    private boolean dropping = false;
    private int dropTicks = 0;

    private boolean shouldReport = false;
    private boolean hasReported = false;

    private int gearBarOffset = 0;

    private int lastInputRotation = 0;

    @Override
    public void updateEntity() {
        if (hasInputBelow()){
            IAdaptiveMachineTransmitter iamt = (IAdaptiveMachineTransmitter) getTileEntityBelow();
            if (iamt.canOutputToSide(ForgeDirection.UP)){
                this.inputRotationTicks = iamt.getOutputRotationAngle();
                if (!initialized){
                    this.lastInputRotation = inputRotationTicks;
                    initialized = true;
                }
                checkProgress();
                while (passesInQueue > 0){
                    checkDroppingTicks();
                    checkGearTicks();
                    checkGearExtensionTicks();
                    passesInQueue--;
                }
            }
        }
        if (shouldReport && !hasReported){
            printStuff();
            hasReported = true;
            shouldReport = false;
        }
    }

    public TileEntity getTileEntityBelow(){
        int x, y, z;
        x = ForgeDirection.DOWN.offsetX + this.xCoord;
        y = ForgeDirection.DOWN.offsetY + this.yCoord;
        z = ForgeDirection.DOWN.offsetZ + this.zCoord;
        TileEntity te = this.worldObj.getTileEntity(x, y, z);
        return te;
    }

    public boolean hasInputBelow(){
        TileEntity te = getTileEntityBelow();
        return te instanceof IAdaptiveMachineTransmitter;
    }

    private boolean checkProgress(){
        if (this.inputRotationTicks != this.lastInputRotation){
            int diff = inputRotationTicks > lastInputRotation ?
                    inputRotationTicks - lastInputRotation :
                    360+ inputRotationTicks - lastInputRotation;
            diff += remainder;

            passesInQueue = diff / 2;
            remainder = diff % passesInQueue;

            this.lastInputRotation = this.inputRotationTicks;
            this.hasReported = false;
            return diff > 0;
        } else {
            if (!this.hasReported){
                //this.shouldReport = true;
            }
        }
        return false;
    }

    private void checkGearTicks(){
        workingTicks++;
        if (workingTicks < EXTEND_START){
            if (this.gearRotationTicks < 360){
                this.gearRotationTicks++;
                this.gearBarOffset++;
            } else {
                this.gearRotationTicks = 0;
            }
        }
    }

    private void checkGearExtensionTicks(){
        if (workingTicks > EXTEND_START){
           if ( workingTicks <= DROP_START) {
               this.extendedTicks++;
           } else if (workingTicks == DROP_START + 1){
               dropping = true;
           } else if (workingTicks > RETRACT_START){
               this.extendedTicks--;
               if (this.extendedTicks <= 0){
                   clientPrint(workingTicks + "");
                   workingTicks = 0;
               }
           }
        }
    }

    private void checkDroppingTicks(){
        if (this.dropping && this.dropTicks < MAX_DROP_TICKS ){
            dropTicks++;
            double gearBarPercent = 1.0D - (float) dropTicks / (float) MAX_DROP_TICKS;
            gearBarOffset = (int) ( gearBarPercent * (double) gearBarOffset);
            if (this.dropTicks >= MAX_DROP_TICKS){
                this.dropTicks = 0;
                this.dropping = false;
            }
        }
    }

    public int getGearBarOffset() {
        return gearBarOffset;
    }

    public int getInputRotationTicks(){
        return inputRotationTicks;
    }

    public int getGearRotationTicks(){
        return gearRotationTicks;
    }

    public boolean isDropping(){
        return dropping;
    }

    @Override
    public boolean[] getInputSides() {
        boolean[] sides = new boolean[6];
        for (int i = 0; i < 6; i++){
            ForgeDirection dir = ForgeDirection.getOrientation(blockMetadata);
            ForgeDirection right = dir.getRotation(ForgeDirection.DOWN);
            int side = right.ordinal();
            if (side == i){
                sides[i] = true;
            } else {
                sides[i] = false;
            }
        }

        return null;
    }

    public int getExtendedTicks(){
        return this.extendedTicks;
    }

    @Override
    public int getOutputRotationAngle() {
        return this.getInputRotationTicks();
    }

    @Override
    public float getOutputRotationAnglef() {
        return (float) (this.getInputRotationTicks() / 360F * 2 * (float)Math.PI);
    }

    public float getGearRotationAnglef() {
        return (float) (this.gearRotationTicks / 360F * 2 * (float) Math.PI);
    }

    @Override
    public boolean canOutputToSide(ForgeDirection side) {
        if (side == ForgeDirection.UP){
            return true;
        } else {
            return false;
        }
    }

    public void clientPrint(String message){
        if (this.worldObj.isRemote){
            System.out.println(message);
        }
    }

    public void printStuff(){
        clientPrint("workingTicks: " + workingTicks);
        clientPrint("inputRotationTicks: " + inputRotationTicks);
        clientPrint("extendedTicks: " + extendedTicks);
        clientPrint("dropping: " + dropping + ", dropTicks: " + dropTicks);
        clientPrint("gearBarOffset: " + gearBarOffset);
    }

    public int getDropTicks() {
        return dropTicks;
    }

    public boolean needsInputRotation(){
        return false;
    }
}
