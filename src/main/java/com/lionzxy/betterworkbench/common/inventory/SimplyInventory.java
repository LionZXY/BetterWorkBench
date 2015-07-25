package com.lionzxy.betterworkbench.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;


/**
 * Created by nikit_000 on 24.07.2015.
 */
public class SimplyInventory extends InventoryCrafting implements IInventory {
    private ItemStack[] stackList;
    private int inventoryWidht;
    private Container container;
    public SimplyInventory(Container container, int x, int y){
        super(container,x,y);
        this.stackList = new ItemStack[x*y];
        this.container=container;
        this.inventoryWidht=x;
    }

    @Override
    public int getSizeInventory() {
        return this.stackList.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot>= this.getSizeInventory() ? null : this.stackList[slot];
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        if (this.stackList[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if (this.stackList[p_70298_1_].stackSize <= p_70298_2_)
            {
                itemstack = this.stackList[p_70298_1_];
                this.stackList[p_70298_1_] = null;
                this.container.onCraftMatrixChanged(this);
                return itemstack;
            }
            else
            {
                itemstack = this.stackList[p_70298_1_].splitStack(p_70298_2_);

                if (this.stackList[p_70298_1_].stackSize == 0)
                {
                    this.stackList[p_70298_1_] = null;
                }

                this.container.onCraftMatrixChanged(this);
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        if (this.stackList[p_70304_1_] != null)
        {
            ItemStack itemstack = this.stackList[p_70304_1_];
            this.stackList[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack item) {
        stackList[slot]=item;
        this.container.onCraftMatrixChanged(this);
    }

    @Override
    public String getInventoryName() {
        return "simlycrafting";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {}

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {return true;}

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {return true;}
    public int getInventoryWidht(){
        return inventoryWidht;
    }
    public ItemStack[] getInventoryList(){
        return stackList;
    }
    /**
     * A custom method to read our inventory from an ItemStack's NBT compound
     */
    public void readFromNBT(NBTTagCompound tagcompound) {
        NBTTagList items = tagcompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < items.tagCount(); ++i) {
            NBTTagCompound item = /*(NBTTagCompound)*/ items.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");

            // Just double-checking that the saved slot index is within our inventory array bounds
            if (slot >= 0 && slot < getSizeInventory()) {
                stackList[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }

    /**
     * A custom method to write our inventory to an ItemStack's NBT compound
     */
    public void writeToNBT(NBTTagCompound tagcompound) {

        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < stackList.length; i++){
            if(stackList[i] != null){
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                stackList[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
                tagcompound.setTag("Items", nbttaglist);
            }
        }
    }
}
