package com.lionzxy.betterworkbench.common.block;

import com.lionzxy.betterworkbench.client.handler.GUIHandler;
import com.lionzxy.betterworkbench.common.item.ItemSimplyWorkBench;
import net.minecraft.block.Block;

public class BlockInit {
	public static Block simplyWorkBench;
	
	public static void init(){
		simplyWorkBench = new BlockSimplyWorkBench("simplyworkbenchblock", ItemSimplyWorkBench.class, GUIHandler.SIMPLY_WORKBENCH);
	}
}
