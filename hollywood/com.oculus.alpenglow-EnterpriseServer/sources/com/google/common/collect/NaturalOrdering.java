package com.google.common.collect;

import X.AnonymousClass0tL;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable = true)
public final class NaturalOrdering extends AnonymousClass0tL<Comparable> implements Serializable {
    public static final NaturalOrdering A00 = new NaturalOrdering();
    public static final long serialVersionUID = 0;

    public final String toString() {
        return "Ordering.natural()";
    }

    private Object readResolve() {
        return A00;
    }

    @Override // X.AnonymousClass0tL
    public final <S extends Comparable> AnonymousClass0tL<S> A00() {
        return ReverseNaturalOrdering.A00;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator, X.AnonymousClass0tL
    public final int compare(Comparable comparable, Comparable comparable2) {
        Comparable comparable3 = comparable;
        if (comparable3 == null) {
            throw null;
        } else if (comparable2 != null) {
            return comparable3.compareTo(comparable2);
        } else {
            throw null;
        }
    }
}
