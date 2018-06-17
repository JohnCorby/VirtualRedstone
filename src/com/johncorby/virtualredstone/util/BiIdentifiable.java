package com.johncorby.virtualredstone.util;

import java.util.Objects;

/**
 * Identifiable but with 2 identities
 */
public class BiIdentifiable<I1, I2> extends Class {
    protected I1 identity1;
    protected I2 identity2;

    public BiIdentifiable(I1 identity1, I2 identity2) {
        super();
        create(identity1, identity2);
    }

    // TODO: Override this in subclasses
    public static BiIdentifiable get(Object identity) {
        return get(BiIdentifiable.class, identity);
    }

    protected static BiIdentifiable get(java.lang.Class<? extends BiIdentifiable> clazz,
                                        Object identity) {
        for (Class c : classes) {
            if (c.getClass().equals(clazz) &&
                    ((BiIdentifiable) c).get().equals(identity)) {
                return ((BiIdentifiable) c);
            }
        }
        throw new IllegalStateException(clazz.getSimpleName() + "<" + identity + "> doesn't exist");
    }

    protected boolean create(I1 identity1, I2 identity2) {
        this.identity1 = identity1;
        this.identity2 = identity2;
        return super.create();
    }

    @Override
    protected boolean create() {
        return true;
    }

    public final Tuple<I1, I2> get() throws IllegalStateException {
        if (!exists())
            throw new IllegalStateException(this + " doesn't exist");
        Tuple<Boolean, Boolean> a = available();
        if (!a.a) {
            super.dispose();
            throw new IllegalStateException("Identity1 for " + this + " unavailable");
        }
        if (!a.b) {
            super.dispose();
            throw new IllegalStateException("Identity2 for " + this + " unavailable");
        }
        return new Tuple<>(identity1, identity2);
    }

    protected Tuple<Boolean, Boolean> available() {
        return new Tuple<>(identity1 != null, identity2 != null);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "<" + identity1 + ", " + identity2 + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().equals(obj.getClass())) return false;
        BiIdentifiable i = (BiIdentifiable) obj;
        return Objects.equals(identity1, i.identity1) && Objects.equals(identity2, i.identity2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity1, identity2);
    }
}
