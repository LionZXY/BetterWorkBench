package com.lionzxy.betterworkbench.common.inventory.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created by LionZXY on 22.10.2015.
 * BetterWorkbench
 */
public abstract class WorkBenchInventory implements IInventory {

    int craftSlot;

    public ItemStack items[];

    private ItemStack mainItemStack;
    //Используется для крафта
    private EntityPlayer player;


    public WorkBenchInventory(ItemStack itemStack, EntityPlayer player) {

        this.mainItemStack = itemStack;
        this.player = player;

        if (!mainItemStack.hasTagCompound())
            mainItemStack.setTagCompound(new NBTTagCompound());

        this.readToNBT(mainItemStack.getTagCompound());

        items = new ItemStack[getSizeInventory()];
    }

    @Override
    public abstract int getSizeInventory();

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < getSizeInventory())
            return items[slot];
        else return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        //Когда игрок берет предмет
        ItemStack stack = getStackInSlot(slot);
        //Если что-то есть, то сносим нафиг
        if (stack != null) {
            //Чисто теоритически наврядли, но условие стоит. Пусть будет.
            if (stack.stackSize > amount) {
                stack = stack.splitStack(amount);
                markDirty();
            } else {
                setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return getStackInSlot(slot);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        this.items[slot] = itemStack;
        //Проверка на адекватность блока
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        markDirty();
    }

    @Override
    public abstract String getInventoryName();

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {
        //Вызывается при любом изменении инвентаря. В будущем будет проверка на крафт
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
                items[i] = null;
        }
        //Проверка на крафт
        checkToCraft();

        writeToNBT(mainItemStack.getTagCompound());
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
        //На открытие инвентаря вытаскиваем всё
        readToNBT(mainItemStack.getTagCompound());
    }

    @Override
    public void closeInventory() {
        //На закрытие инвентаря сохраняем всё
        writeToNBT(mainItemStack.getTagCompound());
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        //Если это слот выхода рецепта, то нельзя ставить
        if (slot != craftSlot)
            return true;
        else return false;
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        //Вся информация будет храниться в одном листе для упрощения переброски тегов между блоком-игроком-предметом
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound tagCompound1 = new NBTTagCompound();
        //Предметы в отдельном листе
        NBTTagList itemsList = new NBTTagList();
        //Каждый слог это свой тег. В нем содержиться itemstack и номер слота.
        NBTTagCompound slotTag = new NBTTagCompound();
        //Цикл записи всех предметов в NBT
        for (byte i = 0; i < getSizeInventory(); i++) {
            if (items[i] != null) {
                //Один байт лучше четырех
                slotTag.setByte("Slot", i);
                items[i].writeToNBT(slotTag);
                itemsList.appendTag(slotTag);
            }
        }
        tagCompound1.setTag("Items", itemsList);
        tagList.appendTag(tagCompound1);
        tagCompound.setTag("WorkBench", tagList);
    }

    public void readToNBT(NBTTagCompound tagCompound) {
        //Выдергиваем лист.
        NBTTagList itemsList = tagCompound.getTagList("WorkBench", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND)
                //Предпологается, что Items всегда на первом месте
                .getCompoundTagAt(0).getTagList("Items", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);

        //Цикл записи всех предметов в NBT
        for (byte i = 0; i < itemsList.tagCount(); i++) {
            //Каждый слог это свой тег. В нем содержиться itemstack и номер слота.
            NBTTagCompound item = itemsList.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");

            // Двойная проверка на адекватность слота
            if (slot >= 0 && slot < getSizeInventory()) {
                items[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }

    }

    public abstract boolean checkToCraft();
}
