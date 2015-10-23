package com.lionzxy.betterworkbench.common.item;

import com.lionzxy.betterworkbench.client.handler.GUIHandler;
import com.lionzxy.betterworkbench.common.block.BlockInit;

import net.minecraft.item.Item;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class ItemInit {
    public static Item simplyWorkBench;

    public static void init() {
        //GUI ID: 1
        simplyWorkBench = new ItemSimplyWorkBench(GUIHandler.SIMPLY_WORKBENCH, BlockInit.simplyWorkBench);
    }
}
