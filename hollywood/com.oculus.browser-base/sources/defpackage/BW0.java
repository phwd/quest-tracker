package defpackage;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* renamed from: BW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BW0 {
    public static Object[] F;
    public static int G;
    public static Object[] H;
    public static int I;

    /* renamed from: J  reason: collision with root package name */
    public int[] f7743J;
    public Object[] K;
    public int L;

    public BW0() {
        this.f7743J = AbstractC0179Cy.f7849a;
        this.K = AbstractC0179Cy.c;
        this.L = 0;
    }

    public static void c(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (BW0.class) {
                if (I < 10) {
                    objArr[0] = H;
                    objArr[1] = iArr;
                    for (int i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    H = objArr;
                    I++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (BW0.class) {
                if (G < 10) {
                    objArr[0] = F;
                    objArr[1] = iArr;
                    for (int i3 = (i << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    F = objArr;
                    G++;
                }
            }
        }
    }

    public final void a(int i) {
        if (i == 8) {
            synchronized (BW0.class) {
                Object[] objArr = H;
                if (objArr != null) {
                    this.K = objArr;
                    H = (Object[]) objArr[0];
                    this.f7743J = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    I--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (BW0.class) {
                Object[] objArr2 = F;
                if (objArr2 != null) {
                    this.K = objArr2;
                    F = (Object[]) objArr2[0];
                    this.f7743J = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    G--;
                    return;
                }
            }
        }
        this.f7743J = new int[i];
        this.K = new Object[(i << 1)];
    }

    public void b(int i) {
        int i2 = this.L;
        int[] iArr = this.f7743J;
        if (iArr.length < i) {
            Object[] objArr = this.K;
            a(i);
            if (this.L > 0) {
                System.arraycopy(iArr, 0, this.f7743J, 0, i2);
                System.arraycopy(objArr, 0, this.K, 0, i2 << 1);
            }
            c(iArr, objArr, i2);
        }
        if (this.L != i2) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i = this.L;
        if (i > 0) {
            int[] iArr = this.f7743J;
            Object[] objArr = this.K;
            this.f7743J = AbstractC0179Cy.f7849a;
            this.K = AbstractC0179Cy.c;
            this.L = 0;
            c(iArr, objArr, i);
        }
        if (this.L > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    public int d(Object obj, int i) {
        int i2 = this.L;
        if (i2 == 0) {
            return -1;
        }
        try {
            int a2 = AbstractC0179Cy.a(this.f7743J, i2, i);
            if (a2 < 0 || obj.equals(this.K[a2 << 1])) {
                return a2;
            }
            int i3 = a2 + 1;
            while (i3 < i2 && this.f7743J[i3] == i) {
                if (obj.equals(this.K[i3 << 1])) {
                    return i3;
                }
                i3++;
            }
            int i4 = a2 - 1;
            while (i4 >= 0 && this.f7743J[i4] == i) {
                if (obj.equals(this.K[i4 << 1])) {
                    return i4;
                }
                i4--;
            }
            return ~i3;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public int e(Object obj) {
        return obj == null ? f() : d(obj, obj.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof BW0) {
                BW0 bw0 = (BW0) obj;
                if (this.L != bw0.L) {
                    return false;
                }
                for (int i = 0; i < this.L; i++) {
                    Object h = h(i);
                    Object k = k(i);
                    Object obj2 = bw0.get(h);
                    if (k == null) {
                        if (obj2 != null || !bw0.containsKey(h)) {
                            return false;
                        }
                    } else if (!k.equals(obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (this.L != map.size()) {
                    return false;
                }
                for (int i2 = 0; i2 < this.L; i2++) {
                    Object h2 = h(i2);
                    Object k2 = k(i2);
                    Object obj3 = map.get(h2);
                    if (k2 == null) {
                        if (obj3 != null || !map.containsKey(h2)) {
                            return false;
                        }
                    } else if (!k2.equals(obj3)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (ClassCastException | NullPointerException unused) {
        }
    }

    public int f() {
        int i = this.L;
        if (i == 0) {
            return -1;
        }
        try {
            int a2 = AbstractC0179Cy.a(this.f7743J, i, 0);
            if (a2 < 0 || this.K[a2 << 1] == null) {
                return a2;
            }
            int i2 = a2 + 1;
            while (i2 < i && this.f7743J[i2] == 0) {
                if (this.K[i2 << 1] == null) {
                    return i2;
                }
                i2++;
            }
            int i3 = a2 - 1;
            while (i3 >= 0 && this.f7743J[i3] == 0) {
                if (this.K[i3 << 1] == null) {
                    return i3;
                }
                i3--;
            }
            return ~i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public int g(Object obj) {
        int i = this.L * 2;
        Object[] objArr = this.K;
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

    public Object get(Object obj) {
        return getOrDefault(obj, null);
    }

    public Object getOrDefault(Object obj, Object obj2) {
        int e = e(obj);
        return e >= 0 ? this.K[(e << 1) + 1] : obj2;
    }

    public Object h(int i) {
        return this.K[i << 1];
    }

    public int hashCode() {
        int[] iArr = this.f7743J;
        Object[] objArr = this.K;
        int i = this.L;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    public Object i(int i) {
        Object[] objArr = this.K;
        int i2 = i << 1;
        Object obj = objArr[i2 + 1];
        int i3 = this.L;
        if (i3 <= 1) {
            clear();
        } else {
            int i4 = i3 - 1;
            int[] iArr = this.f7743J;
            int i5 = 8;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i < i4) {
                    int i6 = i + 1;
                    int i7 = i4 - i;
                    System.arraycopy(iArr, i6, iArr, i, i7);
                    Object[] objArr2 = this.K;
                    System.arraycopy(objArr2, i6 << 1, objArr2, i2, i7 << 1);
                }
                Object[] objArr3 = this.K;
                int i8 = i4 << 1;
                objArr3[i8] = null;
                objArr3[i8 + 1] = null;
            } else {
                if (i3 > 8) {
                    i5 = i3 + (i3 >> 1);
                }
                a(i5);
                if (i3 == this.L) {
                    if (i > 0) {
                        System.arraycopy(iArr, 0, this.f7743J, 0, i);
                        System.arraycopy(objArr, 0, this.K, 0, i2);
                    }
                    if (i < i4) {
                        int i9 = i + 1;
                        int i10 = i4 - i;
                        System.arraycopy(iArr, i9, this.f7743J, i, i10);
                        System.arraycopy(objArr, i9 << 1, this.K, i2, i10 << 1);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
            if (i3 == this.L) {
                this.L = i4;
            } else {
                throw new ConcurrentModificationException();
            }
        }
        return obj;
    }

    public boolean isEmpty() {
        return this.L <= 0;
    }

    public Object j(int i, Object obj) {
        int i2 = (i << 1) + 1;
        Object[] objArr = this.K;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    public Object k(int i) {
        return this.K[(i << 1) + 1];
    }

    public Object put(Object obj, Object obj2) {
        int i;
        int i2;
        int i3 = this.L;
        if (obj == null) {
            i2 = f();
            i = 0;
        } else {
            int hashCode = obj.hashCode();
            i = hashCode;
            i2 = d(obj, hashCode);
        }
        if (i2 >= 0) {
            int i4 = (i2 << 1) + 1;
            Object[] objArr = this.K;
            Object obj3 = objArr[i4];
            objArr[i4] = obj2;
            return obj3;
        }
        int i5 = ~i2;
        int[] iArr = this.f7743J;
        if (i3 >= iArr.length) {
            int i6 = 4;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i6 = 8;
            }
            Object[] objArr2 = this.K;
            a(i6);
            if (i3 == this.L) {
                int[] iArr2 = this.f7743J;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr2, 0, this.K, 0, objArr2.length);
                }
                c(iArr, objArr2, i3);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i5 < i3) {
            int[] iArr3 = this.f7743J;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.K;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.L - i5) << 1);
        }
        int i8 = this.L;
        if (i3 == i8) {
            int[] iArr4 = this.f7743J;
            if (i5 < iArr4.length) {
                iArr4[i5] = i;
                Object[] objArr4 = this.K;
                int i9 = i5 << 1;
                objArr4[i9] = obj;
                objArr4[i9 + 1] = obj2;
                this.L = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        Object orDefault = getOrDefault(obj, null);
        return orDefault == null ? put(obj, obj2) : orDefault;
    }

    public Object remove(Object obj) {
        int e = e(obj);
        if (e >= 0) {
            return i(e);
        }
        return null;
    }

    public Object replace(Object obj, Object obj2) {
        int e = e(obj);
        if (e >= 0) {
            return j(e, obj2);
        }
        return null;
    }

    public int size() {
        return this.L;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.L * 28);
        sb.append('{');
        for (int i = 0; i < this.L; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object h = h(i);
            if (h != this) {
                sb.append(h);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object k = k(i);
            if (k != this) {
                sb.append(k);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean remove(Object obj, Object obj2) {
        int e = e(obj);
        if (e < 0) {
            return false;
        }
        Object k = k(e);
        if (obj2 != k && (obj2 == null || !obj2.equals(k))) {
            return false;
        }
        i(e);
        return true;
    }

    public boolean replace(Object obj, Object obj2, Object obj3) {
        int e = e(obj);
        if (e < 0) {
            return false;
        }
        Object k = k(e);
        if (k != obj2 && (obj2 == null || !obj2.equals(k))) {
            return false;
        }
        j(e, obj3);
        return true;
    }

    public BW0(int i) {
        if (i == 0) {
            this.f7743J = AbstractC0179Cy.f7849a;
            this.K = AbstractC0179Cy.c;
        } else {
            a(i);
        }
        this.L = 0;
    }
}
