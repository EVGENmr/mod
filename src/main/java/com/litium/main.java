package com.litium;

import com.litium.common.ModNetworkHandler;
import com.litium.common.PlayerDataSyncPacket;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import static com.litium.common.ModNetworkHandler.INSTANCE;

@Mod(modid = main.MODID, name = main.NAME, version = main.VERSION)

public class main
{

    //public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(main.MODID);
    //public static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper(main.MODID);

    public static final String MODID = "litium";
    public static final String NAME = "litium core";
    public static final String VERSION = "1.0";

    @SidedProxy(clientSide = "com.litium.ClientProxy", serverSide = "com.litium.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}

