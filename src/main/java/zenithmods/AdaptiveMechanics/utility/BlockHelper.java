package zenithmods.AdaptiveMechanics.utility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zenithmods.AdaptiveMechanics.codechicken.lib.raytracer.RayTracer;

public class BlockHelper {

    @SideOnly(Side.CLIENT)
    public static RayTracer rayTracer = new RayTracer();

    public static int getMetaFromPlacement(EntityLivingBase entity){
        int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            return 2;
        }

        if (l == 1) {
            return 5;
        }

        if (l == 2) {
            return 3;
        }

        if (l == 3) {
            return 4;
        }

        return 0;
    }
}
