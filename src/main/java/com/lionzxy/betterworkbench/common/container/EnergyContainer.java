package com.lionzxy.betterworkbench.common.container;

import com.lionzxy.betterworkbench.common.tiles.EnergyWorkbenchTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
;

/**
 * Created by nikit_000 on 25.07.2015.
 */
public class EnergyContainer extends Container {
    public EnergyContainer(EntityPlayer player, EnergyWorkbenchTile tile){
      /*  this.addSlotToContainer(new SlotCrafting(player, this.craftMatrix, this.craftResult, 0, 124, 35));
        int i;
        int l;
        for (l = 0; l < 3; l++)
        {
            for (i = 0; i < 3; i++)
            {
                this.addSlotToContainer(new Slot(tile, i + l * 3, 30 + i * 18, 17 + l * 18));
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
        }*/
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}
