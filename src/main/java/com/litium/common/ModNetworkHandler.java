package com.litium.common;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("modid");

    public static void registerMessages() {
        int id = 0;
        // Отправляем на клиент
        INSTANCE.registerMessage(PlayerDataSyncPacket.Handler.class, PlayerDataSyncPacket.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(PlayerDataSyncPacket.Handler.class, PlayerDataSyncPacket.class, id++, Side.SERVER);
        System.out.println("Litium: Packets is loading!");
    }
}