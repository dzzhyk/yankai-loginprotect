package com.yankaizhang.loginprotect;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = LoginProtectMod.MODID, name = LoginProtectMod.NAME, version = LoginProtectMod.VERSION)
public class LoginProtectMod {
    public static final String MODID = "yankailoginprotect";
    public static final String NAME = "Yankai Login Protect";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }
}
