package com.lionzxy.betterworkbench.common.item;

import com.lionzxy.betterworkbench.client.handler.GUIHandler;
import net.minecraft.item.Item;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class ItemInit {
    public static Item simplyWorkBench;

    public static boolean init() {
        //GUI ID: 1
        simplyWorkBench = new ItemSimplyWorkBench(GUIHandler.SIMPLY_WORKBENCH);

        return true;
    }
}
