package com.google.common.collect;

import X.AnonymousClass8f;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

@GwtCompatible(emulated = true)
public abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    public abstract E A0H(int i);

    @Override // com.google.common.collect.ImmutableSet
    public final ImmutableList<E> A0E() {
        return new ImmutableList<E>() {
            /* class com.google.common.collect.IndexedImmutableSet.AnonymousClass1 */

            @Override // java.util.List
            public final E get(int i) {
                return (E) IndexedImmutableSet.this.A0H(i);
            }

            public final int size() {
                return IndexedImmutableSet.this.size();
            }
        };
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public final int A0C(Object[] objArr, int i) {
        return A0F().A0C(objArr, i);
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    /* renamed from: A0D */
    public final AnonymousClass8f<E> iterator() {
        return A0F().iterator();
    }
}
