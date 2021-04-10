package com.google.common.collect;

import X.AnonymousClass0Y0;
import X.C07340r5;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class Multisets$ImmutableEntry<E> extends AnonymousClass0Y0<E> implements Serializable {
    public static final long serialVersionUID = 0;
    public final int count;
    @NullableDecl
    public final E element;

    @Override // X.AnonymousClass0Y0
    public final int A00() {
        return this.count;
    }

    @Override // X.AnonymousClass0Y0
    @NullableDecl
    public final E A01() {
        return this.element;
    }

    public Multisets$ImmutableEntry(@NullableDecl E e, int i) {
        this.element = e;
        this.count = i;
        C07340r5.A00(i, "count");
    }
}
