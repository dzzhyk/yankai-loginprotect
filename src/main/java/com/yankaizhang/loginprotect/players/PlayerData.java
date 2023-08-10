package com.yankaizhang.loginprotect.players;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

/**
 * Holds the needed player data
 */
public class PlayerData {
    public EntityPlayer player;
    public BlockPos loginPos;
    public int immutableTicks;

    public PlayerData(final EntityPlayer player, final BlockPos loginPos, final int immutableTicks) {
        this.player = player;
        this.loginPos = loginPos;
        this.immutableTicks = immutableTicks;
    }
}
