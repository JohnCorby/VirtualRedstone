package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.storedclass.IdentNode;
import org.bukkit.block.Sign;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class Static extends IdentNode<String, IdentNode, Instance> implements ConfigurationSerializable {
    protected Static(String identity) {
        super(identity, null);
    }

    public static Static newInstance(CircuitType circuitType, String identity) {
        if (circuitType == CircuitType.SEQUENCER)
            return new com.johncorby.virtualredstone.sequencer.Static(identity);
        return new com.johncorby.virtualredstone.table.Static(identity);
    }

    @Nullable
    public static Static get(CircuitType circuitType, String identity) {
        if (circuitType == CircuitType.SEQUENCER)
            return get(com.johncorby.virtualredstone.sequencer.Static.class, identity);
        return get(com.johncorby.virtualredstone.table.Static.class, identity);
    }

    // Get RedstoneSign from sign
    public static Static get(@NotNull Sign sign) {
        CircuitType circuitType = CircuitType.get(sign);
        if (circuitType == null) return null;

        String statName = sign.getLine(1).toLowerCase();
        if (statName.isEmpty()) return null;

        return get(circuitType, statName);
    }

    public static Static deserialize(Map<String, Object> map) {
        return newInstance(CircuitType.valueOf((String) map.get("CircuitType")), (String) map.get("Name"));
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("Name", get());
        if (getClass().equals(com.johncorby.virtualredstone.sequencer.Static.class))
            map.put("CircuitType", "SEQUENCER");
        else
            map.put("CircuitType", "TABLE");
        return map;
    }

    @Override
    protected boolean create(String identity, IdentNode parent) {
        if (!super.create(identity, parent)) return false;
        Config.add("Statics", this);
        return true;
    }

    @Override
    public boolean dispose() {
        if (!stored()) return false;
        Config.add("Statics", this);
        return super.dispose();
    }
}
