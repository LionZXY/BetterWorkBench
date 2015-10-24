package com.lionzxy.betterworkbench.common.item.base;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.utils.Constant;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public abstract class BaseItem extends Item{

    public int GUI_ID;
    public Block block;

    public BaseItem(String name, int guiid) {
        this.setTextureName(Constant.MODID + ":" + name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(BetterWorkbench.BWTab);
        GameRegistry.registerItem(this, name);
        this.GUI_ID = guiid;
    }
    
    public BaseItem(String name, int guiid, Block block) {
        this.setTextureName(Constant.MODID + ":" + name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(BetterWorkbench.BWTab);
        GameRegistry.registerItem(this, name);
        this.GUI_ID = guiid;
        this.block = block;
        System.out.println(this.block);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
    	//Чтоб открывался только на клиентской стороне, как я понял
    	
    	if (world.isRemote || player.isSneaking())
        	return itemStack;
        //Основной код открытия гуи
        player.openGui(BetterWorkbench.getInstance(), GUI_ID, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        return itemStack;
    }
    
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_){
    	if(this.block != null && !world.isRemote && player.isSneaking()){
    		switch(side){
				case 0 : setBlock(world, block, x, y - 1, z);
						 return true;
				case 1 : setBlock(world, block, x, y + 1, z);
						 return true;
				case 2 : setBlock(world, block, x, y, z - 1);
						 return true;
				case 3 : setBlock(world, block, x, y, z + 1);
						 return true;
				case 4 : setBlock(world, block, x - 1, y, z);
						 return true;
				case 5 : setBlock(world, block, x + 1, y, z);
						 return true;
			}
    	}
		
		return false;
    }
    
    public void setBlock(World world, Block block, int x, int y, int z){
    	if(world.isAirBlock(x, y, z)){
    		world.setBlock(x, y, z, block);
    		world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, block.stepSound.func_150496_b(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
        }
    }
}
