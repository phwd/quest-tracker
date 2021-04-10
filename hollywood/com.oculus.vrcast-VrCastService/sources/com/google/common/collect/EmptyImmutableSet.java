package com.google.common.collect;

import java.util.Collection;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class EmptyImmutableSet extends ImmutableSet<Object> {
    static final EmptyImmutableSet INSTANCE = new EmptyImmutableSet();
    private static final long serialVersionUID = 0;

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(Object obj) {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        return i;
    }

    @Override // com.google.common.collect.ImmutableSet
    public final int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public boolean isHashCodeFast() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return 0;
    }

    public String toString() {
        return "[]";
    }

    private EmptyImmutableSet() {
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        return collection.isEmpty();
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<Object> iterator() {
        return Iterators.emptyIterator();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<Object> asList() {
        return ImmutableList.of();
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean equals(Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return INSTANCE;
    }
}
