package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

@GwtCompatible
public interface PeekingIterator<E> extends Iterator<E> {
    @Override // java.util.Iterator
    E next();

    E peek();

    void remove();
}
