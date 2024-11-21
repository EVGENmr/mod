package com.litium.common;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class NetworkHandler {
    private short id;

    /**
     *  ChannelName - название канала. Канал должен быть один на весь мод, создавать дополнительные каналы не нужно!
     */
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("ChannelName");

    public NetworkHandler() {
        //Здесь будет происходить регистрация
    }

    /**
     * Получение дистанции от определённой точки в мире.
    // * @param world - мир
     //* @param updateDistance - радиус в котором будет действовать пакет
     * @return позиция
     */
    public static int gameType(EntityPlayerMP entity) {
        //if (entity.interactionManager.getGameType().getID() == 3) {
            return entity.interactionManager.getGameType().getID();
       // }
    }

    public static void sendTo(SimplePacket packet, EntityPlayerMP entity) {
        NETWORK.sendTo(packet, entity);
    }

    /**
     * Регистрация пакета через один метод.
     * @param packet - класс пакета
     * @param side - сторона (клиент/сервер)
     */
    private void register(Class<? extends SimplePacket> packet, Side side) {
        try {
            NETWORK.registerMessage(packet.newInstance(), packet, id++, side);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}