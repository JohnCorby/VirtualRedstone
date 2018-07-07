package com.johncorby.virtualredstone.command;

import com.johncorby.virtualredstone.circuit.CircuitType;
import com.johncorby.virtualredstone.circuit.Static;
import org.bukkit.entity.Player;

import static com.johncorby.virtualredstone.util.MessageHandler.MessageType.INFO;
import static com.johncorby.virtualredstone.util.MessageHandler.msg;
import static com.johncorby.virtualredstone.util.MessageHandler.playerError;

public class Add extends BaseCommand {
    Add() {
        super("Add a sequencer or table", "<name>", "vrs.admin");
        TabCompleteHandler.register(getName(), 0, "sequencer", "table");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        if (args.length == 0) return playerError(sender, "You must say whether to add a sequencer or table");

        if (args.length == 1) return playerError(sender, "You must supply a name");

        switch (args[0]) {
            case "sequencer":
                if (Static.get(CircuitType.SEQUENCER, args[1]) != null)
                    return playerError(sender, "Sequencer " + args[1] + " already exists");
                Static.newInstance(CircuitType.SEQUENCER, args[1]);
                msg(sender, INFO, "Added sequencer " + args[1]);
                break;
            case "table":
                if (Static.get(CircuitType.TABLE, args[1]) != null)
                    return playerError(sender, "Table " + args[1] + " already exists");
                Static.newInstance(CircuitType.TABLE, args[1]);
                msg(sender, INFO, "Added table " + args[1]);
                break;
            default:
                return playerError(sender, "You must say whether to add a sequencer or table");
        }
        return true;
    }
}
