package com.google.common.collect;

import X.UR;
import java.io.Serializable;

public final class NaturalOrdering extends UR implements Serializable {
    public static final NaturalOrdering A00 = new NaturalOrdering();
    public static final long serialVersionUID = 0;

    public final String toString() {
        return "Ordering.natural()";
    }

    private Object readResolve() {
        return A00;
    }
}
