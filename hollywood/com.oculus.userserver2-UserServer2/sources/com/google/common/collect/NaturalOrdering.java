package com.google.common.collect;

import X.RF;
import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;
import java.io.Serializable;

@GwtCompatible(serializable = BuildConfig.IS_LIBCXX_BUILD)
public final class NaturalOrdering extends RF<Comparable> implements Serializable {
    public static final NaturalOrdering A00 = new NaturalOrdering();
    public static final long serialVersionUID = 0;

    public final String toString() {
        return "Ordering.natural()";
    }

    private Object readResolve() {
        return A00;
    }
}
