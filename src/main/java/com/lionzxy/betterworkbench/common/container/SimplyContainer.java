package com.lionzxy.betterworkbench.common.container;

import com.lionzxy.betterworkbench.common.inventory.SimplyInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
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

    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }
}
