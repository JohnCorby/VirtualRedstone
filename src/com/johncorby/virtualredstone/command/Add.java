package com.johncorby.virtualredstone.command;

import org.bukkit.entity.Player;

import java.util.Arrays;

import static com.johncorby.virtualredstone.util.MessageHandler.commandError;

public class Add extends BaseCommand {
    Add() {
        super("Add a sequencer or table", "<name>", "gg.admin");
        TabCompleteHandler.register(getName(), 0, () -> Arrays.asList("sequencer", "table"));
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        if (args.length == 0) return commandError(sender, "You must say whether to add a sequencer or table");

        if (args.length == 1) return commandError(sender, "You must supply a name");

        switch (args[1]) {
            case "sequencer":
                break;
            case "table":
                break;
            default:
                break;
        }
        return true;
    }
}
