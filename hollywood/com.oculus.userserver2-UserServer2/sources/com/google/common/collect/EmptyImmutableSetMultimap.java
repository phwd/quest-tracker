package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;

@GwtCompatible(serializable = BuildConfig.IS_LIBCXX_BUILD)
public class EmptyImmutableSetMultimap extends ImmutableSetMultimap<Object, Object> {
    public static final EmptyImmutableSetMultimap A00 = new EmptyImmutableSetMultimap();
    public static final long serialVersionUID = 0;

    public EmptyImmutableSetMultimap() {
        super(RegularImmutableMap.A03);
    }

    private Object readResolve() {
        return A00;
    }
}
