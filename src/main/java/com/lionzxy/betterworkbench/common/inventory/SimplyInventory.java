package com.lionzxy.betterworkbench.common.inventory;

import com.lionzxy.betterworkbench.common.inventory.base.WorkBenchInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by LionZXY on 22.10.2015.
 * BetterWorkbench
 */
public class SimplyInventory extends WorkBenchInventory {


    public SimplyInventory(ItemStack itemStack, EntityPlayer player) {
        super(itemStack, player);
    }

    @Override
    public int getSizeInventory() {
        return 10;
    }

    @Override
    public String getInventoryName() {
        return "Simply Workbench";
    }

    @Override
    public boolean checkToCraft() {
        //TODO
        return false;
    }
}
