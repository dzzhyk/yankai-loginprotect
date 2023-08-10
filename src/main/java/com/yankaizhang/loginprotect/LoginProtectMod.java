package com.yankaizhang.loginprotect;

import net.minecraftforge.common.config.Config;
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

    public static Logger LOGGER;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Yankai Login Protect up!");
    }

    @Config(modid = LoginProtectMod.MODID)
    public static class LoginProtectConfig {
        @Config.Comment("Time in ticks the logging player is invulnerable, 20 ticks = 1sec. Default is 30secs = 600 ticks")
        public static int immutableTicks = 600;

        @Config.Comment("Max distance in blocks(2d) the invulnerability lasts, default: 10")
        public static int maxDist = 10;
    }

}
