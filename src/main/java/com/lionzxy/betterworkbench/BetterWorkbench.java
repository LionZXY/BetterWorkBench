package com.lionzxy.betterworkbench;

import com.lionzxy.betterworkbench.proxy.ProxyCommon;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = BetterWorkbenchVersion.MODID, name = BetterWorkbenchVersion.NAME, version = BetterWorkbenchVersion.VERSION)
public class BetterWorkbench {

    @SidedProxy(clientSide = "com.lionzxy.betterworkbench.proxy.ProxyClient", serverSide = "com.lionzxy.betterworkbench.proxy.ProxyServer")
    public static ProxyCommon proxy;

    @Mod.Instance
    public static BetterWorkbench INSTANCE;

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
