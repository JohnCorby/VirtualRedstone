package com.johncorby.virtualredstone;

import com.johncorby.virtualredstone.command.CommandHandler;
import com.johncorby.virtualredstone.command.TabCompleteHandler;
import com.johncorby.virtualredstone.event.EventHandler;
import com.johncorby.virtualredstone.util.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * TODO BUGS: https://github.com/JohnCorby/VirtualRedstone/issues?q=is%3Aopen+is%3Aissue+label%3Abug
 * TODO FEATURES: https://github.com/JohnCorby/VirtualRedstone/issues?q=is%3Aopen+is%3Aissue+label%3Afeature
 */
public class VirtualRedstone extends JavaPlugin {
    public static JavaPlugin virtualRedstone;
    public static CommandHandler commandHandler;
    public static TabCompleteHandler tabCompleteHandler;
    public static EventHandler eventHandler;

    public static FileConfiguration CONFIG;

    // When plugin enabled
    @Override
    public void onEnable() {
        // Init classes
        virtualRedstone = this;
        CONFIG = getConfig();

        commandHandler = new CommandHandler();
        tabCompleteHandler = new TabCompleteHandler();
        eventHandler = new EventHandler();

        // Set up config
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Load stuff from config

        MessageHandler.log(MessageHandler.MessageType.GENERAL, "VirtualRedstone Enabled");
    }

    // When plugin disabled
    @Override
    public void onDisable() {
        // Stop all VirtualRedstone tasks
        Bukkit.getScheduler().cancelTasks(this);

        MessageHandler.log(MessageHandler.MessageType.GENERAL, "VirtualRedstone Disabled");
    }
}
