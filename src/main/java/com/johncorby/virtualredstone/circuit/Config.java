package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.MessageHandler;

import java.util.Collection;
import java.util.Set;

import static com.johncorby.coreapi.CoreApiPlugin.PLUGIN;
import static com.johncorby.coreapi.util.Common.toList;
import static com.johncorby.coreapi.util.Common.toSet;

public class Config {
    public Config() {
        // Setup
        PLUGIN.getConfig().options().copyDefaults(true);
        PLUGIN.saveConfig();

        // Load statics
        for (Object object : get("Statics")) {
            try {
                MessageHandler.info("Found static " + object + " in config");
            } catch (Exception e) {
                MessageHandler.error(e);
            }
        }

        // Load signs
        for (Object object : get("Signs")) {
            try {
                MessageHandler.info("Found sign " + object + " in config");
            } catch (Exception e) {
                MessageHandler.error(e);
            }
        }
    }

    public static Set<Object> get(String path) {
        return toSet((Collection<Object>) PLUGIN.getConfig().getList(path));
    }

    public static void set(String path, Set<Object> set) {
        //set.removeIf(Objects::isNull);
        PLUGIN.getConfig().set(path, toList(set));
        PLUGIN.saveConfig();
    }

    public static void add(String path, Object object) {
        Set<Object> set = get(path);
        set.add(object);
        set(path, set);
    }

    public static void remove(String path, Object object) {
        Set<Object> set = get(path);
        set.remove(object);
        set(path, set);
    }

    public static boolean contains(String path, Object object) {
        return get(path).contains(object);
    }
}
