package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

public enum Iterators$EmptyModifiableIterator implements Iterator {
    INSTANCE;

    public boolean hasNext() {
        return false;
    }

    public void remove() {
        Preconditions.checkState(false, "no calls to next() since the last call to remove()");
    }

    @Override // java.util.Iterator
    public Object next() {
        throw new NoSuchElementException();
    }
}
