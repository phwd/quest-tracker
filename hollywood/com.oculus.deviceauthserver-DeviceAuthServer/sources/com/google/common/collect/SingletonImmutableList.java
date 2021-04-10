package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public final class SingletonImmutableList<E> extends ImmutableList<E> {
    final transient E element;

    SingletonImmutableList(E element2) {
        this.element = (E) Preconditions.checkNotNull(element2);
    }

    @Override // java.util.List
    public E get(int index) {
        Preconditions.checkElementIndex(index, 1);
        return this.element;
    }

    @Override // com.google.common.collect.ImmutableList
    public int indexOf(@Nullable Object object) {
        return this.element.equals(object) ? 0 : -1;
    }

    @Override // java.util.AbstractCollection, java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.element);
    }

    @Override // com.google.common.collect.ImmutableList
    public int lastIndexOf(@Nullable Object object) {
        return indexOf(object);
    }

    public int size() {
        return 1;
    }

    @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
    public ImmutableList<E> subList(int fromIndex, int toIndex) {
        Preconditions.checkPositionIndexes(fromIndex, toIndex, 1);
        return fromIndex == toIndex ? ImmutableList.of() : this;
    }

    @Override // com.google.common.collect.ImmutableList
    public ImmutableList<E> reverse() {
        return this;
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public boolean contains(@Nullable Object object) {
        return this.element.equals(object);
    }

    @Override // com.google.common.collect.ImmutableList
    public boolean equals(@Nullable Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof List)) {
            return false;
        }
        List<?> that = (List) object;
        if (that.size() != 1 || !this.element.equals(that.get(0))) {
            return false;
        }
        return true;
    }

    @Override // com.google.common.collect.ImmutableList
    public int hashCode() {
        return this.element.hashCode() + 31;
    }

    public String toString() {
        String elementToString = this.element.toString();
        StringBuilder sb = new StringBuilder(elementToString.length() + 2);
        sb.append('[');
        sb.append(elementToString);
        sb.append(']');
        return sb.toString();
    }

    public boolean isEmpty() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int offset) {
        dst[offset] = this.element;
        return offset + 1;
    }
}
