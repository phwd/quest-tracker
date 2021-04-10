package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Serialization;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

public final class ConcurrentHashMultiset<E> extends AbstractMultiset<E> implements Serializable {
    private static final long serialVersionUID = 1;
    private final transient ConcurrentMap<E, AtomicInteger> countMap;

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean add(@Nullable Object obj) {
        return super.add(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        return super.addAll(collection);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean contains(@Nullable Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.AbstractMultiset, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean remove(@Nullable Object obj) {
        return super.remove(obj);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    private static class FieldSettersHolder {
        static final Serialization.FieldSetter<ConcurrentHashMultiset> COUNT_MAP_FIELD_SETTER = Serialization.getFieldSetter(ConcurrentHashMultiset.class, "countMap");

        private FieldSettersHolder() {
        }
    }

    public static <E> ConcurrentHashMultiset<E> create() {
        return new ConcurrentHashMultiset<>(new ConcurrentHashMap());
    }

    public static <E> ConcurrentHashMultiset<E> create(Iterable<? extends E> elements) {
        ConcurrentHashMultiset<E> multiset = create();
        Iterables.addAll(multiset, elements);
        return multiset;
    }

    @Beta
    public static <E> ConcurrentHashMultiset<E> create(MapMaker mapMaker) {
        return new ConcurrentHashMultiset<>(mapMaker.makeMap());
    }

    @VisibleForTesting
    ConcurrentHashMultiset(ConcurrentMap<E, AtomicInteger> countMap2) {
        Preconditions.checkArgument(countMap2.isEmpty());
        this.countMap = countMap2;
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public int count(@Nullable Object element) {
        AtomicInteger existingCounter = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            return 0;
        }
        return existingCounter.get();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public int size() {
        long sum = 0;
        for (AtomicInteger value : this.countMap.values()) {
            sum += (long) value.get();
        }
        return Ints.saturatedCast(sum);
    }

    public Object[] toArray() {
        return snapshot().toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] array) {
        return (T[]) snapshot().toArray(array);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.ArrayList */
    /* JADX WARN: Multi-variable type inference failed */
    private List<E> snapshot() {
        ArrayList newArrayListWithExpectedSize = Lists.newArrayListWithExpectedSize(size());
        for (Multiset.Entry<E> entry : entrySet()) {
            E element = entry.getElement();
            for (int i = entry.getCount(); i > 0; i--) {
                newArrayListWithExpectedSize.add(element);
            }
        }
        return newArrayListWithExpectedSize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        r3 = new java.util.concurrent.atomic.AtomicInteger(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0077, code lost:
        if (r6.countMap.putIfAbsent(r7, r3) == null) goto L_0x0083;
     */
    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int add(E r7, int r8) {
        /*
        // Method dump skipped, instructions count: 132
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ConcurrentHashMultiset.add(java.lang.Object, int):int");
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public int remove(@Nullable Object element, int occurrences) {
        int oldValue;
        int newValue;
        if (occurrences == 0) {
            return count(element);
        }
        Preconditions.checkArgument(occurrences > 0, "Invalid occurrences: %s", Integer.valueOf(occurrences));
        AtomicInteger existingCounter = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            return 0;
        }
        do {
            oldValue = existingCounter.get();
            if (oldValue == 0) {
                return 0;
            }
            newValue = Math.max(0, oldValue - occurrences);
        } while (!existingCounter.compareAndSet(oldValue, newValue));
        if (newValue == 0) {
            this.countMap.remove(element, existingCounter);
        }
        return oldValue;
    }

    public boolean removeExactly(@Nullable Object element, int occurrences) {
        int oldValue;
        int newValue;
        if (occurrences == 0) {
            return true;
        }
        Preconditions.checkArgument(occurrences > 0, "Invalid occurrences: %s", Integer.valueOf(occurrences));
        AtomicInteger existingCounter = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (existingCounter == null) {
            return false;
        }
        do {
            oldValue = existingCounter.get();
            if (oldValue < occurrences) {
                return false;
            }
            newValue = oldValue - occurrences;
        } while (!existingCounter.compareAndSet(oldValue, newValue));
        if (newValue == 0) {
            this.countMap.remove(element, existingCounter);
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        if (r7 != 0) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        r3 = new java.util.concurrent.atomic.AtomicInteger(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
        if (r5.countMap.putIfAbsent(r6, r3) == null) goto L_0x0048;
     */
    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int setCount(E r6, int r7) {
        /*
            r5 = this;
            com.google.common.base.Preconditions.checkNotNull(r6)
            java.lang.String r0 = "count"
            com.google.common.collect.CollectPreconditions.checkNonnegative(r7, r0)
        L_0x0008:
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r0 = r5.countMap
            java.lang.Object r0 = com.google.common.collect.Maps.safeGet(r0, r6)
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            r1 = 0
            if (r0 != 0) goto L_0x0027
            if (r7 != 0) goto L_0x0016
            return r1
        L_0x0016:
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r2 = r5.countMap
            java.util.concurrent.atomic.AtomicInteger r3 = new java.util.concurrent.atomic.AtomicInteger
            r3.<init>(r7)
            java.lang.Object r2 = r2.putIfAbsent(r6, r3)
            r0 = r2
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            if (r0 != 0) goto L_0x0027
            return r1
        L_0x0027:
            int r2 = r0.get()
            if (r2 != 0) goto L_0x0049
            if (r7 != 0) goto L_0x0030
            return r1
        L_0x0030:
            java.util.concurrent.atomic.AtomicInteger r3 = new java.util.concurrent.atomic.AtomicInteger
            r3.<init>(r7)
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r4 = r5.countMap
            java.lang.Object r4 = r4.putIfAbsent(r6, r3)
            if (r4 == 0) goto L_0x0048
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r4 = r5.countMap
            boolean r4 = r4.replace(r6, r0, r3)
            if (r4 == 0) goto L_0x0046
            goto L_0x0048
        L_0x0046:
            goto L_0x0008
        L_0x0048:
            return r1
        L_0x0049:
            boolean r3 = r0.compareAndSet(r2, r7)
            if (r3 == 0) goto L_0x0057
            if (r7 != 0) goto L_0x0056
            java.util.concurrent.ConcurrentMap<E, java.util.concurrent.atomic.AtomicInteger> r1 = r5.countMap
            r1.remove(r6, r0)
        L_0x0056:
            return r2
        L_0x0057:
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ConcurrentHashMultiset.setCount(java.lang.Object, int):int");
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public boolean setCount(E element, int expectedOldCount, int newCount) {
        Preconditions.checkNotNull(element);
        CollectPreconditions.checkNonnegative(expectedOldCount, "oldCount");
        CollectPreconditions.checkNonnegative(newCount, "newCount");
        AtomicInteger existingCounter = (AtomicInteger) Maps.safeGet(this.countMap, element);
        if (existingCounter != null) {
            int oldValue = existingCounter.get();
            if (oldValue == expectedOldCount) {
                if (oldValue == 0) {
                    if (newCount == 0) {
                        this.countMap.remove(element, existingCounter);
                        return true;
                    }
                    AtomicInteger newCounter = new AtomicInteger(newCount);
                    return this.countMap.putIfAbsent(element, newCounter) == null || this.countMap.replace(element, existingCounter, newCounter);
                } else if (existingCounter.compareAndSet(oldValue, newCount)) {
                    if (newCount == 0) {
                        this.countMap.remove(element, existingCounter);
                    }
                    return true;
                }
            }
            return false;
        } else if (expectedOldCount != 0) {
            return false;
        } else {
            if (newCount == 0) {
                return true;
            }
            return this.countMap.putIfAbsent(element, new AtomicInteger(newCount)) == null;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public Set<E> createElementSet() {
        final Set<E> delegate = this.countMap.keySet();
        return new ForwardingSet<E>() {
            /* class com.google.common.collect.ConcurrentHashMultiset.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
            public Set<E> delegate() {
                return delegate;
            }

            @Override // com.google.common.collect.ForwardingCollection
            public boolean contains(@Nullable Object object) {
                return object != null && Collections2.safeContains(delegate, object);
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public boolean containsAll(Collection<?> collection) {
                return standardContainsAll(collection);
            }

            @Override // com.google.common.collect.ForwardingCollection
            public boolean remove(Object object) {
                return object != null && Collections2.safeRemove(delegate, object);
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public boolean removeAll(Collection<?> c) {
                return standardRemoveAll(c);
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public int distinctElements() {
        return this.countMap.size();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public boolean isEmpty() {
        return this.countMap.isEmpty();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        final Iterator<Multiset.Entry<E>> readOnlyIterator = new AbstractIterator<Multiset.Entry<E>>() {
            /* class com.google.common.collect.ConcurrentHashMultiset.AnonymousClass2 */
            private Iterator<Map.Entry<E, AtomicInteger>> mapEntries = ConcurrentHashMultiset.this.countMap.entrySet().iterator();

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.AbstractIterator
            public Multiset.Entry<E> computeNext() {
                while (this.mapEntries.hasNext()) {
                    Map.Entry<E, AtomicInteger> mapEntry = this.mapEntries.next();
                    int count = mapEntry.getValue().get();
                    if (count != 0) {
                        return Multisets.immutableEntry(mapEntry.getKey(), count);
                    }
                }
                return (Multiset.Entry) endOfData();
            }
        };
        return new ForwardingIterator<Multiset.Entry<E>>() {
            /* class com.google.common.collect.ConcurrentHashMultiset.AnonymousClass3 */
            private Multiset.Entry<E> last;

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
            public Iterator<Multiset.Entry<E>> delegate() {
                return readOnlyIterator;
            }

            @Override // com.google.common.collect.ForwardingIterator, java.util.Iterator
            public Multiset.Entry<E> next() {
                this.last = (Multiset.Entry) super.next();
                return this.last;
            }

            @Override // com.google.common.collect.ForwardingIterator
            public void remove() {
                CollectPreconditions.checkRemove(this.last != null);
                ConcurrentHashMultiset.this.setCount(this.last.getElement(), 0);
                this.last = null;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset
    public void clear() {
        this.countMap.clear();
    }

    private class EntrySet extends AbstractMultiset<E>.EntrySet {
        private EntrySet() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMultiset.EntrySet, com.google.common.collect.Multisets.EntrySet
        public ConcurrentHashMultiset<E> multiset() {
            return ConcurrentHashMultiset.this;
        }

        public Object[] toArray() {
            return snapshot().toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] array) {
            return (T[]) snapshot().toArray(array);
        }

        private List<Multiset.Entry<E>> snapshot() {
            List<Multiset.Entry<E>> list = Lists.newArrayListWithExpectedSize(size());
            Iterators.addAll(list, iterator());
            return list;
        }
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(this.countMap);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        FieldSettersHolder.COUNT_MAP_FIELD_SETTER.set(this, (ConcurrentMap) stream.readObject());
    }
}
