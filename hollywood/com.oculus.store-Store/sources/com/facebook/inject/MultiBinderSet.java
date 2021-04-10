package com.facebook.inject;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

public final class MultiBinderSet<T> extends AbstractSet<T> {
    private static final Object ITEM_UNINITIALIZED = new Object();
    @GuardedBy("mItems")
    private int mFirstUninitializedIndex = 0;
    private final Injector mInjector;
    private final Object[] mItems;
    private final MultiBindIndexedProvider<T> mProvider;

    static /* synthetic */ int access$204(MultiBinderSet x0) {
        int i = x0.mFirstUninitializedIndex + 1;
        x0.mFirstUninitializedIndex = i;
        return i;
    }

    public MultiBinderSet(Injector injector, MultiBindIndexedProvider<T> provider) {
        this.mInjector = injector;
        this.mProvider = provider;
        this.mItems = new Object[provider.size()];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object other) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T obj = it.next();
            if (other == null) {
                if (obj == null) {
                }
            } else if (other.equals(obj)) {
            }
            return true;
        }
        return false;
    }

    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator<T> iterator() {
        return new IndexedIterator();
    }

    public int size() {
        return this.mItems.length;
    }

    /* access modifiers changed from: private */
    public class IndexedIterator implements Iterator<T> {
        private final AtomicInteger mIndex;

        private IndexedIterator() {
            this.mIndex = new AtomicInteger(0);
        }

        public boolean hasNext() {
            return this.mIndex.get() < MultiBinderSet.this.size();
        }

        @Override // java.util.Iterator
        public T next() {
            Object item;
            int idx = this.mIndex.getAndIncrement();
            if (idx >= MultiBinderSet.this.size()) {
                throw new NoSuchElementException();
            }
            synchronized (MultiBinderSet.this.mItems) {
                if (idx < MultiBinderSet.this.mFirstUninitializedIndex) {
                    while (true) {
                        item = (T) MultiBinderSet.this.mItems[idx];
                        if (item != MultiBinderSet.ITEM_UNINITIALIZED) {
                            break;
                        }
                        try {
                            MultiBinderSet.this.mItems.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    MultiBinderSet.this.mItems[idx] = MultiBinderSet.ITEM_UNINITIALIZED;
                    MultiBinderSet.access$204(MultiBinderSet.this);
                    try {
                        item = (T) MultiBinderSet.this.mProvider.provide(MultiBinderSet.this.mInjector, idx);
                        synchronized (MultiBinderSet.this.mItems) {
                            MultiBinderSet.this.mItems[idx] = item;
                            MultiBinderSet.this.mItems.notifyAll();
                        }
                    } catch (Throwable th) {
                        synchronized (MultiBinderSet.this.mItems) {
                            MultiBinderSet.this.mItems[idx] = null;
                            MultiBinderSet.this.mItems.notifyAll();
                            throw th;
                        }
                    }
                }
            }
            return (T) item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
