package com.lionzxy.betterworkbench.tileentity.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by LionZXY on 24.10.2015.
 * BetterWorkbench
 */
public abstract class BaseTileEntity extends TileEntity implements IInventory {
    int craftSlot;

    public ItemStack[] inventory = new ItemStack[10];


    public BaseTileEntity() {
        super();
        //this.inventory = new ItemStack[getSizeInventory()];
    }

    @Override
    public abstract int getSizeInventory();

    @Override
    public ItemStack getStackInSlot(int slot) {
        //if(inventory[slot] != null)
        //    System.out.println(inventory[slot]);
        return inventory[slot];
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

        this.inventory[slot] = itemStack;
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
        return 1024;
    }

    @Override
    public void markDirty() {
        //Вызывается при любом изменении инвентаря. В будущем будет проверка на крафт
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
                inventory[i] = null;
        }
        //Проверка на крафт
        checkToCraft();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
        //На открытие инвентаря вытаскиваем всё
    }
    @Override
    public void closeInventory() {
        //На закрытие инвентаря сохраняем всё}
    }
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        //Если это слот выхода рецепта, то нельзя ставить
        if (slot != craftSlot)
            return true;
        else return false;
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        //Вся информация будет храниться в одном листе для упрощения переброски тегов между блоком-игроком-предметом
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound tagCompound1 = new NBTTagCompound();
        //Предметы в отдельном листе
        NBTTagList itemsList = new NBTTagList();
        //Каждый слог это свой тег. В нем содержиться itemstack и номер слота.
        NBTTagCompound slotTag = new NBTTagCompound();
        //Цикл записи всех предметов в NBT
        for (byte i = 0; i < getSizeInventory(); i++) {
            if (inventory[i] != null) {
                //Один байт лучше четырех
                slotTag.setByte("Slot", i);
                inventory[i].writeToNBT(slotTag);
                itemsList.appendTag(slotTag);
            }
        }
        tagCompound1.setTag("Items", itemsList);
        tagList.appendTag(tagCompound1);
        tagCompound.setTag("WorkBench", tagList);
    }

    public void readToNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
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
                inventory[slot] = ItemStack.loadItemStackFromNBT(item);
                System.out.println(inventory[slot].getDisplayName());
            }
        }

    }

    public abstract boolean checkToCraft();
}
