package com.johncorby.virtualredstone.circuit;

import com.johncorby.virtualredstone.util.storedclass.IdentNode;
import org.bukkit.block.Sign;
import org.jetbrains.annotations.Nullable;

public abstract class Static extends IdentNode<String, IdentNode, Instance> {
    protected Static(String identity, IdentNode parent) {
        super(identity, parent);
    }

    public static Static newInstance(CircuitType circuitType, String identity) {
        if (circuitType == CircuitType.SEQUENCER)
            return new com.johncorby.virtualredstone.sequencer.Static(identity);
        return new com.johncorby.virtualredstone.table.Static(identity);
    }

    public static Static get(CircuitType circuitType, String identity) {
        if (circuitType == CircuitType.SEQUENCER)
            return (Static) get(com.johncorby.virtualredstone.sequencer.Static.class, identity);
        return (Static) get(com.johncorby.virtualredstone.table.Static.class, identity);
    }

    // Get RedstoneSign from sign
    @Nullable
    public static Static get(Sign sign) {
        CircuitType circuitType = CircuitType.get(sign);
        if (circuitType == null) return null;

        String statName = sign.getLine(1).toLowerCase();
        if (statName.isEmpty()) return null;

        return Static.get(circuitType, statName);
    }
}
