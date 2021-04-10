package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible("not needed in emulated source.")
    private static final long serialVersionUID = -2250766705698539974L;
    private transient Map<E, Count> backingMap;
    private transient long size = ((long) super.size());

    static /* synthetic */ long access$110(AbstractMapBasedMultiset x0) {
        long j = x0.size;
        x0.size = j - 1;
        return j;
    }

    static /* synthetic */ long access$122(AbstractMapBasedMultiset x0, long x1) {
        long j = x0.size - x1;
        x0.size = j;
        return j;
    }

    protected AbstractMapBasedMultiset(Map<E, Count> backingMap2) {
        this.backingMap = (Map) Preconditions.checkNotNull(backingMap2);
    }

    /* access modifiers changed from: package-private */
    public void setBackingMap(Map<E, Count> backingMap2) {
        this.backingMap = backingMap2;
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public Set<Multiset.Entry<E>> entrySet() {
        return super.entrySet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        final Iterator<Map.Entry<E, Count>> backingEntries = this.backingMap.entrySet().iterator();
        return new Iterator<Multiset.Entry<E>>() {
            /* class com.google.common.collect.AbstractMapBasedMultiset.AnonymousClass1 */
            Map.Entry<E, Count> toRemove;

            public boolean hasNext() {
                return backingEntries.hasNext();
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                final Map.Entry<E, Count> mapEntry = (Map.Entry) backingEntries.next();
                this.toRemove = mapEntry;
                return new Multisets.AbstractEntry<E>() {
                    /* class com.google.common.collect.AbstractMapBasedMultiset.AnonymousClass1.AnonymousClass1 */

                    @Override // com.google.common.collect.Multiset.Entry
                    public E getElement() {
                        return (E) mapEntry.getKey();
                    }

                    @Override // com.google.common.collect.Multiset.Entry
                    public int getCount() {
                        Count frequency;
                        Count count = (Count) mapEntry.getValue();
                        if ((count == null || count.get() == 0) && (frequency = (Count) AbstractMapBasedMultiset.this.backingMap.get(getElement())) != null) {
                            return frequency.get();
                        }
                        if (count == null) {
                            return 0;
                        }
                        return count.get();
                    }
                };
            }

            public void remove() {
                CollectPreconditions.checkRemove(this.toRemove != null);
                AbstractMapBasedMultiset.access$122(AbstractMapBasedMultiset.this, (long) this.toRemove.getValue().getAndSet(0));
                backingEntries.remove();
                this.toRemove = null;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset
    public void clear() {
        for (Count frequency : this.backingMap.values()) {
            frequency.set(0);
        }
        this.backingMap.clear();
        this.size = 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public int distinctElements() {
        return this.backingMap.size();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public int size() {
        return Ints.saturatedCast(this.size);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.AbstractMultiset, java.lang.Iterable
    public Iterator<E> iterator() {
        return new MapBasedMultisetIterator();
    }

    private class MapBasedMultisetIterator implements Iterator<E> {
        boolean canRemove;
        Map.Entry<E, Count> currentEntry;
        final Iterator<Map.Entry<E, Count>> entryIterator;
        int occurrencesLeft;

        MapBasedMultisetIterator() {
            this.entryIterator = AbstractMapBasedMultiset.this.backingMap.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.occurrencesLeft > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.occurrencesLeft == 0) {
                this.currentEntry = this.entryIterator.next();
                this.occurrencesLeft = this.currentEntry.getValue().get();
            }
            this.occurrencesLeft--;
            this.canRemove = true;
            return this.currentEntry.getKey();
        }

        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            if (this.currentEntry.getValue().get() > 0) {
                if (this.currentEntry.getValue().addAndGet(-1) == 0) {
                    this.entryIterator.remove();
                }
                AbstractMapBasedMultiset.access$110(AbstractMapBasedMultiset.this);
                this.canRemove = false;
                return;
            }
            throw new ConcurrentModificationException();
        }
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public int count(@Nullable Object element) {
        Count frequency = (Count) Maps.safeGet(this.backingMap, element);
        if (frequency == null) {
            return 0;
        }
        return frequency.get();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public int add(@Nullable E element, int occurrences) {
        int oldCount;
        if (occurrences == 0) {
            return count(element);
        }
        Preconditions.checkArgument(occurrences > 0, "occurrences cannot be negative: %s", Integer.valueOf(occurrences));
        Count frequency = this.backingMap.get(element);
        if (frequency == null) {
            oldCount = 0;
            this.backingMap.put(element, new Count(occurrences));
        } else {
            int oldCount2 = frequency.get();
            long newCount = ((long) oldCount2) + ((long) occurrences);
            Preconditions.checkArgument(newCount <= 2147483647L, "too many occurrences: %s", Long.valueOf(newCount));
            frequency.getAndAdd(occurrences);
            oldCount = oldCount2;
        }
        this.size += (long) occurrences;
        return oldCount;
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public int remove(@Nullable Object element, int occurrences) {
        int numberRemoved;
        if (occurrences == 0) {
            return count(element);
        }
        Preconditions.checkArgument(occurrences > 0, "occurrences cannot be negative: %s", Integer.valueOf(occurrences));
        Count frequency = this.backingMap.get(element);
        if (frequency == null) {
            return 0;
        }
        int oldCount = frequency.get();
        if (oldCount > occurrences) {
            numberRemoved = occurrences;
        } else {
            numberRemoved = oldCount;
            this.backingMap.remove(element);
        }
        frequency.addAndGet(-numberRemoved);
        this.size -= (long) numberRemoved;
        return oldCount;
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public int setCount(@Nullable E element, int count) {
        int oldCount;
        CollectPreconditions.checkNonnegative(count, "count");
        if (count == 0) {
            oldCount = getAndSet(this.backingMap.remove(element), count);
        } else {
            Count existingCounter = this.backingMap.get(element);
            oldCount = getAndSet(existingCounter, count);
            if (existingCounter == null) {
                this.backingMap.put(element, new Count(count));
            }
        }
        this.size += (long) (count - oldCount);
        return oldCount;
    }

    private static int getAndSet(Count i, int count) {
        if (i == null) {
            return 0;
        }
        return i.getAndSet(count);
    }

    @GwtIncompatible("java.io.ObjectStreamException")
    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("Stream data required");
    }
}
