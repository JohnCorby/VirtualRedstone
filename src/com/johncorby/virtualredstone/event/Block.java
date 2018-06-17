package com.johncorby.virtualredstone.event;

import com.johncorby.virtualredstone.block.Input;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

public class Block implements Listener {
    @EventHandler
    public void onSignChange(BlockPlaceEvent event) {
        // Ignore if not sign
        if (!(event instanceof Sign)) return;
        Sign s = (Sign) event;

        // Ignore if not in/out sign
        if (!s.getLine(0).equalsIgnoreCase("[in]") &&
                !s.getLine(0).equals("[out]")) return;

        // Get
        String seq = s.getLine(0).toLowerCase();


    }


    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        // Ignore if not sign
        if (!(event instanceof Sign)) return;
        Sign s = (Sign) event;

        // Ignore if not in/out sign
        if (!s.getLine(0).equalsIgnoreCase("[in]") &&
                !s.getLine(0).equals("[out]")) return;

        // Get
    }


    @EventHandler
    public void onRedstone(BlockRedstoneEvent event) {
        // Ignore if not sign
        if (!(event instanceof Sign)) return;
        Sign s = (Sign) event;

        // Ignore if not in sign
        if (!s.getLine(0).equalsIgnoreCase("[in]")) return;

        // Set power
        Input.get(s).set(s.getBlock().isBlockPowered() || s.getBlock().isBlockIndirectlyPowered());
    }
}
