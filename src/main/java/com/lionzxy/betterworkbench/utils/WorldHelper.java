package com.lionzxy.betterworkbench.utils;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by LionZXY on 01.11.2015.
 * BetterWorkbench
 */
public class WorldHelper {

    public static void spawnItemStack(World world, int x, int y, int z,ItemStack itemstack ){
        EntityItem entityitem = new EntityItem(world,
                x + 0.5, y + 0.5, z + 0.5,
                itemstack);
        //entityitem.setDefaultPickupDelay();
        world.spawnEntityInWorld(entityitem);
    }
}
