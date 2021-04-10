package com.oculus.messengervr.common.utils;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Quadruplet<A, B, C, D> {
    public final A first;
    public final D forth;
    public final B second;
    public final C third;

    public static <A, B, C, D> Quadruplet<A, B, C, D> fromArray(Object[] objArr) {
        return new Quadruplet<>(objArr[0], objArr[1], objArr[2], objArr[3]);
    }

    public Quadruplet(A a, B b, C c, D d) {
        this.first = a;
        this.second = b;
        this.third = c;
        this.forth = d;
    }
}
