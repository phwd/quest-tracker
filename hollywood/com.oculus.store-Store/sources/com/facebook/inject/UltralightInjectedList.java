package com.facebook.inject;

import com.facebook.ultralight.UL;
import java.util.AbstractList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javax.annotation.concurrent.GuardedBy;

public final class UltralightInjectedList<T> extends AbstractList<T> {
    private static final Object ITEM_UNINITIALIZED = new Object();
    private final int[] mCollectionIndexToBindingId;
    @GuardedBy("mItems")
    private int mFirstUninitializedIndex = 0;
    private final InjectorLike mInjector;
    private final Object[] mItems;

    static /* synthetic */ int access$204(UltralightInjectedList x0) {
        int i = x0.mFirstUninitializedIndex + 1;
        x0.mFirstUninitializedIndex = i;
        return i;
    }

    public UltralightInjectedList(InjectorLike injector, int[] collectionIndexToBindingId) {
        this.mInjector = (InjectorLike) injector.getScopeAwareInjector();
        this.mCollectionIndexToBindingId = collectionIndexToBindingId;
        this.mItems = new Object[collectionIndexToBindingId.length];
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.AbstractList
    public T get(int index) {
        if (index < 0 || index >= this.mItems.length) {
            throw new RuntimeException("Cannot call get with an index less than 0 or greater/equal to than size()");
        }
        synchronized (this.mItems) {
            if (this.mItems[index] == null) {
                this.mItems[index] = UL.factorymap.get(this.mCollectionIndexToBindingId[index], this.mInjector);
            }
        }
        return (T) this.mItems[index];
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public Stream<T> stream() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public Stream<T> parallelStream() {
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

    @Override // java.util.List
    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean removeIf(Predicate<? super T> predicate) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void replaceAll(UnaryOperator<T> unaryOperator) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    public void sort(Comparator<? super T> comparator) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator<T> iterator() {
        return new IndexedIterator();
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super T> consumer) {
        throw new UnsupportedOperationException();
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
            return this.mIndex.get() < UltralightInjectedList.this.size();
        }

        @Override // java.util.Iterator
        public T next() {
            Object item;
            int idx = this.mIndex.getAndIncrement();
            if (idx >= UltralightInjectedList.this.size()) {
                throw new NoSuchElementException();
            }
            synchronized (UltralightInjectedList.this.mItems) {
                if (idx < UltralightInjectedList.this.mFirstUninitializedIndex) {
                    while (true) {
                        item = (T) UltralightInjectedList.this.mItems[idx];
                        if (item != UltralightInjectedList.ITEM_UNINITIALIZED) {
                            break;
                        }
                        try {
                            UltralightInjectedList.this.mItems.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    UltralightInjectedList.this.mItems[idx] = UltralightInjectedList.ITEM_UNINITIALIZED;
                    UltralightInjectedList.access$204(UltralightInjectedList.this);
                    try {
                        item = (T) UL.factorymap.get(UltralightInjectedList.this.mCollectionIndexToBindingId[idx], UltralightInjectedList.this.mInjector);
                        synchronized (UltralightInjectedList.this.mItems) {
                            UltralightInjectedList.this.mItems[idx] = item;
                            UltralightInjectedList.this.mItems.notifyAll();
                        }
                    } catch (IllegalArgumentException e2) {
                        throw new IllegalArgumentException("Invalid binding id %d" + UltralightInjectedList.this.mCollectionIndexToBindingId[idx], e2);
                    } catch (Throwable th) {
                        synchronized (UltralightInjectedList.this.mItems) {
                            UltralightInjectedList.this.mItems[idx] = null;
                            UltralightInjectedList.this.mItems.notifyAll();
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
