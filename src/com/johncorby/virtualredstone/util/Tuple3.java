package com.johncorby.virtualredstone.util;

import java.util.Objects;

/**
 * A immutable tuple of 3 objects
 *
 * @param <A> the 1st object
 * @param <B> the 2nd object
 * @param <C> the 3rd object
 */
public class Tuple3<A, B, C> {
    public final A a;
    public final B b;
    public final C c;

    public Tuple3(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple3)) return false;
        Tuple3 t = (Tuple3) obj;
        return Objects.equals(a, t.a) && Objects.equals(b, t.b) && Objects.equals(c, t.c);
    }

    @Override
    public String toString() {
        return "Tuple3<" + a + ", " + b + ", " + c + ">@" + Integer.toHexString(hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
