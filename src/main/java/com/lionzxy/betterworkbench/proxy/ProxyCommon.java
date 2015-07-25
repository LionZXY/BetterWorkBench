package com.lionzxy.betterworkbench.proxy;

import com.lionzxy.betterworkbench.BetterWorkBench;
import com.lionzxy.betterworkbench.BetterWorkBenchGuiHandler;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ProxyCommon {

	public void preInit(FMLPreInitializationEvent aEvent) {
		NetworkRegistry.INSTANCE.registerGuiHandler(BetterWorkBench.INSTANCE, new BetterWorkBenchGuiHandler());
	}
	
	public void init(FMLInitializationEvent aEvent) {
		
	}
	
	public void postInit(FMLPostInitializationEvent aEvent) {
		
	}
	
}
