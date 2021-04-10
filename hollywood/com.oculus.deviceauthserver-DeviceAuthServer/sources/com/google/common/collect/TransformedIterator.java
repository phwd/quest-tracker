package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Iterator;

/* access modifiers changed from: package-private */
@GwtCompatible
public abstract class TransformedIterator<F, T> implements Iterator<T> {
    final Iterator<? extends F> backingIterator;

    /* access modifiers changed from: package-private */
    public abstract T transform(F f);

    TransformedIterator(Iterator<? extends F> backingIterator2) {
        this.backingIterator = (Iterator) Preconditions.checkNotNull(backingIterator2);
    }

    public final boolean hasNext() {
        return this.backingIterator.hasNext();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.google.common.collect.TransformedIterator<F, T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Iterator
    public final T next() {
        return (T) transform(this.backingIterator.next());
    }

    public final void remove() {
        this.backingIterator.remove();
    }
}
