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

    //176 72 : 200 107 - Боковущка слева сверху 25 : 36
    //202 72 : 226 107 - Боковушка справа сверху 25 : 36
    //176 108 : 200 132 - Боковушка слева снизу
    //202 108 : 226 132 - Боковушка справа снизу
    //176 0 : 193 35 - Палочка вверх 18 : 36
    //194 0 : 211 23 - Палочка вниз
    //176 36 : 200 53 - Палочка влево 25 18
    //176 54 : 200 71 - Палочка вправо 25 18
    //202 36 : 219 53 - Слот 17
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
        //System.out.println("Left: " + guiLeft + " Top: " + guiTop);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 166);
        generate(20);
    }

    public void generate(int x) {
        int columns = ((guiLeft - 48) / 18);
        if (x < (columns + 2))
            columns = x - 2;
        int row = x / columns;
        int startX = guiLeft - 50 - columns * 18;
        int thisX = startX;
        int thisY = guiTop;
        drawTexturedModalRect(thisX, guiTop, 176, 72, 25, 36);
        thisX += 25;
        for (int i = 0; i < columns; i++) {
            drawTexturedModalRect(thisX, guiTop, 176, 0, 18, 36);
            thisX += 18;
        }
        drawTexturedModalRect(thisX, guiTop, 202, 72, 24, 36);
        thisX = startX;
        for (int r = 2; r < (row - 1); r++) {
            //System.out.println("X: " + thisX + " Y: " + thisY);
            thisY += 18;
            drawTexturedModalRect(thisX, thisY, 176, 36, 25, 18);
            thisX += 25;
            for (int i = 0; i < columns; i++) {
                drawTexturedModalRect(thisX, thisY, 194, 0, 18, 36);
                thisX += 18;
            }
            drawTexturedModalRect(thisX, thisY, 176, 108, 25, 36);
            thisX = startX;
        }
        thisY += 18;
        //System.out.println(startX);
        int lastColumn;
        if (x > (columns + 2))
            lastColumn = x - (columns + 2) * (row);
        else lastColumn = columns;
        drawTexturedModalRect(thisX, thisY, 202, 108, 25, 36);
        if (lastColumn > 0)
            thisX += 25;
        else thisX += 7;
        for (int i = 0; i < lastColumn; i++) {
            drawTexturedModalRect(thisX, thisY, 194, 0, 18, 36);
            thisX += 18;
        }
        drawTexturedModalRect(thisX, thisY, 176, 108, 25, 36);
        thisX = startX;

    }

}
