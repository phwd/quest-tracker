package com.google.common.collect;

import X.AnonymousClass0vx;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = true)
public final class ReverseOrdering<T> extends AnonymousClass0vx<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final AnonymousClass0vx<? super T> forwardOrder;

    @Override // X.AnonymousClass0vx, java.util.Comparator
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
        StringBuilder sb = new StringBuilder();
        sb.append(this.forwardOrder);
        sb.append(".reverse()");
        return sb.toString();
    }

    public ReverseOrdering(AnonymousClass0vx<? super T> r1) {
        this.forwardOrder = r1;
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: X.0vx<? super T>, X.0vx<S extends T> */
    @Override // X.AnonymousClass0vx
    public final <S extends T> AnonymousClass0vx<S> A00() {
        return (AnonymousClass0vx<? super T>) this.forwardOrder;
    }
}
