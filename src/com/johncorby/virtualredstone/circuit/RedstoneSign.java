package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.Common;
import com.johncorby.coreapi.util.MessageHandler;
import com.johncorby.coreapi.util.storedclass.IdentNode;
import com.johncorby.virtualredstone.sequencer.Input;
import com.johncorby.virtualredstone.sequencer.Output;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class RedstoneSign extends IdentNode<Integer, Instance, IdentNode> implements ConfigurationSerializable {
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

    @Nullable
    public static RedstoneSign get(Sign sign) {
        CircuitType circuitType = CircuitType.get(sign);
        if (circuitType == null) return null;

        SignType signType = SignType.get(sign);
        if (signType == null) return null;

        Integer num = Common.toInt(sign.getLine(3));
        if (num == null) {
            MessageHandler.warn("Line 4: invalid sign num");
            return null;
        }

        Instance inst = Instance.get(sign);
        if (inst == null) {
            MessageHandler.warn("Line 3: inst not found");
            return null;
        }

        RedstoneSign redstoneSign = get(circuitType, signType, num, inst);
        if (redstoneSign == null) redstoneSign = newInstance(circuitType, signType, sign, num, inst);
        return redstoneSign;
    }
    // Get RedstoneSign from sign

    public static RedstoneSign get(CircuitType circuitType, SignType signType, Integer identity, Instance parent) {
        if (circuitType == CircuitType.SEQUENCER) {
            if (signType == SignType.INPUT)
                return get(Input.class, identity, parent);
            return get(Output.class, identity, parent);
        }
        if (signType == SignType.INPUT)
            return get(com.johncorby.virtualredstone.table.Input.class, identity, parent);
        return get(com.johncorby.virtualredstone.table.Output.class, identity, parent);
    }

    @Nullable
    public static RedstoneSign signPlace(Sign sign) {
        return get(sign);
    }

    public static void signBreak(Sign sign) {
        // So you can break non-rs signs
        try {
            Objects.requireNonNull(get(sign)).dispose();

            Instance inst = Instance.get(sign);
            if (Objects.requireNonNull(inst).getChildren().isEmpty())
                inst.dispose();
        } catch (NullPointerException e) {
            MessageHandler.warn(e);
        }
    }

    public static void signPower(BlockRedstoneEvent event) {
        Sign sign = (Sign) event.getBlock().getState();

        Objects.requireNonNull(get(sign)).set(event.getNewCurrent() > 0);
    }

    public static RedstoneSign deserialize(Map<String, Object> map) {
        Location l = (Location) map.get("Location");
        Sign s = (Sign) l.getBlock().getState();

        return signPlace(s);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("Location", sign.getLocation());
        return map;
    }

    protected boolean create(Sign sign, Integer identity, Instance parent) {
        if (!super.create(identity, parent)) return false;
        this.sign = sign;
        Config.add("Signs", this);
        return true;
    }

    @Override
    protected final boolean create(Integer identity, Instance parent) {
        return true;
    }

    @Override
    public boolean dispose() {
        if (!stored()) return false;
        Config.remove("Signs", this);
        return super.dispose();
    }

    public abstract void set(boolean powered);

    public abstract boolean power();

    @Override
    public List<String> getDebug() {
        List<String> r = super.getDebug();
        r.add("Power: " + power());
        return r;
    }
}
