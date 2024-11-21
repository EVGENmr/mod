package com.litium;

//import com.litium.common.ServerMessagePacket;
import com.litium.hud.Overlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        //MinecraftForge.EVENT_BUS.register(new NetworkHandler());
       //main.NETWORK.registerMessage(new ServerMessagePacket.Handler(), ServerMessagePacket.class, 0, Side.SERVER);
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

}
