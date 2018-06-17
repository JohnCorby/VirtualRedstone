package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.util.Identifiable;

public class Sequencer extends Identifiable<String> {
    public Sequencer(String identity) {
        super(identity);
    }

    public static Sequencer get(String identity) {
        return (Sequencer) get(Sequencer.class, identity);
    }
}
