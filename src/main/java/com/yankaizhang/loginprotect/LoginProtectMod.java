package com.yankaizhang.loginprotect;

import com.yankaizhang.loginprotect.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = LoginProtectMod.MODID, name = LoginProtectMod.NAME, version = LoginProtectMod.VERSION)
public class LoginProtectMod {
    public static final String MODID = "yankailoginprotect";
    public static final String NAME = "Yankai Login Protect";
    public static final String VERSION = "1.0";

    public static Logger LOGGER;

    @SidedProxy(
            serverSide = "com.yankaizhang.loginprotect.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Yankai Login Protect up!");
    }
}
