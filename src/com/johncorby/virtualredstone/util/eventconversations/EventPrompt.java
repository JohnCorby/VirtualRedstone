package com.johncorby.virtualredstone.util.eventconversations;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import java.util.function.Function;

import static com.johncorby.virtualredstone.VirtualRedstone.virtualRedstone;

public abstract class EventPrompt<E extends Event> implements Listener, EventExecutor {
    private final Class<E> event;
    private final EventPriority priority;
    private final boolean ignoreCancelled;
    private final EventConversation conversation;
    private final Function<E, Player> playerGetter;

    public EventPrompt(Class<E> event,
                       EventConversation conversation, Function<E, Player> playerGetter) {
        this(event, EventPriority.NORMAL, false, conversation, playerGetter);
    }

    public EventPrompt(Class<E> event, EventPriority priority, boolean ignoreCancelled,
                       EventConversation conversation, Function<E, Player> playerGetter) {
        this.event = event;
        this.priority = priority;
        this.ignoreCancelled = ignoreCancelled;
        this.conversation = conversation;
        this.playerGetter = playerGetter;
    }

    protected final Player getForWhom() {
        return conversation.get();
    }

    protected final Object getSessionData(Object key) {
        return conversation.sessionData.get(key);
    }

    protected final void setSessionData(Object key, Object value) {
        conversation.sessionData.put(key, value);
    }

    public final void register() {
        unregister();
        Bukkit.getPluginManager().registerEvent(event, conversation, priority, this, virtualRedstone, ignoreCancelled);
    }

    public final void unregister() {
        HandlerList.unregisterAll(conversation);
    }


    @Override
    public void execute(Listener listener, Event event) {
        conversation.debug("execute");
        conversation.acceptInput(event);
    }

    EventPrompt acceptInput(E input) {
        // Don't do anything if event is not for us
        if (!playerGetter.apply(input).equals(getForWhom())) return this;

        if (isInputValid(input)) {
            unregister();
            return acceptValidInput(input);
        } else {
            String failMessage = getInvalidInputText(input);
            if (failMessage != null)
                getForWhom().sendRawMessage(ChatColor.RED + failMessage);

            // Redisplay this prompt to the user to re-collect input
            return this;
        }
    }

    protected abstract String getPromptText();

    protected abstract boolean isInputValid(E input);

    protected abstract String getInvalidInputText(E input);

    protected abstract EventPrompt acceptValidInput(E input);
}
