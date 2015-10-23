package com.lionzxy.betterworkbench.common.block;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.common.block.base.BaseBlock;
import com.lionzxy.betterworkbench.utils.Constant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSimplyWorkBench extends BaseBlock{

	public BlockSimplyWorkBench(String name, int GUI_ID) {
		super(name, true, true, true, GUI_ID);
	}
}
