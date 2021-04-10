package com.google.common.collect;

import X.UR;
import java.io.Serializable;

public final class ReverseNaturalOrdering extends UR implements Serializable {
    public static final ReverseNaturalOrdering A00 = new ReverseNaturalOrdering();
    public static final long serialVersionUID = 0;

    public final String toString() {
        return "Ordering.natural().reverse()";
    }

    private Object readResolve() {
        return A00;
    }
}
