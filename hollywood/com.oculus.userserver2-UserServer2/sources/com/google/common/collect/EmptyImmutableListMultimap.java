package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;

@GwtCompatible(serializable = BuildConfig.IS_LIBCXX_BUILD)
public class EmptyImmutableListMultimap extends ImmutableListMultimap<Object, Object> {
    public static final EmptyImmutableListMultimap A00 = new EmptyImmutableListMultimap();
    public static final long serialVersionUID = 0;

    public EmptyImmutableListMultimap() {
        super(RegularImmutableMap.A03);
    }

    private Object readResolve() {
        return A00;
    }
}
