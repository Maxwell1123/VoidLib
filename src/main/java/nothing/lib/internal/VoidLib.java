package nothing.lib.internal;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nothing.lib.internal.proxy.IVoidLibProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static nothing.lib.internal.util.ModPrefs.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPS, acceptedMinecraftVersions = ACC_MC)
public class VoidLib {

    @Mod.Instance(value = MODID)
    public static VoidLib INSTANCE = new VoidLib();
    @SidedProxy(clientSide = CSIDE, serverSide = SSIDE)
    public static IVoidLibProxy PROXY;
    public static Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }

}
