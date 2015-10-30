package com.lionzxy.betterworkbench.common.container.base;

import java.util.Iterator;

import com.lionzxy.betterworkbench.common.inventory.base.WorkBenchInventory;
import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by LionZXY on 23.10.2015. BetterWorkbench
 */
public abstract class BaseContainer extends Container {
	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	IInventory inventory;
	boolean blocked = false;
	EntityPlayer player;
	SlotCrafting slot;

	public BaseContainer(IInventory inventory, EntityPlayer player) {
		this.inventory = inventory;
		this.player = player;
		slot = new SlotCrafting(player, craftMatrix, inventory, 9, 124, 35);
		for(int i = 0; i < craftMatrix.getSizeInventory(); i++){
			if(inventory.getStackInSlot(i) != null)
				craftMatrix.setInventorySlotContents(i, inventory.getStackInSlot(i));
		}
		this.addSlotToContainer(slot);
		addCraftingGrid(craftMatrix, 0, 30, 17, 3, 3);
		addPlayerInventory(player.inventory);
		onCraftMatrixChanged(inventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	

	public void addPlayerInventory(InventoryPlayer inv) {
		addPlayerInventory(inv, 8, 84);
	}
	

	public void addPlayerInventory(InventoryPlayer inv, int x, int y) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inv, j + (i + 1) * 9, x + j * 18, y + i * 18));
			}
		}
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inv, i, 8 + i * 18, y + 58));
		}
	}
	

	public void addCraftingGrid(IInventory inventory, int startSlot, int x, int y, int width, int height) {
		int i = 0;
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				this.addSlotToContainer(new Slot(inventory, startSlot + i++, x + (w * 18), y + (h * 18)));
			}
		}
	}
	

	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotIndex >= 10 && slotIndex < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (slotIndex >= 37 && slotIndex < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
	}

	public void onCraftMatrixChanged(IInventory inv) {
		slot.putStack(CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, player.worldObj));
	}
	
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
    	for(int i = 0; i < craftMatrix.getSizeInventory(); i++){
    		inventory.setInventorySlotContents(i, craftMatrix.getStackInSlot(i));
    	}
    }
}
