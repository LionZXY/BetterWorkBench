package com.lionzxy.betterworkbench.common.utils;

import net.minecraft.item.ItemStack;

// TODO Дописать этот класс

public class SimpleEnergyRecipe {

	private final ItemStack[] mInput;
	private final ItemStack   mOutput;
	private final int		  mEnergyCost;
	
	public SimpleEnergyRecipe(ItemStack[] aInput, ItemStack aOutput, int aEnergyCost) {
		mInput = aInput;
		mOutput = aOutput;
		mEnergyCost = aEnergyCost;
	}
	
	public ItemStack[] getInput() {
		return mInput;
	}
	
	public ItemStack getOutput() {
		return mOutput;
	}
	
	public int getEnergyCost() {
		return mEnergyCost;
	}
	
}
