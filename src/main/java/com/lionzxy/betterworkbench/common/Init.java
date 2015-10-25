package com.lionzxy.betterworkbench.common;

import com.lionzxy.betterworkbench.common.block.BlockInit;
import com.lionzxy.betterworkbench.common.item.ItemInit;
import com.lionzxy.betterworkbench.tileentity.SimplyTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class Init {

    public static void init() {
        BlockInit.init();
        ItemInit.init();
        registerTEs();
    }

    public static void registerTEs(){
        GameRegistry.registerTileEntity(SimplyTileEntity.class, "simplytileentity");
    }
}
