package com.lionzxy.betterworkbench.common.listcraft;

import com.lionzxy.betterworkbench.common.utils.CraftItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class EnergyCraft {
    private static List<CraftItem> listCraft = new ArrayList<CraftItem>();



    public static List getRecipeList(){
        return listCraft;
    }
}
