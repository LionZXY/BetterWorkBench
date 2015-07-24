package com.lionzxy.betterworkbench.common.blocks;

import com.lionzxy.betterworkbench.BetterWorkBenchVersion;
import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


/**
 * Created by nikit_000 on 24.07.2015.
 */
public class SimplyWorkbench extends BlockContainer{
    public String unlocalizedName;
    public SimplyWorkbench(){
        super(Material.wood);
        this.setUnlocalizedName("simplyworkbench");
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        player.openGui(BetterWorkBenchVersion.MODID,1,world,x,y,z);
        return true;
    }
}
