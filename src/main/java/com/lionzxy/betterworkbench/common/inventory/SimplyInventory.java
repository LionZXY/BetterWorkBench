package com.lionzxy.betterworkbench.common.inventory;

import com.lionzxy.betterworkbench.common.inventory.base.WorkBenchInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by LionZXY on 22.10.2015.
 * BetterWorkbench
 */
public class SimplyInventory extends WorkBenchInventory {

    public SimplyInventory(ItemStack itemStack) {
        super(itemStack);
    }

    @Override
    public int getSizeInventory() {
        return 9;
    }

    @Override
    public String getInventoryName() {
        return "Simply Workbench";
    }
}
