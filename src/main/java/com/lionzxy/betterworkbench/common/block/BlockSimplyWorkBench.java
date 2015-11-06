package com.lionzxy.betterworkbench.common.block;

import com.lionzxy.betterworkbench.common.block.base.BaseBlock;
import com.lionzxy.betterworkbench.common.item.ItemInit;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class BlockSimplyWorkBench extends BaseBlock{

	public BlockSimplyWorkBench(String name, Class item, int GUI_ID) {
		super(name, item, true, true, true, GUI_ID);
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
