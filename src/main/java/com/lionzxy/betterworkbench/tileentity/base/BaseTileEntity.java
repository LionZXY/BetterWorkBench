package com.lionzxy.betterworkbench.tileentity.base;

import com.lionzxy.betterworkbench.tileentity.SimplyTileEntity;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by LionZXY on 24.10.2015.
 * BetterWorkbench
 */
public abstract class BaseTileEntity extends TileEntity implements IInventory {
    int craftSlot;
    

    public ItemStack[] inventory = new ItemStack[10];


    public BaseTileEntity() {
        super();
    }

    @Override
    public int getSizeInventory(){
    	return 1024;
    }

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
        return inventory[slot];
    }

    
    public void updateEntity() {
    	
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
        return 64;
    }

    @Override
    public void markDirty() {
        for (int i = 0; i < getSizeInventory(); i++) {
            if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
                inventory[i] = null;
        }
        checkToCraft();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }
    
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (slot != craftSlot)
            return true;
        else return false;
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
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
        super.readFromNBT(tagCompound);
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
}
