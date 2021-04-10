package com.google.common.collect;

import X.AnonymousClass4J;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@GwtCompatible(serializable = true)
public final class NaturalOrdering extends AnonymousClass4J<Comparable> implements Serializable {
    public static final NaturalOrdering A00 = new NaturalOrdering();
    public static final long serialVersionUID = 0;

    public final String toString() {
        return "Ordering.natural()";
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // X.AnonymousClass4J, java.util.Comparator
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

    private Object readResolve() {
        return A00;
    }

    @Override // X.AnonymousClass4J
    public final <S extends Comparable> AnonymousClass4J<S> A00() {
        return ReverseNaturalOrdering.A00;
    }
}
