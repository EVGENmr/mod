package com.litium.hud;

import com.litium.common.SimplePacket;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.client.renderer.GlStateManager;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

public class Overlay extends SimplePacket {
    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onOverlayPre(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ARMOR || event.getType() == RenderGameOverlayEvent.ElementType.AIR || event.getType() == RenderGameOverlayEvent.ElementType.FOOD || event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent//(priority = EventPriority.NORMAL)
    public void RenderHp(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            //EntityPlayerSP player = this.mc.player;
            EntityPlayer player = this.mc.player;


            int w = event.getResolution().getScaledWidth();
            int h = event.getResolution().getScaledHeight();

            GlStateManager.pushMatrix();
            //GlStateManager.translate(0, 0, 0);
            GlStateManager.enableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.depthMask(true);
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            //GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("litium:textures/util.png"));
            Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(w / 8, h / 2, 0, 0, 42, 42, 256, 256);
            float Hp = player.getHealth() / player.getMaxHealth();
            if (Hp > 1) {
                Hp = 1;
            }
            int offsetHpY, offsetFoodY, offsetAirY, w1, h1;
            int sizeHealth = 22;

            offsetHpY = (int) Math.round(Hp * 22);
            w1 = (w / 8) + 10;
            h1 = (h / 2) + 10;
            if (player.hurtTime > 0 && player.hurtTime < 3) {
                Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(w1, h1, 42, sizeHealth, sizeHealth, sizeHealth, 256, 256);
            }
           if (mc.player.isPotionActive(Objects.requireNonNull(Potion.getPotionFromResourceLocation("wither")))) {
                Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(w1, h1 + sizeHealth - offsetHpY, 108, sizeHealth - offsetHpY, sizeHealth, offsetHpY, 256, 256);
            } else if (mc.player.isPotionActive(Objects.requireNonNull(Potion.getPotionFromResourceLocation("poison")))) {
                Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(w1, h1 + sizeHealth - offsetHpY, 86, sizeHealth - offsetHpY, sizeHealth, offsetHpY, 256, 256);
            }
            else {
                Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(w1, h1 + sizeHealth - offsetHpY, 42, sizeHealth - offsetHpY, sizeHealth, offsetHpY, 256, 256);
            }
            if (mc.player.isPotionActive(Objects.requireNonNull(Potion.getPotionFromResourceLocation("absorption")))) {
                if (player.getAbsorptionAmount() > 0) {
                    Hp = player.getAbsorptionAmount() / player.getMaxHealth();
                    if (Hp > 1) {
                        Hp = 1;
                    }
                    offsetHpY = (int) Math.round(Hp * 22);
                    Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture(w1, h1 + sizeHealth - offsetHpY, 64, sizeHealth - offsetHpY, sizeHealth, offsetHpY, 256, 256);
                }
            }

            float Food = (float) player.getFoodStats().getFoodLevel() / 20;

            offsetFoodY = (int) Math.round(Food * 34);
            Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) - 42, (h / 2), 0, 84, 42, 42, 256, 256);
            Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) - 42 + 4, (h / 2) + 34 - offsetFoodY + 4, 42, 88 + 34 - offsetFoodY, 34, offsetFoodY, 256, 256);

    if (player.isInsideOfMaterial(Material.WATER)) {
        float Air = (float) player.getAir() / 300;

        offsetAirY = (int) Math.round(Air * 34);
        Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8), (h / 2) - 42, 0, 84, 42, 42, 256, 256);
        Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) + 4, (h / 2) - 42 + 34 - offsetAirY + 4, 42, 130 + 34 - offsetAirY, 34, offsetAirY, 256, 256);
    }
    else {
        Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8), (h / 2) - 42, 0, 210, 42, 42, 256, 256);
    }

            Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) - 42, (h / 2) - 42, 0, 42, 42, 42, 256, 256);
        if (player.getEntityData().getBoolean("target")) {
            Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) - 42 + 12, (h / 2) + 12 - 42, 42, 46, 18, 18, 256, 256);
        }

            if (player.getEntityData().getInteger("mask") > 0) {
                Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8), (h / 2) + 42, 0, 168, 42, 42, 256, 256);
                switch (player.getEntityData().getInteger("mask")) {
                    case 1: Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) + 8, (h / 2) + 6 + 42, 127, 172, 26, 18, 256, 256);
                    break;
                    case 2: Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) + 8, (h / 2) + 6 + 42, 99, 172, 26, 18, 256, 256);
                    break;
                    case 3: Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) + 8, (h / 2) + 6 + 42, 71, 172, 26, 18, 256, 256);
                    break;
                    default: Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8) + 8, (h / 2) + 6 + 42, 43, 172, 26, 18, 256, 256);
                }
            }
            else {
                Minecraft.getMinecraft().ingameGUI.drawModalRectWithCustomSizedTexture((w / 8), (h / 2) + 42, 0, 210, 42, 42, 256, 256);
            }

            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.disableAlpha();
            //GlStateManager.disableBlend();
            //GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.popMatrix();
        }
    }
}
