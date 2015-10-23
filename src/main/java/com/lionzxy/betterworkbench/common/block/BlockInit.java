package com.lionzxy.betterworkbench.common.block;

import com.lionzxy.betterworkbench.client.handler.GUIHandler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockInit {
	public static Block simplyWorkBench;
	
	public static void init(){
		simplyWorkBench = new BlockSimplyWorkBench("simplyworkbenchblock", GUIHandler.SIMPLY_WORKBENCH);
	}
}
