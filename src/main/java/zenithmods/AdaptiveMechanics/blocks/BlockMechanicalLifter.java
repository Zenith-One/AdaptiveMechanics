package zenithmods.AdaptiveMechanics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zenithmods.AdaptiveMechanics.AdaptiveMechanics;
import zenithmods.AdaptiveMechanics.api.block.BlockAdaptiveMachine;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineTransmitter;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.AMRenderingRegister;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalLifter;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalReceiver;
import zenithmods.AdaptiveMechanics.utility.BlockHelper;

public class BlockMechanicalLifter extends BlockAdaptiveMachine {

    public static final String NAME = "blockMechanicalLifter";

    public BlockMechanicalLifter(){
        super(Material.rock);
        setBlockName(Constants.MOD_ID + ":" + NAME);
        setCreativeTab(AdaptiveMechanics.tab);
   }

   @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityMechanicalLifter();
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess iba, int x, int y, int z, int meta){
        return false;
    }

    @Override
    public int getRenderType() {
        return AMRenderingRegister.mechanicalLifterRenderID;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int meta = BlockHelper.getMetaFromPlacement(entity);
        world.setBlockMetadataWithNotify(x, y, z, meta, 2);
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        this.blockIcon = ir.registerIcon(Constants.MOD_ID + ":" + "blockMechanicalStone");
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
        TileEntityMechanicalLifter tileEntityMechanicalLifter = (TileEntityMechanicalLifter) par1World.getTileEntity(par2, par3, par4);
        if (tileEntityMechanicalLifter.getWorldObj().isRemote){
            System.out.println("Lifter meta: " + tileEntityMechanicalLifter.getBlockMetadata());
            System.out.println("Below needs rotation? " + ((IAdaptiveMachineTransmitter) tileEntityMechanicalLifter.getTileEntityBelow()).needsInputRotation());
        }
        return true;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
        float pixel = 1f / 16f;
        return AxisAlignedBB.getBoundingBox(i + pixel , j, k + pixel, i + 1.0F - pixel, j + 1.0F, k + 1.0F - pixel);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess, int i, int j, int k) {
        float pixel = 1f / 16f;
        setBlockBounds(pixel , 0, pixel, 1.0F - pixel, 1.0F, 1.0F - pixel);
        super.setBlockBoundsBasedOnState(par1iBlockAccess, i, j, k);
    }
}
