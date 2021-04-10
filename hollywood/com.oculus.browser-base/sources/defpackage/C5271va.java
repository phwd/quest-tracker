package defpackage;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

/* renamed from: va  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5271va implements Collection, Set {
    public static Object[] F;
    public static int G;
    public static Object[] H;
    public static int I;

    /* renamed from: J  reason: collision with root package name */
    public static final Object f11486J = new Object();
    public static final Object K = new Object();
    public int[] L;
    public Object[] M;
    public int N;

    public C5271va(int i) {
        if (i == 0) {
            this.L = AbstractC0179Cy.f7849a;
            this.M = AbstractC0179Cy.c;
        } else {
            a(i);
        }
        this.N = 0;
    }

    public static void b(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (K) {
                if (I < 10) {
                    objArr[0] = H;
                    objArr[1] = iArr;
                    for (int i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    H = objArr;
                    I++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (f11486J) {
                if (G < 10) {
                    objArr[0] = F;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
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
            synchronized (K) {
                Object[] objArr = H;
                if (objArr != null) {
                    try {
                        this.M = objArr;
                        H = (Object[]) objArr[0];
                        int[] iArr = (int[]) objArr[1];
                        this.L = iArr;
                        if (iArr != null) {
                            objArr[1] = null;
                            objArr[0] = null;
                            I--;
                            return;
                        }
                    } catch (ClassCastException unused) {
                    }
                    System.out.println("ArraySet Found corrupt ArraySet cache: [0]=" + objArr[0] + " [1]=" + objArr[1]);
                    H = null;
                    I = 0;
                }
            }
        } else if (i == 4) {
            synchronized (f11486J) {
                Object[] objArr2 = F;
                if (objArr2 != null) {
                    try {
                        this.M = objArr2;
                        F = (Object[]) objArr2[0];
                        int[] iArr2 = (int[]) objArr2[1];
                        this.L = iArr2;
                        if (iArr2 != null) {
                            objArr2[1] = null;
                            objArr2[0] = null;
                            G--;
                            return;
                        }
                    } catch (ClassCastException unused2) {
                    }
                    System.out.println("ArraySet Found corrupt ArraySet cache: [0]=" + objArr2[0] + " [1]=" + objArr2[1]);
                    F = null;
                    G = 0;
                }
            }
        }
        this.L = new int[i];
        this.M = new Object[i];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        int i;
        int i2;
        int i3 = this.N;
        if (obj == null) {
            i2 = e();
            i = 0;
        } else {
            int hashCode = obj.hashCode();
            i = hashCode;
            i2 = c(obj, hashCode);
        }
        if (i2 >= 0) {
            return false;
        }
        int i4 = ~i2;
        int[] iArr = this.L;
        if (i3 >= iArr.length) {
            int i5 = 4;
            if (i3 >= 8) {
                i5 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i5 = 8;
            }
            Object[] objArr = this.M;
            a(i5);
            if (i3 == this.N) {
                int[] iArr2 = this.L;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr, 0, this.M, 0, objArr.length);
                }
                b(iArr, objArr, i3);
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i3) {
            int[] iArr3 = this.L;
            int i6 = i4 + 1;
            int i7 = i3 - i4;
            System.arraycopy(iArr3, i4, iArr3, i6, i7);
            Object[] objArr2 = this.M;
            System.arraycopy(objArr2, i4, objArr2, i6, i7);
        }
        int i8 = this.N;
        if (i3 == i8) {
            int[] iArr4 = this.L;
            if (i4 < iArr4.length) {
                iArr4[i4] = i;
                this.M[i4] = obj;
                this.N = i8 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        int size = collection.size() + this.N;
        int i = this.N;
        int[] iArr = this.L;
        boolean z = false;
        if (iArr.length < size) {
            Object[] objArr = this.M;
            a(size);
            int i2 = this.N;
            if (i2 > 0) {
                System.arraycopy(iArr, 0, this.L, 0, i2);
                System.arraycopy(objArr, 0, this.M, 0, this.N);
            }
            b(iArr, objArr, this.N);
        }
        if (this.N == i) {
            for (Object obj : collection) {
                z |= add(obj);
            }
            return z;
        }
        throw new ConcurrentModificationException();
    }

    public final int c(Object obj, int i) {
        int i2 = this.N;
        if (i2 == 0) {
            return -1;
        }
        try {
            int a2 = AbstractC0179Cy.a(this.L, i2, i);
            if (a2 < 0 || obj.equals(this.M[a2])) {
                return a2;
            }
            int i3 = a2 + 1;
            while (i3 < i2 && this.L[i3] == i) {
                if (obj.equals(this.M[i3])) {
                    return i3;
                }
                i3++;
            }
            int i4 = a2 - 1;
            while (i4 >= 0 && this.L[i4] == i) {
                if (obj.equals(this.M[i4])) {
                    return i4;
                }
                i4--;
            }
            return ~i3;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public void clear() {
        int i = this.N;
        if (i != 0) {
            int[] iArr = this.L;
            Object[] objArr = this.M;
            this.L = AbstractC0179Cy.f7849a;
            this.M = AbstractC0179Cy.c;
            this.N = 0;
            b(iArr, objArr, i);
        }
        if (this.N != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean contains(Object obj) {
        return (obj == null ? e() : c(obj, obj.hashCode())) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    public final int e() {
        int i = this.N;
        if (i == 0) {
            return -1;
        }
        try {
            int a2 = AbstractC0179Cy.a(this.L, i, 0);
            if (a2 < 0 || this.M[a2] == null) {
                return a2;
            }
            int i2 = a2 + 1;
            while (i2 < i && this.L[i2] == 0) {
                if (this.M[i2] == null) {
                    return i2;
                }
                i2++;
            }
            int i3 = a2 - 1;
            while (i3 >= 0 && this.L[i3] == 0) {
                if (this.M[i3] == null) {
                    return i3;
                }
                i3--;
            }
            return ~i2;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (this.N != set.size()) {
                return false;
            }
            for (int i = 0; i < this.N; i++) {
                try {
                    if (!set.contains(this.M[i])) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public Object f(int i) {
        int i2 = this.N;
        Object[] objArr = this.M;
        Object obj = objArr[i];
        if (i2 <= 1) {
            clear();
        } else {
            int i3 = i2 - 1;
            int[] iArr = this.L;
            int i4 = 8;
            if (iArr.length <= 8 || i2 >= iArr.length / 3) {
                if (i < i3) {
                    int i5 = i + 1;
                    int i6 = i3 - i;
                    System.arraycopy(iArr, i5, iArr, i, i6);
                    Object[] objArr2 = this.M;
                    System.arraycopy(objArr2, i5, objArr2, i, i6);
                }
                this.M[i3] = null;
            } else {
                if (i2 > 8) {
                    i4 = i2 + (i2 >> 1);
                }
                a(i4);
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.L, 0, i);
                    System.arraycopy(objArr, 0, this.M, 0, i);
                }
                if (i < i3) {
                    int i7 = i + 1;
                    int i8 = i3 - i;
                    System.arraycopy(iArr, i7, this.L, i, i8);
                    System.arraycopy(objArr, i7, this.M, i, i8);
                }
            }
            if (i2 == this.N) {
                this.N = i3;
            } else {
                throw new ConcurrentModificationException();
            }
        }
        return obj;
    }

    public int hashCode() {
        int[] iArr = this.L;
        int i = this.N;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public boolean isEmpty() {
        return this.N <= 0;
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return new C5101ua(this);
    }

    public boolean remove(Object obj) {
        int e = obj == null ? e() : c(obj, obj.hashCode());
        if (e < 0) {
            return false;
        }
        f(e);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection collection) {
        boolean z = false;
        for (Object obj : collection) {
            z |= remove(obj);
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        boolean z = false;
        for (int i = this.N - 1; i >= 0; i--) {
            if (!collection.contains(this.M[i])) {
                f(i);
                z = true;
            }
        }
        return z;
    }

    public int size() {
        return this.N;
    }

    public Object[] toArray() {
        int i = this.N;
        Object[] objArr = new Object[i];
        System.arraycopy(this.M, 0, objArr, 0, i);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.N * 14);
        sb.append('{');
        for (int i = 0; i < this.N; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object obj = this.M[i];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray(Object[] objArr) {
        if (objArr.length < this.N) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.N);
        }
        System.arraycopy(this.M, 0, objArr, 0, this.N);
        int length = objArr.length;
        int i = this.N;
        if (length > i) {
            objArr[i] = null;
        }
        return objArr;
    }
}
