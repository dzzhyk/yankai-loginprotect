package com.yankaizhang.loginprotect.players;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class PlayerData {
    public EntityPlayer player;
    public BlockPos loginPos;
    public long immutableBeginTime;

    public PlayerData(final EntityPlayer player, final BlockPos loginPos, final long immutableBeginTime) {
        this.player = player;
        this.loginPos = loginPos;
        this.immutableBeginTime = immutableBeginTime;
    }
}
