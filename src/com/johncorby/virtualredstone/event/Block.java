package com.johncorby.virtualredstone.event;

import com.johncorby.virtualredstone.sequencer.Instance;
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
        // Ignore if not sign
        if (!(event.getBlock().getState() instanceof Sign)) return;
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

        switch (s.getLine(0).toLowerCase()) {
            case "[sin]":
                com.johncorby.virtualredstone.sequencer.Input.signPlace(event);
                break;
            case "[sout]":
                com.johncorby.virtualredstone.sequencer.Output.signPlace(event);
                break;
//            case "[tin]":
//                new com.johncorby.virtualredstone.table.Input(
//                        s.getLocation(),
//                        Static.get(s.getLine(1)),
//                        toInt(s.getLine(2)),
//                        toInt(s.getLine(3))
//                );
//                break;
//            case "[tout]":
//                new com.johncorby.virtualredstone.table.Output(
//                        s.getLocation(),
//                        Static.get(s.getLine(1)),
//                        toInt(s.getLine(2)),
//                        toInt(s.getLine(3))
//                );
//                break;
        }
    }


    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        // Ignore if not sign
        if (!(event.getBlock().getState() instanceof Sign)) return;
        Sign s = (Sign) event.getBlock().getState();

        Instance seqInst;
        switch (s.getLine(0).toLowerCase()) {
            case "[sin]":
                com.johncorby.virtualredstone.sequencer.Input.signBreak(event);
                break;
            case "[sout]":
                com.johncorby.virtualredstone.sequencer.Output.signBreak(event);
                break;
//            case "[tin]":
//                com.johncorby.virtualredstone.table.Input.get(
//                        s.getLocation(),
//                        Static.get(s.getLine(1)),
//                        toInt(s.getLine(2)),
//                        toInt(s.getLine(3))
//                ).dispose();
//                break;
//            case "[tout]":
//                com.johncorby.virtualredstone.table.Output.get(
//                        s.getLocation(),
//                        Static.get(s.getLine(1)),
//                        toInt(s.getLine(2)),
//                        toInt(s.getLine(3))
//                ).dispose();
//                break;
        }
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
                    com.johncorby.virtualredstone.sequencer.Input.signPower(event);
                    break;
//                case "[tin]":
//                    com.johncorby.virtualredstone.table.Input.get(
//                            s.getLocation(),
//                            Static.get(s.getLine(1)),
//                            toInt(s.getLine(2)),
//                            toInt(s.getLine(3))
//                    ).set(event.getNewCurrent() > 0);
//                    break;
            }
        }
    }
}
