package X;

import androidx.annotation.Nullable;
import java.util.ConcurrentModificationException;
import java.util.Map;

/* renamed from: X.3C  reason: invalid class name */
public class AnonymousClass3C<K, V> {
    public static int A03;
    public static int A04;
    @Nullable
    public static Object[] A05;
    @Nullable
    public static Object[] A06;
    public int A00 = 0;
    public int[] A01 = AnonymousClass33.A00;
    public Object[] A02 = AnonymousClass33.A01;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof AnonymousClass3C) {
                AnonymousClass3C r7 = (AnonymousClass3C) obj;
                if (size() == r7.size()) {
                    for (int i = 0; i < this.A00; i++) {
                        Object[] objArr = this.A02;
                        Object obj2 = objArr[i << 1];
                        Object obj3 = objArr[(i << 1) + 1];
                        Object obj4 = r7.get(obj2);
                        if (obj3 == null) {
                            if (obj4 != null || !r7.containsKey(obj2)) {
                                return false;
                            }
                        } else if (!obj3.equals(obj4)) {
                            return false;
                        }
                    }
                }
            } else {
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    if (size() == map.size()) {
                        for (int i2 = 0; i2 < this.A00; i2++) {
                            try {
                                Object[] objArr2 = this.A02;
                                Object obj5 = objArr2[i2 << 1];
                                Object obj6 = objArr2[(i2 << 1) + 1];
                                Object obj7 = map.get(obj5);
                                if (obj6 == null) {
                                    if (obj7 == null) {
                                        if (!map.containsKey(obj5)) {
                                            return false;
                                        }
                                    }
                                } else if (!obj6.equals(obj7)) {
                                    return false;
                                }
                            } catch (ClassCastException | NullPointerException unused) {
                            }
                        }
                    }
                }
                return false;
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final V get(Object obj) {
        return getOrDefault(obj, null);
    }

    private final int A00() {
        int i = this.A00;
        if (i == 0) {
            return -1;
        }
        int[] iArr = this.A01;
        try {
            int A002 = AnonymousClass33.A00(iArr, i, 0);
            if (A002 >= 0) {
                Object[] objArr = this.A02;
                if (objArr[A002 << 1] != null) {
                    int i2 = A002 + 1;
                    while (i2 < i && iArr[i2] == 0) {
                        if (objArr[i2 << 1] == null) {
                            return i2;
                        }
                        i2++;
                    }
                    do {
                        A002--;
                        if (A002 < 0 || iArr[A002] != 0) {
                            return i2 ^ -1;
                        }
                    } while (objArr[A002 << 1] != null);
                    return A002;
                }
            }
            return A002;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private final int A01(Object obj, int i) {
        int i2 = this.A00;
        if (i2 == 0) {
            return -1;
        }
        try {
            int A002 = AnonymousClass33.A00(this.A01, i2, i);
            if (A002 < 0 || obj.equals(this.A02[A002 << 1])) {
                return A002;
            }
            int i3 = A002 + 1;
            while (i3 < i2 && this.A01[i3] == i) {
                if (obj.equals(this.A02[i3 << 1])) {
                    return i3;
                }
                i3++;
            }
            do {
                A002--;
                if (A002 < 0 || this.A01[A002] != i) {
                    return i3 ^ -1;
                }
            } while (!obj.equals(this.A02[A002 << 1]));
            return A002;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static void A02(AnonymousClass3C r6, int i) {
        if (i == 8) {
            synchronized (AnonymousClass3C.class) {
                Object[] objArr = A06;
                if (objArr != null) {
                    r6.A02 = objArr;
                    A06 = (Object[]) objArr[0];
                    r6.A01 = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    A04--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (AnonymousClass3C.class) {
                Object[] objArr2 = A05;
                if (objArr2 != null) {
                    r6.A02 = objArr2;
                    A05 = (Object[]) objArr2[0];
                    r6.A01 = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    A03--;
                    return;
                }
            }
        }
        r6.A01 = new int[i];
        r6.A02 = new Object[(i << 1)];
    }

    public static void A03(int[] iArr, Object[] objArr, int i) {
        int length = iArr.length;
        if (length == 8) {
            synchronized (AnonymousClass3C.class) {
                int i2 = A04;
                if (i2 < 10) {
                    objArr[0] = A06;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    A06 = objArr;
                    A04 = i2 + 1;
                }
            }
        } else if (length == 4) {
            synchronized (AnonymousClass3C.class) {
                int i4 = A03;
                if (i4 < 10) {
                    objArr[0] = A05;
                    objArr[1] = iArr;
                    for (int i5 = (i << 1) - 1; i5 >= 2; i5--) {
                        objArr[i5] = null;
                    }
                    A05 = objArr;
                    A03 = i4 + 1;
                }
            }
        }
    }

    public final int A04(@Nullable Object obj) {
        if (obj == null) {
            return A00();
        }
        return A01(obj, obj.hashCode());
    }

    public final int A05(Object obj) {
        int i = this.A00 << 1;
        Object[] objArr = this.A02;
        if (obj == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    return i2 >> 1;
                }
            }
            return -1;
        }
        for (int i3 = 1; i3 < i; i3 += 2) {
            if (obj.equals(objArr[i3])) {
                return i3 >> 1;
            }
        }
        return -1;
    }

    public final V A06(int i) {
        int i2;
        Object[] objArr = this.A02;
        int i3 = i << 1;
        V v = (V) objArr[i3 + 1];
        int i4 = this.A00;
        if (i4 <= 1) {
            A03(this.A01, objArr, i4);
            this.A01 = AnonymousClass33.A00;
            this.A02 = AnonymousClass33.A01;
            i2 = 0;
        } else {
            i2 = i4 - 1;
            int[] iArr = this.A01;
            int length = iArr.length;
            int i5 = 8;
            if (length <= 8 || i4 >= length / 3) {
                if (i < i2) {
                    int i6 = i + 1;
                    int i7 = i2 - i;
                    System.arraycopy(iArr, i6, iArr, i, i7);
                    Object[] objArr2 = this.A02;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i3, i7 << 1);
                }
                Object[] objArr3 = this.A02;
                int i8 = i2 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                if (i4 > 8) {
                    i5 = i4 + (i4 >> 1);
                }
                A02(this, i5);
                if (i4 == this.A00) {
                    if (i > 0) {
                        System.arraycopy(iArr, 0, this.A01, 0, i);
                        System.arraycopy(objArr, 0, this.A02, 0, i3);
                    }
                    if (i < i2) {
                        int i9 = i + 1;
                        int i10 = i2 - i;
                        System.arraycopy(iArr, i9, this.A01, i, i10);
                        System.arraycopy(objArr, i9 << 1, this.A02, i3, i10 << 1);
                    }
                }
                throw new ConcurrentModificationException();
            }
        }
        if (i4 == this.A00) {
            this.A00 = i2;
            return v;
        }
        throw new ConcurrentModificationException();
    }

    public final void clear() {
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A01;
            Object[] objArr = this.A02;
            this.A01 = AnonymousClass33.A00;
            this.A02 = AnonymousClass33.A01;
            this.A00 = 0;
            A03(iArr, objArr, i);
        }
        if (this.A00 > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final int hashCode() {
        int hashCode;
        int[] iArr = this.A01;
        Object[] objArr = this.A02;
        int i = this.A00;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < i) {
            Object obj = objArr[i4];
            int i5 = iArr[i2];
            if (obj == null) {
                hashCode = 0;
            } else {
                hashCode = obj.hashCode();
            }
            i3 += hashCode ^ i5;
            i2++;
            i4 += 2;
        }
        return i3;
    }

    public final boolean isEmpty() {
        if (this.A00 <= 0) {
            return true;
        }
        return false;
    }

    @Nullable
    public final V put(K k, V v) {
        int hashCode;
        int A012;
        int i = this.A00;
        if (k == null) {
            A012 = A00();
            hashCode = 0;
        } else {
            hashCode = k.hashCode();
            A012 = A01(k, hashCode);
        }
        if (A012 >= 0) {
            int i2 = (A012 << 1) + 1;
            Object[] objArr = this.A02;
            V v2 = (V) objArr[i2];
            objArr[i2] = v;
            return v2;
        }
        int i3 = A012 ^ -1;
        int[] iArr = this.A01;
        if (i >= iArr.length) {
            int i4 = 4;
            if (i >= 8) {
                i4 = (i >> 1) + i;
            } else if (i >= 4) {
                i4 = 8;
            }
            Object[] objArr2 = this.A02;
            A02(this, i4);
            if (i == this.A00) {
                int[] iArr2 = this.A01;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr2, 0, this.A02, 0, objArr2.length);
                }
                A03(iArr, objArr2, i);
            }
            throw new ConcurrentModificationException();
        }
        if (i3 < i) {
            int[] iArr3 = this.A01;
            int i5 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i5, i - i3);
            Object[] objArr3 = this.A02;
            System.arraycopy(objArr3, i3 << 1, objArr3, i5 << 1, (this.A00 - i3) << 1);
        }
        int i6 = this.A00;
        if (i == i6) {
            int[] iArr4 = this.A01;
            if (i3 < iArr4.length) {
                iArr4[i3] = hashCode;
                Object[] objArr4 = this.A02;
                int i7 = i3 << 1;
                objArr4[i7] = k;
                objArr4[i7 + 1] = v;
                this.A00 = i6 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final boolean containsKey(@Nullable Object obj) {
        if (A04(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(Object obj) {
        if (A05(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final V getOrDefault(Object obj, V v) {
        int A042 = A04(obj);
        return A042 >= 0 ? (V) this.A02[(A042 << 1) + 1] : v;
    }

    @Nullable
    public final V putIfAbsent(K k, V v) {
        V v2 = get(k);
        if (v2 == null) {
            return put(k, v);
        }
        return v2;
    }

    public final int size() {
        return this.A00;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.A00 * 28);
        sb.append('{');
        for (int i = 0; i < this.A00; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object obj = this.A02[i << 1];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object obj2 = this.A02[(i << 1) + 1];
            if (obj2 != this) {
                sb.append(obj2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Nullable
    public final V remove(Object obj) {
        int A042 = A04(obj);
        if (A042 >= 0) {
            return A06(A042);
        }
        return null;
    }

    public final boolean remove(Object obj, Object obj2) {
        int A042 = A04(obj);
        if (A042 < 0) {
            return false;
        }
        Object obj3 = this.A02[(A042 << 1) + 1];
        if (obj2 != obj3 && (obj2 == null || !obj2.equals(obj3))) {
            return false;
        }
        A06(A042);
        return true;
    }

    @Nullable
    public final V replace(K k, V v) {
        int A042 = A04(k);
        if (A042 < 0) {
            return null;
        }
        int i = (A042 << 1) + 1;
        Object[] objArr = this.A02;
        V v2 = (V) objArr[i];
        objArr[i] = v;
        return v2;
    }

    public final boolean replace(K k, V v, V v2) {
        int A042 = A04(k);
        if (A042 < 0) {
            return false;
        }
        int i = (A042 << 1) + 1;
        Object obj = this.A02[i];
        if (obj != v && (v == null || !v.equals(obj))) {
            return false;
        }
        this.A02[i] = v2;
        return true;
    }
}
