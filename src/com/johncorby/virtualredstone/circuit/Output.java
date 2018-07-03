package com.johncorby.virtualredstone.circuit;

import org.bukkit.Material;
import org.bukkit.block.Sign;

public abstract class Output extends RedstoneSign {
    protected boolean powering = false;

    protected Output(Sign sign, Integer identity, Instance parent) {
        super(sign, identity, parent);
    }

    public void set(boolean powering) {
        if (powering == this.powering) return;
        this.powering = powering;
        debug("powering = " + powering);

        if (powering) sign.getBlock().setType(Material.REDSTONE_BLOCK);
        else sign.update(true, true);
    }
}