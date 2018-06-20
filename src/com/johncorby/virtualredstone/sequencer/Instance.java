package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.util.IdentifiableNode;

import java.util.Set;
import java.util.stream.Collectors;

public class Instance extends IdentifiableNode<Integer, Static, IdentifiableNode> {
    public Instance(Integer identity, Static parent) {
        super(identity, parent);
    }

    public static Instance get(Integer identity, Static parent) {
        return (Instance) get(Instance.class, identity, parent);
    }

    @Override
    protected boolean create(Integer identity, Static parent) {
        if (exists()) return false;
        return super.create(identity, parent);
    }

    public Set<Input> getInputs() {
        return children.stream()
                .filter(c -> c instanceof Input)
                .map(c -> (Input) c)
                .collect(Collectors.toSet());
    }

    public Set<Output> getOutputs() {
        return children.stream()
                .filter(c -> c instanceof Output)
                .map(c -> (Output) c)
                .collect(Collectors.toSet());
    }
}
