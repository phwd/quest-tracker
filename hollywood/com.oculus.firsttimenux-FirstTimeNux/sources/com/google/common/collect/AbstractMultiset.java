package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
abstract class AbstractMultiset<E> extends AbstractCollection<E> implements Multiset<E> {
    @MonotonicNonNullDecl
    private transient Set<E> elementSet;
    @MonotonicNonNullDecl
    private transient Set<Multiset.Entry<E>> entrySet;

    public abstract void clear();

    /* access modifiers changed from: package-private */
    public abstract int distinctElements();

    /* access modifiers changed from: package-private */
    public abstract Iterator<E> elementIterator();

    /* access modifiers changed from: package-private */
    public abstract Iterator<Multiset.Entry<E>> entryIterator();

    AbstractMultiset() {
    }

    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override // com.google.common.collect.Multiset
    public boolean contains(@NullableDecl Object element) {
        return count(element) > 0;
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean add(@NullableDecl E element) {
        add(element, 1);
        return true;
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(@NullableDecl E e, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object element) {
        return remove(element, 1) > 0;
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(@NullableDecl E element, int count) {
        return Multisets.setCountImpl(this, element, count);
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(@NullableDecl E element, int oldCount, int newCount) {
        return Multisets.setCountImpl(this, element, oldCount, newCount);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> elementsToAdd) {
        return Multisets.addAllImpl(this, elementsToAdd);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> elementsToRemove) {
        return Multisets.removeAllImpl(this, elementsToRemove);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> elementsToRetain) {
        return Multisets.retainAllImpl(this, elementsToRetain);
    }

    @Override // com.google.common.collect.Multiset
    public Set<E> elementSet() {
        Set<E> result = this.elementSet;
        if (result != null) {
            return result;
        }
        Set<E> result2 = createElementSet();
        this.elementSet = result2;
        return result2;
    }

    /* access modifiers changed from: package-private */
    public Set<E> createElementSet() {
        return new ElementSet();
    }

    /* access modifiers changed from: package-private */
    public class ElementSet extends Multisets.ElementSet<E> {
        ElementSet() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multisets.ElementSet
        public Multiset<E> multiset() {
            return AbstractMultiset.this;
        }

        @Override // java.util.AbstractCollection, com.google.common.collect.Multisets.ElementSet, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<E> iterator() {
            return AbstractMultiset.this.elementIterator();
        }
    }

    @Override // com.google.common.collect.Multiset
    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> result = this.entrySet;
        if (result != null) {
            return result;
        }
        Set<Multiset.Entry<E>> result2 = createEntrySet();
        this.entrySet = result2;
        return result2;
    }

    /* access modifiers changed from: package-private */
    public class EntrySet extends Multisets.EntrySet<E> {
        EntrySet() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multisets.EntrySet
        public Multiset<E> multiset() {
            return AbstractMultiset.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
        public Iterator<Multiset.Entry<E>> iterator() {
            return AbstractMultiset.this.entryIterator();
        }

        public int size() {
            return AbstractMultiset.this.distinctElements();
        }
    }

    /* access modifiers changed from: package-private */
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    @Override // com.google.common.collect.Multiset
    public final boolean equals(@NullableDecl Object object) {
        return Multisets.equalsImpl(this, object);
    }

    @Override // com.google.common.collect.Multiset
    public final int hashCode() {
        return entrySet().hashCode();
    }

    @Override // com.google.common.collect.Multiset
    public final String toString() {
        return entrySet().toString();
    }
}
