package zenithmods.AdaptiveMechanics.items;

import net.minecraft.item.Item;

public class AMItems {

    public static Item itemGear;
    public static Item itemGearboxCrankSocket;

    public static void registerItems(){

        itemGear = new ItemGear();
        itemGearboxCrankSocket = new ItemGearboxCrankSocket();

    }
}
