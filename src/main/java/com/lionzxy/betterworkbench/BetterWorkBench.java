package com.lionzxy.betterworkbench;

import com.lionzxy.betterworkbench.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by nikit_000 on 24.07.2015.
 */
@Mod(modid = BetterWorkBenchVersion.MODID, name = BetterWorkBenchVersion.NAME,version = BetterWorkBenchVersion.VERSION)
public class BetterWorkBench {

    @SidedProxy(clientSide = "com.lionzxy.betterworkbench.proxy.ClientProxy",serverSide = "com.lionzxy.betterworkbench.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static BetterWorkBench instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.registerProxies();
        BetterWorkBenchBlocks.addBlock();
    }
}
