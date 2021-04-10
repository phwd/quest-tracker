package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public final class EmptyImmutableSet extends ImmutableSet<Object> {
    static final EmptyImmutableSet INSTANCE = new EmptyImmutableSet();
    private static final long serialVersionUID = 0;

    private EmptyImmutableSet() {
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(@Nullable Object target) {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> targets) {
        return targets.isEmpty();
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<Object> iterator() {
        return Iterators.emptyIterator();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int offset) {
        return offset;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<Object> asList() {
        return ImmutableList.of();
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean equals(@Nullable Object object) {
        if (object instanceof Set) {
            return ((Set) object).isEmpty();
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet
    public final int hashCode() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public boolean isHashCodeFast() {
        return true;
    }

    public String toString() {
        return "[]";
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return INSTANCE;
    }
}
