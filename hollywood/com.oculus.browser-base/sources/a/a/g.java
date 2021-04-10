package a.a;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* compiled from: chromium-webapk7.dex */
public class g implements Map {

    /* renamed from: a  reason: collision with root package name */
    public static Object[] f9397a;
    public static int b;
    public static Object[] c;
    public static int d;
    public int[] e = h.f9398a;
    public Object[] f = h.b;
    public int g = 0;
    public a h;
    public c i;
    public e j;

    public static int b(int[] iArr, int i2, int i3) {
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            try {
                int i7 = iArr[i6];
                if (i7 < i3) {
                    i5 = i6 + 1;
                } else if (i7 <= i3) {
                    return i6;
                } else {
                    i4 = i6 - 1;
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
        return ~i5;
    }

    public static boolean c(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() != set2.size() || !set.containsAll(set2)) {
                    return false;
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static void d(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (g.class) {
                if (d < 10) {
                    objArr[0] = c;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    c = objArr;
                    d++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (g.class) {
                if (b < 10) {
                    objArr[0] = f9397a;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    f9397a = objArr;
                    b++;
                }
            }
        }
    }

    public final void a(int i2) {
        if (i2 == 8) {
            synchronized (g.class) {
                Object[] objArr = c;
                if (objArr != null) {
                    this.f = objArr;
                    c = (Object[]) objArr[0];
                    this.e = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    d--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (g.class) {
                Object[] objArr2 = f9397a;
                if (objArr2 != null) {
                    this.f = objArr2;
                    f9397a = (Object[]) objArr2[0];
                    this.e = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    b--;
                    return;
                }
            }
        }
        this.e = new int[i2];
        this.f = new Object[(i2 << 1)];
    }

    public void clear() {
        int i2 = this.g;
        if (i2 > 0) {
            int[] iArr = this.e;
            Object[] objArr = this.f;
            this.e = h.f9398a;
            this.f = h.b;
            this.g = 0;
            d(iArr, objArr, i2);
        }
        if (this.g > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object obj) {
        return f(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return h(obj) >= 0;
    }

    public int e(Object obj, int i2) {
        int i3 = this.g;
        if (i3 == 0) {
            return -1;
        }
        int b2 = b(this.e, i3, i2);
        if (b2 < 0 || obj.equals(this.f[b2 << 1])) {
            return b2;
        }
        int i4 = b2 + 1;
        while (i4 < i3 && this.e[i4] == i2) {
            if (obj.equals(this.f[i4 << 1])) {
                return i4;
            }
            i4++;
        }
        do {
            b2--;
            if (b2 < 0 || this.e[b2] != i2) {
                return ~i4;
            }
        } while (!obj.equals(this.f[b2 << 1]));
        return b2;
    }

    @Override // java.util.Map
    public Set entrySet() {
        a aVar = this.h;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(this);
        this.h = aVar2;
        return aVar2;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            try {
                if (obj instanceof g) {
                    g gVar = (g) obj;
                    if (this.g != gVar.g) {
                        return false;
                    }
                    for (int i2 = 0; i2 < this.g; i2++) {
                        Object i3 = i(i2);
                        Object m = m(i2);
                        Object obj2 = gVar.get(i3);
                        if (m == null) {
                            if (obj2 != null) {
                                return false;
                            }
                            if (!gVar.containsKey(i3)) {
                                return false;
                            }
                        } else if (!m.equals(obj2)) {
                            return false;
                        }
                    }
                } else if (!(obj instanceof Map)) {
                    return false;
                } else {
                    Map map = (Map) obj;
                    if (this.g != map.size()) {
                        return false;
                    }
                    for (int i4 = 0; i4 < this.g; i4++) {
                        Object i5 = i(i4);
                        Object m2 = m(i4);
                        Object obj3 = map.get(i5);
                        if (m2 == null) {
                            if (obj3 != null) {
                                return false;
                            }
                            if (!map.containsKey(i5)) {
                                return false;
                            }
                        } else if (!m2.equals(obj3)) {
                            return false;
                        }
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }
        return true;
    }

    public int f(Object obj) {
        return obj == null ? g() : e(obj, obj.hashCode());
    }

    public int g() {
        int i2 = this.g;
        if (i2 == 0) {
            return -1;
        }
        int b2 = b(this.e, i2, 0);
        if (b2 < 0 || this.f[b2 << 1] == null) {
            return b2;
        }
        int i3 = b2 + 1;
        while (i3 < i2 && this.e[i3] == 0) {
            if (this.f[i3 << 1] == null) {
                return i3;
            }
            i3++;
        }
        int i4 = b2 - 1;
        while (i4 >= 0 && this.e[i4] == 0) {
            if (this.f[i4 << 1] == null) {
                return i4;
            }
            i4--;
        }
        return ~i3;
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return getOrDefault(obj, null);
    }

    @Override // java.util.Map
    public Object getOrDefault(Object obj, Object obj2) {
        int f2 = f(obj);
        return f2 >= 0 ? this.f[(f2 << 1) + 1] : obj2;
    }

    public int h(Object obj) {
        int i2 = this.g * 2;
        Object[] objArr = this.f;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
        } else {
            for (int i4 = 1; i4 < i2; i4 += 2) {
                if (obj.equals(objArr[i4])) {
                    return i4 >> 1;
                }
            }
        }
        return -1;
    }

    public int hashCode() {
        int[] iArr = this.e;
        Object[] objArr = this.f;
        int i2 = this.g;
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            i5 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return i5;
    }

    public Object i(int i2) {
        return this.f[i2 << 1];
    }

    public boolean isEmpty() {
        return this.g <= 0;
    }

    public Object j(int i2) {
        Object[] objArr = this.f;
        int i3 = i2 << 1;
        Object obj = objArr[i3 + 1];
        int i4 = this.g;
        if (i4 <= 1) {
            clear();
        } else {
            int i5 = i4 - 1;
            int[] iArr = this.e;
            int i6 = 8;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i5) {
                    int i7 = i2 + 1;
                    int i8 = i5 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.f;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.f;
                int i9 = i5 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                if (i4 > 8) {
                    i6 = i4 + (i4 >> 1);
                }
                a(i6);
                if (i4 == this.g) {
                    if (i2 > 0) {
                        System.arraycopy(iArr, 0, this.e, 0, i2);
                        System.arraycopy(objArr, 0, this.f, 0, i3);
                    }
                    if (i2 < i5) {
                        int i10 = i2 + 1;
                        int i11 = i5 - i2;
                        System.arraycopy(iArr, i10, this.e, i2, i11);
                        System.arraycopy(objArr, i10 << 1, this.f, i3, i11 << 1);
                    }
                } else {
                    throw new ConcurrentModificationException();
                }
            }
            if (i4 == this.g) {
                this.g = i5;
            } else {
                throw new ConcurrentModificationException();
            }
        }
        return obj;
    }

    public Object k(int i2, Object obj) {
        int i3 = (i2 << 1) + 1;
        Object[] objArr = this.f;
        Object obj2 = objArr[i3];
        objArr[i3] = obj;
        return obj2;
    }

    @Override // java.util.Map
    public Set keySet() {
        c cVar = this.i;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(this);
        this.i = cVar2;
        return cVar2;
    }

    public Object[] l(Object[] objArr, int i2) {
        int i3 = this.g;
        if (objArr.length < i3) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i3);
        }
        for (int i4 = 0; i4 < i3; i4++) {
            objArr[i4] = this.f[(i4 << 1) + i2];
        }
        if (objArr.length > i3) {
            objArr[i3] = null;
        }
        return objArr;
    }

    public Object m(int i2) {
        return this.f[(i2 << 1) + 1];
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        int i2;
        int i3;
        int i4 = this.g;
        if (obj == null) {
            i3 = g();
            i2 = 0;
        } else {
            int hashCode = obj.hashCode();
            i2 = hashCode;
            i3 = e(obj, hashCode);
        }
        if (i3 >= 0) {
            int i5 = (i3 << 1) + 1;
            Object[] objArr = this.f;
            Object obj3 = objArr[i5];
            objArr[i5] = obj2;
            return obj3;
        }
        int i6 = ~i3;
        int[] iArr = this.e;
        if (i4 >= iArr.length) {
            int i7 = 4;
            if (i4 >= 8) {
                i7 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i7 = 8;
            }
            Object[] objArr2 = this.f;
            a(i7);
            if (i4 == this.g) {
                int[] iArr2 = this.e;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr2, 0, this.f, 0, objArr2.length);
                }
                d(iArr, objArr2, i4);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i6 < i4) {
            int[] iArr3 = this.e;
            int i8 = i6 + 1;
            System.arraycopy(iArr3, i6, iArr3, i8, i4 - i6);
            Object[] objArr3 = this.f;
            System.arraycopy(objArr3, i6 << 1, objArr3, i8 << 1, (this.g - i6) << 1);
        }
        int i9 = this.g;
        if (i4 == i9) {
            int[] iArr4 = this.e;
            if (i6 < iArr4.length) {
                iArr4[i6] = i2;
                Object[] objArr4 = this.f;
                int i10 = i6 << 1;
                objArr4[i10] = obj;
                objArr4[i10 + 1] = obj2;
                this.g = i9 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        int size = map.size() + this.g;
        int i2 = this.g;
        int[] iArr = this.e;
        if (iArr.length < size) {
            Object[] objArr = this.f;
            a(size);
            if (this.g > 0) {
                System.arraycopy(iArr, 0, this.e, 0, i2);
                System.arraycopy(objArr, 0, this.f, 0, i2 << 1);
            }
            d(iArr, objArr, i2);
        }
        if (this.g == i2) {
            for (Map.Entry entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Map
    public Object putIfAbsent(Object obj, Object obj2) {
        Object orDefault = getOrDefault(obj, null);
        return orDefault == null ? put(obj, obj2) : orDefault;
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        int f2 = f(obj);
        if (f2 >= 0) {
            return j(f2);
        }
        return null;
    }

    @Override // java.util.Map
    public Object replace(Object obj, Object obj2) {
        int f2 = f(obj);
        if (f2 >= 0) {
            return k(f2, obj2);
        }
        return null;
    }

    public int size() {
        return this.g;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.g * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.g; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object i3 = i(i2);
            if (i3 != this) {
                sb.append(i3);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object m = m(i2);
            if (m != this) {
                sb.append(m);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Map
    public Collection values() {
        e eVar = this.j;
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e(this);
        this.j = eVar2;
        return eVar2;
    }

    public boolean remove(Object obj, Object obj2) {
        Object m;
        int f2 = f(obj);
        if (f2 < 0 || (obj2 != (m = m(f2)) && (obj2 == null || !obj2.equals(m)))) {
            return false;
        }
        j(f2);
        return true;
    }

    @Override // java.util.Map
    public boolean replace(Object obj, Object obj2, Object obj3) {
        Object m;
        int f2 = f(obj);
        if (f2 < 0 || ((m = m(f2)) != obj2 && (obj2 == null || !obj2.equals(m)))) {
            return false;
        }
        k(f2, obj3);
        return true;
    }
}
