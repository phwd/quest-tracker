package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;
import java.util.Iterator;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
public abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public final /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
