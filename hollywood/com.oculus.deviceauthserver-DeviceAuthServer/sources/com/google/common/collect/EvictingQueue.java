package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

@Beta
@GwtIncompatible("java.util.ArrayDeque")
public final class EvictingQueue<E> extends ForwardingQueue<E> implements Serializable {
    private static final long serialVersionUID = 0;
    private final Queue<E> delegate;
    @VisibleForTesting
    final int maxSize;

    private EvictingQueue(int maxSize2) {
        Preconditions.checkArgument(maxSize2 >= 0, "maxSize (%s) must >= 0", Integer.valueOf(maxSize2));
        this.delegate = new ArrayDeque(maxSize2);
        this.maxSize = maxSize2;
    }

    public static <E> EvictingQueue<E> create(int maxSize2) {
        return new EvictingQueue<>(maxSize2);
    }

    public int remainingCapacity() {
        return this.maxSize - size();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
    public Queue<E> delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingQueue, java.util.Queue
    public boolean offer(E e) {
        return add(e);
    }

    @Override // java.util.Collection, com.google.common.collect.ForwardingCollection, java.util.Queue
    public boolean add(E e) {
        Preconditions.checkNotNull(e);
        if (this.maxSize == 0) {
            return true;
        }
        if (size() == this.maxSize) {
            this.delegate.remove();
        }
        this.delegate.add(e);
        return true;
    }

    @Override // java.util.Collection, com.google.common.collect.ForwardingCollection
    public boolean addAll(Collection<? extends E> collection) {
        return standardAddAll(collection);
    }

    @Override // com.google.common.collect.ForwardingCollection
    public boolean contains(Object object) {
        return delegate().contains(Preconditions.checkNotNull(object));
    }

    @Override // com.google.common.collect.ForwardingCollection
    public boolean remove(Object object) {
        return delegate().remove(Preconditions.checkNotNull(object));
    }
}
