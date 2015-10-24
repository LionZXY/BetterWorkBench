package com.lionzxy.betterworkbench.tileentity;

import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by LionZXY on 24.10.2015.
 * BetterWorkbench
 */
public class SimplyTileEntity extends BaseTileEntity {
    public SimplyTileEntity() {
        super();
    }

    @Override
    public int getSizeInventory() {
        return 10;
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean checkToCraft() {
        return true;
    }
}
