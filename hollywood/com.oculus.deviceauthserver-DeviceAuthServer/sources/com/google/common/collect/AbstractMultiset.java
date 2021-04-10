package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractMultiset<E> extends AbstractCollection<E> implements Multiset<E> {
    private transient Set<E> elementSet;
    private transient Set<Multiset.Entry<E>> entrySet;

    /* access modifiers changed from: package-private */
    public abstract int distinctElements();

    /* access modifiers changed from: package-private */
    public abstract Iterator<Multiset.Entry<E>> entryIterator();

    AbstractMultiset() {
    }

    public int size() {
        return Multisets.sizeImpl(this);
    }

    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    @Override // com.google.common.collect.Multiset
    public boolean contains(@Nullable Object element) {
        return count(element) > 0;
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.Multiset
    public int count(@Nullable Object element) {
        for (Multiset.Entry<E> entry : entrySet()) {
            if (Objects.equal(entry.getElement(), element)) {
                return entry.getCount();
            }
        }
        return 0;
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection
    public boolean add(@Nullable E element) {
        add(element, 1);
        return true;
    }

    @Override // com.google.common.collect.Multiset
    public int add(@Nullable E e, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Multiset
    public boolean remove(@Nullable Object element) {
        return remove(element, 1) > 0;
    }

    @Override // com.google.common.collect.Multiset
    public int remove(@Nullable Object element, int occurrences) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Multiset
    public int setCount(@Nullable E element, int count) {
        return Multisets.setCountImpl(this, element, count);
    }

    @Override // com.google.common.collect.Multiset
    public boolean setCount(@Nullable E element, int oldCount, int newCount) {
        return Multisets.setCountImpl(this, element, oldCount, newCount);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean addAll(Collection<? extends E> elementsToAdd) {
        return Multisets.addAllImpl(this, elementsToAdd);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection
    public boolean removeAll(Collection<?> elementsToRemove) {
        return Multisets.removeAllImpl(this, elementsToRemove);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection
    public boolean retainAll(Collection<?> elementsToRetain) {
        return Multisets.retainAllImpl(this, elementsToRetain);
    }

    public void clear() {
        Iterators.clear(entryIterator());
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
    public boolean equals(@Nullable Object object) {
        return Multisets.equalsImpl(this, object);
    }

    @Override // com.google.common.collect.Multiset
    public int hashCode() {
        return entrySet().hashCode();
    }

    @Override // com.google.common.collect.Multiset
    public String toString() {
        return entrySet().toString();
    }
}
