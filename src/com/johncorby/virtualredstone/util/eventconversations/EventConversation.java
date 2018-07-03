package com.johncorby.virtualredstone.util.eventconversations;

import com.johncorby.virtualredstone.util.storedclass.Identifiable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import java.util.HashMap;
import java.util.Map;

import static com.johncorby.virtualredstone.VirtualRedstone.virtualRedstone;

/**
 * I'm hella gay
 */
public class EventConversation extends Identifiable<Player> implements Listener {
    protected EventPrompt currentPrompt;
    private EventPrompt firstPrompt;
    private Map<Object, Object> sessionData;
    private boolean abandoned;

    public EventConversation(Player forWhom) {
        super(forWhom);
    }

    public static EventConversation get(Player identity) {
        return (EventConversation) get(EventConversation.class, identity);
    }

    @Override
    protected boolean create(Player identity) {
        sessionData = new HashMap<>();
        return super.create(identity);
    }

    @Override
    public boolean dispose() {
        if (!exists()) return false;
        abandon();
        return super.dispose();
    }

    public void begin() throws IllegalStateException {
        // Use field instead of method because we want to check for this specific object, not identical one already one possibly already being stored
        if (!exists)
            throw new IllegalStateException(this + " doesn't exist");
        if (firstPrompt == null)
            throw new IllegalStateException(this + " doens't have firstPrompt");
        if (currentPrompt == null) {
            abandoned = false;
            currentPrompt = firstPrompt;
            outputNextPrompt();
        }
    }

    public synchronized void abandon() throws IllegalStateException {
        if (!exists)
            throw new IllegalStateException(this + " doesn't exist");
        if (currentPrompt == null) abandoned = true;
        if (!abandoned) {
            abandoned = true;
            unregister();
            currentPrompt = null;
        }
    }

    private void acceptInput(Event input) {
        if (currentPrompt != null) {
            // Not abandoned, output the next prompt
            currentPrompt = currentPrompt.acceptInput(input);
            outputNextPrompt();
        }
    }

    private void outputNextPrompt() {
        if (currentPrompt == null) {
            abandon();
        } else {
            register(currentPrompt.event, currentPrompt);
            get().sendRawMessage(currentPrompt.getPromptText());
        }
    }

    public ConversationState getState() {
        if (currentPrompt != null) {
            return ConversationState.STARTED;
        } else if (abandoned) {
            return ConversationState.ABANDONED;
        } else {
            return ConversationState.UNSTARTED;
        }
    }

    public void setFirstPrompt(EventPrompt firstPrompt) {
        if (currentPrompt != null) return;
        this.firstPrompt = firstPrompt;
    }

    public void setInitialSessionData(Map<Object, Object> initialSessionData) {
        if (currentPrompt != null) return;
        sessionData = initialSessionData;
    }

    private void register(Class<? extends Event> event, EventExecutor executor) {
        //Common.run(() -> Bukkit.getPluginManager().registerEvents(this, virtualRedstone));

        unregister();
        Bukkit.getPluginManager().registerEvent(event, this, EventPriority.NORMAL, executor, virtualRedstone, false);
    }

    private void unregister() {
        HandlerList.unregisterAll(this);
    }

    public enum ConversationState {
        UNSTARTED,
        STARTED,
        ABANDONED
    }

    public abstract class EventPrompt<E extends Event> implements EventExecutor {
        private final Class<E> event;

        public EventPrompt(Class<E> event) {
            this.event = event;
        }

        private EventPrompt<E> acceptInput(E input) {
            // Don't do anything if event is not for us
            if (!isForWhom(input)) return this;

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

        protected final Player getForWhom() {
            return get();
        }

        protected final Object getSessionData(Object key) {
            return sessionData.get(key);
        }

        protected final void setSessionData(Object key, Object value) {
            sessionData.put(key, value);
        }

        protected abstract String getPromptText();

        protected abstract String getInvalidInputText(E input);

        protected abstract boolean isForWhom(E input);

        protected abstract boolean isInputValid(E input);

        protected abstract EventPrompt<E> acceptValidInput(E input);

        @Override
        public void execute(Listener listener, Event event) {
            debug("execute");
            EventConversation.this.acceptInput(event);
        }

        // TODO: override in subclasses AND include @EventHandler
//        @EventHandler
//        public void onEvent(E event) {
//            debug("onEvent");
//            EventConversation.this.acceptInput(event);
//        }
    }
}
