package com.johncorby.virtualredstone.block;

import org.bukkit.Location;

public class Input extends SignLink {
    private boolean powered = false;

    public Input(Location identity) {
        super(identity);
    }

    public static Input get(Location identity) {
        return (Input) get(Input.class, identity);
    }

    public void set(boolean powered) {
        if (powered == this.powered) return;
        this.powered = powered;
        debug("Powered = " + powered);
    }
}
