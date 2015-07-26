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
            if(listCraft.get(i) != null)listCraft.get(i).check(craftMatrix);
        }
        return null;
    }
    public static void removeRecipe(ItemStack output){
        for(int i = 0; i < listCraft.size(); i++){
            if(listCraft.get(i).getCraftOutput()==output)listCraft.remove(i);

        }
    }
}
