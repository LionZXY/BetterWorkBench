package com.lionzxy.betterworkbench.common.block;

import com.lionzxy.betterworkbench.client.handler.GUIHandler;
import com.lionzxy.betterworkbench.common.item.ItemInit;
import com.lionzxy.betterworkbench.common.item.ItemSimplyWorkBench;
import com.lionzxy.betterworkbench.common.item.base.BaseItem;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockInit {
	public static Block simplyWorkBench;
	
	public static void init(){
		simplyWorkBench = new BlockSimplyWorkBench("simplyworkbenchblock", ItemSimplyWorkBench.class, GUIHandler.SIMPLY_WORKBENCH);
	}
}
