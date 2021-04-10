package com.google.common.collect;

import X.AnonymousClass4J;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = true)
public final class ReverseOrdering<T> extends AnonymousClass4J<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final AnonymousClass4J<? super T> forwardOrder;

    @Override // X.AnonymousClass4J, java.util.Comparator
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

    public ReverseOrdering(AnonymousClass4J<? super T> r1) {
        this.forwardOrder = r1;
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: X.4J<? super T>, X.4J<S extends T> */
    @Override // X.AnonymousClass4J
    public final <S extends T> AnonymousClass4J<S> A00() {
        return (AnonymousClass4J<? super T>) this.forwardOrder;
    }
}
