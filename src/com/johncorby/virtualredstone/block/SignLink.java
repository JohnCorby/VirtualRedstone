package com.johncorby.virtualredstone.block;

import com.johncorby.virtualredstone.util.Identifiable;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import javax.annotation.Nonnull;

public class SignLink extends Identifiable<Location> {
    public SignLink(@Nonnull Location identity) {
        super(identity);
    }

    public static SignLink get(Location identity) {
        return (SignLink) get(SignLink.class, identity);
    }

    @Override
    protected boolean available() {
        // Return if block at loc not sign anymore
        if (!(identity.getBlock().getState() instanceof Sign)) return false;
        return super.available();
    }
}
