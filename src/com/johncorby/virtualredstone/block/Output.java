package com.johncorby.virtualredstone.block;

import org.bukkit.Location;

public class Output extends SignLink {
    private boolean powering = false;

    public Output(Location identity) {
        super(identity);
    }

    public static Output get(Location identity) {
        return (Output) get(Output.class, identity);
    }

    public void set(boolean powering) {
        if (powering == this.powering) return;
        this.powering = powering;
        debug("Powering = " + powering);
    }
}

