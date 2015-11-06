package com.lionzxy.betterworkbench.network.message;

import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by LionZXY on 06.11.2015.
 * BetterWorkbench
 */
public class GUIToServerMessage implements IMessage, IMessageHandler<GUIToServerMessage, IMessage> {
    public int guiTop, guiLeft, x, y, z;

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        guiLeft = buf.readInt();
        guiTop = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(guiLeft);
        buf.writeInt(guiTop);
    }

    public GUIToServerMessage set(BaseTileEntity tileEntity, int guiTop, int guiLeft) {
        this.x = tileEntity.xCoord;
        this.y = tileEntity.yCoord;
        this.z = tileEntity.zCoord;
        this.guiLeft = guiLeft;
        this.guiTop = guiTop;
        return this;
    }

    @Override
    public IMessage onMessage(GUIToServerMessage m, MessageContext ctx) {
        TileEntity tileEntity = Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(m.x,m.y,m.z);
        if(tileEntity != null && tileEntity instanceof BaseTileEntity){
            BaseTileEntity baseTileEntity = (BaseTileEntity) tileEntity;
            baseTileEntity.syncMessage(m.guiLeft,m.guiTop);
        }
        return null;
    }
}
