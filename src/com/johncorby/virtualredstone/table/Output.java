package com.johncorby.virtualredstone.table;

import org.bukkit.Location;

public class Output extends com.johncorby.virtualredstone.block.Output {
    public Output(Location identity) {
        super(identity);
    }

    public static Output get(Location identity) {
        return (Output) get(Output.class, identity);
    }
}

