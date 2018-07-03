package com.johncorby.virtualredstone.util;

import com.johncorby.virtualredstone.circuit.Input;
import com.johncorby.virtualredstone.circuit.Output;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.johncorby.virtualredstone.VirtualRedstone.CONFIG;
import static com.johncorby.virtualredstone.VirtualRedstone.virtualRedstone;

public class Config {
    public Config() {
        // Setup
        virtualRedstone.getConfig().options().copyDefaults(true);
        virtualRedstone.saveConfig();

        // Load Statics

        // Load SignLocs
        for (Location l : getSignLocs()) {
            Sign s = (Sign) l.getBlock().getState();

            BlockPlaceEvent event = new BlockPlaceEvent(l.getBlock(), null, null, null, null, true, null);

            switch (s.getLine(0).toLowerCase()) {
                case "[sin]":
                    Input.signPlace(event);
                    break;
                case "[sout]":
                    Output.signPlace(event);
                    break;
                case "[tin]":
                    Input.signPlace(event);
                    break;
                case "[tout]":
                    Output.signPlace(event);
                    break;
            }
        }
    }

    private static Set<Location> getSignLocs() {
        return new HashSet(CONFIG.getList("SignLocs"));
    }

    public static void addSignLoc(Location l) {
        Set<Location> locs = getSignLocs();
        locs.add(l);
        CONFIG.set("SignLocs", new ArrayList<>(locs));
        virtualRedstone.saveConfig();
    }

    public static void removeSignLoc(Location l) {
        Set<Location> locs = getSignLocs();
        locs.remove(l);
        CONFIG.set("SignLocs", new ArrayList<>(locs));
        virtualRedstone.saveConfig();
    }


}
