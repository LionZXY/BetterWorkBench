package com.lionzxy.betterworkbench.common;

import com.lionzxy.betterworkbench.common.item.ItemInit;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class Init {

    public static boolean init() {

        if (!ItemInit.init())
            return false;

        return true;
    }
}
