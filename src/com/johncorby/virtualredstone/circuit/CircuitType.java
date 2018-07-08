package com.johncorby.virtualredstone.circuit;

import org.bukkit.block.Sign;
import org.jetbrains.annotations.Nullable;

import static com.johncorby.coreapi.CoreApiPlugin.messageHandler;

public enum CircuitType {
    SEQUENCER,
    TABLE;

    @Nullable
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
                messageHandler.warn("Line 1: invalid circuitType");
                return null;
        }
    }
}
