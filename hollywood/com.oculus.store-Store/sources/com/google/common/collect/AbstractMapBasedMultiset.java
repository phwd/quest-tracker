package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    transient ObjectCountHashMap<E> backingMap;
    transient long size;

    /* access modifiers changed from: package-private */
    public abstract void init(int i);

    AbstractMapBasedMultiset(int distinctElements) {
        init(distinctElements);
    }

    @Override // com.google.common.collect.Multiset
    public final int count(@NullableDecl Object element) {
        return this.backingMap.get(element);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public final int add(@NullableDecl E element, int occurrences) {
        boolean z;
        boolean z2 = true;
        if (occurrences == 0) {
            return count(element);
        }
        if (occurrences > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "occurrences cannot be negative: %s", occurrences);
        int entryIndex = this.backingMap.indexOf(element);
        if (entryIndex == -1) {
            this.backingMap.put(element, occurrences);
            this.size += (long) occurrences;
            return 0;
        }
        int oldCount = this.backingMap.getValue(entryIndex);
        long newCount = ((long) oldCount) + ((long) occurrences);
        if (newCount > 2147483647L) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "too many occurrences: %s", newCount);
        this.backingMap.setValue(entryIndex, (int) newCount);
        this.size += (long) occurrences;
        return oldCount;
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public final int remove(@NullableDecl Object element, int occurrences) {
        boolean z;
        int numberRemoved;
        if (occurrences == 0) {
            return count(element);
        }
        if (occurrences > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "occurrences cannot be negative: %s", occurrences);
        int entryIndex = this.backingMap.indexOf(element);
        if (entryIndex == -1) {
            return 0;
        }
        int oldCount = this.backingMap.getValue(entryIndex);
        if (oldCount > occurrences) {
            numberRemoved = occurrences;
            this.backingMap.setValue(entryIndex, oldCount - occurrences);
        } else {
            numberRemoved = oldCount;
            this.backingMap.removeEntry(entryIndex);
        }
        this.size -= (long) numberRemoved;
        return oldCount;
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public final int setCount(@NullableDecl E element, int count) {
        CollectPreconditions.checkNonnegative(count, "count");
        int oldCount = count == 0 ? this.backingMap.remove(element) : this.backingMap.put(element, count);
        this.size += (long) (count - oldCount);
        return oldCount;
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public final boolean setCount(@NullableDecl E element, int oldCount, int newCount) {
        CollectPreconditions.checkNonnegative(oldCount, "oldCount");
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        int entryIndex = this.backingMap.indexOf(element);
        if (entryIndex == -1) {
            if (oldCount != 0) {
                return false;
            }
            if (newCount > 0) {
                this.backingMap.put(element, newCount);
                this.size += (long) newCount;
            }
            return true;
        } else if (this.backingMap.getValue(entryIndex) != oldCount) {
            return false;
        } else {
            if (newCount == 0) {
                this.backingMap.removeEntry(entryIndex);
                this.size -= (long) oldCount;
            } else {
                this.backingMap.setValue(entryIndex, newCount);
                this.size += (long) (newCount - oldCount);
            }
            return true;
        }
    }

    @Override // com.google.common.collect.AbstractMultiset
    public final void clear() {
        this.backingMap.clear();
        this.size = 0;
    }

    abstract class Itr<T> implements Iterator<T> {
        int entryIndex = AbstractMapBasedMultiset.this.backingMap.firstIndex();
        int expectedModCount = AbstractMapBasedMultiset.this.backingMap.modCount;
        int toRemove = -1;

        /* access modifiers changed from: package-private */
        public abstract T result(int i);

        Itr() {
        }

        private void checkForConcurrentModification() {
            if (AbstractMapBasedMultiset.this.backingMap.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasNext() {
            checkForConcurrentModification();
            return this.entryIndex >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = result(this.entryIndex);
            this.toRemove = this.entryIndex;
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.nextIndex(this.entryIndex);
            return result;
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.toRemove != -1);
            AbstractMapBasedMultiset.this.size -= (long) AbstractMapBasedMultiset.this.backingMap.removeEntry(this.toRemove);
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.nextIndexAfterRemove(this.entryIndex, this.toRemove);
            this.toRemove = -1;
            this.expectedModCount = AbstractMapBasedMultiset.this.backingMap.modCount;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public final Iterator<E> elementIterator() {
        return new AbstractMapBasedMultiset<E>.Itr() {
            /* class com.google.common.collect.AbstractMapBasedMultiset.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMapBasedMultiset.Itr
            public E result(int entryIndex) {
                return AbstractMapBasedMultiset.this.backingMap.getKey(entryIndex);
            }
        };
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public final Iterator<Multiset.Entry<E>> entryIterator() {
        return new AbstractMapBasedMultiset<E>.Itr() {
            /* class com.google.common.collect.AbstractMapBasedMultiset.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.AbstractMapBasedMultiset.Itr
            public Multiset.Entry<E> result(int entryIndex) {
                return AbstractMapBasedMultiset.this.backingMap.getEntry(entryIndex);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void addTo(Multiset<? super E> target) {
        Preconditions.checkNotNull(target);
        int i = this.backingMap.firstIndex();
        while (i >= 0) {
            target.add(this.backingMap.getKey(i), this.backingMap.getValue(i));
            i = this.backingMap.nextIndex(i);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public final int distinctElements() {
        return this.backingMap.size();
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.Multiset
    public final int size() {
        return Ints.saturatedCast(this.size);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        Serialization.writeMultiset(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int distinctElements = Serialization.readCount(stream);
        init(3);
        Serialization.populateMultiset(this, stream, distinctElements);
    }
}
