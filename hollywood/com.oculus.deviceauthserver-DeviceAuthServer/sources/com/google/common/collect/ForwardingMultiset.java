package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingMultiset<E> extends ForwardingCollection<E> implements Multiset<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
    public abstract Multiset<E> delegate();

    protected ForwardingMultiset() {
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object element) {
        return delegate().count(element);
    }

    @Override // com.google.common.collect.Multiset
    public int add(E element, int occurrences) {
        return delegate().add(element, occurrences);
    }

    @Override // com.google.common.collect.Multiset
    public int remove(Object element, int occurrences) {
        return delegate().remove(element, occurrences);
    }

    @Override // com.google.common.collect.Multiset
    public Set<E> elementSet() {
        return delegate().elementSet();
    }

    @Override // com.google.common.collect.Multiset
    public Set<Multiset.Entry<E>> entrySet() {
        return delegate().entrySet();
    }

    @Override // com.google.common.collect.Multiset
    public boolean equals(@Nullable Object object) {
        return object == this || delegate().equals(object);
    }

    @Override // com.google.common.collect.Multiset
    public int hashCode() {
        return delegate().hashCode();
    }

    @Override // com.google.common.collect.Multiset
    public int setCount(E element, int count) {
        return delegate().setCount(element, count);
    }

    @Override // com.google.common.collect.Multiset
    public boolean setCount(E element, int oldCount, int newCount) {
        return delegate().setCount(element, oldCount, newCount);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardContains(@Nullable Object object) {
        return count(object) > 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public void standardClear() {
        Iterators.clear(entrySet().iterator());
    }

    /* access modifiers changed from: protected */
    @Beta
    public int standardCount(@Nullable Object object) {
        for (Multiset.Entry<E> entry : entrySet()) {
            if (Objects.equal(entry.getElement(), object)) {
                return entry.getCount();
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public boolean standardAdd(E element) {
        add(element, 1);
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    @Beta
    public boolean standardAddAll(Collection<? extends E> elementsToAdd) {
        return Multisets.addAllImpl(this, elementsToAdd);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRemove(Object element) {
        return remove(element, 1) > 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRemoveAll(Collection<?> elementsToRemove) {
        return Multisets.removeAllImpl(this, elementsToRemove);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRetainAll(Collection<?> elementsToRetain) {
        return Multisets.retainAllImpl(this, elementsToRetain);
    }

    /* access modifiers changed from: protected */
    public int standardSetCount(E element, int count) {
        return Multisets.setCountImpl(this, element, count);
    }

    /* access modifiers changed from: protected */
    public boolean standardSetCount(E element, int oldCount, int newCount) {
        return Multisets.setCountImpl(this, element, oldCount, newCount);
    }

    @Beta
    protected class StandardElementSet extends Multisets.ElementSet<E> {
        public StandardElementSet() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multisets.ElementSet
        public Multiset<E> multiset() {
            return ForwardingMultiset.this;
        }
    }

    /* access modifiers changed from: protected */
    public Iterator<E> standardIterator() {
        return Multisets.iteratorImpl(this);
    }

    /* access modifiers changed from: protected */
    public int standardSize() {
        return Multisets.sizeImpl(this);
    }

    /* access modifiers changed from: protected */
    public boolean standardEquals(@Nullable Object object) {
        return Multisets.equalsImpl(this, object);
    }

    /* access modifiers changed from: protected */
    public int standardHashCode() {
        return entrySet().hashCode();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public String standardToString() {
        return entrySet().toString();
    }
}
