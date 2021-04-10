package com.facebook.inject;

import com.facebook.ultralight.UL;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

public final class UltralightMultiBind<T> extends AbstractCollection<T> implements Set<T> {
    private static final Object ITEM_UNINITIALIZED = new Object();
    private final int[] mCollectionIndexToBindingId;
    @GuardedBy("mItems")
    private int mFirstUninitializedIndex = 0;
    private final InjectorLike mInjector;
    private final Object[] mItems;

    static /* synthetic */ int access$204(UltralightMultiBind x0) {
        int i = x0.mFirstUninitializedIndex + 1;
        x0.mFirstUninitializedIndex = i;
        return i;
    }

    public UltralightMultiBind(InjectorLike injector, int[] collectionIndexToBindingId) {
        this.mInjector = (InjectorLike) injector.getScopeAwareInjector();
        this.mCollectionIndexToBindingId = collectionIndexToBindingId;
        this.mItems = new Object[collectionIndexToBindingId.length];
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
            return this.mIndex.get() < UltralightMultiBind.this.size();
        }

        @Override // java.util.Iterator
        public T next() {
            Object item;
            int idx = this.mIndex.getAndIncrement();
            if (idx >= UltralightMultiBind.this.size()) {
                throw new NoSuchElementException();
            }
            synchronized (UltralightMultiBind.this.mItems) {
                if (idx < UltralightMultiBind.this.mFirstUninitializedIndex) {
                    while (true) {
                        item = (T) UltralightMultiBind.this.mItems[idx];
                        if (item != UltralightMultiBind.ITEM_UNINITIALIZED) {
                            break;
                        }
                        try {
                            UltralightMultiBind.this.mItems.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    UltralightMultiBind.this.mItems[idx] = UltralightMultiBind.ITEM_UNINITIALIZED;
                    UltralightMultiBind.access$204(UltralightMultiBind.this);
                    try {
                        item = (T) UL.factorymap.get(UltralightMultiBind.this.mCollectionIndexToBindingId[idx], UltralightMultiBind.this.mInjector);
                        synchronized (UltralightMultiBind.this.mItems) {
                            UltralightMultiBind.this.mItems[idx] = item;
                            UltralightMultiBind.this.mItems.notifyAll();
                        }
                    } catch (IllegalArgumentException e2) {
                        throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(UltralightMultiBind.this.mCollectionIndexToBindingId[idx])), e2);
                    } catch (Throwable th) {
                        synchronized (UltralightMultiBind.this.mItems) {
                            UltralightMultiBind.this.mItems[idx] = null;
                            UltralightMultiBind.this.mItems.notifyAll();
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
