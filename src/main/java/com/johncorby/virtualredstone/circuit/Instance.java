package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.Common;
import com.johncorby.coreapi.util.MessageHandler;
import com.johncorby.coreapi.util.storedclass.IdentNode;
import org.bukkit.block.Sign;

import java.util.Set;

import static com.johncorby.coreapi.util.Common.*;

public abstract class Instance extends IdentNode<Integer, Static, RedstoneSign> {
    protected Instance(Integer identity, Static parent) {
        super(identity, parent);
    }

    public static Instance newInstance(CircuitType circuitType, Integer identity, Static parent) {
        if (circuitType == CircuitType.SEQUENCER)
            return new com.johncorby.virtualredstone.sequencer.Instance(identity, parent);
        return new com.johncorby.virtualredstone.table.Instance(identity, parent);
    }


    public static Instance get(CircuitType circuitType, Integer identity, Static parent) {
        if (circuitType == CircuitType.SEQUENCER)
            return get(com.johncorby.virtualredstone.sequencer.Instance.class, identity, parent);
        return get(com.johncorby.virtualredstone.table.Instance.class, identity, parent);
    }

    // Get RedstoneSign from sign
    public static Instance get(Sign sign) {
        CircuitType circuitType = CircuitType.get(sign);
        if (circuitType == null) return null;

        Integer instNum = Common.toInt(sign.getLine(2));
        if (instNum == null) {
            MessageHandler.warn("Line 3: invalid inst num");
            return null;
        }

        Static stat = Static.get(sign);
        if (stat == null) {
            MessageHandler.warn("Line 2: stat not found");
            return null;
        }

        Instance inst = Instance.get(circuitType, instNum, stat);
        if (inst == null) inst = Instance.newInstance(circuitType, instNum, stat);
        return inst;
    }

    public Set<Input> getInputs() {
        return toSet(map(filter(children, c -> c instanceof Input), c -> (Input) c));
    }

    public Set<Output> getOutputs() {
        return toSet(map(filter(children, c -> c instanceof Output), c -> (Output) c));
    }
}
