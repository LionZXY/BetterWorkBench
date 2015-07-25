package com.lionzxy.betterworkbench.common;

import com.lionzxy.betterworkbench.common.blocks.SimpleWorkbenchBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class Blocks {

	public static Block mSimpleWorkbench;
	
	public static void initialize() {
		// Initialize
		mSimpleWorkbench = new SimpleWorkbenchBlock();
		// Register
		GameRegistry.registerBlock(mSimpleWorkbench, "block_workbench_simple");
	}
	
}
