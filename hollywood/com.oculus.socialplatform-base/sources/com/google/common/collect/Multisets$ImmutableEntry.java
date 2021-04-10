package com.google.common.collect;

import X.AnonymousClass0f2;
import X.AnonymousClass0th;
import com.oculus.horizon.api.rating.ReviewsRequest;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class Multisets$ImmutableEntry<E> extends AnonymousClass0f2<E> implements Serializable {
    public static final long serialVersionUID = 0;
    public final int count;
    @NullableDecl
    public final E element;

    public Multisets$ImmutableEntry(@NullableDecl E e, int i) {
        this.element = e;
        this.count = i;
        AnonymousClass0th.A00(i, ReviewsRequest.KEY_COUNT);
    }

    @Override // X.AnonymousClass0f2
    public final int A00() {
        return this.count;
    }

    @Override // X.AnonymousClass0f2
    @NullableDecl
    public final E A01() {
        return this.element;
    }
}
