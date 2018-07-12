package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.MessageHandler;
import org.bukkit.block.Sign;

public enum SignType {
    INPUT,
    OUTPUT;

    public static SignType get(Sign sign) {
        switch (sign.getLine(0).toLowerCase()) {
            case "[sin]":
                return INPUT;
            case "[sout]":
                return OUTPUT;
            case "[tin]":
                return INPUT;
            case "[tout]":
                return OUTPUT;
            default:
                MessageHandler.warn("Line 1: invalid signType");
                return null;
        }
    }
}
