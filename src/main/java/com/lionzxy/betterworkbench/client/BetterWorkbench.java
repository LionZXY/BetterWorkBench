package com.lionzxy.betterworkbench.client;

import com.lionzxy.betterworkbench.client.utils.Constant;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by LionZXY on 22.10.2015.
 * BetterWorkbench
 */

@Mod(modid = Constant.MODID, name = Constant.NAME, version = Constant.VERSION)
public class BetterWorkbench {

    @Mod.Instance
    public static BetterWorkbench instance = new BetterWorkbench();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

    }
}
