package com.johncorby.virtualredstone.circuit;

import org.bukkit.block.Sign;

public enum SignType {
    INPUT,
    OUTPUT;

    public static SignType get(Sign sign) {
        switch (sign.getLine(0).toLowerCase()) {
            case "[in]":
                return INPUT;
            case "[out]":
                return OUTPUT;
            default:
                return null;
        }
    }
}
