package com.google.common.collect;

import X.RF;
import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;

@GwtCompatible(serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class ReverseNaturalOrdering extends RF<Comparable> implements Serializable {
    public static final ReverseNaturalOrdering A00 = new ReverseNaturalOrdering();
    public static final long serialVersionUID = 0;

    public final String toString() {
        return "Ordering.natural().reverse()";
    }

    private Object readResolve() {
        return A00;
    }
}
