package com.google.common.collect;

import X.AnonymousClass0dN;
import X.AnonymousClass0p6;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class Multisets$ImmutableEntry<E> extends AnonymousClass0dN<E> implements Serializable {
    public static final long serialVersionUID = 0;
    public final int count;
    @NullableDecl
    public final E element;

    public Multisets$ImmutableEntry(@NullableDecl E e, int i) {
        this.element = e;
        this.count = i;
        AnonymousClass0p6.A00(i, "count");
    }

    @Override // X.AnonymousClass0dN
    public final int A00() {
        return this.count;
    }

    @Override // X.AnonymousClass0dN
    @NullableDecl
    public final E A01() {
        return this.element;
    }
}
