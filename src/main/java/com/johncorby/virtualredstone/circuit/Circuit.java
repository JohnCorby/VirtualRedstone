package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.Config;
import com.johncorby.coreapi.util.storedclass.ConfigIdentNode;
import org.bukkit.block.Sign;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static com.johncorby.coreapi.CoreApiPlugin.PLUGIN;

public class Circuit extends ConfigIdentNode<String, ConfigIdentNode, Instance> {
    public Circuit(String identity) {
        super(identity, null);
        create();
    }

    public Circuit(@NotNull Map<String, Object> map) {
        super(map);
    }

    @Nullable
    public static Circuit get(String identity) {
        return get(Circuit.class, identity);
    }

    // Get Circuit from sign
    @Nullable
    public static Circuit get(Sign sign) {
        String circuitName = sign.getLine(1).toLowerCase();
        return get(circuitName);
    }

    @Override
    public Map<String, Object> serialize() {
        return super.serialize();
    }

    @Override
    public void configAdd() {
        Config.addSet("Circuits", this);
        PLUGIN.saveConfig();
    }

    @Override
    public boolean configRemove() {
        Config.removeSet("Circuits", this);
        PLUGIN.saveConfig();
        return dispose();
    }
}
