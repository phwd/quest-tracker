package com.google.common.collect;

import X.AbstractC0181Ug;
import X.AnonymousClass9M;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class Multisets$ImmutableEntry<E> extends AbstractC0181Ug<E> implements Serializable {
    public static final long serialVersionUID = 0;
    public final int count;
    @NullableDecl
    public final E element;

    public Multisets$ImmutableEntry(@NullableDecl E e, int i) {
        this.element = e;
        this.count = i;
        AnonymousClass9M.A00(i, "count");
    }

    @Override // X.AbstractC0181Ug
    public final int A00() {
        return this.count;
    }

    @Override // X.AbstractC0181Ug
    @NullableDecl
    public final E A01() {
        return this.element;
    }
}
