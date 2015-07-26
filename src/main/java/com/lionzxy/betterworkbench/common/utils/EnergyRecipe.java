package com.lionzxy.betterworkbench.common.utils;

import net.minecraft.item.ItemStack;

public class EnergyRecipe {
    private CraftItem[][] recipe;
    private boolean shaples;
    private ItemStack craftOutput;
    EnergyRecipe(ItemStack craftOutput, boolean shaples, CraftItem[]... craftItem){
        this.recipe=craftItem;//Заполняется массив массивов.
        this.shaples=shaples;//Сразу указывается тип рецепта. Имеет четкую форму или бесформенный
        this.craftOutput=craftOutput;
    }

    public ItemStack check(ItemStack[] craftMatrix){
        if(shaples) checkShapless(craftMatrix);
        else checkCraft(craftMatrix);
        return null;
    }

    //Избавляет от необходимости при первом вызове проверки крафта указывать число, которое не должно учитываться
    public ItemStack checkCraft(ItemStack[] craftMatrix){
        return checkCraftWithNoItems(craftMatrix,-1);
    }

    //Начинаем проверять предмет крафта. Изначально мы ищем первый компонент крафта среди крафтовой сетки, чтобы оптимизировать задачу
    //Чтобы было возможным использование рецептов с одинковыми предметами, здесь введенна рекурсия, которая проверяет каждый первый итем
    //Как только она его найдет или как только не найдет :) Рекурсия остановиться, выдав либо null, либо выход рецепты
    public ItemStack checkCraftWithNoItems(ItemStack[] craftMatrix, int noItem){
        int start = checkFirstItemForCraft(craftMatrix, noItem);
        if(start > 0) if(checkAllCraft(craftMatrix, start)) return craftOutput;
        else return checkCraftWithNoItems(craftMatrix, noItem);//Вызов рекурсии
        else {return null;}
    }

    //Проверка всего крафта, начиная с стартовой позиции (int start). Как только один из предметов не совпадает, вылетает с return false
    public boolean checkAllCraft(ItemStack[] craftMatrix, int start){
        if(start > -1) {
            for (int i = 0; i < recipe[0].length; i++)
                for (int j = 0; j < recipe.length; j++)
                    if (!recipe[j][i].check(craftMatrix[start + j + i * 3])) return false;

            return true;
        }
        return false;
    }

    //Поиск первого предмета в верстаке и выпульнивание его первого номера
    public int checkFirstItemForCraft(ItemStack[] craftMatrix, int noEnterNumber){
        for(int j = 0; j < 3 - recipe.length; j++)
            for(int i = 0 ; i < 3 - recipe[0].length; i++)
                if(recipe[0][0].check(craftMatrix[i + j * 3]) && i + j * 3 != noEnterNumber)return i + j * 3;

        return -1;//Эдакий аналог "null". Ячейка крафта не может быть отрицательной
    }

    //Поиск предмета в верстаке
    boolean findItem(CraftItem itemStack, ItemStack[] CraftMatrix){
        for(int i = 0; i<CraftMatrix.length;i++)
            if(itemStack.check(CraftMatrix[i]))return true;//Находит

        return false;//Выполняется, если не найдет предмет
    }

    //Если рецепт бесформенный, то тупо идет проверка на наличие всех предметов в верстаке
    boolean checkShapless(ItemStack[] CraftMatrix){
        for(int j = 0; j < recipe[0].length; j++)
            for(int i = 0; i < recipe.length; i++)
                if(!findItem(recipe[i][j], CraftMatrix)) return false; //Как только не находит данный предмет, вылетает.

        return true;
    }
    public ItemStack getCraftOutput(){
        return craftOutput;
    }
}
