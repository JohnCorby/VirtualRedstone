package com.johncorby.virtualredstone.util;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class IdentifiableArray<I> extends Class {
    private I[] identities;

    public IdentifiableArray(@Nonnull I... identities) {
        super();
        create(identities);
    }

    public static IdentifiableArray get(Object... identities) {
        return get(IdentifiableArray.class, identities);
    }

    protected static IdentifiableArray get(@Nonnull java.lang.Class<? extends IdentifiableArray> clazz,
                                           @Nonnull Object... identities) {
        for (Class c : classes) {
            if (c.getClass().equals(clazz) &&
                    Arrays.equals(((IdentifiableArray) c).get(), identities)) {
                return ((IdentifiableArray) c);
            }
        }
        return null;
    }

    protected boolean create(I... identities) {
        this.identities = identities;
        return super.create();
    }

    @Override
    protected boolean create() {
        return true;
    }

    public final I[] get() throws IllegalStateException {
        if (!exists())
            throw new IllegalStateException(this + " doesn't exist");
        if (identities == null) {
            super.dispose();
            throw new IllegalStateException("identities for " + this + " unavailable");
        }
        int u = getUnavailable(identities);
        if (u > -1) {
            super.dispose();
            throw new IllegalStateException("Identity #" + u + " for " + this + " unavailable");
        }
        return identities;
    }

    protected int getUnavailable(I[] identities) {
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
        return obj.getClass().equals(getClass()) &&
                Arrays.equals(identities, ((IdentifiableArray) obj).identities);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(identities);
    }
}
