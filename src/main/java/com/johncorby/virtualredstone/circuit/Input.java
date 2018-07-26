package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.MessageHandler;
import org.bukkit.block.Sign;

public abstract class Input extends RedstoneSign {
    private boolean powered = false;

    protected Input(Sign sign, Integer identity, Instance parent) {
        super(sign, identity, parent);
    }

    public void set(boolean powered) {
        if (powered == this.powered) return;
        this.powered = powered;
        MessageHandler.debug("Powered = " + powered);
    }

    @Override
    public boolean power() {
        return powered;
    }
}
