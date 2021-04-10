package com.google.common.collect;

import X.C0362Tn;
import X.C0364Tp;
import X.C0365Tq;
import X.Tw;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

public class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public transient float A00;
    public transient int A01;
    public transient int A02;
    public transient int A03;
    public transient int[] A04;
    public transient long[] A05;
    public transient Object[] A06;
    public transient Object[] A07;
    public transient Collection A08;
    public transient Set A09;
    public transient Set A0A;

    public void A07(int i, float f) {
        boolean z = false;
        boolean z2 = false;
        if (i >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Initial capacity must be non-negative");
        if (f > 0.0f) {
            z = true;
        }
        Preconditions.checkArgument(z, "Illegal load factor");
        int A012 = Tw.A01(i, (double) f);
        int[] iArr = new int[A012];
        Arrays.fill(iArr, -1);
        this.A04 = iArr;
        this.A00 = f;
        this.A06 = new Object[i];
        this.A07 = new Object[i];
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        this.A05 = jArr;
        this.A03 = Math.max(1, (int) (((float) A012) * f));
    }

    public final boolean containsValue(Object obj) {
        for (int i = 0; i < this.A02; i++) {
            if (Objects.equal(obj, this.A07[i])) {
                return true;
            }
        }
        return false;
    }

    public static Object A01(CompactHashMap compactHashMap, Object obj, int i) {
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
                    Object obj2 = compactHashMap.A07[i2];
                    if (i3 == -1) {
                        compactHashMap.A04[length] = (int) compactHashMap.A05[i2];
                    } else {
                        long[] jArr = compactHashMap.A05;
                        jArr[i3] = (jArr[i3] & -4294967296L) | (((long) ((int) jArr[i2])) & 4294967295L);
                    }
                    compactHashMap.A05(i2);
                    compactHashMap.A02--;
                    compactHashMap.A01++;
                    return obj2;
                }
            }
        }
        return null;
    }

    private final void A04(int i) {
        if (this instanceof CompactLinkedHashMap) {
            CompactLinkedHashMap compactLinkedHashMap = (CompactLinkedHashMap) this;
            if (compactLinkedHashMap.accessOrder) {
                CompactLinkedHashMap.A02(compactLinkedHashMap, (int) (compactLinkedHashMap.A02[i] >>> 32), compactLinkedHashMap.A03(i));
                CompactLinkedHashMap.A02(compactLinkedHashMap, compactLinkedHashMap.A01, i);
                CompactLinkedHashMap.A02(compactLinkedHashMap, i, -2);
                ((CompactHashMap) compactLinkedHashMap).A01++;
            }
        }
    }

    public final int A03(int i) {
        if (this instanceof CompactLinkedHashMap) {
            return (int) ((CompactLinkedHashMap) this).A02[i];
        }
        int i2 = i + 1;
        if (i2 >= this.A02) {
            return -1;
        }
        return i2;
    }

    public void A06(int i) {
        this.A06 = Arrays.copyOf(this.A06, i);
        this.A07 = Arrays.copyOf(this.A07, i);
        long[] jArr = this.A05;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1L);
        }
        this.A05 = copyOf;
    }

    public void A08(int i, Object obj, Object obj2, int i2) {
        this.A05[i] = (((long) i2) << 32) | 4294967295L;
        this.A06[i] = obj;
        this.A07[i] = obj2;
    }

    public void clear() {
        this.A01++;
        Arrays.fill(this.A06, 0, this.A02, (Object) null);
        Arrays.fill(this.A07, 0, this.A02, (Object) null);
        Arrays.fill(this.A04, -1);
        Arrays.fill(this.A05, -1L);
        this.A02 = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.A09;
        if (set != null) {
            return set;
        }
        C0362Tn tn = new C0362Tn(this);
        this.A09 = tn;
        return tn;
    }

    public final boolean isEmpty() {
        if (this.A02 == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.A0A;
        if (set != null) {
            return set;
        }
        C0364Tp tp = new C0364Tp(this);
        this.A0A = tp;
        return tp;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        long[] jArr = this.A05;
        Object[] objArr = this.A06;
        Object[] objArr2 = this.A07;
        int A022 = Tw.A02(obj);
        int[] iArr = this.A04;
        int length = (iArr.length - 1) & A022;
        int i = this.A02;
        int i2 = iArr[length];
        if (i2 == -1) {
            iArr[length] = i;
        } else {
            while (true) {
                long j = jArr[i2];
                if (((int) (j >>> 32)) != A022 || !Objects.equal(obj, objArr[i2])) {
                    int i3 = (int) j;
                    if (i3 == -1) {
                        jArr[i2] = (j & -4294967296L) | (((long) i) & 4294967295L);
                        break;
                    }
                    i2 = i3;
                } else {
                    Object obj3 = objArr2[i2];
                    objArr2[i2] = obj2;
                    A04(i2);
                    return obj3;
                }
            }
        }
        if (i != Integer.MAX_VALUE) {
            int i4 = i + 1;
            int length2 = this.A05.length;
            if (i4 > length2) {
                int max = Math.max(1, length2 >>> 1) + length2;
                if (max < 0) {
                    max = Integer.MAX_VALUE;
                }
                if (max != length2) {
                    A06(max);
                }
            }
            A08(i, obj, obj2, A022);
            this.A02 = i4;
            if (i >= this.A03) {
                int length3 = this.A04.length;
                int i5 = length3 << 1;
                if (length3 >= 1073741824) {
                    this.A03 = Integer.MAX_VALUE;
                } else {
                    int i6 = ((int) (((float) i5) * this.A00)) + 1;
                    int[] iArr2 = new int[i5];
                    Arrays.fill(iArr2, -1);
                    long[] jArr2 = this.A05;
                    int length4 = iArr2.length - 1;
                    for (int i7 = 0; i7 < this.A02; i7++) {
                        int i8 = (int) (jArr2[i7] >>> 32);
                        int i9 = i8 & length4;
                        int i10 = iArr2[i9];
                        iArr2[i9] = i7;
                        jArr2[i7] = (((long) i8) << 32) | (4294967295L & ((long) i10));
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

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.A08;
        if (collection != null) {
            return collection;
        }
        C0365Tq tq = new C0365Tq(this);
        this.A08 = tq;
        return tq;
    }

    public static int A00(CompactHashMap compactHashMap, Object obj) {
        int A022 = Tw.A02(obj);
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
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        A07(3, 1.0f);
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

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.A02);
        for (int i = 0; i < this.A02; i++) {
            objectOutputStream.writeObject(this.A06[i]);
            objectOutputStream.writeObject(this.A07[i]);
        }
    }

    public void A05(int i) {
        int size = size() - 1;
        if (i < size) {
            Object[] objArr = this.A06;
            objArr[i] = objArr[size];
            Object[] objArr2 = this.A07;
            objArr2[i] = objArr2[size];
            objArr[size] = null;
            objArr2[size] = null;
            long[] jArr = this.A05;
            long j = jArr[size];
            jArr[i] = j;
            jArr[size] = -1;
            int[] iArr = this.A04;
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
            this.A06[i] = null;
            this.A07[i] = null;
            this.A05[i] = -1;
        }
    }

    public final boolean containsKey(Object obj) {
        if (A00(this, obj) != -1) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        int A002 = A00(this, obj);
        A04(A002);
        if (A002 == -1) {
            return null;
        }
        return this.A07[A002];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        return A01(this, obj, Tw.A02(obj));
    }

    public final int size() {
        return this.A02;
    }

    public CompactHashMap() {
        A07(3, 1.0f);
    }

    public CompactHashMap(int i) {
        A07(i, 1.0f);
    }
}
