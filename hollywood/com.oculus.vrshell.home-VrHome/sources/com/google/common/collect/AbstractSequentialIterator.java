package com.google.common.collect;

import java.util.NoSuchElementException;

public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {
    private T nextOrNull;

    /* access modifiers changed from: protected */
    public abstract T computeNext(T t);

    protected AbstractSequentialIterator(T firstOrNull) {
        this.nextOrNull = firstOrNull;
    }

    public final boolean hasNext() {
        return this.nextOrNull != null;
    }

    @Override // java.util.Iterator
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return this.nextOrNull;
        } finally {
            this.nextOrNull = computeNext(this.nextOrNull);
        }
    }
}
