package nothing.lib.internal.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nothing.lib.block.BlockBase;
import nothing.lib.item.ItemBase;

public interface IVoidLibProxy {

    void preInit(FMLPreInitializationEvent event);

    void init(FMLInitializationEvent event);

    void postInit(FMLPostInitializationEvent event);

    void handleBlock(BlockBase block);

    void handleItem(ItemBase item);

}
