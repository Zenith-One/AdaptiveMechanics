package zenithmods.AdaptiveMechanics.tile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.api.item.IItemMechanicalToolHead;
import zenithmods.AdaptiveMechanics.api.tile.ICustomRaytrace;
import zenithmods.AdaptiveMechanics.api.tile.ILifterReceiver;
import zenithmods.AdaptiveMechanics.api.tile.IMechanicalPowerReceiver;
import zenithmods.AdaptiveMechanics.blocks.BlockMechanicalActuator;
import zenithmods.AdaptiveMechanics.codechicken.lib.raytracer.IndexedCuboid6;
import zenithmods.AdaptiveMechanics.codechicken.lib.vec.Cuboid6;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TileEntityMechanicalActuator extends TileEntity implements ICustomRaytrace, ILifterReceiver, IMechanicalPowerReceiver {

    private ItemStack[] inventoryToolHead = new ItemStack[]{null};
    private boolean lifting = false;
    private boolean wasLifting = false;
    private boolean dropping = false;
    private boolean canDrop = false;
    private boolean readyToDrop = false;
    private int liftingTicks = 0;
    private int previousLifterProgress = 0;
    private boolean wasPowered = false;
    private boolean powerState = false;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (readyToDrop || dropping){
            checkRedstonePower();
            checkOkayToDrop();
            checkDroppingProgress();
        }
    }

    public void addLifterTicks(int ticks){
        liftingTicks += ticks;

        if (!worldObj.isRemote){
            System.out.println("lt: " + liftingTicks);
        }
        if (liftingTicks >= TileEntityMechanicalLifter.MAX_LIFT_TIME - 1){
            liftingTicks = TileEntityMechanicalLifter.MAX_LIFT_TIME - 1;
            readyToDrop = true;
        }
    }

    private void checkRedstonePower(){
        boolean powered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
        if (!wasPowered && powered && !powerState){
            powerState = true;
        }
        wasPowered = powered;
    }

    private void checkOkayToDrop(){
        if (readyToDrop && hasValidDropPath() && powerState){
            canDrop = true;
            dropping = true;
            readyToDrop = false;
            powerState = false;
            markDirty();
        }
    }

    private boolean hasValidDropPath(){
        return worldObj.isAirBlock(xCoord, yCoord - 1, zCoord);
    }


    private void checkDroppingProgress(){
        if (dropping){
            liftingTicks -= 18;
            if (liftingTicks == 40){
               // apply head
                System.out.println("Smash!");
                powerState = false;
            } else if (liftingTicks <= 0){
                dropping = false;
                liftingTicks = 0;
            }
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord, yCoord - 2, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
    }

    @Override
    public List<IndexedCuboid6> getTraceableCuboids() {
        LinkedList<IndexedCuboid6> cuboids = new LinkedList<IndexedCuboid6>();
        double pixel = 1D / 16D;

        double sideMin = 0.39D;
        double sideMax = 0.62D;

        if (this.inventoryToolHead[0] == null){
            cuboids.add(new IndexedCuboid6(0, new Cuboid6(xCoord + sideMin, yCoord + 0.3123 - (2 * pixel), zCoord + sideMin, xCoord + sideMax, yCoord + 0.3125 + pixel, zCoord + sideMax)));
        } else {
            ItemStack stack = this.inventoryToolHead[0];
            Item item = stack == null? null : stack.getItem();
            if (item != null && item instanceof IItemMechanicalToolHead){
                IItemMechanicalToolHead toolhead = (IItemMechanicalToolHead) this.inventoryToolHead[0].getItem();
                cuboids.add(new IndexedCuboid6(1,  toolhead.getCuboid(getBlockMetadata())));
            }
        }

        cuboids.add(new IndexedCuboid6(6, BlockMechanicalActuator.getMainBoxCuboidWithOffset(xCoord, yCoord, zCoord)));
        return cuboids;
    }

    @Override
    public void lifterStateChange(boolean lifting) {
        System.out.println("State change: " + lifting + "; ticks: " + this.liftingTicks);
        this.lifting = lifting;
    }

    @Override
    public boolean canAcceptPower(Set<TileEntity> source) {
        return liftingTicks < TileEntityMechanicalLifter.MAX_LIFT_TIME - 1 && !dropping;
    }

    public IItemMechanicalToolHead getToolHead(){
        ItemStack stack = getToolHeadStack();
        if (stack != null && stack.getItem() instanceof IItemMechanicalToolHead){
           return (IItemMechanicalToolHead) stack.getItem();
        }
        return null;
    }

    public int getLiftingTicks(){
        return liftingTicks;
    }

    public ItemStack getToolHeadStack(){
        return inventoryToolHead[0];
    }

    public void activate(){
        System.out.println("Powered: " + worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) + "; was: " + wasPowered + "; powerState: " + powerState);
    }

    public TileEntity getTileEntityOnInputSide(){
        ForgeDirection targetDir = ForgeDirection.getOrientation(this.getBlockMetadata()).getOpposite();
        int x = xCoord + targetDir.offsetX;
        int y = yCoord + targetDir.offsetY;
        int z = zCoord + targetDir.offsetZ;

        return worldObj.getTileEntity(x, y, z);
    }

    public TileEntityMechanicalLifter getInputLifter(){
        TileEntity te = getTileEntityOnInputSide();
        if (te != null && te instanceof TileEntityMechanicalLifter) {
            return (TileEntityMechanicalLifter) te;
        }
        //passthrough
        return null;
    }

    public boolean isConnectedToLifter(){
        TileEntityMechanicalLifter lifter = getInputLifter();
        return lifter != null && lifter.getBlockMetadata() == getBlockMetadata();
    }



}
