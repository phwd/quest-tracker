package com.google.common.collect;

import X.AnonymousClass0vx;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable = true)
public final class ReverseNaturalOrdering extends AnonymousClass0vx<Comparable> implements Serializable {
    public static final ReverseNaturalOrdering A00 = new ReverseNaturalOrdering();
    public static final long serialVersionUID = 0;

    public final String toString() {
        return "Ordering.natural().reverse()";
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // X.AnonymousClass0vx, java.util.Comparator
    public final int compare(Comparable comparable, Comparable comparable2) {
        Comparable comparable3 = comparable2;
        if (comparable == null) {
            throw null;
        } else if (comparable == comparable3) {
            return 0;
        } else {
            return comparable3.compareTo(comparable);
        }
    }

    private Object readResolve() {
        return A00;
    }

    @Override // X.AnonymousClass0vx
    public final <S extends Comparable> AnonymousClass0vx<S> A00() {
        return NaturalOrdering.A00;
    }
}
