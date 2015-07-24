package com.lionzxy.betterworkbench.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class CraftItem {
    public ItemStack itemStack;
    public OreDictionary oreDictionary;
    public boolean dictionary;
    CraftItem(OreDictionary oreDictionary){
       this.oreDictionary=oreDictionary;
       this.dictionary=true;
    }
    CraftItem(ItemStack itemStack){
        this.itemStack=itemStack;
        this.dictionary=false;
    }

}
