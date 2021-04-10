package com.facebook.common.util;

import javax.annotation.Nullable;

public class Tuple<T0, T1> {
    public final T0 d0;
    public final T1 d1;

    public static <T0, T1> Tuple<T0, T1> create(T0 t0, T1 t1) {
        return new Tuple<>(t0, t1);
    }

    public Tuple(@Nullable T0 t0, @Nullable T1 t1) {
        this.d0 = t0;
        this.d1 = t1;
    }

    public int hashCode() {
        T0 t0 = this.d0;
        int i = 0;
        if (t0 != null) {
            i = 0 ^ t0.hashCode();
        }
        T1 t1 = this.d1;
        return t1 != null ? i ^ t1.hashCode() : i;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tuple)) {
            return false;
        }
        Tuple tuple = (Tuple) obj;
        T0 t0 = this.d0;
        T0 t02 = tuple.d0;
        if (t0 != t02 && t0 != null && !t0.equals(t02)) {
            return false;
        }
        T1 t1 = this.d1;
        T1 t12 = tuple.d1;
        if (t1 == t12 || t1 == null || t1.equals(t12)) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str = "<";
        if (this.d0 != null) {
            str = str + ((Object) this.d0);
        }
        String str2 = str + ":";
        if (this.d1 != null) {
            str2 = str2 + ((Object) this.d1);
        }
        return str2 + ">";
    }
}
