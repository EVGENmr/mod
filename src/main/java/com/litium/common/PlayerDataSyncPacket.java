package com.litium.common;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PlayerDataSyncPacket implements IMessage {
    private NBTTagCompound data;

    public PlayerDataSyncPacket() {} // Пустой конструктор

    public PlayerDataSyncPacket(EntityPlayerMP serverPlayer) {
        // Получаем нужные NBT-данные с серверного игрока
        this.data = serverPlayer.getEntityData();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.data = ByteBufUtils.readTag(buf); // Чтение NBT
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.data); // Запись NBT
    }

    public static class Handler implements IMessageHandler<PlayerDataSyncPacket, IMessage> {
        @Override
        public IMessage onMessage(PlayerDataSyncPacket message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                // На клиенте
                EntityPlayer player = Minecraft.getMinecraft().player;
                if (player != null) {
                    // Применяем NBT данные к клиентскому игроку
                    player.getEntityData().merge(message.data);
                    System.out.println("Litium: data out");
                }
            });
            return null;
        }
    }
}

