package com.johncorby.virtualredstone.circuit;

import org.bukkit.block.Sign;
import org.jetbrains.annotations.Nullable;

import static com.johncorby.coreapi.CoreApiPlugin.messageHandler;

public enum SignType {
    INPUT,
    OUTPUT;

    @Nullable
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
                messageHandler.warn("Line 1: invalid signType");
                return null;
        }
    }
}
