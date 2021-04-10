package com.google.common.collect;

import X.Mh;
import X.QN;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class Multisets$ImmutableEntry<E> extends Mh<E> implements Serializable {
    public static final long serialVersionUID = 0;
    public final int count;
    @NullableDecl
    public final E element;

    public Multisets$ImmutableEntry(@NullableDecl E e, int i) {
        this.element = e;
        this.count = i;
        QN.A00(i, "count");
    }
}
