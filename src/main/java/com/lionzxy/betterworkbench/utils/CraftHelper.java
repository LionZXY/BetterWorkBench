package com.lionzxy.betterworkbench.utils;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by LionZXY on 22.10.2015.
 * BetterWorkbench
 */
public class CraftHelper {

    public static ItemStack getCraftResult(InventoryCrafting ic, World world) {
        for (IRecipe recipe : (ArrayList<IRecipe>) CraftingManager.getInstance().getRecipeList()) {
            if (recipe.matches(ic, world))
                return getCraftResult(ic, world);
        }
        return null;
    }
}
