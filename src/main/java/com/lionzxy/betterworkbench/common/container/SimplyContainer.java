package com.lionzxy.betterworkbench.common.container;

import com.lionzxy.betterworkbench.common.container.base.BaseContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class SimplyContainer extends BaseContainer {

    public SimplyContainer(IInventory baseTileEntity, EntityPlayer player, ItemStack[] itemList){
        super(baseTileEntity, player, itemList);
    }
}
