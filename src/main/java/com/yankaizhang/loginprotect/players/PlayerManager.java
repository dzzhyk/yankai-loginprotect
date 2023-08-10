package com.yankaizhang.loginprotect.players;

import com.yankaizhang.loginprotect.LoginProtectMod;
import com.yankaizhang.loginprotect.utils.BlockPosUtils;
import com.yankaizhang.loginprotect.event.PlayerEventHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 用户进入世界时的管理器
 */
public class PlayerManager {

    private static PlayerManager instance;

    /**
     * Stores the logged players, allows gc deletion
     */
    private final WeakHashMap<EntityPlayer, PlayerData> playerDataMap = new WeakHashMap<>();

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
        playerDataMap.put(player, new PlayerData(player, player.getPosition(), LoginProtectMod.LoginProtectConfig.immutableTicks));
        MinecraftForge.EVENT_BUS.register(PlayerEventHandler.getInstance());
    }

    /**
     * 检查用户距离创建点的方块距离，并且按需移除无敌
     */
    public void updatePlayers() {
        if (playerDataMap.isEmpty()) {
            return;
        }

        final double maxDist = Math.pow(LoginProtectMod.LoginProtectConfig.maxDist, 2);

        Iterator<Map.Entry<EntityPlayer, PlayerData>> iterator = playerDataMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<EntityPlayer, PlayerData> entry = iterator.next();

            if (BlockPosUtils.dist2DSQ(entry.getValue().loginPos, entry.getKey().getPosition()) > maxDist || entry.getValue().immutableTicks-- <= 0) {
                entry.getKey().hurtResistantTime = 0;
                iterator.remove();
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
}
