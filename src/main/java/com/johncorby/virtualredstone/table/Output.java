package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.circuit.Instance;
import org.bukkit.block.Sign;

public class Output extends com.johncorby.virtualredstone.circuit.Output {
    public Output(Sign sign, Integer identity, Instance parent) {
        super(sign, identity, parent);
    }
}
