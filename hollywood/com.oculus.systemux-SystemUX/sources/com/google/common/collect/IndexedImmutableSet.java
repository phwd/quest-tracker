package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
public abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    /* access modifiers changed from: package-private */
    public abstract E get(int i);

    IndexedImmutableSet() {
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return asList().iterator();
    }

    @Override // java.util.Collection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public Spliterator<E> spliterator() {
        return CollectSpliterators.indexed(size(), 1297, new IntFunction() {
            /* class com.google.common.collect.$$Lambda$XwUdI4WSKT8Ax195d22nrD1Xy9w */

            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return IndexedImmutableSet.this.get(i);
            }
        });
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super E> consumer) {
        Preconditions.checkNotNull(consumer);
        int size = size();
        for (int i = 0; i < size; i++) {
            consumer.accept(get(i));
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public int copyIntoArray(Object[] objArr, int i) {
        return asList().copyIntoArray(objArr, i);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList<E> createAsList() {
        return new ImmutableAsList<E>() {
            /* class com.google.common.collect.IndexedImmutableSet.AnonymousClass1 */

            @Override // java.util.List
            public E get(int i) {
                return (E) IndexedImmutableSet.this.get(i);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableAsList, com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return IndexedImmutableSet.this.isPartialView();
            }

            @Override // com.google.common.collect.ImmutableAsList
            public int size() {
                return IndexedImmutableSet.this.size();
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableAsList
            public ImmutableCollection<E> delegateCollection() {
                return IndexedImmutableSet.this;
            }
        };
    }
}
