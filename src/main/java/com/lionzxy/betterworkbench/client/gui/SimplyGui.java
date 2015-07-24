package com.lionzxy.betterworkbench.client.gui;

import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class SimplyGui extends GuiContainer {
    private SimplyContainer simplyContainer;
    private ResourceLocation resourceLocation;
    int guiWidth=176;
    int guiHeight=166;

    public SimplyGui(SimplyContainer container, ResourceLocation textureLocation) {
        super(container);
        this.resourceLocation=textureLocation;

    }

    @Override
    public void drawScreen(int x, int y, float ticks)
    {
        int guiX =(width-guiWidth)/2;
        int guiY =(height-guiHeight)/2;
        GL11.glColor4f(1, 1, 1, 1);
        drawDefaultBackground();
        mc.renderEngine.bindTexture(resourceLocation);
        drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
        super.drawScreen(x,y,ticks);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1, 1, 1, 1);
        int guiX =(width-guiWidth)/2;
        int guiY =(height-guiHeight)/2;
        this.drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
    }

    @Override
    protected void handleMouseClick(Slot p_146984_1_, int p_146984_2_, int p_146984_3_, int p_146984_4_) {
        super.handleMouseClick(p_146984_1_, p_146984_2_, p_146984_3_, p_146984_4_);
    }
}
