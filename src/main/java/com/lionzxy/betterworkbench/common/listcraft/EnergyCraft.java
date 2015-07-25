package com.lionzxy.betterworkbench.common.listcraft;

import com.lionzxy.betterworkbench.common.utils.EnergyRecipe;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class EnergyCraft {
    private static List<EnergyRecipe> listCraft = new ArrayList<EnergyRecipe>();



    public static List getRecipeList(){
        return listCraft;
    }
    public static ItemStack checkToCraft(ItemStack[] craftMatrix){
        for(int i =0;i<listCraft.size();i++){
            listCraft.get(i).check(craftMatrix);
        }
        return null;
    }
}
