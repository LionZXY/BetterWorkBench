package com.lionzxy.betterworkbench.client.handler;

import com.lionzxy.betterworkbench.client.gui.SimplyWorkBenchGui;
import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import com.lionzxy.betterworkbench.common.inventory.SimplyInventory;
import com.lionzxy.betterworkbench.tileentity.SimplyTileEntity;
import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class GUIHandler implements IGuiHandler {

    public static final int SIMPLY_WORKBENCH = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //Возвращает контейнер
        switch (ID) {
            case 1:
                if (!world.blockExists(x, y, z)) {
                    return null;
                }
                if (world.getTileEntity(x, y, z) instanceof BaseTileEntity) {
                    return new SimplyContainer(new SimplyTileEntity(), player);
                } else return new SimplyContainer(new SimplyInventory(player.getCurrentEquippedItem(), player), player);
            default:
                return null;
        }

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //Возвращает гуишку
        switch (ID) {
            case 1:
                if (!world.blockExists(x, y, z)) {
                    return null;
                }
                if (world.getTileEntity(x, y, z) instanceof BaseTileEntity) {
                    return new SimplyWorkBenchGui(new SimplyTileEntity(),player);
                } else return new SimplyWorkBenchGui(new SimplyInventory(player.getCurrentEquippedItem(), player), player);
            default:
                return null;
        }
    }
}
