package com.yankaizhang.loginprotect;

import com.yankaizhang.loginprotect.event.PlayerLoginEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = LoginProtectMod.MODID, name = LoginProtectMod.NAME, version = LoginProtectMod.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class LoginProtectMod {
    public static final String MODID = "yankailoginprotect";
    public static final String NAME = "Yankai Login Protect";
    public static final String VERSION = "1.1";

    public static Logger LOGGER;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(PlayerLoginEventHandler.class);
        LOGGER.info(String.format("Yankai Login Protect (version %s) up!", VERSION));
    }

    @Config(modid = LoginProtectMod.MODID)
    public static class LoginProtectConfig {
        @Config.Comment("Seconds the logging player is invulnerable")
        public static int immutableSeconds = 30;

        @Config.Comment("Max distance in blocks(2d) the invulnerability lasts, default: 10")
        public static int maxDist = 10;

        @Config.Comment("Prevent knock back during protect")
        public static boolean preventKnockBack = true;

        @Config.Comment("Show command message during protection")
        public static boolean showProtectNote = true;

    }

}
