package com.johncorby.virtualredstone.circuit;

import com.johncorby.virtualredstone.util.Common;
import com.johncorby.virtualredstone.util.Config;
import com.johncorby.virtualredstone.util.storedclass.IdentNode;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class RedstoneSign extends IdentNode<Integer, Instance, IdentNode> {
    protected Sign sign;

    protected RedstoneSign(Sign sign, Integer identity, Instance parent) {
        super(identity, parent);
        create(sign, identity, parent);
    }

    public static RedstoneSign newInstance(CircuitType circuitType, SignType signType, Sign sign, Integer identity, Instance parent) {
        if (circuitType == CircuitType.SEQUENCER) {
            if (signType == SignType.INPUT)
                return new com.johncorby.virtualredstone.sequencer.Input(sign, identity, parent);
            return new com.johncorby.virtualredstone.sequencer.Output(sign, identity, parent);
        }
        if (signType == SignType.INPUT)
            return new com.johncorby.virtualredstone.table.Input(sign, identity, parent);
        return new com.johncorby.virtualredstone.table.Output(sign, identity, parent);
    }

    // Get RedstoneSign from sign
    @Nullable
    public static RedstoneSign get(Sign sign) {
        CircuitType circuitType = CircuitType.get(sign);
        if (circuitType == null) return null;

        SignType signType = SignType.get(sign);
        if (signType == null) return null;

        Integer num = Common.toInt(sign.getLine(3));
        if (num == null) return null;

        Instance inst = Instance.get(sign);
        if (inst == null) return null;

        RedstoneSign redstoneSign = get(circuitType, signType, num, inst);
        if (redstoneSign == null) redstoneSign = newInstance(circuitType, signType, sign, num, inst);
        return redstoneSign;
    }

    public static RedstoneSign get(CircuitType circuitType, SignType signType, Integer identity, Instance parent) {
        if (circuitType == CircuitType.SEQUENCER) {
            if (signType == SignType.INPUT)
                return (RedstoneSign) get(com.johncorby.virtualredstone.sequencer.Input.class, identity, parent);
            return (RedstoneSign) get(com.johncorby.virtualredstone.sequencer.Output.class, identity, parent);
        }
        if (signType == SignType.INPUT)
            return (RedstoneSign) get(com.johncorby.virtualredstone.table.Input.class, identity, parent);
        return (RedstoneSign) get(com.johncorby.virtualredstone.table.Output.class, identity, parent);
    }


    public static void signPlace(BlockPlaceEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        get(sign);
    }

    public static void signBreak(BlockBreakEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        // So you can break non-rs signs
        try {
            Objects.requireNonNull(get(sign)).dispose();
        } catch (NullPointerException e) {
            return;
        }

        Instance inst = Instance.get(sign);
        if (Objects.requireNonNull(inst).getChildren().isEmpty())
            inst.dispose();
    }

    public static void signPower(BlockRedstoneEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        Objects.requireNonNull(get(sign)).set(event.getNewCurrent() > 0);
    }

    protected boolean create(Sign sign, Integer identity, Instance parent) {
        if (!super.create(identity, parent)) return false;
        this.sign = sign;
        Config.addSignLoc(sign.getLocation());
        return true;
    }

    @Override
    protected final boolean create(Integer identity, Instance parent) {
        return true;
    }

    @Override
    public boolean dispose() {
        if (!exists()) return false;
        Config.removeSignLoc(sign.getLocation());
        return super.dispose();
    }

    public abstract void set(boolean powered);
}
