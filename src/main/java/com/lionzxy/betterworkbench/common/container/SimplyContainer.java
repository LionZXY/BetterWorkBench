package com.lionzxy.betterworkbench.common.container;

import com.lionzxy.betterworkbench.common.container.base.BaseContainer;
import com.lionzxy.betterworkbench.common.inventory.base.WorkBenchInventory;
import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class SimplyContainer extends BaseContainer {

    public SimplyContainer(IInventory baseTileEntity, EntityPlayer player){
        super(baseTileEntity, player);
    }
}
