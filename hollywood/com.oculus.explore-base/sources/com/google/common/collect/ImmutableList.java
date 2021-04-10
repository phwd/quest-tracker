package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {
    private static final UnmodifiableListIterator<Object> EMPTY_ITR = new Itr(RegularImmutableList.EMPTY, 0);

    public static <E> ImmutableList<E> of() {
        return (ImmutableList<E>) RegularImmutableList.EMPTY;
    }

    static <E> ImmutableList<E> asImmutableList(Object[] elements, int length) {
        if (length == 0) {
            return of();
        }
        return new RegularImmutableList(elements, length);
    }

    ImmutableList() {
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
    public UnmodifiableIterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public UnmodifiableListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public UnmodifiableListIterator<E> listIterator(int index) {
        Preconditions.checkPositionIndex(index, size());
        return isEmpty() ? (UnmodifiableListIterator<E>) EMPTY_ITR : new Itr(this, index);
    }

    /* access modifiers changed from: package-private */
    public static class Itr<E> extends AbstractIndexedListIterator<E> {
        private final ImmutableList<E> list;

        Itr(ImmutableList<E> list2, int index) {
            super(list2.size(), index);
            this.list = list2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.AbstractIndexedListIterator
        public E get(int index) {
            return this.list.get(index);
        }
    }

    public int indexOf(Object object) {
        if (object == null) {
            return -1;
        }
        return Lists.indexOfImpl(this, object);
    }

    public int lastIndexOf(Object object) {
        if (object == null) {
            return -1;
        }
        return Lists.lastIndexOfImpl(this, object);
    }

    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override // java.util.List
    public ImmutableList<E> subList(int fromIndex, int toIndex) {
        Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
        int length = toIndex - fromIndex;
        if (length == size()) {
            return this;
        }
        if (length == 0) {
            return of();
        }
        return subListUnchecked(fromIndex, toIndex);
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> subListUnchecked(int fromIndex, int toIndex) {
        return new SubList(fromIndex, toIndex - fromIndex);
    }

    /* access modifiers changed from: package-private */
    public class SubList extends ImmutableList<E> {
        final transient int length;
        final transient int offset;

        @Override // java.util.AbstractCollection, java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList, java.util.Collection, com.google.common.collect.ImmutableCollection, com.google.common.collect.ImmutableCollection, java.lang.Iterable
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return ImmutableList.super.iterator();
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public /* bridge */ /* synthetic */ ListIterator listIterator() {
            return ImmutableList.super.listIterator();
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public /* bridge */ /* synthetic */ ListIterator listIterator(int i) {
            return ImmutableList.super.listIterator(i);
        }

        SubList(int offset2, int length2) {
            this.offset = offset2;
            this.length = length2;
        }

        public int size() {
            return this.length;
        }

        @Override // java.util.List
        public E get(int index) {
            Preconditions.checkElementIndex(index, this.length);
            return (E) ImmutableList.this.get(this.offset + index);
        }

        @Override // java.util.List, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableList
        public ImmutableList<E> subList(int fromIndex, int toIndex) {
            Preconditions.checkPositionIndexes(fromIndex, toIndex, this.length);
            return ImmutableList.this.subList(this.offset + fromIndex, this.offset + toIndex);
        }
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int index, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int index, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int index, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E remove(int index) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] dst, int offset) {
        int size = size();
        for (int i = 0; i < size; i++) {
            dst[offset + i] = get(i);
        }
        return offset + size;
    }

    public boolean equals(Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    public int hashCode() {
        int hashCode = 1;
        int n = size();
        for (int i = 0; i < n; i++) {
            hashCode = (((hashCode * 31) + get(i).hashCode()) ^ -1) ^ -1;
        }
        return hashCode;
    }

    public static final class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            this(4);
        }

        Builder(int capacity) {
            super(capacity);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder
        public Builder<E> add(E element) {
            super.add((Object) element);
            return this;
        }

        public ImmutableList<E> build() {
            this.forceCopy = true;
            return ImmutableList.asImmutableList(this.contents, this.size);
        }
    }
}
