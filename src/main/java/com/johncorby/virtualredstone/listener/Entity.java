package com.johncorby.virtualredstone.listener;

import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.jetbrains.annotations.NotNull;

public class Entity implements Listener {
    @EventHandler
    public void onSpawn(@NotNull EntitySpawnEvent event) {
        if (event.getEntity() instanceof Slime) {
            Slime slime = (Slime) event.getEntity();

        }
    }
}
