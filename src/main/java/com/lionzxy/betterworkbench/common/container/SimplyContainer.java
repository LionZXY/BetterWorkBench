package com.lionzxy.betterworkbench.common.container;

import com.lionzxy.betterworkbench.common.container.base.BaseContainer;
import com.lionzxy.betterworkbench.common.inventory.base.WorkBenchInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class SimplyContainer extends BaseContainer {

    public SimplyContainer(IInventory workBenchInventory, EntityPlayer player){
        super(workBenchInventory, player);
    }
}
