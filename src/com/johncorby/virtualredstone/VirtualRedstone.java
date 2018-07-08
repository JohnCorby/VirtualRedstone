package com.johncorby.virtualredstone;

import com.johncorby.coreapi.CoreApiPlugin;
import com.johncorby.coreapi.command.CommandHandler;
import com.johncorby.coreapi.command.TabCompleteHandler;
import com.johncorby.coreapi.util.Config;
import com.johncorby.coreapi.util.MessageHandler;
import com.johncorby.virtualredstone.command.Add;
import com.johncorby.virtualredstone.command.Debug;
import com.johncorby.virtualredstone.command.Reload;
import com.johncorby.virtualredstone.command.SetTableCombo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

/**
 * TODO BUGS: https://github.com/JohnCorby/VirtualRedstone/issues?q=is%3Aopen+is%3Aissue+label%3Abug
 * TODO FEATURES: https://github.com/JohnCorby/VirtualRedstone/issues?q=is%3Aopen+is%3Aissue+label%3Afeature
 */
public class VirtualRedstone extends CoreApiPlugin {
    @Override
    public void register() {
        // Register ConfigSerializable
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.sequencer.Static.class);
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.table.Static.class);

        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.sequencer.Input.class);
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.sequencer.Output.class);
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.table.Input.class);
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.table.Output.class);

        // Init classes
        messageHandler = new MessageHandler() {
            @Override
            protected String getPrefix() {
                return ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "VirtualRedstone" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET;
            }
        };
        commandHandler = new CommandHandler() {
            @Override
            protected void register() {
                register(new Reload());
                register(new Debug());
                register(new Add());
                register(new SetTableCombo());
            }
        };
        eventHandler = new com.johncorby.coreapi.event.EventHandler() {
            @Override
            protected void register() {

            }
        };
        new Config();
    }
}
