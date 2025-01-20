package com.litium;

import com.litium.common.ModNetworkHandler;
import com.litium.common.PlayerDataSyncPacket;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class Synh {
    @SubscribeEvent
    public void Living(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (!entity.world.isRemote) {
            if (event != null && event.getEntity() instanceof EntityPlayerMP) {
                EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
                // Отправляем пакет с параметрами, записывая их сразу в буфер
                ModNetworkHandler.INSTANCE.sendTo(new PlayerDataSyncPacket(player), player);
                System.out.println("Litium: data in");
            }
        }
    }
}
