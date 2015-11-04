package com.lionzxy.betterworkbench.tileentity.base;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.network.NBTMessage;
import com.lionzxy.betterworkbench.tileentity.SimplyTileEntity;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by LionZXY on 24.10.2015.
 * BetterWorkbench
 */
public abstract class BaseTileEntity extends TileEntity implements IInventory {

    public int facing;
    public ItemStack craftResult;
    public ItemStack[] inventory = new ItemStack[10];

    public BaseTileEntity() {
        super();
        BetterWorkbench.network.network.sendToServer(new NBTMessage().set(this));
    }

    @Override
    public int getSizeInventory() {
        return 10;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot < getSizeInventory())
            return inventory[slot];
        else
            return (ItemStack) null;
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

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (slot < getSizeInventory()) {
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
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return true;
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("facing", facing);
        NBTTagList itemsList = new NBTTagList();
        for (byte i = 0; i < getSizeInventory(); i++) {
            if (inventory != null) {
                NBTTagCompound slotTag = new NBTTagCompound();
                slotTag.setByte("Slot", (byte) i);
                if (this.inventory[i] != null)
                    this.inventory[i].writeToNBT(slotTag);
                itemsList.appendTag(slotTag);
            }
        }
        tagCompound.setTag("Items", itemsList);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        facing = tagCompound.getInteger("facing");
        NBTTagList itemsList = tagCompound.getTagList("Items", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);
        for (byte i = 0; i < itemsList.tagCount(); i++) {
            NBTTagCompound item = itemsList.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");
            if (slot >= 0 && slot < getSizeInventory()) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }

    public void readFromItemNBT(NBTTagCompound tagCompound) {
        NBTTagList itemsList = tagCompound.getTagList("Items", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);
        for (byte i = 0; i < itemsList.tagCount(); i++) {
            NBTTagCompound item = itemsList.getCompoundTagAt(i);
            byte slot = item.getByte("Slot");
            if (slot >= 0 && slot < getSizeInventory()) {
                inventory[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }


    public abstract boolean checkToCraft();
}
