package com.google.common.collect;

import X.AnonymousClass0tL;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = true)
public final class ReverseOrdering<T> extends AnonymousClass0tL<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final AnonymousClass0tL<? super T> forwardOrder;

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: X.0tL<? super T>, X.0tL<S extends T> */
    @Override // X.AnonymousClass0tL
    public final <S extends T> AnonymousClass0tL<S> A00() {
        return (AnonymousClass0tL<? super T>) this.forwardOrder;
    }

    @Override // java.util.Comparator, X.AnonymousClass0tL
    public final int compare(T t, T t2) {
        return this.forwardOrder.compare(t2, t);
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReverseOrdering) {
            return this.forwardOrder.equals(((ReverseOrdering) obj).forwardOrder);
        }
        return false;
    }

    public final int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    public final String toString() {
        return this.forwardOrder + ".reverse()";
    }

    public ReverseOrdering(AnonymousClass0tL<? super T> r1) {
        this.forwardOrder = r1;
    }
}
