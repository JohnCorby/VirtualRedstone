package com.johncorby.virtualredstone.util;

import java.util.Objects;

/**
 * A class that is identified by another class
 * Can also be used as a wrapper to associate methods/fields/classes with the identities
 */
public class Identifiable<I> extends Class {
    protected I identity;

    public Identifiable(I identity) {
        super();
        create(identity);
    }

    // TODO: Override this in subclasses
    public static Identifiable get(Object identity) {
        return get(Identifiable.class, identity);
    }

    protected static Identifiable get(java.lang.Class<? extends Identifiable> clazz,
                                      Object identity) {
        for (Class c : classes) {
            if (c.getClass().equals(clazz) &&
                    ((Identifiable) c).get().equals(identity)) {
                return ((Identifiable) c);
            }
        }
        //throw new IllegalStateException(clazz.getSimpleName() + "<" + identity + "> doesn't exist");
        return null;
    }

    protected boolean create(I identity) {
        this.identity = identity;
        return super.create();
    }

    @Override
    protected final boolean create() {
        return true;
    }

    public final I get() throws IllegalStateException {
        if (!exists())
            throw new IllegalStateException(this + " doesn't exist");
        if (!available()) {
            super.dispose();
            throw new IllegalStateException("Identity for " + this + " unavailable");
        }
        return identity;
    }

    protected boolean available() {
        return identity != null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "<" + identity + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().equals(obj.getClass())) return false;
        Identifiable i = (Identifiable) obj;
        return Objects.equals(identity, i.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identity);
    }
}
