package com.lionzxy.betterworkbench.common.item.base;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.utils.Constant;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public abstract class BaseItem extends Item {

    public int GUI_ID;

    public BaseItem(String name, int guiid) {
        this.setTextureName(Constant.MODID + ":" + name);
        this.setUnlocalizedName(name);
        //Временно
        this.setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerItem(this, name);
        this.GUI_ID = guiid;
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        //Чтоб открывался только на клиентской стороне, как я понял
        if (world.isRemote)
            return itemStack;
        //Основной код открытия гуи
        player.openGui(BetterWorkbench.getInstance(), GUI_ID, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        return itemStack;
    }

}
