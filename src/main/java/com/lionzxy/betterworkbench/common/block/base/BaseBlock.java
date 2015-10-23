package com.lionzxy.betterworkbench.common.block.base;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.utils.Constant;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public abstract class BaseBlock extends Block{
	
	protected String name = null;
	@SideOnly(Side.CLIENT)
	protected IIcon top;
	@SideOnly(Side.CLIENT)
	protected IIcon front;
	@SideOnly(Side.CLIENT)
	protected IIcon bottom;
	
	boolean hasFront = false, hasTop = false, hasBottom = false;
	
	protected int GUI_ID;
	
	
	public BaseBlock(String name, int guiid){
		super(new Material(MapColor.brownColor));
		this.name = name;
		setBlockName(Constant.MODID + ".block." + name);
		setCreativeTab(BetterWorkbench.BWTab);
		GameRegistry.registerBlock(this, name);
		GUI_ID = guiid;
	}
	
	public BaseBlock(String name, boolean hasFront, boolean hasTop, boolean hasBottom, int guiid){
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
    public void registerBlockIcons(IIconRegister iconRegister){
        blockIcon = iconRegister.registerIcon(Constant.MODID + ":" + name + "/" + name + "_side");
        if(hasTop)
        	top = iconRegister.registerIcon(Constant.MODID + ":" + name + "/" + name + "_top");
        if(hasFront)
        	front = iconRegister.registerIcon(Constant.MODID + ":" + name + "/" + name + "_front");
        if(hasBottom)
        	bottom = iconRegister.registerIcon(Constant.MODID + ":" + name + "/" + name + "_bottom");
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side) {
		switch(side){
		case 0 :
            return bottom;
		case 1 :
            return top;
		}
		return blockIcon;
    }
}
