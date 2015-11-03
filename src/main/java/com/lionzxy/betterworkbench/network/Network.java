package com.lionzxy.betterworkbench.network;

import com.lionzxy.betterworkbench.utils.Constant;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.util.Constants;

/**
 * Created by LionZXY on 01.11.2015.
 * BetterWorkbench
 */
public class Network {

    public final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Constant.MODID);

    public void init(){
        network.registerMessage(NBTMessage.class, NBTMessage.class, 0, Side.CLIENT);
    }
}
