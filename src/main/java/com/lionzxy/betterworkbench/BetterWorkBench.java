package com.lionzxy.betterworkbench;

import com.lionzxy.betterworkbench.proxy.ProxyCommon;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


/**
 * Created by nikit_000 on 24.07.2015.
 */
@Mod(modid = BetterWorkBenchVersion.MODID, name = BetterWorkBenchVersion.NAME, version = BetterWorkBenchVersion.VERSION)
public class BetterWorkBench {

    @SidedProxy(clientSide = "com.lionzxy.betterworkbench.proxy.ProxyClient", serverSide = "com.lionzxy.betterworkbench.proxy.ProxyServer")
    public static ProxyCommon proxy;

    @Mod.Instance
    public static BetterWorkBench INSTANCE;

    @EventHandler
    public void preInit(FMLPreInitializationEvent aEvent){
        proxy.preInit(aEvent);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent aEvent) {
    	proxy.init(aEvent);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent aEvent) {
    	proxy.postInit(aEvent);
    }
}
