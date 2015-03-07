package zenithmods.AdaptiveMechanics.tile;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.api.item.IItemMechanicalToolHead;
import zenithmods.AdaptiveMechanics.api.tile.ICustomRaytrace;
import zenithmods.AdaptiveMechanics.codechicken.lib.raytracer.IndexedCuboid6;

import java.util.List;

public class TileEntityMechanicalActuator extends TileEntity implements ICustomRaytrace{

    private ItemStack[] inventoryToolHead = new ItemStack[]{null};


    @Override
    public List<IndexedCuboid6> getTraceableCuboids() {
        return null;
    }

    public IItemMechanicalToolHead getToolHead(){
        return null;
    }

    public ItemStack getToolHeadStack(){
        return inventoryToolHead[0];
    }

    public void activate(){
        ForgeDirection targetDir = ForgeDirection.getOrientation(this.getBlockMetadata()).getOpposite();
        int x = this.xCoord + targetDir.offsetX;
        int y = this.yCoord + targetDir.offsetY;
        int z = this.zCoord + targetDir.offsetZ;

        worldObj.setBlock(x, y, z, Blocks.brick_block);
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
