package com.lionzxy.betterworkbench.common.interfaceclass;

import com.lionzxy.betterworkbench.common.inventory.SimplyInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public interface IRecipe {
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    boolean matches(SimplyInventory p_77569_1_, World p_77569_2_);

    /**
     * Returns an Item that is the result of this recipe
     */
    ItemStack getCraftingResult(SimplyInventory p_77572_1_);

    /**
     * Returns the size of the recipe area
     */
    int getRecipeSize();

    ItemStack getRecipeOutput();
}
