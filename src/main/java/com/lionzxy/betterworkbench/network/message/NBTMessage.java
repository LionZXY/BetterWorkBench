package com.lionzxy.betterworkbench.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by LionZXY on 01.11.2015.
 * BetterWorkbench
 */
public class NBTMessage implements IMessage, IMessageHandler<NBTMessage,IMessage> {
    public int x,y,z;
    NBTTagCompound tagCompound;

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        tagCompound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        ByteBufUtils.writeTag(buf,tagCompound);
    }

    public NBTMessage set(TileEntity te)
    {
        this.x = te.xCoord;
        this.y = te.yCoord;
        this.z = te.zCoord;
        NBTTagCompound tagCompound = new NBTTagCompound();
        te.writeToNBT(tagCompound);
        this.tagCompound = tagCompound;

        return this;
    }

    @Override
    public IMessage onMessage(NBTMessage m, MessageContext ctx) {
        TileEntity te = Minecraft.getMinecraft().thePlayer.worldObj.getTileEntity(m.x,m.y,m.z);
        if(te != null)
            te.readFromNBT(m.tagCompound);
        return null;
    }
}
