package com.lionzxy.betterworkbench.common.item.base;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;
import com.lionzxy.betterworkbench.utils.Constant;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public abstract class BaseItem extends ItemBlock{

    public int GUI_ID;
    public Block block;
    String name;
    IIcon icon;

    public BaseItem(Block block) {
        super(block);
        this.block = block;
    }
    
    @SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icon = iconRegister.registerIcon(Constant.MODID + ":" + name);
	}

    public Item register(String name, int guiid){
    	this.name = name;
    	this.setTextureName(Constant.MODID + ":" + name);
        this.setUnlocalizedName(name);
        this.GUI_ID = guiid;
        return this;
    }
    
    @Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining){
		return icon;
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
				case 0 : setBlock(itemStack, world, block, x, y - 1, z);
						 break;
				case 1 : setBlock(itemStack, world, block, x, y + 1, z);
						 break;
				case 2 : setBlock(itemStack, world, block, x, y, z - 1);
						 break;
				case 3 : setBlock(itemStack, world, block, x, y, z + 1);
						 break;
				case 4 : setBlock(itemStack, world, block, x - 1, y, z);
						 break;
				case 5 : setBlock(itemStack, world, block, x + 1, y, z);
						 break;
                default: return false;
			}
            player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    	}

		return false;
    }

    public void setBlock(ItemStack itemStack,World world, Block block, int x, int y, int z){
    	if(world.isAirBlock(x, y, z)){
    		world.setBlock(x, y, z, block);
    		world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, block.stepSound.func_150496_b(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
            if(itemStack.hasTagCompound())
                ((BaseTileEntity)world.getTileEntity(x,y,z)).readFromItemNBT(itemStack.getTagCompound());
        }
    }
}
