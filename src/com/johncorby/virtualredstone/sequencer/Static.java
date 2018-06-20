package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.util.IdentifiableNode;

public class Static extends IdentifiableNode<String, IdentifiableNode, Instance> {
    public Static(String identity) {
        super(identity, null);
    }

    public static Static get(String identity) {
        return (Static) get(Static.class, identity, null);
    }
}
