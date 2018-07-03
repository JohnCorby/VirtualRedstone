package com.johncorby.virtualredstone.util.eventconversations;

import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.EventExecutor;

import static com.johncorby.virtualredstone.VirtualRedstone.virtualRedstone;

public abstract class EventListener<E extends Event> implements Listener, EventExecutor {
    private final Class<E> event;
    private final EventPriority priority;
    private final boolean ignoreCancelled;

    public EventListener(Class<E> event) {
        this(event, EventPriority.NORMAL, false);
    }

    public EventListener(Class<E> event, EventPriority priority, boolean ignoreCancelled) {
        this.event = event;
        this.priority = priority;
        this.ignoreCancelled = ignoreCancelled;
    }

    public Class<E> getEvent() {
        return event;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public boolean isIgnoreCancelled() {
        return ignoreCancelled;
    }

    public void register() {
        // Just in case it's already registered
        unregister();
        Bukkit.getPluginManager().registerEvent(event, this, priority, this, virtualRedstone, ignoreCancelled);
    }

    public void unregister() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public abstract void execute(Listener listener, Event event) throws EventException;
}
