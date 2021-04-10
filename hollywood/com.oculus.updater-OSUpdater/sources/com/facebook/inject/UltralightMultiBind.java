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

    static /* synthetic */ int access$204(UltralightMultiBind ultralightMultiBind) {
        int i = ultralightMultiBind.mFirstUninitializedIndex + 1;
        ultralightMultiBind.mFirstUninitializedIndex = i;
        return i;
    }

    public UltralightMultiBind(InjectorLike injectorLike, int[] iArr) {
        this.mInjector = (InjectorLike) injectorLike.getScopeAwareInjector();
        this.mCollectionIndexToBindingId = iArr;
        this.mItems = new Object[iArr.length];
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
            return this.mIndex.get() < UltralightMultiBind.this.size();
        }

        @Override // java.util.Iterator
        public T next() {
            T t;
            int andIncrement = this.mIndex.getAndIncrement();
            if (andIncrement < UltralightMultiBind.this.size()) {
                synchronized (UltralightMultiBind.this.mItems) {
                    if (andIncrement < UltralightMultiBind.this.mFirstUninitializedIndex) {
                        while (true) {
                            t = (T) UltralightMultiBind.this.mItems[andIncrement];
                            if (t != UltralightMultiBind.ITEM_UNINITIALIZED) {
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
                        UltralightMultiBind.this.mItems[andIncrement] = UltralightMultiBind.ITEM_UNINITIALIZED;
                        UltralightMultiBind.access$204(UltralightMultiBind.this);
                        try {
                            t = (T) UL.factorymap.get(UltralightMultiBind.this.mCollectionIndexToBindingId[andIncrement], UltralightMultiBind.this.mInjector);
                            synchronized (UltralightMultiBind.this.mItems) {
                                UltralightMultiBind.this.mItems[andIncrement] = t;
                                UltralightMultiBind.this.mItems.notifyAll();
                            }
                        } catch (IllegalArgumentException e2) {
                            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(UltralightMultiBind.this.mCollectionIndexToBindingId[andIncrement])), e2);
                        } catch (Throwable th) {
                            synchronized (UltralightMultiBind.this.mItems) {
                                UltralightMultiBind.this.mItems[andIncrement] = null;
                                UltralightMultiBind.this.mItems.notifyAll();
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
