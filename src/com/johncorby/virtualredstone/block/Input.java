package com.johncorby.virtualredstone.block;

import com.johncorby.virtualredstone.util.Identifiable;
import org.bukkit.block.Sign;

public class Input extends Identifiable<Sign> {
    private boolean powered = false;

    public Input(Sign identity) {
        super(identity);
    }

    public static Input get(Sign identity) {
        return (Input) get(Input.class, identity);
    }

    public void set(boolean powered) {
        this.powered = powered;
        if (powered) {

        } else {

        }
    }
}
