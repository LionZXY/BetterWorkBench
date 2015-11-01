package com.lionzxy.betterworkbench.common.block;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.common.block.base.BaseBlock;
import com.lionzxy.betterworkbench.common.item.ItemInit;
import com.lionzxy.betterworkbench.utils.Constant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSimplyWorkBench extends BaseBlock{

	public BlockSimplyWorkBench(String name, int GUI_ID) {
		super(name, true, true, true, GUI_ID);
	}

	@Override
	public ItemStack getItemStack(TileEntity tileEntity) {
		ItemStack is = new ItemStack(ItemInit.simplyWorkBench);
		NBTTagCompound tagCompound = new NBTTagCompound();
		tileEntity.writeToNBT(tagCompound);
		is.setTagCompound(tagCompound);
		return is;
	}
}
