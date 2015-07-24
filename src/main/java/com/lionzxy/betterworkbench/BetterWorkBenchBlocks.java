package com.lionzxy.betterworkbench;


import com.lionzxy.betterworkbench.common.blocks.SimplyWorkbench;
import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class BetterWorkBenchBlocks {
    public static Block simplyWorkbench;

    public static void addBlock(){
        createBlock();
        registerBlock();
    }
    public static void createBlock(){
        simplyWorkbench=new SimplyWorkbench().setCreativeTab(CreativeTabs.tabBlock);
    }
    public static void registerBlock(){
        GameRegistry.registerBlock(simplyWorkbench,simplyWorkbench.getUnlocalizedName());
    }
}
