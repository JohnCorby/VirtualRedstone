package com.johncorby.virtualredstone.arenaapi.arena;

import com.johncorby.virtualredstone.VirtualRedstone;
import org.bukkit.Location;

import static com.johncorby.virtualredstone.VirtualRedstone.virtualRedstone;

public class LobbyHandler {
    // Set lobby loc
    public static void set(Location lobbyLoc) {
        Integer[] iL = new Integer[]{lobbyLoc.getBlockX(), lobbyLoc.getBlockY(), lobbyLoc.getBlockZ(), (int) lobbyLoc.getYaw(), (int) lobbyLoc.getPitch()};
        VirtualRedstone.CONFIG.set("Lobby", iL);
        virtualRedstone.saveConfig();
    }

    // Get lobby loc
    public static Location get() {
        // Try to get loc from config
        Integer[] iL = VirtualRedstone.CONFIG.getIntegerList("Lobby").toArray(new Integer[0]);
        if (iL.length == 0) return null;
        return new Location(VirtualRedstone.WORLD, iL[0] + .5, iL[1], iL[2] + .5, iL[3], iL[4]);
    }
}
