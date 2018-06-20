package com.johncorby.virtualredstone.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Store classes to manage them
 */
public class Class {
    protected static final Set<Class> classes = new HashSet<>();
    private boolean exists = false;

    public Class() throws IllegalStateException {
        create();
    }

    public static Set<Class> getClasses() {
        return classes;
    }

    protected boolean create() throws IllegalStateException {
        if (exists()) return false;
        exists = classes.add(this);
        debug("Created");
        return true;
    }

    public final boolean exists() {
        return exists || classes.contains(this);
    }

    public boolean dispose() {
        if (!exists()) return false;
        exists = !classes.remove(this);
        debug("Disposed");
        return true;
    }

    @Override
    protected void finalize() throws Throwable {
        dispose();
        super.finalize();
    }

    protected final void debug(Object... msgs) {
        MessageHandler.debug(toString(), msgs);
    }

    protected final void error(Object... msgs) {
        MessageHandler.error(toString(), msgs);
    }

    public List<String> getDebug() {
        List<String> r = new ArrayList<>();
        r.add(toString());
        return r;
    }
}

