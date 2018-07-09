package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.circuit.Instance;
import org.bukkit.block.Sign;

public class Input extends com.johncorby.virtualredstone.circuit.Input {
    public Input(Sign sign, Integer identity, Instance parent) {
        super(sign, identity, parent);
    }
}
