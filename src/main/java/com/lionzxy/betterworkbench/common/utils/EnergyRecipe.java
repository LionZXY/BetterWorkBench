package com.lionzxy.betterworkbench.common.utils;

import com.lionzxy.betterworkbench.common.tiles.EnergyWorkbenchTile;
import net.minecraft.item.ItemStack;

/**
 * Created by nikit_000 on 25.07.2015.
 */
public class EnergyRecipe {
    private CraftItem[] recipe;
    int craftHeight;//Высота
    boolean shaples;
    private ItemStack craftOutput;
    EnergyRecipe(ItemStack craftOutput, int craftHeight, boolean shaples, CraftItem... craftItem){
       this.craftHeight=craftHeight;
       this.recipe=craftItem;
       this.shaples=shaples;
       this.craftOutput=craftOutput;
    }
    public ItemStack check(ItemStack[] craftMatrix){
        int craftWidth = recipe.length/craftHeight;//Ширина
        if(shaples){
        switch (craftHeight){
            case 1:
            case 2:
            case 3:{
                switch (craftWidth){
                    case 1:
                    case 2:return checkCraft3x2(craftMatrix);
                    case 3:return checkCraft3x3(craftMatrix);
                }
                break;
            }
        }}else{
            checkShapless(craftMatrix);
        }
        return null;
    }
    ItemStack checkCraft3x3(ItemStack[] craftMatrix){
        for(int i=0;i<9;i++){
            if(!recipe[i].check(craftMatrix[i])){
                return null;
            }
        }
        return craftOutput;
    }
    ItemStack checkCraft3x2(ItemStack[] craftMatrix){
        //Todo
        for(int i=0;i<craftMatrix.length;i++){
            if(!recipe[i].check(craftMatrix[i])){
                return null;
            }
        }
        return craftOutput;
    }
    boolean findItem(CraftItem itemStack,ItemStack[] CraftMatrix){
        for(int i = 0; i<CraftMatrix.length;i++){
            if(itemStack.check(CraftMatrix[i])){return true;}
        }
        return false;
    }
    boolean checkShapless(ItemStack[] CraftMatrix){
        for(int i = 0; i<recipe.length;i++){
            if(!findItem(recipe[i],CraftMatrix)){return false;}
        }
        return true;
    }
}
