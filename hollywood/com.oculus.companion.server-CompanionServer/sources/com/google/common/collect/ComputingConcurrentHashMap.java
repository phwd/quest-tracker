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

/* access modifiers changed from: package-private */
public class ComputingConcurrentHashMap<K, V> extends MapMakerInternalMap<K, V> {
    private static final long serialVersionUID = 4;
    final Function<? super K, ? extends V> computingFunction;

    ComputingConcurrentHashMap(MapMaker mapMaker, Function<? super K, ? extends V> function) {
        super(mapMaker);
        Preconditions.checkNotNull(function);
        this.computingFunction = function;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.MapMakerInternalMap
    public MapMakerInternalMap.Segment<K, V> createSegment(int i, int i2) {
        return new ComputingSegment(this, i, i2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.MapMakerInternalMap
    public ComputingSegment<K, V> segmentFor(int i) {
        return (ComputingSegment) super.segmentFor(i);
    }

    /* access modifiers changed from: package-private */
    public V getOrCompute(K k) throws ExecutionException {
        Preconditions.checkNotNull(k);
        int hash = hash(k);
        return segmentFor(hash).getOrCompute(k, hash, this.computingFunction);
    }

    /* access modifiers changed from: package-private */
    public static final class ComputingSegment<K, V> extends MapMakerInternalMap.Segment<K, V> {
        ComputingSegment(MapMakerInternalMap<K, V> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x00c6 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V getOrCompute(K r12, int r13, com.google.common.base.Function<? super K, ? extends V> r14) throws java.util.concurrent.ExecutionException {
            /*
            // Method dump skipped, instructions count: 248
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.getOrCompute(java.lang.Object, int, com.google.common.base.Function):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
            if (put(r7, r8, r2, true) == null) goto L_0x001e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
            enqueueNotification(r7, r8, r2, com.google.common.collect.MapMaker.RemovalCause.REPLACED);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
            if (r3 != 0) goto L_0x0025;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
            java.lang.System.nanoTime();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
            if (r2 != null) goto L_0x002a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
            clearValue(r7, r8, r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
            return r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x002f, code lost:
            r9 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
            if (r2 == null) goto L_0x001e;
         */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0039  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x003e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V compute(K r7, int r8, com.google.common.collect.MapMakerInternalMap.ReferenceEntry<K, V> r9, com.google.common.collect.ComputingConcurrentHashMap.ComputingValueReference<K, V> r10) throws java.util.concurrent.ExecutionException {
            /*
                r6 = this;
                java.lang.System.nanoTime()
                r0 = 0
                r2 = 0
                monitor-enter(r9)     // Catch:{ all -> 0x0033 }
                java.lang.Object r2 = r10.compute(r7, r8)     // Catch:{ all -> 0x002b }
                long r3 = java.lang.System.nanoTime()     // Catch:{ all -> 0x002b }
                monitor-exit(r9)     // Catch:{ all -> 0x0031 }
                if (r2 == 0) goto L_0x001e
                r9 = 1
                java.lang.Object r9 = r6.put(r7, r8, r2, r9)     // Catch:{ all -> 0x002f }
                if (r9 == 0) goto L_0x001e
                com.google.common.collect.MapMaker$RemovalCause r9 = com.google.common.collect.MapMaker.RemovalCause.REPLACED     // Catch:{ all -> 0x002f }
                r6.enqueueNotification(r7, r8, r2, r9)     // Catch:{ all -> 0x002f }
            L_0x001e:
                int r9 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r9 != 0) goto L_0x0025
                java.lang.System.nanoTime()
            L_0x0025:
                if (r2 != 0) goto L_0x002a
                r6.clearValue(r7, r8, r10)
            L_0x002a:
                return r2
            L_0x002b:
                r5 = move-exception
                r3 = r0
            L_0x002d:
                monitor-exit(r9)
                throw r5
            L_0x002f:
                r9 = move-exception
                goto L_0x0035
            L_0x0031:
                r5 = move-exception
                goto L_0x002d
            L_0x0033:
                r9 = move-exception
                r3 = r0
            L_0x0035:
                int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r0 != 0) goto L_0x003c
                java.lang.System.nanoTime()
            L_0x003c:
                if (r2 != 0) goto L_0x0041
                r6.clearValue(r7, r8, r10)
            L_0x0041:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment.compute(java.lang.Object, int, com.google.common.collect.MapMakerInternalMap$ReferenceEntry, com.google.common.collect.ComputingConcurrentHashMap$ComputingValueReference):java.lang.Object");
        }
    }

    /* access modifiers changed from: private */
    public static final class ComputationExceptionReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {
        final Throwable t;

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
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
        public boolean isComputingReference() {
            return false;
        }

        ComputationExceptionReference(Throwable th) {
            this.t = th;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V waitForValue() throws ExecutionException {
            throw new ExecutionException(this.t);
        }
    }

    /* access modifiers changed from: private */
    public static final class ComputedReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {
        final V value;

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ReferenceEntry<K, V> getEntry() {
            return null;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public boolean isComputingReference() {
            return false;
        }

        ComputedReference(V v) {
            this.value = v;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V get() {
            return this.value;
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public V waitForValue() {
            return get();
        }
    }

    /* access modifiers changed from: private */
    public static final class ComputingValueReference<K, V> implements MapMakerInternalMap.ValueReference<K, V> {
        volatile MapMakerInternalMap.ValueReference<K, V> computedReference = MapMakerInternalMap.unset();
        final Function<? super K, ? extends V> computingFunction;

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, MapMakerInternalMap.ReferenceEntry<K, V> referenceEntry) {
            return this;
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
        public boolean isComputingReference() {
            return true;
        }

        public ComputingValueReference(Function<? super K, ? extends V> function) {
            this.computingFunction = function;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0023, code lost:
            r3 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0024, code lost:
            r0 = r1;
         */
        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public V waitForValue() throws java.util.concurrent.ExecutionException {
            /*
                r3 = this;
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r0 = r3.computedReference
                com.google.common.collect.MapMakerInternalMap$ValueReference<java.lang.Object, java.lang.Object> r1 = com.google.common.collect.MapMakerInternalMap.UNSET
                if (r0 != r1) goto L_0x0031
                r0 = 0
                monitor-enter(r3)     // Catch:{ all -> 0x0026 }
                r1 = r0
            L_0x0009:
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r0 = r3.computedReference     // Catch:{ all -> 0x0020 }
                com.google.common.collect.MapMakerInternalMap$ValueReference<java.lang.Object, java.lang.Object> r2 = com.google.common.collect.MapMakerInternalMap.UNSET     // Catch:{ all -> 0x0020 }
                if (r0 != r2) goto L_0x0015
                r3.wait()     // Catch:{ InterruptedException -> 0x0013 }
                goto L_0x0009
            L_0x0013:
                r1 = 1
                goto L_0x0009
            L_0x0015:
                monitor-exit(r3)
                if (r1 == 0) goto L_0x0031
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
                goto L_0x0031
            L_0x0020:
                r0 = move-exception
                monitor-exit(r3)
                throw r0     // Catch:{ all -> 0x0023 }
            L_0x0023:
                r3 = move-exception
                r0 = r1
                goto L_0x0027
            L_0x0026:
                r3 = move-exception
            L_0x0027:
                if (r0 == 0) goto L_0x0030
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x0030:
                throw r3
            L_0x0031:
                com.google.common.collect.MapMakerInternalMap$ValueReference<K, V> r3 = r3.computedReference
                java.lang.Object r3 = r3.waitForValue()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ComputingConcurrentHashMap.ComputingValueReference.waitForValue():java.lang.Object");
        }

        @Override // com.google.common.collect.MapMakerInternalMap.ValueReference
        public void clear(MapMakerInternalMap.ValueReference<K, V> valueReference) {
            setValueReference(valueReference);
        }

        /* access modifiers changed from: package-private */
        public V compute(K k, int i) throws ExecutionException {
            try {
                V v = (V) this.computingFunction.apply(k);
                setValueReference(new ComputedReference(v));
                return v;
            } catch (Throwable th) {
                setValueReference(new ComputationExceptionReference(th));
                throw new ExecutionException(th);
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

        ComputingSerializationProxy(MapMakerInternalMap.Strength strength, MapMakerInternalMap.Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, long j, long j2, int i, int i2, MapMaker.RemovalListener<? super K, ? super V> removalListener, ConcurrentMap<K, V> concurrentMap, Function<? super K, ? extends V> function) {
            super(strength, strength2, equivalence, equivalence2, j, j2, i, i2, removalListener, concurrentMap);
            this.computingFunction = function;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            writeMapTo(objectOutputStream);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.delegate = readMapMaker(objectInputStream).makeComputingMap(this.computingFunction);
            readEntries(objectInputStream);
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.delegate;
        }
    }
}
