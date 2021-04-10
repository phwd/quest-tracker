package com.google.common.collect;

import X.AnonymousClass0tm;
import X.AnonymousClass0to;
import X.C05110tp;
import X.C05150uI;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
public class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    @VisibleForTesting
    @MonotonicNonNullDecl
    public transient Object[] A00;
    public transient float A01;
    public transient int A02;
    public transient int A03;
    public transient int A04;
    @MonotonicNonNullDecl
    public transient int[] A05;
    @VisibleForTesting
    @MonotonicNonNullDecl
    public transient long[] A06;
    @VisibleForTesting
    @MonotonicNonNullDecl
    public transient Object[] A07;
    @MonotonicNonNullDecl
    public transient Collection<V> A08;
    @MonotonicNonNullDecl
    public transient Set<Map.Entry<K, V>> A09;
    @MonotonicNonNullDecl
    public transient Set<K> A0A;

    public int A05(int i, int i2) {
        return i - 1;
    }

    public void A06(int i) {
    }

    public void A09(int i, float f) {
        boolean z = false;
        boolean z2 = false;
        if (i >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Initial capacity must be non-negative");
        if (f > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            z = true;
        }
        Preconditions.checkArgument(z, "Illegal load factor");
        int A012 = C05150uI.A01(i, (double) f);
        int[] iArr = new int[A012];
        Arrays.fill(iArr, -1);
        this.A05 = iArr;
        this.A01 = f;
        this.A07 = new Object[i];
        this.A00 = new Object[i];
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        this.A06 = jArr;
        this.A04 = Math.max(1, (int) (((float) A012) * f));
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        for (int i = 0; i < this.A03; i++) {
            if (Objects.equal(obj, this.A00[i])) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;I)TV; */
    @NullableDecl
    public static Object A02(@NullableDecl CompactHashMap compactHashMap, Object obj, int i) {
        int[] iArr = compactHashMap.A05;
        int length = (iArr.length - 1) & i;
        int i2 = iArr[length];
        if (i2 != -1) {
            int i3 = -1;
            while (true) {
                if (((int) (compactHashMap.A06[i2] >>> 32)) != i || !Objects.equal(obj, compactHashMap.A07[i2])) {
                    int i4 = (int) compactHashMap.A06[i2];
                    if (i4 == -1) {
                        break;
                    }
                    i3 = i2;
                    i2 = i4;
                } else {
                    Object obj2 = compactHashMap.A00[i2];
                    if (i3 == -1) {
                        compactHashMap.A05[length] = (int) compactHashMap.A06[i2];
                    } else {
                        long[] jArr = compactHashMap.A06;
                        jArr[i3] = (jArr[i3] & -4294967296L) | (((long) ((int) jArr[i2])) & 4294967295L);
                    }
                    compactHashMap.A07(i2);
                    compactHashMap.A03--;
                    compactHashMap.A02++;
                    return obj2;
                }
            }
        }
        return null;
    }

    public int A04(int i) {
        int i2 = i + 1;
        if (i2 >= this.A03) {
            return -1;
        }
        return i2;
    }

    public void A08(int i) {
        this.A07 = Arrays.copyOf(this.A07, i);
        this.A00 = Arrays.copyOf(this.A00, i);
        long[] jArr = this.A06;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1L);
        }
        this.A06 = copyOf;
    }

    public void A0A(int i, @NullableDecl K k, @NullableDecl V v, int i2) {
        this.A06[i] = (((long) i2) << 32) | 4294967295L;
        this.A07[i] = k;
        this.A00[i] = v;
    }

    public void clear() {
        this.A02++;
        Arrays.fill(this.A07, 0, this.A03, (Object) null);
        Arrays.fill(this.A00, 0, this.A03, (Object) null);
        Arrays.fill(this.A05, -1);
        Arrays.fill(this.A06, -1L);
        this.A03 = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.A09;
        if (set != null) {
            return set;
        }
        AnonymousClass0tm r0 = new AnonymousClass0tm(this);
        this.A09 = r0;
        return r0;
    }

    public final boolean isEmpty() {
        if (this.A03 == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        Set<K> set = this.A0A;
        if (set != null) {
            return set;
        }
        AnonymousClass0to r0 = new AnonymousClass0to(this);
        this.A0A = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public final V put(@NullableDecl K k, @NullableDecl V v) {
        long[] jArr = this.A06;
        Object[] objArr = this.A07;
        Object[] objArr2 = this.A00;
        int A022 = C05150uI.A02(k);
        int[] iArr = this.A05;
        int length = (iArr.length - 1) & A022;
        int i = this.A03;
        int i2 = iArr[length];
        if (i2 == -1) {
            iArr[length] = i;
        } else {
            while (true) {
                long j = jArr[i2];
                if (((int) (j >>> 32)) != A022 || !Objects.equal(k, objArr[i2])) {
                    int i3 = (int) j;
                    if (i3 == -1) {
                        jArr[i2] = (j & -4294967296L) | (((long) i) & 4294967295L);
                        break;
                    }
                    i2 = i3;
                } else {
                    V v2 = (V) objArr2[i2];
                    objArr2[i2] = v;
                    A06(i2);
                    return v2;
                }
            }
        }
        if (i != Integer.MAX_VALUE) {
            int i4 = i + 1;
            int length2 = this.A06.length;
            if (i4 > length2) {
                int max = Math.max(1, length2 >>> 1) + length2;
                if (max < 0) {
                    max = Integer.MAX_VALUE;
                }
                if (max != length2) {
                    A08(max);
                }
            }
            A0A(i, k, v, A022);
            this.A03 = i4;
            if (i >= this.A04) {
                int length3 = this.A05.length;
                int i5 = length3 << 1;
                if (length3 >= 1073741824) {
                    this.A04 = Integer.MAX_VALUE;
                } else {
                    int i6 = ((int) (((float) i5) * this.A01)) + 1;
                    int[] iArr2 = new int[i5];
                    Arrays.fill(iArr2, -1);
                    long[] jArr2 = this.A06;
                    int length4 = iArr2.length - 1;
                    for (int i7 = 0; i7 < this.A03; i7++) {
                        int i8 = (int) (jArr2[i7] >>> 32);
                        int i9 = i8 & length4;
                        int i10 = iArr2[i9];
                        iArr2[i9] = i7;
                        jArr2[i7] = (((long) i8) << 32) | (4294967295L & ((long) i10));
                    }
                    this.A04 = i6;
                    this.A05 = iArr2;
                }
            }
            this.A02++;
            return null;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection<V> values() {
        Collection<V> collection = this.A08;
        if (collection != null) {
            return collection;
        }
        C05110tp r0 = new C05110tp(this);
        this.A08 = r0;
        return r0;
    }

    public static int A01(@NullableDecl CompactHashMap compactHashMap, Object obj) {
        int A022 = C05150uI.A02(obj);
        int[] iArr = compactHashMap.A05;
        int i = iArr[(iArr.length - 1) & A022];
        while (i != -1) {
            long j = compactHashMap.A06[i];
            if (((int) (j >>> 32)) == A022 && Objects.equal(obj, compactHashMap.A07[i])) {
                return i;
            }
            i = (int) j;
        }
        return -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.google.common.collect.CompactHashMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        A09(3, 1.0f);
        int readInt = objectInputStream.readInt();
        while (true) {
            readInt--;
            if (readInt >= 0) {
                put(objectInputStream.readObject(), objectInputStream.readObject());
            } else {
                return;
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.A03);
        for (int i = 0; i < this.A03; i++) {
            objectOutputStream.writeObject(this.A07[i]);
            objectOutputStream.writeObject(this.A00[i]);
        }
    }

    public int A03() {
        if (isEmpty()) {
            return -1;
        }
        return 0;
    }

    public void A07(int i) {
        int size = size() - 1;
        if (i < size) {
            Object[] objArr = this.A07;
            objArr[i] = objArr[size];
            Object[] objArr2 = this.A00;
            objArr2[i] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            long[] jArr = this.A06;
            long j = jArr[size];
            jArr[i] = j;
            jArr[size] = -1;
            int[] iArr = this.A05;
            int length = ((int) (j >>> 32)) & (iArr.length - 1);
            int i2 = iArr[length];
            if (i2 == size) {
                iArr[length] = i;
                return;
            }
            while (true) {
                long j2 = jArr[i2];
                int i3 = (int) j2;
                if (i3 == size) {
                    jArr[i2] = (j2 & -4294967296L) | (((long) i) & 4294967295L);
                    return;
                }
                i2 = i3;
            }
        } else {
            this.A07[i] = null;
            this.A00[i] = null;
            this.A06[i] = -1;
        }
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        if (A01(this, obj) != -1) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(@NullableDecl Object obj) {
        int A012 = A01(this, obj);
        A06(A012);
        if (A012 == -1) {
            return null;
        }
        return (V) this.A00[A012];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public final V remove(@NullableDecl Object obj) {
        return (V) A02(this, obj, C05150uI.A02(obj));
    }

    public final int size() {
        return this.A03;
    }

    public CompactHashMap() {
        A09(3, 1.0f);
    }

    public CompactHashMap(int i) {
        A09(i, 1.0f);
    }
}
