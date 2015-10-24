package com.lionzxy.betterworkbench.common.block.base;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.tileentity.SimplyTileEntity;
import com.lionzxy.betterworkbench.utils.Constant;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BaseBlock extends BlockContainer {

    protected String name = null;
    @SideOnly(Side.CLIENT)
    protected IIcon top;
    @SideOnly(Side.CLIENT)
    protected IIcon front;
    @SideOnly(Side.CLIENT)
    protected IIcon bottom;

    boolean hasFront = false, hasTop = false, hasBottom = false;

    protected int GUI_ID;


    public BaseBlock(String name, int guiid) {
        super(new Material(MapColor.brownColor));
        this.name = name;
        setBlockName(Constant.MODID + ".block." + name);
        setCreativeTab(BetterWorkbench.BWTab);
        GameRegistry.registerBlock(this, name);
        GUI_ID = guiid;
    }

    public BaseBlock(String name, boolean hasFront, boolean hasTop, boolean hasBottom, int guiid) {
        super(new Material(MapColor.brownColor));
        this.name = name;
        setBlockName(Constant.MODID + ".block." + name);
        setCreativeTab(BetterWorkbench.BWTab);
        GameRegistry.registerBlock(this, name);
        this.hasFront = hasFront;
        this.hasTop = hasTop;
        this.hasBottom = hasBottom;
        GUI_ID = guiid;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(Constant.MODID + ":" + name + "/" + name + "_side");
        if (hasTop)
            top = iconRegister.registerIcon(Constant.MODID + ":" + name + "/" + name + "_top");
        if (hasFront)
            front = iconRegister.registerIcon(Constant.MODID + ":" + name + "/" + name + "_front");
        if (hasBottom)
            bottom = iconRegister.registerIcon(Constant.MODID + ":" + name + "/" + name + "_bottom");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {
        switch (side) {
            case 0:
                return bottom;
            case 1:
                return top;
        }
        return blockIcon;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new SimplyTileEntity();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (!player.worldObj.isRemote) {
            player.openGui(BetterWorkbench.getInstance(), GUI_ID, world, x, y, z);
            return true;
        }
        return false;
    }
}
