package com.lionzxy.betterworkbench.client.gui;

import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by nikit_000 on 24.07.2015.
 */
public class SimplyGui extends GuiContainer {
    private SimplyContainer simplyContainer;
    private ResourceLocation resourceLocation;
    public SimplyGui(SimplyContainer container, ResourceLocation resourceLocation){
        super(container);
        this.resourceLocation=resourceLocation;
        this.simplyContainer=container;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

    }
}
