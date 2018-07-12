package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.MessageHandler;
import org.bukkit.block.Sign;

public enum CircuitType {
    SEQUENCER,
    TABLE;

    public static CircuitType get(Sign sign) {
        switch (sign.getLine(0).toLowerCase()) {
            case "[sin]":
                return SEQUENCER;
            case "[sout]":
                return SEQUENCER;
            case "[tin]":
                return TABLE;
            case "[tout]":
                return TABLE;
            default:
                MessageHandler.warn("Line 1: invalid circuitType");
                return null;
        }
    }
}
