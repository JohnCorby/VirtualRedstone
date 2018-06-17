package com.johncorby.virtualredstone.table;

import org.bukkit.Location;

public class Input extends com.johncorby.virtualredstone.block.Input {
    public Input(Location identity) {
        super(identity);
    }

    public static Input get(Location identity) {
        return (Input) get(Input.class, identity);
    }
}
