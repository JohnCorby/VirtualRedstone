package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.util.Identifiable;

import javax.annotation.Nonnull;

public class Sequencer extends Identifiable<String> {
    public Sequencer(@Nonnull String identity) {
        super(identity);
    }
}
