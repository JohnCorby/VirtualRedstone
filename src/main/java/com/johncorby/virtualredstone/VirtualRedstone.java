package com.johncorby.virtualredstone;

import com.johncorby.coreapi.CoreApiPlugin;
import com.johncorby.coreapi.command.BaseCommand;
import com.johncorby.coreapi.util.Config;
import com.johncorby.virtualredstone.circuit.Circuit;
import com.johncorby.virtualredstone.circuit.Input;
import com.johncorby.virtualredstone.circuit.Instance;
import com.johncorby.virtualredstone.circuit.Output;
import com.johncorby.virtualredstone.command.Add;
import com.johncorby.virtualredstone.listener.BlockListeners;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * TODO BUGS: https://github.com/JohnCorby/VirtualRedstone/issues?q=is%3Aopen+is%3Aissue+label%3Abug
 * TODO FEATURES: https://github.com/JohnCorby/VirtualRedstone/issues?q=is%3Aopen+is%3Aissue+label%3Afeature
 */
public class VirtualRedstone extends CoreApiPlugin {
    @Override
    public void onEnable() {
        super.onEnable();

        // Register ConfigSerializable
        ConfigurationSerialization.registerClass(Circuit.class);
        ConfigurationSerialization.registerClass(Instance.class);
        ConfigurationSerialization.registerClass(Input.class);
        ConfigurationSerialization.registerClass(Output.class);

        getConfig().options().copyDefaults(true);
        saveConfig();

        Config.getSet("Circuits");
        Config.getSet("Signs");
    }


    @NotNull
    @Override
    public String getMessagePrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "VirtualRedstone" + ChatColor.DARK_GRAY + "]";
    }


    @NotNull
    @Override
    public BaseCommand[] getCommands() {
        return new BaseCommand[]{
                new Add(),
//                new SetTableCombo()
        };
    }


    @NotNull
    @Override
    public Listener[] getListeners() {
        return new Listener[]{
                new BlockListeners(),
        };
    }
}
