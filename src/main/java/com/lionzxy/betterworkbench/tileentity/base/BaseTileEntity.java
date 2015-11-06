package com.lionzxy.betterworkbench.tileentity.base;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.network.message.GUIToServerMessage;
import com.lionzxy.betterworkbench.network.message.NBTMessage;
import com.lionzxy.betterworkbench.network.message.SlotToClientMessage;
import com.lionzxy.betterworkbench.utils.Constant;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LionZXY on 24.10.2015.
 * BetterWorkbench
 */
public abstract class BaseTileEntity extends TileEntity implements IInventory {

    public int facing, guiLeft, guiTop, slots, tickPlus = 0;
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

    public void syncMessage(int guiLeft, int guiTop) {
        //Синхронизация раз в 20 тиков.
        if (tickPlus == Constant.SYNC_PER_TICK && FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            //System.out.println(this.guiLeft + " " + this.guiTop + " " + slots + " IsClient:" + FMLCommonHandler.instance().getEffectiveSide().isClient());
            BetterWorkbench.network.network.sendToServer(new GUIToServerMessage().set(this, guiTop, guiLeft));
            this.guiLeft = guiLeft;
            this.guiTop = guiTop;
            tickPlus = 0;
        } else if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            //System.out.println(this.guiLeft + " " + this.guiTop + " " + slots + " IsClient:" + FMLCommonHandler.instance().getEffectiveSide().isClient());
            this.guiLeft = guiLeft;
            this.guiTop = guiTop;
            setSlots();
            BetterWorkbench.network.network.sendToServer(new SlotToClientMessage().set(this, slots));
        }
        tickPlus++;
    }

    public void setSlots() {
        BaseTileEntity workbench = this;
        List<IInventory> inventories = new ArrayList<IInventory>();

        if (workbench.getWorldObj().getTileEntity(workbench.xCoord - 1, workbench.yCoord, workbench.zCoord) != null &&
                workbench.getWorldObj().getTileEntity(workbench.xCoord - 1, workbench.yCoord, workbench.zCoord) instanceof IInventory)
            inventories.add((IInventory) workbench.getWorldObj().getTileEntity(workbench.xCoord - 1, workbench.yCoord, workbench.zCoord));

        if (workbench.getWorldObj().getTileEntity(workbench.xCoord + 1, workbench.yCoord, workbench.zCoord) != null &&
                workbench.getWorldObj().getTileEntity(workbench.xCoord + 1, workbench.yCoord, workbench.zCoord) instanceof IInventory)
            inventories.add((IInventory) workbench.getWorldObj().getTileEntity(workbench.xCoord + 1, workbench.yCoord, workbench.zCoord));

        if (workbench.getWorldObj().getTileEntity(workbench.xCoord, workbench.yCoord, workbench.zCoord - 1) != null &&
                workbench.getWorldObj().getTileEntity(workbench.xCoord, workbench.yCoord, workbench.zCoord - 1) instanceof IInventory)
            inventories.add((IInventory) workbench.getWorldObj().getTileEntity(workbench.xCoord, workbench.yCoord, workbench.zCoord - 1));

        if (workbench.getWorldObj().getTileEntity(workbench.xCoord, workbench.yCoord, workbench.zCoord + 1) != null &&
                workbench.getWorldObj().getTileEntity(workbench.xCoord, workbench.yCoord, workbench.zCoord + 1) instanceof IInventory)
            inventories.add((IInventory) workbench.getWorldObj().getTileEntity(workbench.xCoord, workbench.yCoord, workbench.zCoord + 1));
        slots = 0;
        for (IInventory inventory : inventories) {
            slots += inventory.getSizeInventory();
        }
    }


    public abstract boolean checkToCraft();
}
