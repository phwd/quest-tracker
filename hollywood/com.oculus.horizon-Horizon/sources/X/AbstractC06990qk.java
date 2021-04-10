package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.MapMakerInternalMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0qk  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC06990qk<T> implements Iterator<T> {
    public int A00;
    @MonotonicNonNullDecl
    public MapMakerInternalMap.Segment<K, V, E, S> A01;
    public int A02 = -1;
    @NullableDecl
    public E A03;
    @NullableDecl
    public MapMakerInternalMap<K, V, E, S>.WriteThroughEntry A04;
    @NullableDecl
    public MapMakerInternalMap<K, V, E, S>.WriteThroughEntry A05;
    @MonotonicNonNullDecl
    public AtomicReferenceArray<E> A06;
    public final /* synthetic */ MapMakerInternalMap A07;

    public static final void A00(AbstractC06990qk r3) {
        MapMakerInternalMap.Segment<K, V, E, S> segment;
        r3.A05 = null;
        E e = r3.A03;
        if (e != null) {
            while (true) {
                E e2 = (E) e.A40();
                r3.A03 = e2;
                if (e2 == null) {
                    break;
                } else if (!r3.A01(e2)) {
                    e = r3.A03;
                } else {
                    return;
                }
            }
        }
        while (true) {
            int i = r3.A02;
            if (i >= 0) {
                AtomicReferenceArray<E> atomicReferenceArray = r3.A06;
                r3.A02 = i - 1;
                E e3 = atomicReferenceArray.get(i);
                r3.A03 = e3;
                if (e3 == null) {
                    continue;
                } else if (!r3.A01(e3)) {
                    E e4 = r3.A03;
                    if (e4 != null) {
                        while (true) {
                            E e5 = (E) e4.A40();
                            r3.A03 = e5;
                            if (e5 == null) {
                                continue;
                                break;
                            } else if (!r3.A01(e5)) {
                                e4 = r3.A03;
                            } else {
                                return;
                            }
                        }
                    } else {
                        continue;
                    }
                } else {
                    return;
                }
            } else {
                do {
                    int i2 = r3.A00;
                    if (i2 >= 0) {
                        MapMakerInternalMap.Segment<K, V, E, S>[] segmentArr = r3.A07.A05;
                        r3.A00 = i2 - 1;
                        segment = segmentArr[i2];
                        r3.A01 = segment;
                    } else {
                        return;
                    }
                } while (segment.count == 0);
                AtomicReferenceArray<E> atomicReferenceArray2 = r3.A01.table;
                r3.A06 = atomicReferenceArray2;
                r3.A02 = atomicReferenceArray2.length() - 1;
            }
        }
    }

    @Override // java.util.Iterator
    public abstract T next();

    public AbstractC06990qk(MapMakerInternalMap mapMakerInternalMap) {
        this.A07 = mapMakerInternalMap;
        this.A00 = mapMakerInternalMap.A05.length - 1;
        A00(this);
    }

    public final boolean hasNext() {
        if (this.A05 != null) {
            return true;
        }
        return false;
    }

    public final void remove() {
        boolean z = false;
        if (this.A04 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        this.A07.remove(this.A04.getKey());
        this.A04 = null;
    }

    private final boolean A01(E e) {
        boolean z;
        Object value;
        try {
            Object key = e.getKey();
            if (e.getKey() == null || (value = e.getValue()) == null) {
                z = false;
            } else {
                this.A05 = new AnonymousClass0dU(this.A07, key, value);
                z = true;
            }
            return z;
        } finally {
            this.A01.A08();
        }
    }
}
