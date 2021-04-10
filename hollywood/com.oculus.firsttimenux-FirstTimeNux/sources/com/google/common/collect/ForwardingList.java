package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class ForwardingList<E> extends ForwardingCollection<E> implements List<E> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
    public abstract List<E> delegate();

    protected ForwardingList() {
    }

    @Override // java.util.List
    public void add(int index, E element) {
        delegate().add(index, element);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public boolean addAll(int index, Collection<? extends E> elements) {
        return delegate().addAll(index, elements);
    }

    @Override // java.util.List
    public E get(int index) {
        return delegate().get(index);
    }

    public int indexOf(Object element) {
        return delegate().indexOf(element);
    }

    public int lastIndexOf(Object element) {
        return delegate().lastIndexOf(element);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return delegate().listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int index) {
        return delegate().listIterator(index);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public E remove(int index) {
        return delegate().remove(index);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public E set(int index, E element) {
        return delegate().set(index, element);
    }

    @Override // java.util.List
    public List<E> subList(int fromIndex, int toIndex) {
        return delegate().subList(fromIndex, toIndex);
    }

    public boolean equals(@NullableDecl Object object) {
        return object == this || delegate().equals(object);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    /* access modifiers changed from: protected */
    public boolean standardAdd(E element) {
        add(size(), element);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean standardAddAll(int index, Iterable<? extends E> elements) {
        return Lists.addAllImpl(this, index, elements);
    }

    /* access modifiers changed from: protected */
    public int standardIndexOf(@NullableDecl Object element) {
        return Lists.indexOfImpl(this, element);
    }

    /* access modifiers changed from: protected */
    public int standardLastIndexOf(@NullableDecl Object element) {
        return Lists.lastIndexOfImpl(this, element);
    }

    /* access modifiers changed from: protected */
    public Iterator<E> standardIterator() {
        return listIterator();
    }

    /* access modifiers changed from: protected */
    public ListIterator<E> standardListIterator() {
        return listIterator(0);
    }

    /* access modifiers changed from: protected */
    @Beta
    public ListIterator<E> standardListIterator(int start) {
        return Lists.listIteratorImpl(this, start);
    }

    /* access modifiers changed from: protected */
    @Beta
    public List<E> standardSubList(int fromIndex, int toIndex) {
        return Lists.subListImpl(this, fromIndex, toIndex);
    }

    /* access modifiers changed from: protected */
    @Beta
    public boolean standardEquals(@NullableDecl Object object) {
        return Lists.equalsImpl(this, object);
    }

    /* access modifiers changed from: protected */
    @Beta
    public int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }
}
