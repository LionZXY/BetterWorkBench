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

    public ItemStack[] inventory = new ItemStack[10];
    //Используется для крафта
    private EntityPlayer player;


    public WorkBenchInventory(EntityPlayer player) {

        this.player = player;

        if (!player.inventory.getCurrentItem().hasTagCompound())
            player.inventory.getCurrentItem().setTagCompound(new NBTTagCompound());

        this.readToNBT(player.inventory.getCurrentItem().getTagCompound());

        //this.inventory = new ItemStack[getSizeInventory()];
    }

    @Override
    public abstract int getSizeInventory();

    @Override
    public ItemStack getStackInSlot(int slot) {
        if(inventory[slot] != null)
            System.out.println(inventory[slot]);
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
        if (inventory[slot]!=null) {
            ItemStack stack = inventory[slot];
            inventory[slot] = null;
            return stack;
        }
        return null;
    }
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        inventory[slot] = itemStack;
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
        return 644;
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

        writeToNBT(player.inventory.getCurrentItem().getTagCompound());
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        //Если это слот выхода рецепта, то нельзя ставить
        if (slot != craftSlot)
            return true;
        else return false;
    }

    public void writeToNBT(NBTTagCompound tagCompound) {

        if (inventory.length>0) {
            NBTTagList contents = new NBTTagList();
            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i] != null) {
                    ItemStack stack = inventory[i];
                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setByte("Slot", (byte)i);
                    stack.writeToNBT(tag);
                    contents.appendTag(tag);
                }
            }
            tagCompound.setTag("Contents", contents);
        }
    }

    public void readToNBT(NBTTagCompound tagCompound) {
        //Выдергиваем лист.
        if (tagCompound.hasKey("Contents")) {
            NBTTagList contents = tagCompound.getTagList("Contents", 10);
            for (int i = 0; i < contents.tagCount(); i++) {
                NBTTagCompound tag = (NBTTagCompound) contents.getCompoundTagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot < inventory.length) {
                    inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
            }
        }

    }

    public abstract boolean checkToCraft();

    public ItemStack getMainItemStack() {
        return player.inventory.getCurrentItem();
    }

    public EntityPlayer getPlayer() {
        return player;
    }
}
