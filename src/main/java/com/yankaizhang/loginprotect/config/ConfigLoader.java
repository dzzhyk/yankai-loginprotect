package com.yankaizhang.loginprotect.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public class ConfigLoader {
    private static Configuration config;
    private static Logger logger;

    public static int immutableTicks;
    public static int maxDist;

    public ConfigLoader(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        load();
    }

    public static void load() {
        logger.info("Started loading config. ");
        immutableTicks = config.get(Configuration.CATEGORY_GENERAL, "immutableTicks", 600, "Time in ticks the logging player is invulnerable, 20 ticks = 1sec. Default is 30secs = 600 ticks").getInt();
        maxDist = config.get(Configuration.CATEGORY_GENERAL, "maxDist", 10, "Max distance in blocks(2d) the invulnerability lasts, default: 10").getInt();
        config.save();
        logger.info("Finished loading yankai login protect config. ");
    }

    public static Logger logger() {
        return logger;
    }
}
