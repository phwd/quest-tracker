package com.google.common.collect;

import X.AnonymousClass0u6;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

@GwtCompatible(emulated = true)
public abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    public abstract E A0B(int i);

    @Override // com.google.common.collect.ImmutableSet
    public final ImmutableList<E> A09() {
        return new ImmutableList<E>() {
            /* class com.google.common.collect.IndexedImmutableSet.AnonymousClass1 */

            @Override // com.google.common.collect.ImmutableCollection
            public final boolean A08() {
                return IndexedImmutableSet.this.A08();
            }

            @Override // java.util.List
            public final E get(int i) {
                return (E) IndexedImmutableSet.this.A0B(i);
            }

            public final int size() {
                return IndexedImmutableSet.this.size();
            }
        };
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public final int copyIntoArray(Object[] objArr, int i) {
        return asList().copyIntoArray(objArr, i);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public final AnonymousClass0u6<E> iterator() {
        return asList().iterator();
    }
}
