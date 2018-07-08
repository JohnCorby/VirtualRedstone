package com.johncorby.virtualredstone.command;

import com.johncorby.coreapi.command.BaseCommand;
import com.johncorby.coreapi.util.storedclass.StoredClass;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.entity.Player;

import static com.johncorby.coreapi.CoreApiPlugin.messageHandler;
import static com.johncorby.coreapi.util.MessageHandler.MessageType.INFO;

public class Debug extends BaseCommand {
    public Debug() {
        super("Get debug stuff", "", "vrs.admin");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
        //debug(Thread.getAllStackTraces());
        for (StoredClass c : StoredClass.getClasses())
            try {
                Object[] debug = c.getDebug().toArray();
                if (debug == null) continue;
                messageHandler.debug(debug);
            } catch (Exception e) {
                messageHandler.debug("Error getting debug for " + c + ": " + ExceptionUtils.getStackTrace(e));
            }
        messageHandler.msg(sender, INFO, "See console");
        return true;
    }
}
