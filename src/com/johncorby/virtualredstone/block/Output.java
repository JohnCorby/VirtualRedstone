package com.johncorby.virtualredstone.block;

import com.johncorby.virtualredstone.util.Identifiable;
import org.bukkit.block.Sign;

public class Output extends Identifiable<Sign> {
    private boolean powering = false;

    public Output(Sign identity) {
        super(identity);
    }

    public static Output get(Sign identity) {
        return (Output) get(Output.class, identity);
    }

    public void set(boolean powering) {
        this.powering = powering;
        if (powering) {

        } else {

        }
    }
}

