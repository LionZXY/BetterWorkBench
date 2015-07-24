package com.lionzxy.betterworkbench.common.utils;

import com.lionzxy.betterworkbench.common.inventory.SimplyInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class IRecipe implements com.lionzxy.betterworkbench.common.interfaceclass.IRecipe {
    @Override
    public boolean matches(SimplyInventory p_77569_1_, World p_77569_2_) {
//To do Я не ебу как это должно работать
        return false;
    }

    @Override
    public ItemStack getCraftingResult(SimplyInventory p_77572_1_) {
        return null;
    }

    @Override
    public int getRecipeSize() {
        return 0;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
