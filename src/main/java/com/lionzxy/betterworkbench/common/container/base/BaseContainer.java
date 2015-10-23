package com.lionzxy.betterworkbench.common.container.base;

import com.lionzxy.betterworkbench.common.inventory.base.WorkBenchInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public abstract class BaseContainer extends Container {
    public final WorkBenchInventory inventory;
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);

    public BaseContainer(WorkBenchInventory inventory) {
        super();
        this.inventory = inventory;

        this.addSlotToContainer(new SlotCrafting(inventory.getPlayer(), craftMatrix, inventory, 9, 124, 35));

        addCraftingGrid(craftMatrix, 0, 30, 17, 3, 3);

        addPlayerInventory(inventory.getPlayer().inventory);

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

    /**
     * Вызывается по шифт-клику на слот
     */
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        //TODO
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);
        return slot != null ? slot.getStack() : null;
    }
    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        inventory.closeInventory();
    }
}
