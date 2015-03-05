package zenithmods.AdaptiveMechanics.utility;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

public class ItemHelper {

    public static void spawnEntityItemAtPlayerFeet(World world, EntityPlayer player, ItemStack stack) {
        EntityItem entityitem = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(stack.getItem(), stack.stackSize, stack.getItemDamage()));

        if (stack.hasTagCompound()) {
            entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
        }

        world.spawnEntityInWorld(entityitem);
    }

    public static void spawnEntityWithRandomMotion(World world, ItemStack stack, int x, int y, int z){
        Random rand = AMMathHelper.getRandom();

        if (stack != null) {
            float f = rand.nextFloat() * 0.8F + 0.1F;
            float f1 = rand.nextFloat() * 0.8F + 0.1F;
            float f2 = rand.nextFloat() * 0.8F + 0.1F;

            while (stack.stackSize > 0) {
                int j1 = rand.nextInt(21) + 10;

                if (j1 > stack.stackSize) {
                    j1 = stack.stackSize;
                }

                stack.stackSize -= j1;
                EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(stack.getItem(), j1, stack.getItemDamage()));

                if (stack.hasTagCompound()) {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (double) ((float) rand.nextGaussian() * f3);
                entityitem.motionY = (double) ((float) rand.nextGaussian() * f3 + 0.2F);
                entityitem.motionZ = (double) ((float) rand.nextGaussian() * f3);
                world.spawnEntityInWorld(entityitem);
            }
        }
    }




}
