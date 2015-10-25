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
    private EntityPlayer player;


    public WorkBenchInventory(EntityPlayer player) {

        this.player = player;

        if (!player.inventory.getCurrentItem().hasTagCompound())
            player.inventory.getCurrentItem().setTagCompound(new NBTTagCompound());

        this.readToNBT(player.inventory.getCurrentItem().getTagCompound());

    }

    @Override
    public abstract int getSizeInventory();

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
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
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
                inventory[i] = null;
        }
        checkToCraft();

        writeToNBT(player.inventory.getCurrentItem().getTagCompound());
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory() {
        readToNBT(player.inventory.getCurrentItem().getTagCompound());
    }

    @Override
    public void closeInventory() {
        writeToNBT(player.inventory.getCurrentItem().getTagCompound());
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (slot != craftSlot)
            return true;
        else return false;
    }

    public void writeToNBT(NBTTagCompound tagCompound) {

        NBTTagList tagList = new NBTTagList();
        NBTTagCompound tagCompound1 = new NBTTagCompound();
        NBTTagList itemsList = new NBTTagList();
        NBTTagCompound slotTag = new NBTTagCompound();
        for (byte i = 0; i < getSizeInventory(); i++) {
            if (inventory[i] != null) {
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
        NBTTagList itemsList = tagCompound.getTagList("WorkBench", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND)
                .getCompoundTagAt(0).getTagList("Items", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);
        for (byte i = 0; i < itemsList.tagCount(); i++) {
            NBTTagCompound item = itemsList.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");
            if (slot >= 0 && slot < getSizeInventory()) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(item);
                System.out.println(inventory[slot].getDisplayName());
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
