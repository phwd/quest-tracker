package com.google.common.collect;

import X.RF;
import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class ComparatorOrdering<T> extends RF<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final Comparator<T> comparator;

    public final boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ComparatorOrdering) {
            return this.comparator.equals(((ComparatorOrdering) obj).comparator);
        }
        return false;
    }

    public final int hashCode() {
        return this.comparator.hashCode();
    }

    public final String toString() {
        return this.comparator.toString();
    }

    public ComparatorOrdering(Comparator<T> comparator2) {
        if (comparator2 != null) {
            this.comparator = comparator2;
            return;
        }
        throw null;
    }
}
