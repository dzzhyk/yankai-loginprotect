package com.yankaizhang.loginprotect.event;

import com.yankaizhang.loginprotect.players.PlayerManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerEventHandler {
    private static final PlayerEventHandler instance = new PlayerEventHandler();

    public static PlayerEventHandler getInstance() {
        return instance;
    }

    @SubscribeEvent
    public void onWorldTick(final TickEvent.WorldTickEvent event) {
        if (!event.world.isRemote) {
            PlayerManager.getInstance().updatePlayers();
        }
    }

    @SubscribeEvent
    public void onLivingDamageEvent(LivingDamageEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            if (PlayerManager.getInstance().isPlayerImmune((EntityPlayer) event.getEntity())) {
                event.setAmount(0);
            }
        }
    }
}
