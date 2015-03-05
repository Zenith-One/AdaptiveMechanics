package zenithmods.AdaptiveMechanics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.AdaptiveMechanics;
import zenithmods.AdaptiveMechanics.api.block.BlockAdaptiveMachine;
import zenithmods.AdaptiveMechanics.api.block.IBlockAdaptiveMachineReceiver;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineReceiver;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineTransmitter;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.AMRenderingRegister;
import zenithmods.AdaptiveMechanics.tile.TileEntityCrank;

import static zenithmods.AdaptiveMechanics.utility.AABBHelper.*;


public class BlockCrank extends BlockAdaptiveMachine{

    public static final String NAME = "blockCrank";

    public BlockCrank(){
        super(Material.wood);
        setBlockName(Constants.MOD_ID + ":" + NAME);
        setCreativeTab(AdaptiveMechanics.tab);
        setLightOpacity(0);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCrank();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock(){
        return false;
    }



    @Override
    public boolean shouldSideBeRendered(IBlockAccess iba, int x, int y, int z, int meta){
        return false;
    }

    @Override
    public int getRenderType() {
        return AMRenderingRegister.crankRenderID;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        if (super.canPlaceBlockAt(world, x, y, z)){
            int potentialMeta = getMetaFromSurrounding(world, x, y, z);
            if ( potentialMeta > 1){
                System.out.println("Yep");
                return true;
            } else {
                if (world.isRemote){
                    System.out.println(potentialMeta);
                }
            }
        } else {
            if (world.isRemote){
                System.out.println("super can't do it.");
            }
        }
        return false;
    }

    private int getMetaFromSurrounding(World world, int x, int y, int z){
        for (ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
            TileEntity te = world.getTileEntity(x + d.offsetX, y + d.offsetY, z + d.offsetZ);
            if (te != null){
                if (te instanceof IAdaptiveMachineTransmitter){
                    IAdaptiveMachineTransmitter iamt = (IAdaptiveMachineTransmitter) te;
                    boolean[] inputSides = iamt.getInputSides();
                    if (inputSides != null && inputSides[d.getOpposite().ordinal()]){
                        return d.getOpposite().ordinal();
                    }
                }
            }
        }

        return -1;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     *
     * @param world
     * @param x
     * @param y
     * @param z
     * @param block
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        int currentMeta = world.getBlockMetadata(x, y, z);
        if (getMetaFromSurrounding(world, x, y, z) != currentMeta ){
            this.dropBlockAsItem(world, x, y, z, currentMeta, 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        System.out.println("ahoy");
        world.setBlockMetadataWithNotify(x, y, z, getMetaFromSurrounding(world, x, y, z), 2);
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
        TileEntityCrank tileEntityCrank = (TileEntityCrank) par1World.getTileEntity(par2, par3, par4);
        tileEntityCrank.rightClicked();
        return true;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        AABBTuple tuple = getBounds(meta);
        return AxisAlignedBB.getBoundingBox(
                x + tuple.getxMin(),
                y + tuple.getyMin(),
                z + tuple.getzMin(),
                x + tuple.getxMax(),
                y + tuple.getyMax(),
                z + tuple.getzMax()
        );
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iba, int x, int y, int z) {
        int meta = iba.getBlockMetadata(x, y, z);
        AABBTuple tuple = getBounds(meta);
        setBlockBounds(
                tuple.getxMin(),
                tuple.getyMin(),
                tuple.getzMin(),
                tuple.getxMax(),
                tuple.getyMax(),
                tuple.getzMax()
        );
        super.setBlockBoundsBasedOnState(iba, x, y, z);
    }

    private AABBTuple getBounds(int meta){
        float pixel = 1f / 16f;
        AABBTuple tuple = getDefault();
        tuple.setyMin(0.88f - 6 * pixel);
        tuple.setyMax(1.02f - pixel);
        switch (meta){
            case 2:
                tuple.setxMin(0.48F - (3 * pixel));
                tuple.setxMax(0.52F + (3 * pixel));
                tuple.setzMin(1 - (5 * pixel));
                tuple.setzMax(1 + (1.5f * pixel));
                break;
            case 3:
                tuple.setxMin(0.48F - (3 * pixel));
                tuple.setxMax(0.52F + (3 * pixel));
                tuple.setzMin(0 - (1.5f * pixel));
                tuple.setzMax(0 + (5 * pixel));
                break;
            case 4:
                tuple.setzMin(0.48F - (3 * pixel));
                tuple.setzMax(0.52F + (3 * pixel));
                tuple.setxMin(1 - (5 * pixel));
                tuple.setxMax(1 + (1.5f * pixel));
                break;
            case 5:
                tuple.setzMin(0.48F - (3 * pixel));
                tuple.setzMax(0.52F + (3 * pixel));
                tuple.setxMax(0 + (5 * pixel));
                tuple.setxMin(0 - (1.5f * pixel));
                break;
            default:
                break;
        }

        return tuple;
    }

}
