package com.johncorby.virtualredstone.command;

import com.johncorby.virtualredstone.util.Class;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.entity.Player;

import static com.johncorby.virtualredstone.util.MessageHandler.MessageType.GENERAL;
import static com.johncorby.virtualredstone.util.MessageHandler.debug;
import static com.johncorby.virtualredstone.util.MessageHandler.msg;

public class Debug extends BaseCommand {
    Debug() {
        super("Get debug stuff", "", "gg.admin");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        //debug(Thread.getAllStackTraces());
        for (Class c : Class.getClasses())
            try {
                debug((Object[]) c.getDebug().toArray());
            } catch (Exception e) {
                debug("Error getting debug for " + c + ": " + ExceptionUtils.getStackTrace(e));
            }
        msg(sender, GENERAL, "See console");
        return true;
    }
}