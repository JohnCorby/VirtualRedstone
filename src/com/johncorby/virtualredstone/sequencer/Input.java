package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.util.Common;
import com.johncorby.virtualredstone.util.IdentifiableNode;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

public class Input extends IdentifiableNode<Integer, Instance, IdentifiableNode> {
    private boolean powered = false;

    public Input(Integer identity, Instance parent) {
        super(identity, parent);
    }

    public static Input get(Integer identity, Instance parent) {
        return (Input) get(Input.class, identity, parent);
    }

    public static void signPlace(BlockPlaceEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        String statName = sign.getLine(1).toLowerCase();
        Integer instNum = Common.toInt(sign.getLine(2));
        Integer inNum = Common.toInt(sign.getLine(3));

        Static stat = Static.get(statName);
        Instance inst = Instance.get(instNum, stat);
        if (inst == null) inst = new Instance(inNum, stat);
        new Input(inNum, inst);
    }

    public static void signBreak(BlockBreakEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        String statName = sign.getLine(1).toLowerCase();
        Integer instNum = Common.toInt(sign.getLine(2));
        Integer inNum = Common.toInt(sign.getLine(3));

        Static stat = Static.get(statName);
        Instance inst = Instance.get(instNum, stat);
        Input.get(inNum, inst).dispose();
    }

    public static void signPower(BlockRedstoneEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        String statName = sign.getLine(1);
        Integer instNum = Common.toInt(sign.getLine(2));
        Integer inNum = Common.toInt(sign.getLine(3));

        Static sequencer = Static.get(statName);
        Instance inst = Instance.get(instNum, sequencer);
        Input.get(inNum, inst).set(event.getNewCurrent() > 0);
    }

    @Override
    protected boolean create(Integer identity, Instance parent) {
        if (exists()) return false;

        return super.create(identity, parent);
    }

    public void set(boolean powered) {
        if (powered == this.powered) return;
        this.powered = powered;
        debug("Powered = " + powered);
    }

    @Override
    public boolean dispose() {
        if (parent.getChildren().isEmpty()) parent.dispose();
        return super.dispose();
    }
}
