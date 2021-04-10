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

    static /* synthetic */ int access$204(UltralightInjectedList ultralightInjectedList) {
        int i = ultralightInjectedList.mFirstUninitializedIndex + 1;
        ultralightInjectedList.mFirstUninitializedIndex = i;
        return i;
    }

    public UltralightInjectedList(InjectorLike injectorLike, int[] iArr) {
        this.mInjector = (InjectorLike) injectorLike.getScopeAwareInjector();
        this.mCollectionIndexToBindingId = iArr;
        this.mItems = new Object[iArr.length];
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.AbstractList
    public T get(int i) {
        if (i >= 0) {
            Object[] objArr = this.mItems;
            if (i < objArr.length) {
                synchronized (objArr) {
                    if (this.mItems[i] == null) {
                        this.mItems[i] = UL.factorymap.get(this.mCollectionIndexToBindingId[i], this.mInjector);
                    }
                }
                return (T) this.mItems[i];
            }
        }
        throw new RuntimeException("Cannot call get with an index less than 0 or greater/equal to than size()");
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

    @Override // java.util.List
    public boolean remove(Object obj) {
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
            T t;
            int andIncrement = this.mIndex.getAndIncrement();
            if (andIncrement < UltralightInjectedList.this.size()) {
                synchronized (UltralightInjectedList.this.mItems) {
                    if (andIncrement < UltralightInjectedList.this.mFirstUninitializedIndex) {
                        while (true) {
                            t = (T) UltralightInjectedList.this.mItems[andIncrement];
                            if (t != UltralightInjectedList.ITEM_UNINITIALIZED) {
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
                        UltralightInjectedList.this.mItems[andIncrement] = UltralightInjectedList.ITEM_UNINITIALIZED;
                        UltralightInjectedList.access$204(UltralightInjectedList.this);
                        try {
                            t = (T) UL.factorymap.get(UltralightInjectedList.this.mCollectionIndexToBindingId[andIncrement], UltralightInjectedList.this.mInjector);
                            synchronized (UltralightInjectedList.this.mItems) {
                                UltralightInjectedList.this.mItems[andIncrement] = t;
                                UltralightInjectedList.this.mItems.notifyAll();
                            }
                        } catch (IllegalArgumentException e2) {
                            throw new IllegalArgumentException("Invalid binding id %d" + UltralightInjectedList.this.mCollectionIndexToBindingId[andIncrement], e2);
                        } catch (Throwable th) {
                            synchronized (UltralightInjectedList.this.mItems) {
                                UltralightInjectedList.this.mItems[andIncrement] = null;
                                UltralightInjectedList.this.mItems.notifyAll();
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
