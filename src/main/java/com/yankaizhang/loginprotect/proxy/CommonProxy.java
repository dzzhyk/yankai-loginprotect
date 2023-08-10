package com.yankaizhang.loginprotect.proxy;

import com.yankaizhang.loginprotect.config.ConfigLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public CommonProxy() {
    }

    public void preInit() {
    }

    public void preInit(FMLPreInitializationEvent event) {
        new ConfigLoader(event);
    }
}
