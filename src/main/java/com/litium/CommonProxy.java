package com.litium;

import com.litium.common.ModNetworkHandler;
import com.litium.hud.Overlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import static com.litium.common.ModNetworkHandler.INSTANCE;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        ModNetworkHandler.registerMessages();
        MinecraftForge.EVENT_BUS.register(new Synh());
    }

    public void init(FMLInitializationEvent event) {}

    public void postInit(FMLPostInitializationEvent event) {}
}
