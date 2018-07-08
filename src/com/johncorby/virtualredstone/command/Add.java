package com.johncorby.virtualredstone.command;

import com.johncorby.coreapi.command.BaseCommand;
import com.johncorby.coreapi.command.TabCompleteHandler;
import com.johncorby.virtualredstone.circuit.CircuitType;
import com.johncorby.virtualredstone.circuit.Static;
import org.bukkit.entity.Player;

import static com.johncorby.coreapi.CoreApiPlugin.messageHandler;
import static com.johncorby.coreapi.CoreApiPlugin.tabCompleteHandler;
import static com.johncorby.coreapi.util.MessageHandler.MessageType.INFO;

public class Add extends BaseCommand {
    public Add() {
        super("Add a sequencer or table", "<name>", "vrs.admin");
        tabCompleteHandler.register(getName(), 0, "sequencer", "table");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        if (args.length == 0) {
            messageHandler.error(sender, "You must say whether to add a sequencer or table");
            return false;
        }

        if (args.length == 1) {
            messageHandler.error(sender, "You must supply a name");
            return false;
        }

        switch (args[0]) {
            case "sequencer":
                if (Static.get(CircuitType.SEQUENCER, args[1]) != null) {
                    messageHandler.error(sender, "Sequencer " + args[1] + " already exists");
                    return false;
                }
                Static.newInstance(CircuitType.SEQUENCER, args[1]);
                messageHandler.msg(sender, INFO, "Added sequencer " + args[1]);
                break;
            case "table":
                if (Static.get(CircuitType.TABLE, args[1]) != null) {
                    messageHandler.error(sender, "Table " + args[1] + " already exists");
                    return false;
                }
                Static.newInstance(CircuitType.TABLE, args[1]);
                messageHandler.msg(sender, INFO, "Added table " + args[1]);
                break;
            default:
                messageHandler.error(sender, "You must say whether to add a sequencer or table");
                return false;
        }
        return true;
    }
}
