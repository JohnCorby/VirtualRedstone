package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.circuit.Static;
import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Instance extends com.johncorby.virtualredstone.circuit.Instance {
    @NotNull
    protected Map<List<Input>, List<Output>> elements = new Hashtable<>();

    public Instance(Integer identity, com.johncorby.virtualredstone.circuit.Static parent) {
        super(identity, parent);
    }

    @Override
    protected boolean create(Integer identity, Static parent) {
        return super.create(identity, parent);
    }
}
