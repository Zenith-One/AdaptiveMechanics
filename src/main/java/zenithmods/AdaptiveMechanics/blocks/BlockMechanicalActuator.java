package zenithmods.AdaptiveMechanics.blocks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.util.ForgeDirection;
import zenithmods.AdaptiveMechanics.AdaptiveMechanics;
import zenithmods.AdaptiveMechanics.api.block.BlockAdaptiveMachine;
import zenithmods.AdaptiveMechanics.api.block.IBlockAdaptiveMachineReceiver;
import zenithmods.AdaptiveMechanics.api.item.IItemGearbox;
import zenithmods.AdaptiveMechanics.api.tile.IAdaptiveMachineReceiver;
import zenithmods.AdaptiveMechanics.api.tile.ICustomRaytrace;
import zenithmods.AdaptiveMechanics.codechicken.lib.raytracer.IndexedCuboid6;
import zenithmods.AdaptiveMechanics.codechicken.lib.raytracer.RayTracer;
import zenithmods.AdaptiveMechanics.codechicken.lib.vec.BlockCoord;
import zenithmods.AdaptiveMechanics.codechicken.lib.vec.Cuboid6;
import zenithmods.AdaptiveMechanics.codechicken.lib.vec.Vector3;
import zenithmods.AdaptiveMechanics.lib.Constants;
import zenithmods.AdaptiveMechanics.render.AMRenderingRegister;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalActuator;
import zenithmods.AdaptiveMechanics.tile.TileEntityMechanicalReceiver;
import zenithmods.AdaptiveMechanics.utility.AMMathHelper;
import zenithmods.AdaptiveMechanics.utility.BlockHelper;
import zenithmods.AdaptiveMechanics.utility.DebugHelper;
import zenithmods.AdaptiveMechanics.utility.ItemHelper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BlockMechanicalActuator extends BlockAdaptiveMachine implements IBlockAdaptiveMachineReceiver{

    public static final String NAME = "blockMechanicalActuator";
    private RayTracer rayTracer = new RayTracer();

    public BlockMechanicalActuator(){
        super(Material.rock);
        setBlockName(Constants.MOD_ID + ":" + NAME);
        setCreativeTab(AdaptiveMechanics.tab);
    }

    @Override
    public boolean canRenderInPass(int pass) {
        return pass == 0;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityMechanicalActuator();
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
    public int getRenderType() {
        return AMRenderingRegister.mechanicalReceiverRenderID;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side != ForgeDirection.DOWN && side != ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)).getOpposite();
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x1, float y1, float z1) {
        boolean isActivated = true;
        TileEntityMechanicalActuator actuator = (TileEntityMechanicalActuator) world.getTileEntity(x, y, z);
        DebugHelper.clientPrint(world, "meta: " + world.getBlockMetadata(x, y, z) + ", connected: " + actuator.isConnectedToLifter());
        DebugHelper.clientPrint(world, x1 + ", " + y1 + ", " + z1);
        if (player.isSneaking()){
            actuator.activate();
        }
/*        if ( side == ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)).getRotation(ForgeDirection.DOWN).ordinal() &&
                y1 < 0.88f && y1 > 0.62f ){
            if (! receiver.getWorldObj().isRemote){
                ItemStack stack = player.getHeldItem();
                if (stack == null ) {
                    if (player.isSneaking()){
                        isActivated =  receiver.uninstallGearbox(player);
                    }
                } else if (stack.getItem() instanceof IItemGearbox){
                    ItemStack result = receiver.installGearbox(player.getHeldItem());
                    if (!player.capabilities.isCreativeMode) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, result);
                    }
                } else {
                    isActivated = false;
                }
            }
        } else {
            isActivated = false;
        }

        return isActivated;*/
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta){
        TileEntityMechanicalActuator actuator = (TileEntityMechanicalActuator) world.getTileEntity(x, y, z);
        if (actuator != null){
            if (!world.isRemote && actuator.getToolHead() != null){
                ItemHelper.spawnEntityWithRandomMotion(world, actuator.getToolHeadStack(), x, y, z);
            }
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        Cuboid6 c = getMainBoxCuboidWithOffset(x, y, z);
        return AxisAlignedBB.getBoundingBox(c.min.x, c.min.y, c.min.z, c.max.x, c.max.y, c.max.z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setBlockBoundsBasedOnState(IBlockAccess iba, int x, int y, int z) {
        Minecraft mc = Minecraft.getMinecraft();

        EntityPlayer player = mc.thePlayer;
        TileEntity te = iba.getTileEntity(x, y, z);

        if (te != null && te instanceof TileEntityMechanicalActuator){
            TileEntityMechanicalActuator actuator = (TileEntityMechanicalActuator) te;
            if (shouldUseCustomRaytrace(actuator)){
                return;
            }
        }

        // fallthrough default behavior
        Cuboid6 c = getMainBoxCuboid();
        setBlockBounds((float) c.min.x, (float) c.min.y, (float) c.min.z, (float) c.max.x, (float) c.max.y, (float) c.max.z);
        super.setBlockBoundsBasedOnState(iba, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onBlockHighlight(DrawBlockHighlightEvent event) {
        Item equipped = event.player.getCurrentEquippedItem() != null ? event.player.getCurrentEquippedItem().getItem() : null;
        if (event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && event.player.worldObj.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ) instanceof BlockMechanicalReceiver){
            int x = event.target.blockX;
            int y = event.target.blockY;
            int z = event.target.blockZ;
            TileEntity te = event.player.worldObj.getTileEntity(x, y, z);
            if (te != null && te instanceof TileEntityMechanicalActuator){
                TileEntityMechanicalActuator actuator = (TileEntityMechanicalActuator) te;
                if (shouldUseCustomRaytrace(actuator)){
                    RayTracer.retraceBlock(event.player.worldObj, event.player, event.target.blockX, event.target.blockY, event.target.blockZ);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static Cuboid6 getMainBoxCuboid(){
        float pixel = 1f / 16f;
        return new Cuboid6(pixel , 5.0F * pixel, pixel, 1.0F - pixel, 1.0F, 1.0F - pixel);
    }

    public static Cuboid6 getMainBoxCuboidWithOffset(int x, int y, int z){
        float pixel = 1f / 16f;
        return new Cuboid6(x + pixel , y + 5.0F * pixel, z + pixel, x + 1.0F - pixel, y + 1.0F, z + 1.0F - pixel);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        Item equipped = player.getCurrentEquippedItem() != null ? player.getCurrentEquippedItem().getItem() : null;
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityMechanicalActuator){
            TileEntityMechanicalActuator actuator = (TileEntityMechanicalActuator) tile;
            boolean actuatorHasSmashingHead = false; //actuator.getGearbox() != null;

            ItemStack stack = player.getCurrentEquippedItem();
            Item item = stack == null? null : stack.getItem();


            if ( shouldUseCustomRaytrace(actuator) ){

                List<IndexedCuboid6> cuboids = ((ICustomRaytrace) tile).getTraceableCuboids();
                if (cuboids != null){
                    return this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(x, y, z), this);
                }
            }

        }
        return super.collisionRayTrace(world, x, y, z, start, end);
    }



    private boolean shouldUseCustomRaytrace(TileEntityMechanicalActuator actuator){
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        ItemStack stack = player.getCurrentEquippedItem();
        Item equipped = stack != null ? stack.getItem() : null;
        boolean isRemovable = false; //actuator.hasGearbox() && equipped == null;
        boolean isInsertable = false; //!(actuator.hasGearbox()) && equipped instanceof IItemGearbox;
        return true;//( isRemovable || isInsertable );
    }
}
