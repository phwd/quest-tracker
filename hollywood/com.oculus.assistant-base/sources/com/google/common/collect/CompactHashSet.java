package com.google.common.collect;

import X.Ts;
import X.Tw;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;

public class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    public transient float A00;
    public transient int A01;
    public transient int A02;
    public transient int A03;
    public transient int[] A04;
    public transient long[] A05;
    public transient Object[] A06;

    private final void A00(int i) {
        int A012 = Tw.A01(i, (double) 1.0f);
        int[] iArr = new int[A012];
        Arrays.fill(iArr, -1);
        this.A04 = iArr;
        this.A00 = 1.0f;
        this.A06 = new Object[i];
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        this.A05 = jArr;
        this.A03 = Math.max(1, (int) (((float) A012) * 1.0f));
    }

    public static boolean A01(CompactHashSet compactHashSet, Object obj, int i) {
        long j;
        int[] iArr = compactHashSet.A04;
        int length = (iArr.length - 1) & i;
        int i2 = iArr[length];
        if (i2 != -1) {
            int i3 = -1;
            while (true) {
                if (((int) (compactHashSet.A05[i2] >>> 32)) != i || !Objects.equal(obj, compactHashSet.A06[i2])) {
                    int i4 = (int) compactHashSet.A05[i2];
                    if (i4 == -1) {
                        break;
                    }
                    i3 = i2;
                    i2 = i4;
                } else {
                    if (i3 == -1) {
                        compactHashSet.A04[length] = (int) compactHashSet.A05[i2];
                    } else {
                        long[] jArr = compactHashSet.A05;
                        jArr[i3] = (jArr[i3] & -4294967296L) | (((long) ((int) jArr[i2])) & 4294967295L);
                    }
                    int size = compactHashSet.size() - 1;
                    if (i2 < size) {
                        Object[] objArr = compactHashSet.A06;
                        objArr[i2] = objArr[size];
                        objArr[size] = null;
                        long[] jArr2 = compactHashSet.A05;
                        long j2 = jArr2[size];
                        jArr2[i2] = j2;
                        jArr2[size] = -1;
                        int[] iArr2 = compactHashSet.A04;
                        int length2 = ((int) (j2 >>> 32)) & (iArr2.length - 1);
                        int i5 = iArr2[length2];
                        if (i5 == size) {
                            iArr2[length2] = i2;
                        } else {
                            while (true) {
                                j = jArr2[i5];
                                int i6 = (int) j;
                                if (i6 == size) {
                                    break;
                                }
                                i5 = i6;
                            }
                            jArr2[i5] = (j & -4294967296L) | (((long) i2) & 4294967295L);
                        }
                    } else {
                        compactHashSet.A06[i2] = null;
                        compactHashSet.A05[i2] = -1;
                    }
                    compactHashSet.A02--;
                    compactHashSet.A01++;
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        long[] jArr = this.A05;
        Object[] objArr = this.A06;
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
                if (((int) (j >>> 32)) == A022 && Objects.equal(obj, objArr[i2])) {
                    return false;
                }
                int i3 = (int) j;
                if (i3 == -1) {
                    jArr[i2] = (j & -4294967296L) | (((long) i) & 4294967295L);
                    break;
                }
                i2 = i3;
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
            this.A06[i] = obj;
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
            return true;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    public final void clear() {
        this.A01++;
        Arrays.fill(this.A06, 0, this.A02, (Object) null);
        Arrays.fill(this.A04, -1);
        Arrays.fill(this.A05, -1L);
        this.A02 = 0;
    }

    public final boolean isEmpty() {
        if (this.A02 == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return new Ts(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.google.common.collect.CompactHashSet<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        A00(3);
        int readInt = objectInputStream.readInt();
        while (true) {
            readInt--;
            if (readInt >= 0) {
                add(objectInputStream.readObject());
            } else {
                return;
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.A02);
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
    }

    public final boolean contains(Object obj) {
        int A022 = Tw.A02(obj);
        int[] iArr = this.A04;
        int i = iArr[(iArr.length - 1) & A022];
        while (i != -1) {
            long j = this.A05[i];
            if (((int) (j >>> 32)) == A022 && Objects.equal(obj, this.A06[i])) {
                return true;
            }
            i = (int) j;
        }
        return false;
    }

    public final boolean remove(Object obj) {
        return A01(this, obj, Tw.A02(obj));
    }

    public final int size() {
        return this.A02;
    }

    public CompactHashSet() {
        A00(3);
    }

    public CompactHashSet(int i) {
        A00(i);
    }

    public final Object[] toArray() {
        return Arrays.copyOf(this.A06, this.A02);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        Object[] objArr2 = this.A06;
        int i = this.A02;
        Preconditions.checkPositionIndexes(0, 0 + i, objArr2.length);
        int length = objArr.length;
        if (length < i) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
        } else if (length > i) {
            objArr[i] = null;
        }
        System.arraycopy(objArr2, 0, objArr, 0, i);
        return objArr;
    }
}
