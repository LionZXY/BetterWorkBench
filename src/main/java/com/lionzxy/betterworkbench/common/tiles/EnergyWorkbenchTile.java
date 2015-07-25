package com.lionzxy.betterworkbench.common.tiles;

import com.lionzxy.betterworkbench.common.container.EnergyContainer;
import com.lionzxy.betterworkbench.common.listcraft.EnergyCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import java.util.List;

/**
 * Created by nikit_000 on 25.07.2015.
 */
public class EnergyWorkbenchTile extends TileEntity {
    private ItemStack[] inventory = new ItemStack[9];

    protected String machineCustomName;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory() {
        return this.inventory.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int index) {
        return this.inventory[index];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int index, int count) {
        if (getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (getStackInSlot(index).stackSize <= count) {
                itemstack = getStackInSlot(index);
                setInventorySlotContents(index, null);
                return itemstack;
            } else {
                itemstack = getStackInSlot(index).splitStack(count);

                if (getStackInSlot(index).stackSize == 0) {
                    setInventorySlotContents(index, null);
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }


    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int index) {
        ItemStack itemstack = getStackInSlot(index);
        if (itemstack != null) {
            setInventorySlotContents(index, null);
        }
        return itemstack;

    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory[index]=stack;
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
/*
*    {
*        return this.hasCustomName() ? this.machineCustomName : "nord.tile.abstractEnergyMachina";
*    }
*/

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName() {
        return this.machineCustomName != null && this.machineCustomName.length() > 0;
    }

    public void setCustomInventoryName(String name) {
        this.machineCustomName = name;
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
                inventory[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }

    /**
     * A custom method to write our inventory to an ItemStack's NBT compound
     */
    public void writeToNBT(NBTTagCompound tagcompound) {

        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] != null){
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
                tagcompound.setTag("Items", nbttaglist);
            }
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit() {
        return 64;
    }


    /**
     * Updates the JList with a new model.
     */
    public void update() {

            //TO DO
        }
    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player) {
    //TO DO
        return true;
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return canInsertItem(index,stack);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn) {
        if(index<10){return true;}else{return false;}
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canExtractItem(int index, ItemStack stack) {
        return true;
    }


    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        //to do
        //return new EnergyContainer(playerInventory, this);
        return null;
    }



    public boolean canStartWorking() {
        if(EnergyCraft.checkToCraft(inventory)!=null){
            return true;
        }
        else {return false;}
    }




}

