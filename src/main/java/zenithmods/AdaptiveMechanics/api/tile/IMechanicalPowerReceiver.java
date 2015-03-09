package zenithmods.AdaptiveMechanics.api.tile;

import net.minecraft.tileentity.TileEntity;

import java.util.Set;

public interface IMechanicalPowerReceiver {
    public boolean canAcceptPower(Set<TileEntity> source);
}
