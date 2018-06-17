package com.johncorby.virtualredstone.util;

import java.util.Arrays;

/**
 * Identifiable but with multiple identities
 */
public class MultiIdentifiable extends Class {
    protected Object[] identities;

    public MultiIdentifiable(Object... identities) {
        super();
        create(identities);
    }

    // TODO: Override this in subclasses
    public static MultiIdentifiable get(Object... identities) {
        return get(MultiIdentifiable.class, identities);
    }

    protected static MultiIdentifiable get(java.lang.Class<? extends MultiIdentifiable> clazz,
                                           Object... identities) {
        for (Class c : classes) {
            if (!clazz.equals(c.getClass())) continue;
            MultiIdentifiable i = (MultiIdentifiable) c;
            if (Arrays.equals(i.get(), identities)) return i;
        }
        throw new IllegalStateException(clazz.getSimpleName() + "<" + Arrays.toString(identities) + "> doesn't exist");
    }

    protected boolean create(Object... identities) {
        this.identities = identities;
        return super.create();
    }

    @Override
    protected final boolean create() {
        return true;
    }

    public final Object[] get() throws IllegalStateException {
        if (!exists())
            throw new IllegalStateException(this + " doesn't exist");
        int a = getUnavailable() + 1;
        if (a > 0) {
            super.dispose();
            throw new IllegalStateException("Identity" + a + " for " + this + " unavailable");
        }
        return identities;
    }

    protected int getUnavailable() {
        for (int i = 0; i < identities.length; i++)
            if (identities[i] == null) return i;
        return -1;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "<" + Arrays.toString(identities) + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().equals(obj.getClass())) return false;
        MultiIdentifiable i = (MultiIdentifiable) obj;
        return Arrays.equals(identities, i.identities);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(identities);
    }
}
