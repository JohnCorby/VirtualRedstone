package com.johncorby.virtualredstone.util;

import java.util.Objects;

/**
 * Identifiable but with 3 identities
 */
public class TriIdentifiable<I1, I2, I3> extends Class {
    protected I1 identity1;
    protected I2 identity2;
    protected I3 identity3;

    public TriIdentifiable(I1 identity1, I2 identity2, I3 identity3) {
        super();
        create(identity1, identity2, identity3);
    }

    // TODO: Override this in subclasses
    public static TriIdentifiable get(Object identity1, Object identity2, Object identity3) {
        return get(TriIdentifiable.class, identity1, identity2, identity3);
    }

    protected static TriIdentifiable get(java.lang.Class<? extends TriIdentifiable> clazz,
                                         Object identity1, Object identity2, Object identity3) {
        for (Class c : classes) {
            if (c.getClass().equals(clazz) &&
                    ((TriIdentifiable) c).get().equals(identity1)) {
                return ((TriIdentifiable) c);
            }
        }
        throw new IllegalStateException(clazz.getSimpleName() + "<" + identity1 + ", " + identity2 + ", " + identity3 + "> doesn't exist");
    }

    protected boolean create(I1 identity1, I2 identity2, I3 identity3) {
        this.identity1 = identity1;
        this.identity2 = identity2;
        this.identity3 = identity3;
        return super.create();
    }

    @Override
    protected final boolean create() {
        return true;
    }

    public final Tuple3<I1, I2, I3> get() throws IllegalStateException {
        if (!exists())
            throw new IllegalStateException(this + " doesn't exist");
        Tuple3<Boolean, Boolean, Boolean> a = available();
        if (!a.a) {
            super.dispose();
            throw new IllegalStateException("Identity1 for " + this + " unavailable");
        }
        if (!a.b) {
            super.dispose();
            throw new IllegalStateException("Identity2 for " + this + " unavailable");
        }
        if (!a.c) {
            super.dispose();
            throw new IllegalStateException("Identity3 for " + this + " unavailable");
        }
        return new Tuple3<>(identity1, identity2, identity3);
    }

    protected Tuple3<Boolean, Boolean, Boolean> available() {
        return new Tuple3<>(identity1 != null, identity2 != null, identity3 != null);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "<" + identity1 + ", " + identity2 + ", " + identity3 + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().equals(obj.getClass())) return false;
        TriIdentifiable i = (TriIdentifiable) obj;
        return Objects.equals(identity1, i.identity1) && Objects.equals(identity2, i.identity2) && Objects.equals(identity3, i.identity3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity1, identity2, identity3);
    }
}
