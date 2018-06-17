package com.johncorby.virtualredstone.command;

import com.johncorby.virtualredstone.sequencer.Sequencer;
import com.johncorby.virtualredstone.table.Table;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static com.johncorby.virtualredstone.util.MessageHandler.MessageType.GENERAL;
import static com.johncorby.virtualredstone.util.MessageHandler.commandError;
import static com.johncorby.virtualredstone.util.MessageHandler.msg;

public class Add extends BaseCommand {
    Add() {
        super("Add a sequencer or table", "<name>", "vrs.admin");
        TabCompleteHandler.register(getName(), 0, () -> Arrays.asList("sequencer", "table"));
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        if (args.length == 0) return commandError(sender, "You must say whether to add a sequencer or table");

        if (args.length == 1) return commandError(sender, "You must supply a name");

        switch (args[0]) {
            case "sequencer":
                try {
                    Sequencer.get(args[1]);
                } catch (Exception e) {
                    new Sequencer(args[1]);
                    msg(sender, GENERAL, "Added sequencer " + args[1]);
                    break;
                }

                return commandError(sender, "Sequencer " + args[1] + " already exists");
            case "table":
                try {
                    Table.get(args[1]);
                } catch (Exception e) {
                    new Table(args[1]);
                    msg(sender, GENERAL, "Added table " + args[1]);
                    break;
                }

                return commandError(sender, "Table " + args[1] + " already exists");
            default:
                return commandError(sender, "You must say whether to add a sequencer or table");
        }
        return true;
    }
}
