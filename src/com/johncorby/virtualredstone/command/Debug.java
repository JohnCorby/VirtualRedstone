package com.johncorby.virtualredstone.command;

import com.johncorby.virtualredstone.util.storedclass.StoredClass;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.entity.Player;

import static com.johncorby.virtualredstone.util.MessageHandler.MessageType.INFO;
import static com.johncorby.virtualredstone.util.MessageHandler.debug;
import static com.johncorby.virtualredstone.util.MessageHandler.msg;

public class Debug extends BaseCommand {
    Debug() {
        super("Get debug stuff", "", "vrs.admin");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        //debug(Thread.getAllStackTraces());
        for (StoredClass c : StoredClass.getClasses())
            try {
                Object[] debug = c.getDebug().toArray();
                if (debug == null) continue;
                debug(debug);
            } catch (Exception e) {
                debug("Error getting debug for " + c + ": " + ExceptionUtils.getStackTrace(e));
            }
        msg(sender, INFO, "See console");
        return true;
    }
}
