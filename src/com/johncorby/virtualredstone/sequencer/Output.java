package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.util.Common;
import com.johncorby.virtualredstone.util.IdentifiableNode;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

public class Output extends IdentifiableNode<Integer, Instance, IdentifiableNode> {
    private boolean powering = false;

    public Output(Integer identity, Instance parent) {
        super(identity, parent);
    }

    public static Output get(Integer identity, Instance parent) {
        return (Output) get(Output.class, identity, parent);
    }

    public static void signPlace(BlockPlaceEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        String statName = sign.getLine(1).toLowerCase();
        Integer instNum = Common.toInt(sign.getLine(2));
        Integer inNum = Common.toInt(sign.getLine(3));

        Static stat = Static.get(statName);
        Instance inst = Instance.get(instNum, stat);
        if (inst == null) inst = new Instance(inNum, stat);
        new Output(inNum, inst);
    }

    public static void signBreak(BlockBreakEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        String statName = sign.getLine(1).toLowerCase();
        Integer instNum = Common.toInt(sign.getLine(2));
        Integer inNum = Common.toInt(sign.getLine(3));

        Static stat = Static.get(statName);
        Instance inst = Instance.get(instNum, stat);
        Output.get(inNum, inst).dispose();
    }

    public static void signPower(BlockRedstoneEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        String statName = sign.getLine(1);
        Integer instNum = Common.toInt(sign.getLine(2));
        Integer inNum = Common.toInt(sign.getLine(3));

        Static sequencer = Static.get(statName);
        Instance inst = Instance.get(instNum, sequencer);
        Output.get(inNum, inst).set(event.getNewCurrent() > 0);
    }

    public void set(boolean powering) {
        if (powering == this.powering) return;
        this.powering = powering;
        debug("powering = " + powering);
    }

    @Override
    public boolean dispose() {
        if (parent.getChildren().isEmpty()) parent.dispose();
        return super.dispose();
    }
}
