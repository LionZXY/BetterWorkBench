package com.lionzxy.betterworkbench.common.listcraft;

import com.lionzxy.betterworkbench.common.inventory.SimplyInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class VanillaCraft {
    private static VanillaCraft instance = new VanillaCraft();
    public ItemStack findMatchingRecipe(SimplyInventory inventory, World world)
    {
        IRecipe irecipe = (IRecipe)this.getList().get(1);
        System.out.print(irecipe.getRecipeOutput().getDisplayName()+" for "+irecipe.getRecipeSize());
        /*for(int i=0;i<getList().size();i++){
            IRecipe irecipe = (IRecipe)this.getList().get(i);
            if(irecipe.getRecipeSize()==)
        }*/
       return null;
    }
    public static VanillaCraft getInstance(){
        return instance;
    }
    public List getList(){
        return CraftingManager.getInstance().getRecipeList();
    }

}
