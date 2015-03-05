package zenithmods.AdaptiveMechanics.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.api.item.IItemGearbox;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineReceiver;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineTransmitter;
import zenithmods.AdaptiveMechanics.api.tile.ICustomRaytrace;
import zenithmods.AdaptiveMechanics.codechicken.lib.raytracer.IndexedCuboid6;
import zenithmods.AdaptiveMechanics.codechicken.lib.vec.Cuboid6;
import zenithmods.AdaptiveMechanics.utility.ItemHelper;

import java.util.LinkedList;
import java.util.List;

public class TileEntityMechanicalReceiver extends TileEntity implements IAdaptiveMachineTransmitter, IAdaptiveMachineReceiver, ICustomRaytrace {

    private int rotationTicks = 0;
    private int inputTicks = 0;
    private int rotationPerTick = 0;
    private int ticksPerRotation = 0;
    private int ticksTillRotate = 0;
    private ItemStack[] inventoryGearbox;

    public TileEntityMechanicalReceiver(){
        inventoryGearbox = new ItemStack[1];
        inventoryGearbox[0] = null; // just in case
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (inputTicks > 0){
            inputTicks--;
            if (rotationPerTick > 0){
                for (int i = 0; i < rotationPerTick; i ++){
                    incrementRotation();
                }
            } else if (ticksPerRotation > 0){
                if (ticksTillRotate <= 0){
                    incrementRotation();
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

    private void incrementRotation(){
        rotationTicks++;
        if (rotationTicks >= 360) {
            rotationTicks = 0;
        }
    }

    @Override
    public boolean[] getInputSides() {
        boolean[] sides = new boolean[6];
        for (int i = 0; i < 6; i++){
            ForgeDirection dir = ForgeDirection.getOrientation(this.getBlockMetadata());
            ForgeDirection right = dir.getRotation(ForgeDirection.DOWN);
            int side = right.ordinal();
            if (side == i && inventoryGearbox[0] != null){
                sides[i] = true;
                //worldObj.setBlock(xCoord + right.offsetX, yCoord + right.offsetY, zCoord + right.offsetZ, Blocks.cobblestone);
            } else {
                sides[i] = false;
            }


        }

        System.out.println(sides);
        return sides;
    }

    public int getRotationTicks(){
        return rotationTicks;
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
        if (amount / ticks > this.getMaximumInputRate()){
            return;
        }
        float energyInput = (float) amount;
        if (energyInput / ticks >= 1){
            rotationPerTick = amount/ticks;
        } else {
            ticksPerRotation = ticks / amount;
        }
        inputTicks = ticks;
    }

    @Override
    public int getMaximumInputRate() {
        ItemStack stack = inventoryGearbox[0];
        if (stack != null){
            Item item = stack.getItem();
            if (item != null && item instanceof IItemGearbox){
                IItemGearbox gearbox = (IItemGearbox) item;
                return gearbox.getMaximumInputRate();
            }
        }
        return 0;
    }

    @Override
    public List<IndexedCuboid6> getTraceableCuboids() {
        int meta = this.getBlockMetadata();
        //clientPrint("meta: " + meta);

        //public Cuboid6(double minx, double miny, double minz, double maxx, double maxy, double maxz) {
        // y1 < 0.88f && y1 > 0.62f
        LinkedList<IndexedCuboid6> cuboids = new LinkedList<IndexedCuboid6>();

        double halfPixel = 1f / 32f;
        double pixel = 2 * halfPixel;

        double minX = this.xCoord;
        double minY = this.yCoord + 0.621d;
        double minZ = this.zCoord;
        double maxX = this.xCoord;
        double maxY = this.yCoord + 0.879d;
        double maxZ = this.zCoord;
        //cuboids.add(new IndexedCuboid6(0, new Cuboid6(this.xCoord + pixel, this.yCoord, this.zCoord + pixel, this.xCoord + 1 - pixel, this.yCoord + 1, this.zCoord + 1 - pixel)));

        double mainMin = 0.0625f;
        double mainMax = 0.9375f;
        double sideMin = 0.33f;
        double sideMax = 0.63f;


        switch (meta){
            case 2:
            case 3:
            case 4:

                minX = this.xCoord + sideMin;
                maxX = this.xCoord + sideMax;
                minZ = this.zCoord + mainMax - halfPixel;
                maxZ = this.zCoord + 1;
                cuboids.add(new IndexedCuboid6(4, new Cuboid6(this.xCoord + sideMin, minY, this.zCoord + mainMax - pixel, this.xCoord + sideMax, maxY, this.zCoord + 1)));
                break;
            case 5:
            default:
                break;
        }
        //cuboids.add(new IndexedCuboid6(meta, new Cuboid6(minX, minY, minZ, maxX, maxY, maxZ )));

        return cuboids;
    }

    public ItemStack installGearbox(ItemStack gearboxStack){
        if (this.inventoryGearbox[0] == null){
            Item item = gearboxStack.getItem();
            if (item instanceof IItemGearbox){
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                IItemGearbox gearbox = (IItemGearbox) item;
                inventoryGearbox[0] = new ItemStack(item);
                inventoryGearbox[0].stackSize = 1;
                inventoryGearbox[0].setItemDamage(gearboxStack.getItemDamage());
                gearboxStack.stackSize--;
                if (gearboxStack.stackSize == 0){
                    return null;
                }
            }
        }
        return gearboxStack; // if nothing else.
    }

    public boolean uninstallGearbox(EntityPlayer player){
        if (!worldObj.isRemote){
            if (this.inventoryGearbox[0] != null) {
                ItemStack stack = this.inventoryGearbox[0];
                this.inventoryGearbox[0] = null;
                ItemHelper.spawnEntityItemAtPlayerFeet(worldObj, player, stack);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                return true;
            }
        }

        return false;
    }

    public IItemGearbox getGearbox(){
        if (inventoryGearbox[0] != null){
            Item item = inventoryGearbox[0].getItem();
            if (item instanceof IItemGearbox){
                return (IItemGearbox) item;
            }
        }
        return null;
    }

    public boolean hasGearbox(){
        return inventoryGearbox[0] != null;
    }

    public ItemStack getGearboxStack(){
        return inventoryGearbox[0];
    }

    public void clientPrint(String msg){
        if (worldObj.isRemote){
            System.out.println(msg);
        }
    }

     @Override
    public Packet getDescriptionPacket() {
        super.getDescriptionPacket();
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("rotationTicks", this.rotationTicks);
        nbt.setInteger("inputTicks", this.inputTicks);
        nbt.setInteger("rotationPerTick", this.rotationPerTick);
        nbt.setInteger("ticksPerRotation", this.ticksPerRotation);
        nbt.setInteger("ticksTillRotate", this.ticksTillRotate);
        NBTTagCompound compound;


        if (this.inventoryGearbox[0] != null) {
            compound = new NBTTagCompound();
            this.inventoryGearbox[0].writeToNBT(compound);
            nbt.setTag("gearbox", compound);
        }

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
    }


    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        NBTTagCompound nbt = pkt.func_148857_g();
        this.rotationTicks = nbt.getInteger("rotationTicks");
        this.inputTicks = nbt.getInteger("inputTicks");
        this.rotationPerTick = nbt.getInteger("rotationPerTick");
        this.ticksPerRotation = nbt.getInteger("ticksPerRotation");
        this.ticksTillRotate = nbt.getInteger("ticksTillRotate");

        if (nbt.hasKey("gearbox")) {

            this.inventoryGearbox[0] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("gearbox"));
        } else {
            this.inventoryGearbox[0] = null;
        }
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.rotationTicks = nbt.getInteger("rotationTicks");
        this.inputTicks = nbt.getInteger("inputTicks");
        this.rotationPerTick = nbt.getInteger("rotationPerTick");
        this.ticksPerRotation = nbt.getInteger("ticksPerRotation");
        this.ticksTillRotate = nbt.getInteger("ticksTillRotate");

        if (nbt.hasKey("gearbox")) {

            this.inventoryGearbox[0] = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("gearbox"));
        } else {
            this.inventoryGearbox[0] = null;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("rotationTicks", this.rotationTicks);
        nbt.setInteger("inputTicks", this.inputTicks);
        nbt.setInteger("rotationPerTick", this.rotationPerTick);
        nbt.setInteger("ticksPerRotation", this.ticksPerRotation);
        nbt.setInteger("ticksTillRotate", this.ticksTillRotate);
        NBTTagCompound compound;


        if (this.inventoryGearbox[0] != null) {
            compound = new NBTTagCompound();
            this.inventoryGearbox[0].writeToNBT(compound);
            nbt.setTag("gearbox", compound);
        }
    }

}
