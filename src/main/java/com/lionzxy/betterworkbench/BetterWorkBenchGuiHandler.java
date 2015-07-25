package com.lionzxy.betterworkbench;

import com.lionzxy.betterworkbench.client.gui.SimplyGui;
import com.lionzxy.betterworkbench.common.container.SimplyContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;


/**
 * Created by nikit_000 on 24.07.2015.
 */
public class BetterWorkBenchGuiHandler implements IGuiHandler {
    public static final int SIMPLY_WORKBENCH_BLOCK=1;

    public static ResourceLocation resourceLocationSimply=new ResourceLocation(BetterWorkbenchVersion.MODID,"textures/gui/crafting_table.png");
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case SIMPLY_WORKBENCH_BLOCK:return new SimplyContainer(player.inventory,world,x,y,z);
            default:return null;}
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        Object server= getServerGuiElement(ID,player,world,x,y,z);
        switch (ID){
            case SIMPLY_WORKBENCH_BLOCK:return new SimplyGui((SimplyContainer) server,resourceLocationSimply);
            default:return null;}
    }
}
