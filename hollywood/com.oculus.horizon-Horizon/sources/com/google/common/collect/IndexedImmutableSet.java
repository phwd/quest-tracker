package com.google.common.collect;

import X.AbstractC07380s1;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

@GwtCompatible(emulated = true)
public abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    public abstract E A0N(int i);

    @Override // com.google.common.collect.ImmutableSet
    public final ImmutableList<E> A0L() {
        return new ImmutableList<E>() {
            /* class com.google.common.collect.IndexedImmutableSet.AnonymousClass1 */

            @Override // com.google.common.collect.ImmutableCollection
            public final boolean A0H() {
                return IndexedImmutableSet.this.A0H();
            }

            @Override // java.util.List
            public final E get(int i) {
                return (E) IndexedImmutableSet.this.A0N(i);
            }

            public final int size() {
                return IndexedImmutableSet.this.size();
            }
        };
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public final int A0I(Object[] objArr, int i) {
        return A0J().A0I(objArr, i);
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    /* renamed from: A0K */
    public final AbstractC07380s1<E> iterator() {
        return A0J().iterator();
    }
}
