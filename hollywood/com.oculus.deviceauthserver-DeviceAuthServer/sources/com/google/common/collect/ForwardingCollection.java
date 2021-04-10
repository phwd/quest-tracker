package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingCollection<E> extends ForwardingObject implements Collection<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    public abstract Collection<E> delegate();

    protected ForwardingCollection() {
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return delegate().iterator();
    }

    public int size() {
        return delegate().size();
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return delegate().removeAll(collection);
    }

    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    public boolean contains(Object object) {
        return delegate().contains(object);
    }

    @Override // java.util.Collection
    public boolean add(E element) {
        return delegate().add(element);
    }

    public boolean remove(Object object) {
        return delegate().remove(object);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return delegate().containsAll(collection);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return delegate().addAll(collection);
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return delegate().retainAll(collection);
    }

    public void clear() {
        delegate().clear();
    }

    public Object[] toArray() {
        return delegate().toArray();
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] array) {
        return (T[]) delegate().toArray(array);
    }

    /* access modifiers changed from: protected */
    public boolean standardContains(@Nullable Object object) {
        return Iterators.contains(iterator(), object);
    }

    /* access modifiers changed from: protected */
    public boolean standardContainsAll(Collection<?> collection) {
        return Collections2.containsAllImpl(this, collection);
    }

    /* access modifiers changed from: protected */
    public boolean standardAddAll(Collection<? extends E> collection) {
        return Iterators.addAll(this, collection.iterator());
    }

    /* access modifiers changed from: protected */
    public boolean standardRemove(@Nullable Object object) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (Objects.equal(iterator.next(), object)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean standardRemoveAll(Collection<?> collection) {
        return Iterators.removeAll(iterator(), collection);
    }

    /* access modifiers changed from: protected */
    public boolean standardRetainAll(Collection<?> collection) {
        return Iterators.retainAll(iterator(), collection);
    }

    /* access modifiers changed from: protected */
    public void standardClear() {
        Iterators.clear(iterator());
    }

    /* access modifiers changed from: protected */
    public boolean standardIsEmpty() {
        return !iterator().hasNext();
    }

    /* access modifiers changed from: protected */
    public String standardToString() {
        return Collections2.toStringImpl(this);
    }

    /* access modifiers changed from: protected */
    public Object[] standardToArray() {
        return toArray(new Object[size()]);
    }

    /* access modifiers changed from: protected */
    public <T> T[] standardToArray(T[] array) {
        return (T[]) ObjectArrays.toArrayImpl(this, array);
    }
}
