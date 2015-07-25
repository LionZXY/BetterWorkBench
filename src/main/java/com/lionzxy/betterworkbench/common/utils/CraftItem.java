package com.lionzxy.betterworkbench.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class CraftItem {
    public ItemStack itemStack;
    public String oreDictionary;
    CraftItem(String oreDictionary){
       this.oreDictionary=oreDictionary;
    }
    CraftItem(ItemStack itemStack){
        this.itemStack=itemStack;
    }
    boolean check(ItemStack inItemStack){
        if(itemStack!=null){
            return inItemStack.getItem()==itemStack.getItem()&&itemStack.stackSize<=inItemStack.stackSize;
        }else{
            for(int i=0;i<OreDictionary.getOres(oreDictionary).size();i++){
                if(OreDictionary.getOres(oreDictionary).get(i)==inItemStack){return true;}
            }
            return false;
        }
    }

}
