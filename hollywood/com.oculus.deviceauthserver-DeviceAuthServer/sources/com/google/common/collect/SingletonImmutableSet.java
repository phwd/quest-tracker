package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Set;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public final class SingletonImmutableSet<E> extends ImmutableSet<E> {
    private transient int cachedHashCode;
    final transient E element;

    SingletonImmutableSet(E element2) {
        this.element = (E) Preconditions.checkNotNull(element2);
    }

    SingletonImmutableSet(E element2, int hashCode) {
        this.element = element2;
        this.cachedHashCode = hashCode;
    }

    public int size() {
        return 1;
    }

    public boolean isEmpty() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean contains(Object target) {
        return this.element.equals(target);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableSet, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.util.Set, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.element);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int offset) {
        dst[offset] = this.element;
        return offset + 1;
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Set)) {
            return false;
        }
        Set<?> that = (Set) object;
        if (that.size() != 1 || !this.element.equals(that.iterator().next())) {
            return false;
        }
        return true;
    }

    @Override // com.google.common.collect.ImmutableSet
    public final int hashCode() {
        int code = this.cachedHashCode;
        if (code != 0) {
            return code;
        }
        int code2 = this.element.hashCode();
        this.cachedHashCode = code2;
        return code2;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSet
    public boolean isHashCodeFast() {
        return this.cachedHashCode != 0;
    }

    public String toString() {
        String elementToString = this.element.toString();
        StringBuilder sb = new StringBuilder(elementToString.length() + 2);
        sb.append('[');
        sb.append(elementToString);
        sb.append(']');
        return sb.toString();
    }
}
