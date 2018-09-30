package com.johncorby.virtualredstone.circuit;

import org.bukkit.block.Sign;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Input extends RedstoneSign {
    public Input(Sign sign, int identity, Instance parent) {
        super(sign, identity, parent);
    }

    public Input(@NotNull Map<String, Object> map) {
        super(map);
    }

    public void setActive(boolean active) {
        if (this.active == active) return;
        this.active = active;
        debug("Active = " + active);

        parent.onActiveStateChanged(SignType.INPUT, get(), active);
    }
}
