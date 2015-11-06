package com.lionzxy.betterworkbench.utils;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;

public class ExtendedInventoryCrafting extends InventoryCrafting{
	
	public ExtendedInventoryCrafting(Container container, int w, int h, ItemStack[] itemList) {
		super(container, w, h);
		try {
			Field stacks = InventoryCrafting.class.getDeclaredField("stackList");
			stacks.setAccessible(true);
			stacks.set(this, itemList);
		} 
		catch (NoSuchFieldException e) {
			try {
				Field stacks = InventoryCrafting.class.getDeclaredField("field_70466_a");
				stacks.setAccessible(true);
				stacks.set(this, itemList);
			}
			catch (NoSuchFieldException e1) {e1.printStackTrace();} 
			catch (SecurityException e1) {e1.printStackTrace();} 
			catch (IllegalArgumentException e1) {e1.printStackTrace();} 
			catch (IllegalAccessException e1) {e1.printStackTrace();}
		} 
		catch (SecurityException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
	}
}
