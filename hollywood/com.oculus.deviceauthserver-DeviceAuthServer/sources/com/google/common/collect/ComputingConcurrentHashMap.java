package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* access modifiers changed from: package-private */
public class ComputingConcurrentHashMap<K, V> extends MapMakerInternalMap<K, V> {
    private static final long serialVersionUID = 4;
    final Function<? super K, ? extends V> computingFunction;

    ComputingConcurrentHashMap(MapMaker builder, Function<? super K, ? extends V> computingFunction2) {
        super(builder);
        this.computingFunction = (Function) Preconditions.checkNotNull(computingFunction2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.MapMakerInternalMap
    public MapMakerInternalMap.Segment<K, V> createSegment(int initialCapacity, int maxSegmentSize) {
        return new ComputingSegment(this, initialCapacity, maxSegmentSize);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.MapMakerInternalMap
    public ComputingSegment<K, V> segmentFor(int hash) {
        return (ComputingSegment) super.segmentFor(hash);
    }

    /* access modifiers changed from: package-private */
    public V getOrCompute(K key) throws ExecutionException {
        int hash = hash(Preconditions.checkNotNull(key));
        return segmentFor(hash).getOrCompute(key, hash, this.computingFunction);
    }

    /* access modifiers changed from: package-private */
    public static final class ComputingSegment<K, V> extends MapMakerInternalMap.Segment<K, V> {
        ComputingSegment(MapMakerInternalMap<K, V> map, int initialCapacity, int maxSegmentSize) {
            super(map, initialCapacity, maxSegmentSize);
        }

        /* access modifiers changed from: package-private */
        public V getOrCompute(K key, int hash, Function<? super K, ? extends V> computingFunction) throws ExecutionException {
            MapMakerInternalMap.ReferenceEntry<K, V> e;
            V value;
            V value2;
            do {
                try {
                    e = getEntry(key, hash);
                    if (e == null || (value2 = getLiveValue(e)) == null) {
                        boolean z = true;
                        if (e == null || !e.getValueReference().isComputingReference()) {
                            boolean createNewEntry = true;
                            ComputingValueReference<K, V> computingValueReference = null;
                            lock();
                            try {
                                preWriteCleanup();
                                int newCount = this.count - 1;
                                AtomicReferenceArray<MapMakerInternalMap.ReferenceEntry<K, V>> table = this.table;
                                int index = (table.length() - 1) & hash;
                                MapMakerInternalMap.ReferenceEntry<K, V> first = table.get(index);
                                e = first;
                                while (true) {
                                    if (e == null) {
                                        break;
                                    }
                                    K entryKey = e.getKey();
                                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                                        e = e.getNext();
                                    } else if (e.getValueReference().isComputingReference()) {
                                        createNewEntry = false;
                                    } else {
                                        V value3 = e.getValueReference().get();
                                        if (value3 == null) {
                                            enqueueNotification(entryKey, hash, value3, MapMaker.RemovalCause.COLLECTED);
                                        } else if (!this.map.expires() || !this.map.isExpired(e)) {
                                            recordLockedRead(e);
                                            unlock();
                                            postWriteCleanup();
                                            postReadCleanup();
                                            return value3;
                                        } else {
                                            enqueueNotification(entryKey, hash, value3, MapMaker.RemovalCause.EXPIRED);
                                        }
                                        this.evictionQueue.remove(e);
                                        this.expirationQueue.remove(e);
                                        this.count = newCount;
                                    }
                                }
                                if (createNewEntry) {
                                    computingValueReference = new ComputingValueReference<>(computingFunction);
                                    if (e == null) {
                                        e = newEntry(key, hash, first);
                                        e.setValueReference(computingValueReference);
                                        table.set(index, e);
                                    } else {
                                        e.setValueReference(computingValueReference);
                                    }
                                }
                                if (createNewEntry) {
                                    V compute = compute(key, hash, e, computingValueReference);
                                    postReadCleanup();
                                    return compute;
                                }
                            } finally {
                                unlock();
                                postWriteCleanup();
                            }
                        }
                        if (Thread.holdsLock(e)) {
                            z = false;
                        }
                        Preconditions.checkState(z, "Recursive computation");
                        value = e.getValueReference().waitForValue();
                    } else {
                        recordRead(e);
                        postReadCleanup();
                        return value2;
                    }
                } finally {
                    postReadCleanup();
                }
            } while (value == null);
            recordRead(e);
            return value;
        }

        /* access modifiers changed from: package-private */
        public V compute(K key, int hash, MapMakerInternalMap.ReferenceEntry<K, V> e, ComputingValueReference<K, V> computingValueReference) throws ExecutionException {
            V value = null;
            System.nanoTime();
            long end = 0;
            try {
                synchronized (e) {
                    value = computingValueReference.compute(key, hash);
                    end = System.nanoTime();
                }
                if (!(value == null || put(key, hash, value, true) == null)) {
                    enqueueNotification(key, hash, value, MapMaker.RemovalCause.REPLACED);
                }
                return value;
            } finally {
                if (end == 0) {
                    System.nanoTime();
                }
                if (value == null) {
                    clearValue(key, hash, computingValueReference);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class ComputationExceptionReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {
        final Throwable t;

        ComputationExceptionReference(Throwable t2) {
            this.t = t2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V get() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public boolean isComputingReference() {
            return false;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V waitForValue() throws ExecutionException {
            throw new ExecutionException(this.t);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
        }
    }

    /* access modifiers changed from: private */
    public static final class ComputedReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {
        final V value;

        ComputedReference(@Nullable V value2) {
            this.value = value2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V get() {
            return this.value;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public boolean isComputingReference() {
            return false;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V waitForValue() {
            return get();
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
        }
    }

    /* access modifiers changed from: private */
    public static final class ComputingValueReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {
        @GuardedBy("ComputingValueReference.this")
        volatile MapMakerInternalMap.ValueReference<K, V> computedReference = MapMakerInternalMap.unset();
        final Function<? super K, ? extends V> computingFunction;

        public ComputingValueReference(Function<? super K, ? extends V> computingFunction2) {
            this.computingFunction = computingFunction2;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V get() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @Nullable V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public boolean isComputingReference() {
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0024, code lost:
            r0 = th;
         */
        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V waitForValue() throws java.util.concurrent.ExecutionException {
            /*
                r4 = this;
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r0 = r4.computedReference
                com.google.common.collect.MapMakerInternalMap$ValueReference<java.lang.Object, java.lang.Object> r1 = com.google.common.collect.MapMakerInternalMap.UNSET
                if (r0 != r1) goto L_0x0034
                r0 = 0
                monitor-enter(r4)     // Catch:{ all -> 0x0026 }
                r1 = r0
            L_0x0009:
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r0 = r4.computedReference     // Catch:{ all -> 0x0021 }
                com.google.common.collect.MapMakerInternalMap$ValueReference<java.lang.Object, java.lang.Object> r2 = com.google.common.collect.MapMakerInternalMap.UNSET     // Catch:{ all -> 0x0021 }
                if (r0 != r2) goto L_0x0016
                r4.wait()     // Catch:{ InterruptedException -> 0x0013 }
            L_0x0012:
                goto L_0x0009
            L_0x0013:
                r0 = move-exception
                r1 = 1
                goto L_0x0012
            L_0x0016:
                monitor-exit(r4)
                if (r1 == 0) goto L_0x0034
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
                goto L_0x0034
            L_0x0021:
                r0 = move-exception
                monitor-exit(r4)
                throw r0     // Catch:{ all -> 0x0024 }
            L_0x0024:
                r0 = move-exception
                goto L_0x002a
            L_0x0026:
                r1 = move-exception
                r3 = r1
                r1 = r0
                r0 = r3
            L_0x002a:
                if (r1 == 0) goto L_0x0033
                java.lang.Thread r2 = java.lang.Thread.currentThread()
                r2.interrupt()
            L_0x0033:
                throw r0
            L_0x0034:
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r0 = r4.computedReference
                java.lang.Object r0 = r0.waitForValue()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingValueReference.waitForValue():java.lang.Object");
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(MapMakerInternalMap.ValueReference<K, V> newValue) {
            setValueReference(newValue);
        }

        /* access modifiers changed from: package-private */
        public V compute(K key, int hash) throws ExecutionException {
            try {
                V value = (V) this.computingFunction.apply(key);
                setValueReference(new ComputedReference(value));
                return value;
            } catch (Throwable t) {
                setValueReference(new ComputationExceptionReference(t));
                throw new ExecutionException(t);
            }
        }

        /* access modifiers changed from: package-private */
        public void setValueReference(MapMakerInternalMap.ValueReference<K, V> valueReference) {
            synchronized (this) {
                if (this.computedReference == MapMakerInternalMap.UNSET) {
                    this.computedReference = valueReference;
                    notifyAll();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.MapMakerInternalMap
    public Object writeReplace() {
        return new ComputingSerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this, this.computingFunction);
    }

    static final class ComputingSerializationProxy<K, V> extends MapMakerInternalMap.AbstractSerializationProxy<K, V> {
        private static final long serialVersionUID = 4;
        final Function<? super K, ? extends V> computingFunction;

        ComputingSerializationProxy(MapMakerInternalMap.Strength keyStrength, MapMakerInternalMap.Strength valueStrength, Equivalence<Object> keyEquivalence, Equivalence<Object> valueEquivalence, long expireAfterWriteNanos, long expireAfterAccessNanos, int maximumSize, int concurrencyLevel, MapMaker.RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> delegate, Function<? super K, ? extends V> computingFunction2) {
            super(keyStrength, valueStrength, keyEquivalence, valueEquivalence, expireAfterWriteNanos, expireAfterAccessNanos, maximumSize, concurrencyLevel, removalListener, delegate);
            this.computingFunction = computingFunction2;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            writeMapTo(out);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            this.delegate = readMapMaker(in).makeComputingMap(this.computingFunction);
            readEntries(in);
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.delegate;
        }
    }
}
