package com.johncorby.virtualredstone;

import com.johncorby.coreapi.CoreApiPlugin;
import com.johncorby.coreapi.command.BaseCommand;
import com.johncorby.virtualredstone.circuit.Config;
import com.johncorby.virtualredstone.command.Add;
import com.johncorby.virtualredstone.command.SetTableCombo;
import com.johncorby.virtualredstone.listener.Block;
import com.johncorby.virtualredstone.listener.Entity;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;

/**
 * TODO BUGS: https://github.com/JohnCorby/VirtualRedstone/issues?q=is%3Aopen+is%3Aissue+label%3Abug
 * TODO FEATURES: https://github.com/JohnCorby/VirtualRedstone/issues?q=is%3Aopen+is%3Aissue+label%3Afeature
 */
public class VirtualRedstone extends CoreApiPlugin {
    @Override
    public void onEnable() {
        super.onEnable();

        // Register ConfigSerializable
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.sequencer.Static.class);
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.table.Static.class);

        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.sequencer.Input.class);
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.sequencer.Output.class);
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.table.Input.class);
        ConfigurationSerialization.registerClass(com.johncorby.virtualredstone.table.Output.class);

        // Init classes
        new Config();
    }

    @Override
    public String getMessagePrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "VirtualRedstone" + ChatColor.DARK_GRAY + "]";
    }

    @Override
    public BaseCommand[] getCommands() {
        return new BaseCommand[]{
                new Add(),
                new SetTableCombo()
        };
    }

    @Override
    public Listener[] getListeners() {
        return new Listener[]{
                new Block(),
                new Entity()
        };
    }
}
