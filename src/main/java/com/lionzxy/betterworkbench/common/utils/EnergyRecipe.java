package com.lionzxy.betterworkbench.common.utils;

import net.minecraft.item.ItemStack;

public class EnergyRecipe {
    private CraftItem[][] recipe;
    private boolean shaples;
    private ItemStack craftOutput;
    EnergyRecipe(ItemStack craftOutput, boolean shaples, CraftItem[]... craftItem){
        this.recipe=craftItem;//����������� ������ ��������.
        this.shaples=shaples;//����� ����������� ��� �������. ����� ������ ����� ��� ������������
        this.craftOutput=craftOutput;
    }

    public ItemStack check(ItemStack[] craftMatrix){
        if(shaples) checkShapless(craftMatrix);
        else checkCraft(craftMatrix);
        return null;
    }

    //��������� �� ������������� ��� ������ ������ �������� ������ ��������� �����, ������� �� ������ �����������
    public ItemStack checkCraft(ItemStack[] craftMatrix){
        return checkCraftWithNoItems(craftMatrix,-1);
    }

    //�������� ��������� ������� ������. ���������� �� ���� ������ ��������� ������ ����� ��������� �����, ����� �������������� ������
    //����� ���� ��������� ������������� �������� � ���������� ����������, ����� �������� ��������, ������� ��������� ������ ������ ����
    //��� ������ ��� ��� ������ ��� ��� ������ �� ������ :) �������� ������������, ����� ���� null, ���� ����� �������
    public ItemStack checkCraftWithNoItems(ItemStack[] craftMatrix, int noItem){
        int start = checkFirstItemForCraft(craftMatrix, noItem);
        if(start > 0) if(checkAllCraft(craftMatrix, start)) return craftOutput;
        else return checkCraftWithNoItems(craftMatrix, noItem);//����� ��������
        else {return null;}
    }

    //�������� ����� ������, ������� � ��������� ������� (int start). ��� ������ ���� �� ��������� �� ���������, �������� � return false
    public boolean checkAllCraft(ItemStack[] craftMatrix, int start){
        if(start > -1) {
            for (int i = 0; i < recipe[0].length; i++)
                for (int j = 0; j < recipe.length; j++)
                    if (!recipe[j][i].check(craftMatrix[start + j + i * 3])) return false;

            return true;
        }
        return false;
    }

    //����� ������� �������� � �������� � ������������� ��� ������� ������
    public int checkFirstItemForCraft(ItemStack[] craftMatrix, int noEnterNumber){
        for(int j = 0; j < 3 - recipe.length; j++)
            for(int i = 0 ; i < 3 - recipe[0].length; i++)
                if(recipe[0][0].check(craftMatrix[i + j * 3]) && i + j * 3 != noEnterNumber)return i + j * 3;

        return -1;//������ ������ "null". ������ ������ �� ����� ���� �������������
    }

    //����� �������� � ��������
    boolean findItem(CraftItem itemStack, ItemStack[] CraftMatrix){
        for(int i = 0; i<CraftMatrix.length;i++)
            if(itemStack.check(CraftMatrix[i]))return true;//�������

        return false;//�����������, ���� �� ������ �������
    }

    //���� ������ ������������, �� ���� ���� �������� �� ������� ���� ��������� � ��������
    boolean checkShapless(ItemStack[] CraftMatrix){
        for(int j = 0; j < recipe[0].length; j++)
            for(int i = 0; i < recipe.length; i++)
                if(!findItem(recipe[i][j], CraftMatrix)) return false; //��� ������ �� ������� ������ �������, ��������.

        return true;
    }
    public ItemStack getCraftOutput(){
        return craftOutput;
    }
}
