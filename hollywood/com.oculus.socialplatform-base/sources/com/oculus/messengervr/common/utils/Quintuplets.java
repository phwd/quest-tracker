package com.oculus.messengervr.common.utils;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Quintuplets<A, B, C, D, E> {
    public final E fifth;
    public final A first;
    public final D forth;
    public final B second;
    public final C third;

    public static <A, B, C, D, E> Quintuplets<A, B, C, D, E> fromArray(Object[] objArr) {
        return new Quintuplets<>(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
    }

    public Quintuplets(A a, B b, C c, D d, E e) {
        this.first = a;
        this.second = b;
        this.third = c;
        this.forth = d;
        this.fifth = e;
    }
}
