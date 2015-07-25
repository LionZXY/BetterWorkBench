package com.lionzxy.betterworkbench.common.utils;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

/*
 *  Не понимаю смысл этого класса. Разве нельзя использовать ванильные
 *  рецепты?
 *  
 *  @deprecated Ненужный класс?
 */
@Deprecated
public class SimpleWorkbenchRecipe implements IRecipe {

	/*
	 *  Я подумала что собственный подинтерфейс IRecipe не нужен,
	 *  тк можно использовать нативный форджевый IRecipe. Если
	 *  всё-таки понадобится собственный, то исправь.
	 */
	
    @Override
    public int getRecipeSize() {
        return 0;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

	@Override
	public ItemStack getCraftingResult(InventoryCrafting aInventory) {
		return null;
	}

	@Override
	public boolean matches(InventoryCrafting aInventory, World aWorld) {
		return false;
	}
}
