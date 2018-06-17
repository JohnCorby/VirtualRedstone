package com.johncorby.virtualredstone.command;

import com.johncorby.virtualredstone.util.MessageHandler;
import org.bukkit.entity.Player;

public class Add extends BaseCommand {
    Add() {
        super("Add an arena", "<name>", "gg.admin");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        // Error if no name given
        if (args.length == 0) return MessageHandler.commandError(sender, "You must give a name for the arena");

        // Add arena
        return true;
        //return new SetRegion(sender, args[0], true).exists();
    }
}
