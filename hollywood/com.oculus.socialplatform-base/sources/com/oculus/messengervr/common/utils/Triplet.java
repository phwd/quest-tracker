package com.oculus.messengervr.common.utils;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Triplet<A, B, C> {
    public final A first;
    public final B second;
    public final C third;

    public static <A, B, C> Triplet<A, B, C> fromArray(Object[] objArr) {
        return new Triplet<>(objArr[0], objArr[1], objArr[2]);
    }

    public Triplet(A a, B b, C c) {
        this.first = a;
        this.second = b;
        this.third = c;
    }
}
