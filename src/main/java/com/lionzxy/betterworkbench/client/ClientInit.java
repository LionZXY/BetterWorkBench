package com.lionzxy.betterworkbench.client;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.client.handler.GUIHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class ClientInit {


    public static boolean init() {

        NetworkRegistry.INSTANCE.registerGuiHandler(BetterWorkbench.getInstance(), new GUIHandler());

        return true;
    }
}
