package com.johncorby.virtualredstone.command;

import com.johncorby.coreapi.command.BaseCommand;
import com.johncorby.coreapi.util.MessageHandler;
import com.johncorby.virtualredstone.circuit.Circuit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Add extends BaseCommand {
    public Add() {
        super("Add a circuit", "<name>", PERM_ADMIN);
//        TabCompleteHandler.register(getName(), 0, "sequencer", "table");
    }

    @Override
    public boolean onCommand(@NotNull Player sender, @NotNull String[] args) {
//        if (args.length == 0) {
//            MessageHandler.error(sender, "You must say whether to add a sequencer or table");
//            return false;
//        }
//
//        if (args.length == 1) {
//            MessageHandler.error(sender, "You must supply a name");
//            return false;
//        }
//
//        switch (args[0]) {
//            case "sequencer":
//                if (Circuit.get(CircuitType.SEQUENCER, args[1]) != null) {
//                    MessageHandler.error(sender, "Sequencer " + args[1] + " already exists");
//                    return false;
//                }
//                Circuit.newInstance(CircuitType.SEQUENCER, args[1]);
//                MessageHandler.info(sender, "Added sequencer " + args[1]);
//                break;
//            case "table":
//                if (Circuit.get(CircuitType.TABLE, args[1]) != null) {
//                    MessageHandler.error(sender, "Table " + args[1] + " already exists");
//                    return false;
//                }
//                Circuit.newInstance(CircuitType.TABLE, args[1]);
//                MessageHandler.info(sender, "Added table " + args[1]);
//                break;
//            default:
//                MessageHandler.error(sender, "You must say whether to add a sequencer or table");
//                return false;
//        }
//        return true;

        if (args.length == 0) {
            MessageHandler.error(sender, "You must supply a name");
            return false;
        }

        if (Circuit.get(args[0]) != null) {
            MessageHandler.error(sender, "Circuit already exists");
            return false;
        }

        new Circuit(args[0]);
        MessageHandler.info(sender, "Added circuit " + args[0]);
        return true;
    }
}
