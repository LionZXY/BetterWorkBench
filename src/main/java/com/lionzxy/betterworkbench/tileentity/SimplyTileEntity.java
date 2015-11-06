package com.lionzxy.betterworkbench.tileentity;

import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;

/**
 * Created by LionZXY on 24.10.2015.
 * BetterWorkbench
 */
public class SimplyTileEntity extends BaseTileEntity {
    public SimplyTileEntity() {
        super();
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean checkToCraft() {
        return true;
    }

	@Override
	public void openInventory() {	
	}

	@Override
	public void closeInventory() {	
	}
}
