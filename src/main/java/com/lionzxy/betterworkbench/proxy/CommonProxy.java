package com.lionzxy.betterworkbench.proxy;

import com.lionzxy.betterworkbench.BetterWorkBench;
import com.lionzxy.betterworkbench.BetterWorkBenchGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class CommonProxy {
    public void registerProxies(){
        NetworkRegistry.INSTANCE.registerGuiHandler(BetterWorkBench.instance,new BetterWorkBenchGuiHandler());
    }
}
