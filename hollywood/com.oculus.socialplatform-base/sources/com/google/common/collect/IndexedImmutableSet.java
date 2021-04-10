package com.google.common.collect;

import X.AbstractC05710wh;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

@GwtCompatible(emulated = true)
public abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    public abstract E A0L(int i);

    @Override // com.google.common.collect.ImmutableSet
    public final ImmutableList<E> A0J() {
        return new ImmutableList<E>() {
            /* class com.google.common.collect.IndexedImmutableSet.AnonymousClass1 */

            @Override // com.google.common.collect.ImmutableCollection
            public final boolean A0F() {
                return IndexedImmutableSet.this.A0F();
            }

            @Override // java.util.List
            public final E get(int i) {
                return (E) IndexedImmutableSet.this.A0L(i);
            }

            public final int size() {
                return IndexedImmutableSet.this.size();
            }
        };
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public final int A0G(Object[] objArr, int i) {
        return A0H().A0G(objArr, i);
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    /* renamed from: A0I */
    public final AbstractC05710wh<E> iterator() {
        return A0H().iterator();
    }
}
