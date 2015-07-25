package com.lionzxy.betterworkbench.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.lionzxy.betterworkbench.BetterWorkBenchVersion;

public class SimpleWorkbenchBlock extends BlockContainer {

    public SimpleWorkbenchBlock() {
        super(Material.wood);
        this.setBlockName("workbench.simple");
        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @Override
    public TileEntity createNewTileEntity(World aWorld, int aMeta) {
    	// TODO TileEntity
        return null;
    }
    
    @Override
    public boolean onBlockActivated(World aWorld, int x, int y, int z, EntityPlayer aPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        aPlayer.openGui(BetterWorkBenchVersion.MODID, 1, aWorld, x, y, z);
        return true;
    }
}
