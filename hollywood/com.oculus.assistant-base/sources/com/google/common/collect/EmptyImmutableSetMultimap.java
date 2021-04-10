package com.google.common.collect;

public class EmptyImmutableSetMultimap extends ImmutableSetMultimap {
    public static final EmptyImmutableSetMultimap A00 = new EmptyImmutableSetMultimap();
    public static final long serialVersionUID = 0;

    public EmptyImmutableSetMultimap() {
        super(RegularImmutableMap.A03, 0);
    }

    private Object readResolve() {
        return A00;
    }
}
