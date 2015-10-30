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
    public ItemStack[] inventory = new ItemStack[10];
    private EntityPlayer player;

    public WorkBenchInventory(EntityPlayer player) {

        this.player = player;

        if (!player.inventory.getCurrentItem().hasTagCompound())
            player.inventory.getCurrentItem().setTagCompound(new NBTTagCompound());

        this.readFromNBT(player.inventory.getCurrentItem().getTagCompound());

    }

    @Override
    public int getSizeInventory(){
    	return 10;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
    	if(slot < getSizeInventory())
    		return inventory[slot];
    	else 
    		return (ItemStack)null;
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
    	if(slot < getSizeInventory()){
    		inventory[slot] = itemStack;
        	if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
           		itemStack.stackSize = this.getInventoryStackLimit();
        	}
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
        readFromNBT(player.inventory.getCurrentItem().getTagCompound());
    }

    @Override
    public void closeInventory() {
        writeToNBT(player.inventory.getCurrentItem().getTagCompound());
    }
    
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
    	return true;
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        NBTTagList itemsList = new NBTTagList();

        for (byte i = 0; i < getSizeInventory(); i++) {
            if (inventory != null) {
            	NBTTagCompound slotTag = new NBTTagCompound();
                slotTag.setByte("Slot", i);
                if(this.inventory[i] != null)
                	this.inventory[i].writeToNBT(slotTag);
                itemsList.appendTag(slotTag);
            }
        }
        tagCompound.setTag("Items", itemsList);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
    	
        NBTTagList itemsList = tagCompound.getTagList("Items", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);
        for (byte i = 0; i < itemsList.tagCount(); i++) {
            NBTTagCompound item = itemsList.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");
            if (slot >= 0 && slot < getSizeInventory()) {
                inventory[i] = ItemStack.loadItemStackFromNBT(item);
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
