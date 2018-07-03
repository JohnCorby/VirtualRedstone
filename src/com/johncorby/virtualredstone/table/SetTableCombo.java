package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.util.storedclass.Identifiable;
import org.bukkit.entity.Player;

public class SetTableCombo extends Identifiable<Player> {
    public int step;

    public SetTableCombo(Player identity) {
        super(identity);
        create(identity);
    }

    public static SetTableCombo get(Player identity) {
        return (SetTableCombo) get(SetTableCombo.class, identity);
    }

    /*
    protected boolean create(Player identity, int instNum) {
        // Error if instance doesn't exist
        if (Instance.get(instNum) == null)
            return MessageHandler.playerError(identity, "Table instance " + instNum + " doesn't exist");

        // Error if player already setting region
        SetTableCombo sR = get(identity);
        if (sR != null)
            return MessageHandler.commandError(identity, "You are already setting region for arena " + sR.name);

        MessageHandler.msg(identity, MessageHandler.MessageType.GENERAL, "Left click block to set pos 1");

        this.name = name.toLowerCase();
        this.add = add;
        return super.create(identity);
    }

    @Override
    protected boolean create(Player identity) {
        return true;
    }

    // Go to next step in region setting
    public void next() {
        switch (step) {
        }
        step++;
    }
    */
}



