package com.lionzxy.betterworkbench.network.message;

import com.lionzxy.betterworkbench.tileentity.base.BaseTileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by LionZXY on 06.11.2015.
 * BetterWorkbench
 */
public class SlotToClientMessage implements IMessage, IMessageHandler<SlotToClientMessage, IMessage> {
    int x, y, z, slots;

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        slots = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(slots);
    }

    public SlotToClientMessage set(BaseTileEntity tileEntity, int slots) {
        this.x = tileEntity.xCoord;
        this.y = tileEntity.yCoord;
        this.z = tileEntity.zCoord;
        this.slots = slots;
        return this;
    }

    @Override
    public IMessage onMessage(SlotToClientMessage m, MessageContext ctx) {
        TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(m.x, m.y, m.z);
        if (tileEntity != null && tileEntity instanceof BaseTileEntity) {
            ((BaseTileEntity) tileEntity).slots = m.slots;
        }
        return null;
    }
}
