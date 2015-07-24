package com.lionzxy.betterworkbench.common.container;

import com.lionzxy.betterworkbench.BetterWorkBenchBlocks;
import com.lionzxy.betterworkbench.common.inventory.SimplyInventory;
import com.lionzxy.betterworkbench.common.listcraft.VanillaCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class SimplyContainer extends Container {
    public SimplyInventory craftMatrix = new SimplyInventory(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;


    public SimplyContainer(InventoryPlayer inventoryPlayer, World worldObj, int posX, int posY, int posZ){
        this.posX=posX;
        this.posY=posY;
        this.posZ=posZ;
        this.worldObj=worldObj;
        this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 124, 35));
        int i;
        int l;
        for (l = 0; l < 3; l++)
        {
            for (i = 0; i < 3; i++)
            {
                this.addSlotToContainer(new Slot(this.craftMatrix, i + l * 3, 30 + i * 18, 17 + l * 18));
            }
        }

        for (l = 0; l < 3; ++l)
        {
            for (i = 0; i < 9; ++i)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, i + l * 9 + 9, 8 + i * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, l, 8 + l * 18, 142));
        }
        this.onCraftMatrixChanged(this.craftMatrix);
    }
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ >= 10 && p_82846_2_ < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (p_82846_2_ >= 37 && p_82846_2_ < 46)
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

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {

        return this.worldObj.getBlock(this.posX, this.posY, this.posZ) != BetterWorkBenchBlocks.simplyWorkbench ? false : p_75145_1_.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
    }
    public void onCraftMatrixChanged(IInventory p_75130_1_)
    {
        this.craftResult.setInventorySlotContents(0, VanillaCraft.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
    }
}
