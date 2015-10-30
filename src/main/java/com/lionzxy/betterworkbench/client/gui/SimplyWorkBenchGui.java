package com.lionzxy.betterworkbench.client.gui;

import com.lionzxy.betterworkbench.BetterWorkbench;
import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import com.lionzxy.betterworkbench.common.container.base.BaseContainer;
import com.lionzxy.betterworkbench.common.inventory.base.WorkBenchInventory;
import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;
import com.lionzxy.betterworkbench.utils.Constant;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

/**
 * Created by LionZXY on 23.10.2015.
 * BetterWorkbench
 */
public class SimplyWorkBenchGui extends GuiContainer {

    protected ResourceLocation background = new ResourceLocation(Constant.MODID, "textures/gui/crafting_table.png");

    public SimplyWorkBenchGui(IInventory workBenchInventory, EntityPlayer player, ItemStack[] itemList) {
        super(new SimplyContainer(workBenchInventory, player, itemList));
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("workbench.simply"), 28, 6, 0x404040);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, 74, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 166);
    }
}
