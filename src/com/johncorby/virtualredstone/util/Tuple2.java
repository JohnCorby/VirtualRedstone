package com.johncorby.virtualredstone.util;

import java.util.Objects;

/**
 * A immutable tuple of 2 objects
 *
 * @param <A> the 1st object
 * @param <B> the 2nd object
 * @see <a href=https://stackoverflow.com/questions/457629/how-to-return-multiple-objects-from-a-java-method>Source</a>
 */
public class Tuple2<A, B> {
    public final A a;
    public final B b;

    public Tuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple2)) return false;
        Tuple2 t = (Tuple2) obj;
        return Objects.equals(a, t.a) && Objects.equals(b, t.b);
    }

    @Override
    public String toString() {
        return "Tuple2<" + a + ", " + b + ">@" + Integer.toHexString(hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
