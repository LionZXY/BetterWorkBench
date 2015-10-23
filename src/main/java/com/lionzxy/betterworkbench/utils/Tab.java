package com.lionzxy.betterworkbench.utils;

import com.lionzxy.betterworkbench.common.item.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Tab extends CreativeTabs{

	public Tab(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return ItemInit.simplyWorkBench;
	}
}
