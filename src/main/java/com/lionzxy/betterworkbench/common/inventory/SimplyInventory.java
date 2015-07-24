package com.lionzxy.betterworkbench.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;


/**
 * Created by nikit_000 on 24.07.2015.
 */
public class SimplyInventory implements IInventory {
    private ItemStack[] stackList;
    private int inventoryWidht;
    private Container container;
    public SimplyInventory(Container container, int x, int y){
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
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack item) {
        stackList[slot]=item;
        //TO DO matrix change
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
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {return true;}

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {return true;}
}
