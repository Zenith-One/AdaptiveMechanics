package zenithmods.AdaptiveMechanics.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineTransmitter;

public class TileEntityMechanicalLifter extends TileEntity implements IAdaptiveMachineTransmitter {

    public static final int MAX_EXTENDED_TICKS = 10;
    public static final int MAX_DROP_TICKS = 7;
    public static final int MAX_LIFT_TIME = 90;

    private int rotationTicks = 0;
    private int gearRotationTicks = 0;
    private int ticksToExtend = MAX_LIFT_TIME;
    private boolean extended = false;
    private boolean extending = false;
    private int dropTicks = 0;
    private boolean dropping = false;
    private int extendedTicks = 0;

    private int gearBarOffset = 0;

    private int lastInputRotation = 0;

    @Override
    public void updateEntity() {
        if (hasInputBelow()){
            IAdaptiveMachineTransmitter iamt = (IAdaptiveMachineTransmitter) getTileEntityBelow();
            if (iamt.canOutputToSide(ForgeDirection.UP)){
                this.rotationTicks = iamt.getOutputRotationAngle();
                checkDroppingTicks();
                checkGearTicks();
                checkGearExtensionTicks();
            }
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

    private void checkGearTicks(){
        if (this.rotationTicks != this.lastInputRotation){
            this.lastInputRotation = this.rotationTicks;
            if (!this.extended && this.rotationTicks % 2 == 0){
                if (this.gearRotationTicks < 360){
                    this.gearRotationTicks++;
                    this.ticksToExtend--;
                    this.gearBarOffset++;
                } else {
                    this.gearRotationTicks = 0;
                }
            }
        }
    }

    private void checkGearExtensionTicks(){
        if (!this.extended && ticksToExtend <= 0){
            this.extended = true;
            this.extending = true;
        } else {
           if (this.extendedTicks <= MAX_EXTENDED_TICKS) {
               if (this.extending){
                   this.extendedTicks++;
                   if (this.extendedTicks >= MAX_EXTENDED_TICKS){
                       this.extending = false;
                       this.dropping = true;
                   }
               } else if (!this.dropping && this.extended){
                   this.extendedTicks--;
                   if (this.extendedTicks <= 0){
                       this.extended = false;
                   }
               }
           }
        }
    }

    private void checkDroppingTicks(){
        if (this.dropping && this.dropTicks < MAX_DROP_TICKS ){
            dropTicks++;
            double gearBarPercent = 1.0D - (float) dropTicks / (float) MAX_DROP_TICKS;
            clientPrint("gearBarPercent: " + gearBarPercent);
            gearBarOffset = (int) ( gearBarPercent * (double) gearBarOffset);
            clientPrint("gearBarOffset: " + gearBarOffset);
            clientPrint("dropTicks: " + dropTicks);
            if (this.dropTicks >= MAX_DROP_TICKS){
                this.dropTicks = 0;
                ticksToExtend = MAX_LIFT_TIME;
                this.dropping = false;
                clientPrint("unextending");
            }
        }
    }

    public int getGearBarOffset() {
        return gearBarOffset;
    }

    public int getRotationTicks(){
        return rotationTicks;
    }

    public int getGearRotationTicks(){
        return gearRotationTicks;
    }

    public int getTicksToExtend(){
        return ticksToExtend;
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
        return this.getRotationTicks();
    }

    @Override
    public float getOutputRotationAnglef() {
        return (float) (this.getRotationTicks() / 360F * 2 * (float)Math.PI);
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
        clientPrint("rotationTicks: " + rotationTicks);
        clientPrint("extendedTicks: " + extendedTicks + " extended:" + extended + ", extending:" + extending);
        clientPrint("dropping: " + dropping + ", dropTicks: " + dropTicks + ", ticksToExtend: " + ticksToExtend );
    }

    public int getDropTicks() {
        return dropTicks;
    }

    public boolean isExtended() {
        return extended;
    }

    public boolean needsInputRotation(){
        return false;
    }
}
