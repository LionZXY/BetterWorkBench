package com.lionzxy.betterworkbench.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CraftItem {
	
    public ItemStack mStack;
    public String 	 mDictionaryEntry;
    
    public CraftItem(String aDictionaryEntry) {
       this.mStack = null;
       this.mDictionaryEntry = aDictionaryEntry;
    }
    
    public CraftItem(ItemStack aItemStack) {
    	this.mStack = aItemStack;
        this.mDictionaryEntry = null;
    }
    
    boolean check(ItemStack aStack) {
        if(mStack != null){
            return mStack == aStack;
        } else {
        	return OreDictionary.getOres(mDictionaryEntry).contains(aStack);
        }
    }

}
