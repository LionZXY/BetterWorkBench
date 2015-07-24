package com.lionzxy.betterworkbench.common.listcraft;

import com.lionzxy.betterworkbench.common.inventory.SimplyInventory;
import com.lionzxy.betterworkbench.common.utils.IRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class VanillaCraft {
    private static VanillaCraft instance = new VanillaCraft();
    public ItemStack findMatchingRecipe(SimplyInventory p_82787_1_, World p_82787_2_)
    {
        int i = 0;
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;
        int j;

        for (j = 0; j < p_82787_1_.getSizeInventory(); ++j)
        {
            ItemStack itemstack2 = p_82787_1_.getStackInSlot(j);

            if (itemstack2 != null)
            {
                if (i == 0)
                {
                    itemstack = itemstack2;
                }

                if (i == 1)
                {
                    itemstack1 = itemstack2;
                }

                ++i;
            }
        }

        if (i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && itemstack1.stackSize == 1 && itemstack.getItem().isRepairable())
        {
            Item item = itemstack.getItem();
            int j1 = item.getMaxDamage() - itemstack.getItemDamageForDisplay();
            int k = item.getMaxDamage() - itemstack1.getItemDamageForDisplay();
            int l = j1 + k + item.getMaxDamage() * 5 / 100;
            int i1 = item.getMaxDamage() - l;

            if (i1 < 0)
            {
                i1 = 0;
            }

            return new ItemStack(itemstack.getItem(), 1, i1);
        }
        else
        {
            for (j = 0; j < this.getList().size(); ++j)
            {
                IRecipe irecipe = (IRecipe)this.getList().get(j);

                if (irecipe.matches(p_82787_1_, p_82787_2_))
                {
                    return irecipe.getCraftingResult(p_82787_1_);
                }
            }

            return null;
        }
    }
    public static VanillaCraft getInstance(){
        return instance;
    }
    public List getList(){
        return CraftingManager.getInstance().getRecipeList();
    }

}
