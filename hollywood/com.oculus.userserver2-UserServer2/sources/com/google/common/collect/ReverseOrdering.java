package com.google.common.collect;

import X.RF;
import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class ReverseOrdering<T> extends RF<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final RF<? super T> forwardOrder;

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

    public ReverseOrdering(RF<? super T> rf) {
        this.forwardOrder = rf;
    }
}
