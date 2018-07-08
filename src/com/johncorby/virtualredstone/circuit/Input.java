package com.johncorby.virtualredstone.circuit;

import org.bukkit.block.Sign;

import static com.johncorby.coreapi.CoreApiPlugin.messageHandler;

public abstract class Input extends RedstoneSign {
    protected boolean powered = false;

    protected Input(Sign sign, Integer identity, Instance parent) {
        super(sign, identity, parent);
    }

    public void set(boolean powered) {
        if (powered == this.powered) return;
        this.powered = powered;
        messageHandler.debug("Powered = " + powered);
    }

    @Override
    public boolean power() {
        return powered;
    }
}
