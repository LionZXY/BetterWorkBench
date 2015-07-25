package com.lionzxy.betterworkbench.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CraftItem {
	
    public ItemStack mItemStack;
    public String    mOreDictionary;
    
    public CraftItem(String aOreDictionary) {
       this.mOreDictionary = aOreDictionary;
    }
    
    public CraftItem(ItemStack aItemStack){
        this.mItemStack = aItemStack;
    }
    
    boolean check(ItemStack aItemStack) {
        if (mItemStack != null) {
            return aItemStack.getItem() == mItemStack.getItem() && mItemStack.stackSize <= aItemStack.stackSize;
        } else if (mOreDictionary != null && aItemStack != null) {
            return OreDictionary.getOres(mOreDictionary).contains(aItemStack.getItem());
        } else {
            return (aItemStack == null);
        }
    }

}
