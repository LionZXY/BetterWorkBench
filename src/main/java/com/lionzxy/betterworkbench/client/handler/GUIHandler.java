package com.lionzxy.betterworkbench.client.handler;

import com.lionzxy.betterworkbench.client.gui.SimplyWorkBenchGui;
import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import com.lionzxy.betterworkbench.common.inventory.SimplyInventory;
import com.lionzxy.betterworkbench.tileentity.SimplyTileEntity;
import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class GUIHandler implements IGuiHandler {

    public static final int SIMPLY_WORKBENCH = 1;
    public static final int SIMPLY_WORKBENCH_ITEM = 2;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //Возвращает контейнер
        switch (ID) {
            case SIMPLY_WORKBENCH:
                if (!world.blockExists(x, y, z)) {
                    return null;
                }
                if (world.getTileEntity(x, y, z) instanceof BaseTileEntity) {
                    return new SimplyContainer((BaseTileEntity) world.getTileEntity(x, y, z), player);
                }
                return null;
            case SIMPLY_WORKBENCH_ITEM:
                return new SimplyContainer(new SimplyInventory(player.getCurrentEquippedItem(), player), player);
            default:
                return null;
        }

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //Возвращает гуишку
        switch (ID) {
            case SIMPLY_WORKBENCH:
                if (!world.blockExists(x, y, z)) {
                    return null;
                }
                if (world.getTileEntity(x, y, z) instanceof BaseTileEntity) {
                    return new SimplyWorkBenchGui((BaseTileEntity) world.getTileEntity(x, y, z),player);
                }
                return null;
            case SIMPLY_WORKBENCH_ITEM:
                return new SimplyWorkBenchGui(new SimplyInventory(player.getCurrentEquippedItem(), player), player);
            default:
                return null;
        }
    }
}
