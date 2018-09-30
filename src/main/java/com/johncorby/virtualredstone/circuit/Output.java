package com.johncorby.virtualredstone.circuit;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Output extends RedstoneSign {
    public Output(Sign sign, int identity, Instance parent) {
        super(sign, identity, parent);
    }

    public Output(@NotNull Map<String, Object> map) {
        super(map);
    }

    public void setActive(boolean active) {
        if (this.active == active) return;
        this.active = active;
        debug("Active = " + active);

        if (active) sign.getBlock().setType(Material.REDSTONE_BLOCK);
        else sign.update(true, true);

        parent.onActiveStateChanged(SignType.OUTPUT, get(), active);
    }
}
