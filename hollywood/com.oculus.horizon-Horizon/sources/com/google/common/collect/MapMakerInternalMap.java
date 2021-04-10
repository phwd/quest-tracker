package com.google.common.collect;

import X.AbstractC07000ql;
import X.AbstractC07010qm;
import X.AbstractC07040qr;
import X.AnonymousClass0C4;
import X.AnonymousClass0C5;
import X.AnonymousClass0C6;
import X.AnonymousClass0C7;
import X.AnonymousClass0CI;
import X.AnonymousClass0dW;
import X.AnonymousClass0dr;
import X.C03590dj;
import X.C03610dm;
import X.C06980qi;
import X.C07030qq;
import X.C07820vP;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.MapMakerInternalMap.Segment;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
public class MapMakerInternalMap<K, V, E extends AbstractC07000ql<K, V, E>, S extends Segment<K, V, E, S>> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    public static final AbstractC07040qr<Object, Object, DummyInternalEntry> A07 = new AnonymousClass0dr();
    public static final long serialVersionUID = 5;
    @MonotonicNonNullDecl
    public transient Collection<V> A00;
    @MonotonicNonNullDecl
    public transient Set<Map.Entry<K, V>> A01;
    @MonotonicNonNullDecl
    public transient Set<K> A02;
    public final transient int A03;
    public final transient int A04;
    public final transient Segment<K, V, E, S>[] A05;
    public final transient AbstractC07010qm<K, V, E, S> A06;
    public final int concurrencyLevel;
    public final Equivalence<Object> keyEquivalence;

    public static abstract class Segment<K, V, E extends AbstractC07000ql<K, V, E>, S extends Segment<K, V, E, S>> extends ReentrantLock {
        public volatile int count;
        @Weak
        public final MapMakerInternalMap<K, V, E, S> map;
        public final int maxSegmentSize;
        public int modCount;
        public final AtomicInteger readCount = new AtomicInteger();
        @MonotonicNonNullDecl
        public volatile AtomicReferenceArray<E> table;
        public int threshold;

        @GuardedBy("this")
        public void A06() {
        }

        public void A07() {
        }

        /* JADX INFO: finally extract failed */
        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends X.0ql<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        @GuardedBy("this")
        public final void A09(ReferenceQueue<K> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends K> poll = referenceQueue.poll();
                if (poll != null) {
                    AbstractC07000ql r7 = (AbstractC07000ql) poll;
                    MapMakerInternalMap<K, V, E, S> mapMakerInternalMap = this.map;
                    int A3V = r7.A3V();
                    Segment A01 = MapMakerInternalMap.A01(mapMakerInternalMap, A3V);
                    A01.lock();
                    try {
                        AtomicReferenceArray<E> atomicReferenceArray = A01.table;
                        int length = A3V & (atomicReferenceArray.length() - 1);
                        AbstractC07000ql r2 = (AbstractC07000ql) atomicReferenceArray.get(length);
                        AbstractC07000ql r1 = r2;
                        while (true) {
                            if (r1 == null) {
                                break;
                            } else if (r1 == r7) {
                                A01.modCount++;
                                atomicReferenceArray.set(length, A00(A01, r2, r1));
                                A01.count--;
                                break;
                            } else {
                                r1 = r1.A40();
                            }
                        }
                        A01.unlock();
                        i++;
                    } catch (Throwable th) {
                        A01.unlock();
                        throw th;
                    }
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* JADX INFO: finally extract failed */
        /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends X.0ql<K, V, E>> */
        /* JADX WARN: Multi-variable type inference failed */
        @GuardedBy("this")
        public final void A0A(ReferenceQueue<V> referenceQueue) {
            int i = 0;
            do {
                Reference<? extends V> poll = referenceQueue.poll();
                if (poll != null) {
                    AbstractC07040qr<K, V, E> r9 = (AbstractC07040qr) poll;
                    MapMakerInternalMap<K, V, E, S> mapMakerInternalMap = this.map;
                    E A3O = r9.A3O();
                    int A3V = A3O.A3V();
                    Segment A01 = MapMakerInternalMap.A01(mapMakerInternalMap, A3V);
                    Object key = A3O.getKey();
                    A01.lock();
                    try {
                        AtomicReferenceArray<E> atomicReferenceArray = A01.table;
                        int length = (atomicReferenceArray.length() - 1) & A3V;
                        AbstractC07000ql r7 = (AbstractC07000ql) atomicReferenceArray.get(length);
                        AbstractC07000ql r2 = r7;
                        while (true) {
                            if (r2 == null) {
                                break;
                            }
                            Object key2 = r2.getKey();
                            if (r2.A3V() != A3V || key2 == null || !A01.map.keyEquivalence.equivalent(key, key2)) {
                                r2 = r2.A40();
                            } else if (((AnonymousClass0dW) r2).A4f() == r9) {
                                A01.modCount++;
                                atomicReferenceArray.set(length, A00(A01, r7, r2));
                                A01.count--;
                            }
                        }
                        A01.unlock();
                        i++;
                    } catch (Throwable th) {
                        A01.unlock();
                        throw th;
                    }
                } else {
                    return;
                }
            } while (i != 16);
        }

        /* JADX WARN: Incorrect args count in method signature: (TE;TE;)TE; */
        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: X.0qm<K, V, E extends X.0ql<K, V, E>, S extends com.google.common.collect.MapMakerInternalMap$Segment<K, V, E, S>> */
        /* JADX WARN: Multi-variable type inference failed */
        @GuardedBy("this")
        public static final AbstractC07000ql A00(Segment segment, AbstractC07000ql r4, AbstractC07000ql r5) {
            int i = segment.count;
            AbstractC07000ql A40 = r5.A40();
            while (r4 != r5) {
                AbstractC07000ql A1t = segment.map.A06.A1t(segment, r4, A40);
                if (A1t != null) {
                    A40 = A1t;
                } else {
                    i--;
                }
                r4 = r4.A40();
            }
            segment.count = i;
            return A40;
        }

        /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;I)TE; */
        public static final AbstractC07000ql A01(Segment segment, Object obj, int i) {
            if (segment.count == 0) {
                return null;
            }
            AtomicReferenceArray<E> atomicReferenceArray = segment.table;
            for (E e = atomicReferenceArray.get((atomicReferenceArray.length() - 1) & i); e != null; e = e.A40()) {
                if (e.A3V() == i) {
                    Object key = e.getKey();
                    if (key == null) {
                        A03(segment);
                    } else if (segment.map.keyEquivalence.equivalent(obj, key)) {
                        return e;
                    }
                }
            }
            return null;
        }

        /* JADX WARN: Incorrect args count in method signature: (TE;TV;)V */
        public static final void A04(Segment segment, AbstractC07000ql r2, Object obj) {
            segment.map.A06.A8s(segment, r2, obj);
        }

        public final void A08() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                A02(this);
            }
        }

        public Segment(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i, int i2) {
            this.map = mapMakerInternalMap;
            this.maxSegmentSize = i2;
            AtomicReferenceArray<E> atomicReferenceArray = new AtomicReferenceArray<>(i);
            int length = (atomicReferenceArray.length() * 3) >> 2;
            this.threshold = length;
            if (length == this.maxSegmentSize) {
                this.threshold = length + 1;
            }
            this.table = atomicReferenceArray;
        }

        public static final void A02(Segment segment) {
            if (segment.tryLock()) {
                try {
                    segment.A06();
                    segment.readCount.set(0);
                } finally {
                    segment.unlock();
                }
            }
        }

        public static final void A03(Segment segment) {
            if (segment.tryLock()) {
                try {
                    segment.A06();
                } finally {
                    segment.unlock();
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r5v2, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends X.0ql<K, V, E>> */
        /* JADX DEBUG: Multi-variable search result rejected for r0v26, resolved type: X.0qm<K, V, E extends X.0ql<K, V, E>, S extends com.google.common.collect.MapMakerInternalMap$Segment<K, V, E, S>> */
        /* JADX WARN: Multi-variable type inference failed */
        public final V A05(K k, int i, V v, boolean z) {
            lock();
            try {
                A02(this);
                int i2 = this.count + 1;
                if (i2 > this.threshold) {
                    AtomicReferenceArray<E> atomicReferenceArray = this.table;
                    int length = atomicReferenceArray.length();
                    if (length < 1073741824) {
                        int i3 = this.count;
                        AtomicReferenceArray<E> atomicReferenceArray2 = (AtomicReferenceArray<E>) new AtomicReferenceArray(length << 1);
                        this.threshold = (atomicReferenceArray2.length() * 3) >> 2;
                        int length2 = atomicReferenceArray2.length() - 1;
                        for (int i4 = 0; i4 < length; i4++) {
                            E e = atomicReferenceArray.get(i4);
                            if (e != null) {
                                AbstractC07000ql A40 = e.A40();
                                int A3V = e.A3V() & length2;
                                if (A40 == null) {
                                    atomicReferenceArray2.set(A3V, e);
                                } else {
                                    AbstractC07000ql r2 = e;
                                    while (A40 != null) {
                                        int A3V2 = A40.A3V() & length2;
                                        if (A3V2 != A3V) {
                                            r2 = A40;
                                            A3V = A3V2;
                                        }
                                        A40 = A40.A40();
                                    }
                                    atomicReferenceArray2.set(A3V, r2);
                                    while (e != r2) {
                                        int A3V3 = e.A3V() & length2;
                                        AbstractC07000ql A1t = this.map.A06.A1t(this, e, (AbstractC07000ql) atomicReferenceArray2.get(A3V3));
                                        if (A1t != null) {
                                            atomicReferenceArray2.set(A3V3, A1t);
                                        } else {
                                            i3--;
                                        }
                                        e = e.A40();
                                    }
                                }
                            }
                        }
                        this.table = atomicReferenceArray2;
                        this.count = i3;
                    }
                    i2 = this.count + 1;
                }
                AtomicReferenceArray<E> atomicReferenceArray3 = this.table;
                int length3 = (atomicReferenceArray3.length() - 1) & i;
                E e2 = atomicReferenceArray3.get(length3);
                AbstractC07000ql r22 = e2;
                while (true) {
                    if (r22 == null) {
                        this.modCount++;
                        E A5Z = this.map.A06.A5Z(this, k, i, e2);
                        A04(this, A5Z, v);
                        atomicReferenceArray3.set(length3, A5Z);
                        this.count = i2;
                        break;
                    }
                    Object key = r22.getKey();
                    if (r22.A3V() != i || key == null || !this.map.keyEquivalence.equivalent(k, key)) {
                        r22 = r22.A40();
                    } else {
                        V v2 = (V) r22.getValue();
                        if (v2 == null) {
                            this.modCount++;
                            A04(this, r22, v);
                            this.count = this.count;
                        } else {
                            if (!z) {
                                this.modCount++;
                                A04(this, r22, v);
                            }
                            unlock();
                            return v2;
                        }
                    }
                }
                return null;
            } finally {
                unlock();
            }
        }
    }

    public static final class StrongKeyWeakValueSegment<K, V> extends Segment<K, V, AnonymousClass0C6<K, V>, StrongKeyWeakValueSegment<K, V>> {
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public final void A06() {
            A0A(this.queueForValues);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public final void A07() {
            do {
            } while (this.queueForValues.poll() != null);
        }

        public StrongKeyWeakValueSegment(MapMakerInternalMap<K, V, AnonymousClass0C6<K, V>, StrongKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }
    }

    public static final class WeakKeyStrongValueSegment<K, V> extends Segment<K, V, AnonymousClass0C5<K, V>, WeakKeyStrongValueSegment<K, V>> {
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public final void A06() {
            A09(this.queueForKeys);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public final void A07() {
            do {
            } while (this.queueForKeys.poll() != null);
        }

        public WeakKeyStrongValueSegment(MapMakerInternalMap<K, V, AnonymousClass0C5<K, V>, WeakKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }
    }

    public static final class WeakKeyWeakValueSegment<K, V> extends Segment<K, V, AnonymousClass0C4<K, V>, WeakKeyWeakValueSegment<K, V>> {
        public final ReferenceQueue<K> queueForKeys = new ReferenceQueue<>();
        public final ReferenceQueue<V> queueForValues = new ReferenceQueue<>();

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public final void A06() {
            A09(this.queueForKeys);
            A0A(this.queueForValues);
        }

        @Override // com.google.common.collect.MapMakerInternalMap.Segment
        public final void A07() {
            do {
            } while (this.queueForKeys.poll() != null);
        }

        public WeakKeyWeakValueSegment(MapMakerInternalMap<K, V, AnonymousClass0C4<K, V>, WeakKeyWeakValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        Object value;
        if (obj == null) {
            return false;
        }
        Segment<K, V, E, S>[] segmentArr = this.A05;
        long j = -1;
        int i = 0;
        do {
            long j2 = 0;
            for (Segment<K, V, E, S> segment : segmentArr) {
                AtomicReferenceArray<E> atomicReferenceArray = segment.table;
                for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                    for (E e = atomicReferenceArray.get(i2); e != null; e = e.A40()) {
                        if (e.getKey() == null || (value = e.getValue()) == null) {
                            Segment.A03(segment);
                        } else if (this.A06.A9k().defaultEquivalence().equivalent(obj, value)) {
                            return true;
                        }
                    }
                }
                j2 += (long) segment.modCount;
            }
            if (j2 == j) {
                return false;
            }
            i++;
            j = j2;
        } while (i < 3);
        return false;
    }

    public static abstract class AbstractSerializationProxy<K, V> extends AnonymousClass0CI<K, V> implements Serializable {
        public static final long serialVersionUID = 3;
        public transient ConcurrentMap<K, V> A00;
        public final int concurrencyLevel;
        public final Equivalence<Object> keyEquivalence;
        public final Strength keyStrength;
        public final Equivalence<Object> valueEquivalence;
        public final Strength valueStrength;

        public AbstractSerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            this.keyStrength = strength;
            this.valueStrength = strength2;
            this.keyEquivalence = equivalence;
            this.valueEquivalence = equivalence2;
            this.concurrencyLevel = i;
            this.A00 = concurrentMap;
        }

        @Override // X.AnonymousClass0CI
        /* renamed from: A02 */
        public final ConcurrentMap<K, V> A01() {
            return this.A00;
        }
    }

    public static final class SerializationProxy<K, V> extends AbstractSerializationProxy<K, V> {
        public static final long serialVersionUID = 3;

        /* JADX DEBUG: Multi-variable search result rejected for r0v12, resolved type: java.util.concurrent.ConcurrentMap<K, V> */
        /* JADX WARN: Multi-variable type inference failed */
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            int readInt = objectInputStream.readInt();
            C06980qi r4 = new C06980qi();
            int i = r4.A01;
            int i2 = 1;
            boolean z = true;
            boolean z2 = false;
            if (i == -1) {
                z2 = true;
            }
            String str = "initial capacity was already set to %s";
            if (z2) {
                if (readInt < 0) {
                    z = false;
                }
                Preconditions.checkArgument(z);
                r4.A01 = readInt;
                r4.A01(this.keyStrength);
                Strength strength = this.valueStrength;
                Strength strength2 = r4.A04;
                i2 = 1;
                boolean z3 = true;
                boolean z4 = false;
                if (strength2 == null) {
                    z4 = true;
                }
                Preconditions.checkState(z4, "Value strength was already set to %s", strength2);
                if (strength != null) {
                    r4.A04 = strength;
                    if (strength != Strength.STRONG) {
                        r4.A05 = true;
                    }
                    Equivalence<Object> equivalence = this.keyEquivalence;
                    Equivalence<Object> equivalence2 = r4.A02;
                    boolean z5 = false;
                    if (equivalence2 == null) {
                        z5 = true;
                    }
                    Preconditions.checkState(z5, "key equivalence was already set to %s", equivalence2);
                    if (equivalence != null) {
                        r4.A02 = equivalence;
                        r4.A05 = true;
                        int i3 = this.concurrencyLevel;
                        i = r4.A00;
                        boolean z6 = false;
                        if (i == -1) {
                            z6 = true;
                        }
                        str = "concurrency level was already set to %s";
                        if (z6) {
                            if (i3 <= 0) {
                                z3 = false;
                            }
                            Preconditions.checkArgument(z3);
                            r4.A00 = i3;
                            this.A00 = r4.A00();
                            while (true) {
                                Object readObject = objectInputStream.readObject();
                                if (readObject != null) {
                                    this.A00.put(readObject, objectInputStream.readObject());
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                }
                throw null;
            }
            Object[] objArr = new Object[i2];
            objArr[0] = Integer.valueOf(i);
            throw new IllegalStateException(Strings.lenientFormat(str, objArr));
        }

        private Object readResolve() {
            return this.A00;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeInt(this.A00.size());
            for (Map.Entry<K, V> entry : this.A00.entrySet()) {
                objectOutputStream.writeObject(entry.getKey());
                objectOutputStream.writeObject(entry.getValue());
            }
            objectOutputStream.writeObject(null);
        }

        public SerializationProxy(Strength strength, Strength strength2, Equivalence<Object> equivalence, Equivalence<Object> equivalence2, int i, ConcurrentMap<K, V> concurrentMap) {
            super(strength, strength2, equivalence, equivalence2, i, concurrentMap);
        }
    }

    private final int A00(Object obj) {
        int doHash = this.keyEquivalence.doHash(obj);
        int i = doHash + ((doHash << 15) ^ -12931);
        int i2 = i ^ (i >>> 10);
        int i3 = i2 + (i2 << 3);
        int i4 = i3 ^ (i3 >>> 6);
        int i5 = i4 + (i4 << 2) + (i4 << 14);
        return i5 ^ (i5 >>> 16);
    }

    /* JADX WARN: Incorrect args count in method signature: (I)Lcom/google/common/collect/MapMakerInternalMap$Segment<TK;TV;TE;TS;>; */
    public static final Segment A01(MapMakerInternalMap mapMakerInternalMap, int i) {
        return mapMakerInternalMap.A05[(i >>> mapMakerInternalMap.A04) & mapMakerInternalMap.A03];
    }

    public final void clear() {
        Segment<K, V, E, S>[] segmentArr = this.A05;
        int length = segmentArr.length;
        for (int i = 0; i < length; i++) {
            Segment<K, V, E, S> segment = segmentArr[i];
            if (segment.count != 0) {
                segment.lock();
                try {
                    AtomicReferenceArray<E> atomicReferenceArray = segment.table;
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, null);
                    }
                    segment.A07();
                    segment.readCount.set(0);
                    segment.modCount++;
                    segment.count = 0;
                } finally {
                    segment.unlock();
                }
            }
        }
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        AbstractC07000ql A012;
        if (obj == null) {
            return false;
        }
        int A002 = A00(obj);
        Segment A013 = A01(this, A002);
        try {
            boolean z = false;
            if (!(A013.count == 0 || (A012 = Segment.A01(A013, obj, A002)) == null || A012.getValue() == null)) {
                z = true;
            }
            return z;
        } finally {
            A013.A08();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.A01;
        if (set != null) {
            return set;
        }
        C03610dm r0 = new C03610dm(this);
        this.A01 = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(@NullableDecl Object obj) {
        V v;
        if (obj == null) {
            return null;
        }
        int A002 = A00(obj);
        Segment A012 = A01(this, A002);
        try {
            AbstractC07000ql A013 = Segment.A01(A012, obj, A002);
            if (A013 == null) {
                v = null;
            } else {
                v = (V) A013.getValue();
                if (v == null) {
                    Segment.A03(A012);
                }
            }
            return v;
        } finally {
            A012.A08();
        }
    }

    public final boolean isEmpty() {
        Segment<K, V, E, S>[] segmentArr = this.A05;
        long j = 0;
        int i = 0;
        while (true) {
            int length = segmentArr.length;
            if (i < length) {
                if (segmentArr[i].count != 0) {
                    break;
                }
                j += (long) segmentArr[i].modCount;
                i++;
            } else if (j == 0) {
                return true;
            } else {
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        if (segmentArr[i2].count != 0) {
                            break;
                        }
                        j -= (long) segmentArr[i2].modCount;
                        i2++;
                    } else if (j != 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        Set<K> set = this.A02;
        if (set != null) {
            return set;
        }
        C03590dj r0 = new C03590dj(this);
        this.A02 = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public final V put(K k, V v) {
        if (k == null) {
            throw null;
        } else if (v != null) {
            int A002 = A00(k);
            return (V) A01(this, A002).A05(k, A002, v, false);
        } else {
            throw null;
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public final V putIfAbsent(K k, V v) {
        if (k == null) {
            throw null;
        } else if (v != null) {
            int A002 = A00(k);
            return (V) A01(this, A002).A05(k, A002, v, true);
        } else {
            throw null;
        }
    }

    public final int size() {
        Segment<K, V, E, S>[] segmentArr;
        long j = 0;
        for (Segment<K, V, E, S> segment : this.A05) {
            j += (long) segment.count;
        }
        return C07820vP.A00(j);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection<V> values() {
        Collection<V> collection = this.A00;
        if (collection != null) {
            return collection;
        }
        C07030qq r0 = new C07030qq(this);
        this.A00 = r0;
        return r0;
    }

    public Object writeReplace() {
        AbstractC07010qm<K, V, E, S> r0 = this.A06;
        Strength A5B = r0.A5B();
        Strength A9k = r0.A9k();
        return new SerializationProxy(A5B, A9k, this.keyEquivalence, A9k.defaultEquivalence(), this.concurrencyLevel, this);
    }

    public MapMakerInternalMap(C06980qi r8, AbstractC07010qm<K, V, E, S> r9) {
        int i = r8.A00;
        int min = Math.min(i == -1 ? 4 : i, 65536);
        this.concurrencyLevel = min;
        this.keyEquivalence = (Equivalence) MoreObjects.firstNonNull(r8.A02, ((Strength) MoreObjects.firstNonNull(r8.A03, Strength.STRONG)).defaultEquivalence());
        this.A06 = r9;
        int i2 = r8.A01;
        int min2 = Math.min(i2 == -1 ? 16 : i2, 1073741824);
        int i3 = 0;
        int i4 = 1;
        int i5 = 1;
        int i6 = 0;
        while (i5 < min) {
            i6++;
            i5 <<= 1;
        }
        this.A04 = 32 - i6;
        this.A03 = i5 - 1;
        this.A05 = new Segment[i5];
        int i7 = min2 / i5;
        while (i4 < (i5 * i7 < min2 ? i7 + 1 : i7)) {
            i4 <<= 1;
        }
        while (true) {
            Segment<K, V, E, S>[] segmentArr = this.A05;
            if (i3 < segmentArr.length) {
                segmentArr[i3] = this.A06.A5c(this, i4, -1);
                i3++;
            } else {
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.MapMakerInternalMap<K, V, E extends X.0ql<K, V, E>, S extends com.google.common.collect.MapMakerInternalMap$Segment<K, V, E, S>> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public static final class StrongKeyStrongValueSegment<K, V> extends Segment<K, V, AnonymousClass0C7<K, V>, StrongKeyStrongValueSegment<K, V>> {
        public StrongKeyStrongValueSegment(MapMakerInternalMap<K, V, AnonymousClass0C7<K, V>, StrongKeyStrongValueSegment<K, V>> mapMakerInternalMap, int i, int i2) {
            super(mapMakerInternalMap, i, i2);
        }
    }

    public enum Strength {
        STRONG {
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.Equals.INSTANCE;
            }
        },
        WEAK {
            @Override // com.google.common.collect.MapMakerInternalMap.Strength
            public Equivalence<Object> defaultEquivalence() {
                return Equivalence.Identity.INSTANCE;
            }
        };

        public abstract Equivalence<Object> defaultEquivalence();

        /* access modifiers changed from: public */
        /* synthetic */ Strength(AnonymousClass0dr r3) {
            this();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends X.0ql<K, V, E>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        if (r0 != false) goto L_0x0051;
     */
    @Override // java.util.AbstractMap, java.util.Map
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V remove(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r10) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.remove(java.lang.Object):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends X.0ql<K, V, E>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005d, code lost:
        if (r5.getValue() == null) goto L_0x005f;
     */
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean remove(@org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r11, @org.checkerframework.checker.nullness.compatqual.NullableDecl java.lang.Object r12) {
        /*
        // Method dump skipped, instructions count: 123
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.MapMakerInternalMap.remove(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends X.0ql<K, V, E>> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public final V replace(K k, V v) {
        if (k == null) {
            throw null;
        } else if (v != null) {
            int A002 = A00(k);
            Segment A012 = A01(this, A002);
            A012.lock();
            try {
                Segment.A02(A012);
                AtomicReferenceArray<E> atomicReferenceArray = A012.table;
                int length = (atomicReferenceArray.length() - 1) & A002;
                AbstractC07000ql r6 = (AbstractC07000ql) atomicReferenceArray.get(length);
                AbstractC07000ql r5 = r6;
                while (true) {
                    if (r5 == null) {
                        break;
                    }
                    Object key = r5.getKey();
                    if (r5.A3V() != A002 || key == null || !A012.map.keyEquivalence.equivalent(k, key)) {
                        r5 = r5.A40();
                    } else {
                        V v2 = (V) r5.getValue();
                        if (v2 == null) {
                            boolean z = false;
                            if (r5.getValue() == null) {
                                z = true;
                            }
                            if (z) {
                                A012.modCount++;
                                atomicReferenceArray.set(length, Segment.A00(A012, r6, r5));
                                A012.count--;
                            }
                        } else {
                            A012.modCount++;
                            Segment.A04(A012, r5, v);
                            A012.unlock();
                            return v2;
                        }
                    }
                }
                return null;
            } finally {
                A012.unlock();
            }
        } else {
            throw null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.concurrent.atomic.AtomicReferenceArray<E extends X.0ql<K, V, E>> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    @CanIgnoreReturnValue
    public final boolean replace(K k, @NullableDecl V v, V v2) {
        if (k == null) {
            throw null;
        } else if (v2 == null) {
            throw null;
        } else if (v == null) {
            return false;
        } else {
            int A002 = A00(k);
            Segment A012 = A01(this, A002);
            A012.lock();
            try {
                Segment.A02(A012);
                AtomicReferenceArray<E> atomicReferenceArray = A012.table;
                int length = (atomicReferenceArray.length() - 1) & A002;
                AbstractC07000ql r6 = (AbstractC07000ql) atomicReferenceArray.get(length);
                AbstractC07000ql r3 = r6;
                while (true) {
                    if (r3 == null) {
                        break;
                    }
                    Object key = r3.getKey();
                    if (r3.A3V() != A002 || key == null || !A012.map.keyEquivalence.equivalent(k, key)) {
                        r3 = r3.A40();
                    } else {
                        Object value = r3.getValue();
                        if (value == null) {
                            if (r3.getValue() == null) {
                                A012.modCount++;
                                atomicReferenceArray.set(length, Segment.A00(A012, r6, r3));
                                A012.count--;
                            }
                        } else if (A012.map.A06.A9k().defaultEquivalence().equivalent(v, value)) {
                            A012.modCount++;
                            Segment.A04(A012, r3, v2);
                            A012.unlock();
                            return true;
                        }
                    }
                }
                return false;
            } finally {
                A012.unlock();
            }
        }
    }
}
