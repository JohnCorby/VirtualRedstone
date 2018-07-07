package com.johncorby.virtualredstone.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Static extends com.johncorby.virtualredstone.circuit.Static {
    public Map<Set<Integer>, Set<Integer>> combos = new HashMap<>();

    public Static(String identity) {
        super(identity, null);
    }

    @Override
    public List<String> getDebug() {
        List<String> r = super.getDebug();
        r.add("Combos: " + combos);
        return r;
    }
}
