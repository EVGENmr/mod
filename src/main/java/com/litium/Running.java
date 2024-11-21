package com.litium;

import net.minecraft.client.Minecraft;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class Running {
    private final Minecraft mc = Minecraft.getMinecraft();
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.CLIENT && this.mc.player != null) {
            if (this.mc.currentScreen != null) {
                return;
            }

            EntityPlayerSP player = this.mc.player;

            if (Keyboard.isKeyDown(this.mc.gameSettings.keyBindSprint.getKeyCode())) {
                if (player.movementInput.moveForward > 0.0F && !player.isSneaking() && player.getFoodStats().getFoodLevel() > 3.0F) {
                    player.setSprinting(true);
                }
            } else {
                player.setSprinting(false);
            }
        }
    }
}
