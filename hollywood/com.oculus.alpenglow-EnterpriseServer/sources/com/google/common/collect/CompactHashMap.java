package com.google.common.collect;

import X.AnonymousClass0rg;
import X.C07350rA;
import X.C07370rC;
import X.C07380rD;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
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
    public transient float A00;
    public transient int A01;
    public transient int A02;
    public transient int A03;
    @MonotonicNonNullDecl
    public transient int[] A04;
    @VisibleForTesting
    @MonotonicNonNullDecl
    public transient long[] A05;
    @VisibleForTesting
    @MonotonicNonNullDecl
    public transient Object[] A06;
    @VisibleForTesting
    @MonotonicNonNullDecl
    public transient Object[] A07;
    @MonotonicNonNullDecl
    public transient Collection<V> A08;
    @MonotonicNonNullDecl
    public transient Set<Map.Entry<K, V>> A09;
    @MonotonicNonNullDecl
    public transient Set<K> A0A;

    private final void A02() {
        int A012 = AnonymousClass0rg.A01(3, (double) 1.0f);
        int[] iArr = new int[A012];
        Arrays.fill(iArr, -1);
        this.A04 = iArr;
        this.A00 = 1.0f;
        this.A06 = new Object[3];
        this.A07 = new Object[3];
        long[] jArr = new long[3];
        Arrays.fill(jArr, -1L);
        this.A05 = jArr;
        this.A03 = Math.max(1, (int) (((float) A012) * 1.0f));
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        for (int i = 0; i < this.A02; i++) {
            if (Objects.equal(obj, this.A07[i])) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;I)TV; */
    @NullableDecl
    public static Object A01(@NullableDecl CompactHashMap compactHashMap, Object obj, int i) {
        long[] jArr;
        long j;
        int[] iArr = compactHashMap.A04;
        int length = (iArr.length - 1) & i;
        int i2 = iArr[length];
        if (i2 != -1) {
            int i3 = -1;
            while (true) {
                if (((int) (compactHashMap.A05[i2] >>> 32)) != i || !Objects.equal(obj, compactHashMap.A06[i2])) {
                    int i4 = (int) compactHashMap.A05[i2];
                    if (i4 == -1) {
                        break;
                    }
                    i3 = i2;
                    i2 = i4;
                } else {
                    Object[] objArr = compactHashMap.A07;
                    Object obj2 = objArr[i2];
                    if (i3 == -1) {
                        int[] iArr2 = compactHashMap.A04;
                        jArr = compactHashMap.A05;
                        iArr2[length] = (int) jArr[i2];
                    } else {
                        jArr = compactHashMap.A05;
                        jArr[i3] = (jArr[i3] & -4294967296L) | (((long) ((int) jArr[i2])) & 4294967295L);
                    }
                    int size = compactHashMap.size() - 1;
                    if (i2 < size) {
                        Object[] objArr2 = compactHashMap.A06;
                        objArr2[i2] = objArr2[size];
                        objArr[i2] = objArr[size];
                        objArr2[size] = null;
                        objArr[size] = null;
                        long j2 = jArr[size];
                        jArr[i2] = j2;
                        jArr[size] = -1;
                        int[] iArr3 = compactHashMap.A04;
                        int length2 = ((int) (j2 >>> 32)) & (iArr3.length - 1);
                        int i5 = iArr3[length2];
                        if (i5 == size) {
                            iArr3[length2] = i2;
                        } else {
                            while (true) {
                                j = jArr[i5];
                                int i6 = (int) j;
                                if (i6 == size) {
                                    break;
                                }
                                i5 = i6;
                            }
                            jArr[i5] = (j & -4294967296L) | (((long) i2) & 4294967295L);
                        }
                    } else {
                        compactHashMap.A06[i2] = null;
                        objArr[i2] = null;
                        jArr[i2] = -1;
                    }
                    compactHashMap.A02--;
                    compactHashMap.A01++;
                    return obj2;
                }
            }
        }
        return null;
    }

    public final void clear() {
        this.A01++;
        Arrays.fill(this.A06, 0, this.A02, (Object) null);
        Arrays.fill(this.A07, 0, this.A02, (Object) null);
        Arrays.fill(this.A04, -1);
        Arrays.fill(this.A05, -1L);
        this.A02 = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.A09;
        if (set != null) {
            return set;
        }
        C07350rA r0 = new C07350rA(this);
        this.A09 = r0;
        return r0;
    }

    public final boolean isEmpty() {
        if (this.A02 == 0) {
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
        C07370rC r0 = new C07370rC(this);
        this.A0A = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public final V put(@NullableDecl K k, @NullableDecl V v) {
        long[] jArr = this.A05;
        Object[] objArr = this.A06;
        Object[] objArr2 = this.A07;
        int A022 = AnonymousClass0rg.A02(k);
        int[] iArr = this.A04;
        int length = (iArr.length - 1) & A022;
        int i = this.A02;
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
                    return v2;
                }
            }
        }
        if (i != Integer.MAX_VALUE) {
            int i4 = i + 1;
            long[] jArr2 = this.A05;
            int length2 = jArr2.length;
            if (i4 > length2) {
                int max = Math.max(1, length2 >>> 1) + length2;
                if (max < 0) {
                    max = Integer.MAX_VALUE;
                }
                if (max != length2) {
                    this.A06 = Arrays.copyOf(this.A06, max);
                    this.A07 = Arrays.copyOf(this.A07, max);
                    long[] jArr3 = this.A05;
                    int length3 = jArr3.length;
                    jArr2 = Arrays.copyOf(jArr3, max);
                    if (max > length3) {
                        Arrays.fill(jArr2, length3, max, -1L);
                    }
                    this.A05 = jArr2;
                }
            }
            jArr2[i] = (((long) A022) << 32) | 4294967295L;
            this.A06[i] = k;
            this.A07[i] = v;
            this.A02 = i4;
            if (i >= this.A03) {
                int length4 = this.A04.length;
                int i5 = length4 << 1;
                if (length4 >= 1073741824) {
                    this.A03 = Integer.MAX_VALUE;
                } else {
                    int i6 = ((int) (((float) i5) * this.A00)) + 1;
                    int[] iArr2 = new int[i5];
                    Arrays.fill(iArr2, -1);
                    long[] jArr4 = this.A05;
                    int length5 = iArr2.length - 1;
                    for (int i7 = 0; i7 < this.A02; i7++) {
                        int i8 = (int) (jArr4[i7] >>> 32);
                        int i9 = i8 & length5;
                        int i10 = iArr2[i9];
                        iArr2[i9] = i7;
                        jArr4[i7] = (((long) i8) << 32) | (4294967295L & ((long) i10));
                    }
                    this.A03 = i6;
                    this.A04 = iArr2;
                }
            }
            this.A01++;
            return null;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    public final int size() {
        return this.A02;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection<V> values() {
        Collection<V> collection = this.A08;
        if (collection != null) {
            return collection;
        }
        C07380rD r0 = new C07380rD(this);
        this.A08 = r0;
        return r0;
    }

    public CompactHashMap() {
        A02();
    }

    public static int A00(@NullableDecl CompactHashMap compactHashMap, Object obj) {
        int A022 = AnonymousClass0rg.A02(obj);
        int[] iArr = compactHashMap.A04;
        int i = iArr[(iArr.length - 1) & A022];
        while (i != -1) {
            long j = compactHashMap.A05[i];
            if (((int) (j >>> 32)) == A022 && Objects.equal(obj, compactHashMap.A06[i])) {
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
        A02();
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
        objectOutputStream.writeInt(this.A02);
        for (int i = 0; i < this.A02; i++) {
            objectOutputStream.writeObject(this.A06[i]);
            objectOutputStream.writeObject(this.A07[i]);
        }
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        if (A00(this, obj) != -1) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(@NullableDecl Object obj) {
        int A002 = A00(this, obj);
        if (A002 == -1) {
            return null;
        }
        return (V) this.A07[A002];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public final V remove(@NullableDecl Object obj) {
        return (V) A01(this, obj, AnonymousClass0rg.A02(obj));
    }
}
