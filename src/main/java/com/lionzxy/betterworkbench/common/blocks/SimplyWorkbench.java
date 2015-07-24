package com.lionzxy.betterworkbench.common.blocks;

import com.lionzxy.betterworkbench.BetterWorkBenchVersion;
import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


/**
 * Created by nikit_000 on 24.07.2015.
 */
public class SimplyWorkbench extends BlockContainer{
    private String unlocalizedName;
    SimplyWorkbench(SimplyContainer container){
        super(Material.wood);
    }
    @Override
    public String getUnlocalizedName() {
        return "tile." + BetterWorkBenchVersion.MODID + "." + this.unlocalizedName;
    }

    public Block setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }
    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}
