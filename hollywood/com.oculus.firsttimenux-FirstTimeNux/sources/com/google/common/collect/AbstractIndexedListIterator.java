package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

/* access modifiers changed from: package-private */
@GwtCompatible
public abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E> {
    private int position;
    private final int size;

    /* access modifiers changed from: protected */
    public abstract E get(int i);

    protected AbstractIndexedListIterator(int size2) {
        this(size2, 0);
    }

    protected AbstractIndexedListIterator(int size2, int position2) {
        Preconditions.checkPositionIndex(position2, size2);
        this.size = size2;
        this.position = position2;
    }

    public final boolean hasNext() {
        return this.position < this.size;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = this.position;
        this.position = i + 1;
        return get(i);
    }

    public final int nextIndex() {
        return this.position;
    }

    public final boolean hasPrevious() {
        return this.position > 0;
    }

    @Override // java.util.ListIterator
    public final E previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i = this.position - 1;
        this.position = i;
        return get(i);
    }

    public final int previousIndex() {
        return this.position - 1;
    }
}
