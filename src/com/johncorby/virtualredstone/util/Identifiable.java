package com.johncorby.virtualredstone.util;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * A class that is identified by another class
 * Can also be used as a wrapper to associate methods/fields/classes with the identity
 */
public class Identifiable<I> extends Class {
    private I identity;

    public Identifiable(@Nonnull I identity) {
        super();
        create(identity);
    }

    // TODO: Override this in subclasses
    public static Identifiable get(Object identity) {
        return get(Identifiable.class, identity);
    }

    protected static Identifiable get(@Nonnull java.lang.Class<? extends Identifiable> clazz,
                                      @Nonnull Object identity) {
        for (Class c : classes) {
            if (c.getClass().equals(clazz) &&
                    ((Identifiable) c).get().equals(identity)) {
                return ((Identifiable) c);
            }
        }
        return null;
    }

    protected boolean create(I identity) {
        this.identity = identity;
        return super.create();
    }

    @Override
    protected boolean create() {
        return true;
    }

    public final I get() throws IllegalStateException {
        if (!exists())
            throw new IllegalStateException(this + " doesn't exist");
        if (getUnavailable()) {
            super.dispose();
            throw new IllegalStateException("Identity for " + this + " unavailable");
        }
        return identity;
    }

    protected boolean getUnavailable() {
        return identity == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "<" + identity + ">";
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(getClass()) &&
                Objects.equals(identity, ((Identifiable) obj).identity);
    }

    @Override
    public int hashCode() {
        return identity.hashCode();
    }
}
