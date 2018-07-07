package com.johncorby.virtualredstone.command;

import com.johncorby.virtualredstone.util.MessageHandler;
import org.bukkit.entity.Player;

public class Help extends BaseCommand {
    Help() {
        super("Display help", "[command]", "");
    }

    static void getHelp(Player sender, BaseCommand... commands) {
        // Header
        if (commands.length == 1)
            MessageHandler.msg(sender, MessageHandler.MessageType.INFO, "----- Help for command " + commands[0].getName() + " -----");
        else
            MessageHandler.msg(sender, MessageHandler.MessageType.INFO, "----- Help for commands -----");

        // Get help for commands
        for (BaseCommand c : commands) {
            MessageHandler.msg(sender, MessageHandler.MessageType.INFO, "/virtualredstone " + c.getName() + " " + c.getUsage() + " - " + c.getDescription());
        }
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        // If no args: show all help
        if (args.length == 0) {
            Help.getHelp(sender, CommandHandler.getCommands(sender).toArray(new BaseCommand[0]));
            return false;
        }

        // Try to get command
        BaseCommand command = CommandHandler.getCommand(args[0]);
        if (command == null)
            return MessageHandler.playerError(sender, "Command " + args[0] + " not found", "Do /virtualredstone help for a list of commands");

        // Get help for command
        getHelp(sender, command);
        return true;
    }
}
