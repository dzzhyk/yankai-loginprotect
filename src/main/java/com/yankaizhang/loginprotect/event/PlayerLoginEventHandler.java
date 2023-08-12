package com.yankaizhang.loginprotect.event;

import com.yankaizhang.loginprotect.LoginProtectMod;
import com.yankaizhang.loginprotect.players.PlayerManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerLoginEventHandler {

    @SubscribeEvent
    public static void onEntityAdded(final EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        World world = event.getWorld();
        if (entity instanceof EntityPlayer) {
            PlayerManager.getInstance().onPlayerLogin(world.getPlayerEntityByUUID(entity.getUniqueID()));
            if (LoginProtectMod.LoginProtectConfig.showProtectNote) {
                TextComponentString msg = new TextComponentString(String.format("你已经重生并拥有 %d 秒重生保护", LoginProtectMod.LoginProtectConfig.immutableSeconds));
                msg.getStyle().setBold(true).setColor(TextFormatting.RED);
                entity.sendMessage(msg);
            }
        }
    }
}
