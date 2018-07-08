package com.johncorby.virtualredstone.command;

import com.johncorby.coreapi.command.BaseCommand;
import com.johncorby.coreapi.util.MessageHandler;
import org.bukkit.entity.Player;

import static com.johncorby.coreapi.CoreApiPlugin.messageHandler;
import static org.bukkit.Bukkit.getConsoleSender;
import static org.bukkit.Bukkit.getServer;

public class Reload extends BaseCommand {
    public Reload() {
        super("Reload the plugin", "", "vrs.admin");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        messageHandler.msg(sender, MessageHandler.MessageType.INFO, "Reloading VirtualRedstone");
        // Reload using console and plugman
        return getServer().dispatchCommand(getConsoleSender(), "plugman reload VirtualRedstone");
    }
}
