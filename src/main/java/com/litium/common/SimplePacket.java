package com.litium.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SimplePacket implements IMessage, IMessageHandler<SimplePacket, SimplePacket> {
    private ByteBuf buf;

    @Override
    public SimplePacket onMessage(SimplePacket sp, MessageContext ctx) {
        if (ctx.side.isServer())
            sp.server(ctx.getServerHandler().player);
        else
            sp.client(clientPlayer());
        return null;
    }

    protected ByteBuf buf() {
        return buf != null ? buf : (buf = Unpooled.buffer());
    }

    public void client(EntityPlayer player) {}
    public void server(EntityPlayerMP player) {}

    @Override
    public final void fromBytes(ByteBuf buf) {
        this.buf = buf;
    }

    @Override
    public final void toBytes(ByteBuf buf) {
        if (buf != null)
            buf.writeBytes(this.buf);
    }

    @SideOnly(Side.CLIENT)
    private EntityPlayer clientPlayer() {
        return Minecraft.getMinecraft().player;
    }
}