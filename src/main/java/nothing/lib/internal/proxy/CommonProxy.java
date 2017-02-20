package nothing.lib.internal.proxy;

import codechicken.lib.packet.PacketCustom;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nothing.lib.block.BlockBase;
import nothing.lib.internal.VoidLib;
import nothing.lib.internal.network.VoidLibSPH;
import nothing.lib.item.ItemBase;

public class CommonProxy implements IVoidLibProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {
        PacketCustom.assignHandler(VoidLib.INSTANCE, new VoidLibSPH());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void handleBlock(BlockBase block) {

    }

    @Override
    public void handleItem(ItemBase item) {

    }

}
