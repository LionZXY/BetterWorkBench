package com.lionzxy.betterworkbench;

import com.lionzxy.betterworkbench.client.ClientInit;
import com.lionzxy.betterworkbench.common.Init;
import com.lionzxy.betterworkbench.utils.Constant;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by LionZXY on 22.10.2015.
 * BetterWorkbench
 */

@Mod(modid = Constant.MODID, name = Constant.NAME, version = Constant.VERSION)
public class BetterWorkbench {

    @Mod.Instance
    private static BetterWorkbench instance = new BetterWorkbench();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Init.init();
        ClientInit.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    public static BetterWorkbench getInstance() {
        return instance;
    }
}
