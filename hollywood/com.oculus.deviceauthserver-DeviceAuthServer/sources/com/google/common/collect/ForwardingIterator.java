package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
public abstract class ForwardingIterator<T> extends ForwardingObject implements Iterator<T> {
    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    public abstract Iterator<T> delegate();

    protected ForwardingIterator() {
    }

    public boolean hasNext() {
        return delegate().hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        return delegate().next();
    }

    public void remove() {
        delegate().remove();
    }
}
