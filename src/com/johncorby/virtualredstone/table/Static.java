package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.util.Class;
import com.johncorby.virtualredstone.util.Identifiable;

import java.util.ArrayList;
import java.util.List;

public class Static extends Identifiable<String> {
    public Static(String identity) {
        super(identity);
    }

    public static Static get(String identity) {
        return (Static) get(Static.class, identity);
    }

    public List<Input> getInputs() {
        List<Input> r = new ArrayList<>();
        for (Class c : classes) {
            if (!c.getClass().equals(Input.class)) continue;
            Input i = (Input) c;
            if (i.getTab().equals(this)) r.add(i);
        }
        return r;
    }

    public List<Output> getOutputs() {
        List<Output> r = new ArrayList<>();
        for (Class c : classes) {
            if (!c.getClass().equals(Output.class)) continue;
            Output i = (Output) c;
            if (i.getTab().equals(this)) r.add(i);
        }
        return r;
    }
}
