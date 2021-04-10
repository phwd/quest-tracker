package com.google.common.collect;

import X.AbstractC07150rI;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = true)
public final class ReverseOrdering<T> extends AbstractC07150rI<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final AbstractC07150rI<? super T> forwardOrder;

    @Override // X.AbstractC07150rI, java.util.Comparator
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

    public ReverseOrdering(AbstractC07150rI<? super T> r1) {
        this.forwardOrder = r1;
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: X.0rI<? super T>, X.0rI<S extends T> */
    @Override // X.AbstractC07150rI
    public final <S extends T> AbstractC07150rI<S> A00() {
        return (AbstractC07150rI<? super T>) this.forwardOrder;
    }
}
