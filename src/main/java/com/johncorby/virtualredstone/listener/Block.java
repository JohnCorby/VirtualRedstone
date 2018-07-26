package com.johncorby.virtualredstone.listener;

import com.johncorby.virtualredstone.circuit.Input;
import com.johncorby.virtualredstone.circuit.RedstoneSign;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;

public class Block implements Listener {
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Sign s = (Sign) event.getBlock().getState();

        s.setLine(0, event.getLine(0));
        s.setLine(1, event.getLine(1));
        s.setLine(2, event.getLine(2));
        s.setLine(3, event.getLine(3));
        s.update(true, false);
        onPlace(new BlockPlaceEvent(s.getBlock(), null, null, null, event.getPlayer(), true, null));
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        // Ignore if not sign
        if (!(event.getBlock().getState() instanceof Sign)) return;
        Sign s = (Sign) event.getBlock().getState();

        RedstoneSign.signPlace(s);
    }


    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        // Ignore if not sign
        if (!(event.getBlock().getState() instanceof Sign)) return;
        Sign s = (Sign) event.getBlock().getState();

        RedstoneSign.signBreak(s);
    }


    @EventHandler
    public void onRedstone(BlockRedstoneEvent event) {
        for (BlockFace f : new BlockFace[]{
                BlockFace.NORTH,
                BlockFace.SOUTH,
                BlockFace.EAST,
                BlockFace.WEST,
                BlockFace.UP,
                BlockFace.DOWN
        }) {
            BlockState bs = event.getBlock().getRelative(f).getState();

            // Ignore if not sign
            if (!(bs instanceof Sign)) continue;
            Sign s = (Sign) bs;

            switch (s.getLine(0).toLowerCase()) {
                case "[sin]":
                case "[tin]":
                    Input.signPower(event);
            }
        }
    }
}
