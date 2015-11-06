package com.lionzxy.betterworkbench.network;

import com.lionzxy.betterworkbench.network.message.GUIToServerMessage;
import com.lionzxy.betterworkbench.network.message.NBTMessage;
import com.lionzxy.betterworkbench.network.message.SlotToClientMessage;
import com.lionzxy.betterworkbench.utils.Constant;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by LionZXY on 01.11.2015.
 * BetterWorkbench
 */
public class Network {

    public final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Constant.MODID);

    public void init(){
        network.registerMessage(GUIToServerMessage.class, GUIToServerMessage.class, 2 , Side.SERVER);

        network.registerMessage(NBTMessage.class, NBTMessage.class, 0, Side.CLIENT);
        network.registerMessage(SlotToClientMessage.class, SlotToClientMessage.class, 1, Side.CLIENT);
    }
}
