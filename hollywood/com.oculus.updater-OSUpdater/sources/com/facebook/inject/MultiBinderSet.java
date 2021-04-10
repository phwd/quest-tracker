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

    static /* synthetic */ int access$204(MultiBinderSet multiBinderSet) {
        int i = multiBinderSet.mFirstUninitializedIndex + 1;
        multiBinderSet.mFirstUninitializedIndex = i;
        return i;
    }

    public MultiBinderSet(Injector injector, MultiBindIndexedProvider<T> multiBindIndexedProvider) {
        this.mInjector = injector;
        this.mProvider = multiBindIndexedProvider;
        this.mItems = new Object[multiBindIndexedProvider.size()];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (obj == null) {
                if (next == null) {
                    return true;
                }
            } else if (obj.equals(next)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(Object obj) {
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
            T t;
            int andIncrement = this.mIndex.getAndIncrement();
            if (andIncrement < MultiBinderSet.this.size()) {
                synchronized (MultiBinderSet.this.mItems) {
                    if (andIncrement < MultiBinderSet.this.mFirstUninitializedIndex) {
                        while (true) {
                            t = (T) MultiBinderSet.this.mItems[andIncrement];
                            if (t != MultiBinderSet.ITEM_UNINITIALIZED) {
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
                        MultiBinderSet.this.mItems[andIncrement] = MultiBinderSet.ITEM_UNINITIALIZED;
                        MultiBinderSet.access$204(MultiBinderSet.this);
                        try {
                            t = (T) MultiBinderSet.this.mProvider.provide(MultiBinderSet.this.mInjector, andIncrement);
                            synchronized (MultiBinderSet.this.mItems) {
                                MultiBinderSet.this.mItems[andIncrement] = t;
                                MultiBinderSet.this.mItems.notifyAll();
                            }
                        } catch (Throwable th) {
                            synchronized (MultiBinderSet.this.mItems) {
                                MultiBinderSet.this.mItems[andIncrement] = null;
                                MultiBinderSet.this.mItems.notifyAll();
                                throw th;
                            }
                        }
                    }
                }
                return t;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
