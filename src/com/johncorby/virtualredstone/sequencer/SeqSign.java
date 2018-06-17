package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.block.SignLink;
import org.bukkit.Location;

public class SeqSign extends SignLink {
    private Sequencer seq;

    public SeqSign(Location identity) {
        super(identity);
    }

    public static SeqSign get(Location identity) {
        return (SeqSign) get(SeqSign.class, identity);
    }
}
