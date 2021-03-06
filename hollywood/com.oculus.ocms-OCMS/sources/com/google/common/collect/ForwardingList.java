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
    public void add(int i, E e) {
        delegate().add(i, e);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public boolean addAll(int i, Collection<? extends E> collection) {
        return delegate().addAll(i, collection);
    }

    @Override // java.util.List
    public E get(int i) {
        return delegate().get(i);
    }

    public int indexOf(Object obj) {
        return delegate().indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        return delegate().lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return delegate().listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return delegate().listIterator(i);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public E remove(int i) {
        return delegate().remove(i);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public E set(int i, E e) {
        return delegate().set(i, e);
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return delegate().subList(i, i2);
    }

    public boolean equals(@NullableDecl Object obj) {
        return obj == this || delegate().equals(obj);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    /* access modifiers changed from: protected */
    public boolean standardAdd(E e) {
        add(size(), e);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean standardAddAll(int i, Iterable<? extends E> iterable) {
        return Lists.addAllImpl(this, i, iterable);
    }

    /* access modifiers changed from: protected */
    public int standardIndexOf(@NullableDecl Object obj) {
        return Lists.indexOfImpl(this, obj);
    }

    /* access modifiers changed from: protected */
    public int standardLastIndexOf(@NullableDecl Object obj) {
        return Lists.lastIndexOfImpl(this, obj);
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
    public ListIterator<E> standardListIterator(int i) {
        return Lists.listIteratorImpl(this, i);
    }

    /* access modifiers changed from: protected */
    @Beta
    public List<E> standardSubList(int i, int i2) {
        return Lists.subListImpl(this, i, i2);
    }

    /* access modifiers changed from: protected */
    @Beta
    public boolean standardEquals(@NullableDecl Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    /* access modifiers changed from: protected */
    @Beta
    public int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }
}
