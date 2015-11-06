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

    //176 72 : 200 107 - ��������� ����� ������ 25 : 36
    //202 72 : 226 107 - ��������� ������ ������ 25 : 36
    //202 108 : 226 132 - ��������� ����� �����
    //176 108 : 200 132 - ��������� ������ �����
    //176 0 : 193 35 - ������� ����� 18 : 36
    //194 0 : 211 23 - ������� ����
    //176 36 : 200 53 - ������� ����� 25 18
    //176 54 : 200 71 - ������� ������ 25 18
    //202 36 : 219 53 - ���� 17
    //176 133 193 149
    protected ResourceLocation background = new ResourceLocation(Constant.MODID, "textures/gui/crafting_table.png");

    public SimplyWorkBenchGui(IInventory workBenchInventory, EntityPlayer player, ItemStack[] itemList) {
        super(new SimplyContainer(workBenchInventory, player, itemList));

    }


    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("workbench.simply"), 28, 6, 0x404040);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, 74, 0x404040);
        int slotsVal = getSizeAddInventory();
        if (slotsVal == 0)
            return;
        int columns = ((guiLeft - 48) / 18) + 2;
        if (slotsVal < columns)
            columns = slotsVal;
        int startX = -7 - columns * 18;
        this.fontRendererObj.drawString(StatCollector.translateToLocal("Slots: " + slotsVal), startX, 8, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.renderEngine.bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 166);
        if (this.inventorySlots instanceof BaseContainer && ((BaseContainer) this.inventorySlots).inventory instanceof BaseTileEntity)
            ((BaseTileEntity) ((BaseContainer) this.inventorySlots).inventory).syncMessage(guiLeft, guiTop);
        generate(getSizeAddInventory());
    }

    public void generate(int x) {
        if (x == 0)
            return;
        int columns = ((guiLeft - 48) / 18) + 2;
        if (x < columns)
            columns = x;
        int row = x / columns;
        int lastSlot = x - row * columns;
        int startX = guiLeft - 14 - columns * 18;
        int thisX = startX;
        int thisY = guiTop;
        //Draw first column
        drawTexturedModalRect(thisX, thisY, 176, 72, 25, 36);
        if (row == 1)
            thisY += 18;
        else {
            thisY += 36;
            for (int r = 1; r < row - 1; r++) {
                drawTexturedModalRect(thisX, thisY, 176, 36, 25, 18);
                thisY += 18;
            }
        }
        if (lastSlot != 0) {
            drawTexturedModalRect(thisX, thisY, 176, 36, 25, 18);
            thisY += 18;
        }
        drawTexturedModalRect(thisX, thisY, 202, 108, 25, 36);

        //Draw Other
        if (columns == 1) {
            thisX += 7;
            thisY = guiTop;
        } else {
            int tmp;
            thisX += 7;
            for (int c = 1; c < columns - 1; c++) {
                thisX += 18;
                thisY = guiTop;
                drawTexturedModalRect(thisX, thisY, 176, 0, 18, 36);
                if (row == 1) {
                    thisY += 18;
                } else {
                    thisY += 36;
                    tmp = row - 1;
                    for (int r = 1; r < tmp; r++) {
                        drawTexturedModalRect(thisX, thisY, 194, 0, 25, 18);
                        thisY += 18;
                    }
                }
                if (lastSlot != 0 && c < lastSlot) {
                    drawTexturedModalRect(thisX, thisY, 194, 0, 25, 18);
                    thisY += 18;
                }
                drawTexturedModalRect(thisX, thisY, 194, 0, 18, 25);
                //drawTexturedModalRect(thisX, thisY, 176, 108, 25, 18);

            }

            thisX += 18;
            thisY = guiTop;
        }

        //Draw last column
        drawTexturedModalRect(thisX, thisY, 202, 72, 25, 36);
        if (row == 1)
            thisY += 18;
        else {
            thisY += 36;
            for (int r = 1; r < row - 1; r++) {
                drawTexturedModalRect(thisX, thisY, 176, 54, 25, 18);
                thisY += 18;
            }
        }
        drawTexturedModalRect(thisX, thisY, 176, 108, 25, 36);

        if (lastSlot != 0) {
            if (lastSlot == 1)
                thisX = startX + 7;
            else thisX = startX + (lastSlot - 2) * 18 + 25;
            thisY = guiTop + ((row - 1) * 18 + 36);

            drawTexturedModalRect(thisX, thisY, 176, 108, 25, 36);
            thisX += 19;
            drawTexturedModalRect(thisX, thisY, 1, 176, 16, 16);

        }

    }

    public int getSizeAddInventory() {
        if (this.inventorySlots instanceof BaseContainer)
            return ((BaseContainer) this.inventorySlots).getSizeAddInventory();
        return 0;
    }

}
