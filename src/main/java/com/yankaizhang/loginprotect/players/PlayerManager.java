package com.yankaizhang.loginprotect.players;

import com.yankaizhang.loginprotect.LoginProtectMod;
import com.yankaizhang.loginprotect.event.PlayerEventHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 用户进入世界时的管理器
 */
public class PlayerManager {

    private static PlayerManager instance;

    /**
     * 存储登录用户
     */
    private final Map<EntityPlayer, PlayerData> playerDataMap = new HashMap<>();

    private PlayerManager() {
    }

    public static PlayerManager getInstance() {
        if (PlayerManager.instance == null) {
            PlayerManager.instance = new PlayerManager();
        }
        return PlayerManager.instance;
    }

    /**
     * 用户进入世界时，新增用户信息
     */
    public void onPlayerLogin(final EntityPlayer player) {
        playerDataMap.put(player, new PlayerData(player, player.getPosition(), System.currentTimeMillis()));
        MinecraftForge.EVENT_BUS.register(PlayerEventHandler.getInstance());
    }

    /**
     * 检查用户距离创建点的方块距离，并且按需移除无敌
     */
    public void updatePlayers() {
        if (playerDataMap.isEmpty()) {
            return;
        }

        final int maxDist = LoginProtectMod.LoginProtectConfig.maxDist * LoginProtectMod.LoginProtectConfig.maxDist;

        Iterator<Map.Entry<EntityPlayer, PlayerData>> iterator = playerDataMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<EntityPlayer, PlayerData> entry = iterator.next();
            EntityPlayer player = entry.getKey();
            PlayerData playerData = entry.getValue();
            if (distance2D(playerData.loginPos, player.getPosition()) > maxDist ||
                    playerData.immutableBeginTime + LoginProtectMod.LoginProtectConfig.immutableSeconds * 1000L <= System.currentTimeMillis()) {
                player.hurtResistantTime = 0;
                iterator.remove();
                if (LoginProtectMod.LoginProtectConfig.showProtectNote) {
                    TextComponentString msg = new TextComponentString("你的重生保护已失效");
                    msg.getStyle().setBold(true).setColor(TextFormatting.RED);
                    player.sendMessage(msg);
                }
            }
        }

        if (playerDataMap.isEmpty()) {
            MinecraftForge.EVENT_BUS.unregister(PlayerEventHandler.getInstance());
        }
    }

    /**
     * 判断用户是否免疫伤害中
     */
    public boolean isPlayerImmune(final EntityPlayer EntityPlayer) {
        return playerDataMap.containsKey(EntityPlayer);
    }


    private int distance2D(final BlockPos a, final BlockPos b) {
        final int xDiff = a.getX() - b.getX();
        final int zDiff = a.getZ() - b.getZ();

        return xDiff * xDiff + zDiff * zDiff;
    }
}
