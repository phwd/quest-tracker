package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

/* renamed from: X.30  reason: invalid class name */
public final class AnonymousClass30<E> implements Collection<E>, Set<E> {
    public static int A04;
    public static int A05;
    @Nullable
    public static Object[] A06;
    @Nullable
    public static Object[] A07;
    public static final int[] A08 = new int[0];
    public static final Object[] A09 = new Object[0];
    public static final Object A0A = new Object();
    public static final Object A0B = new Object();
    public int A00 = 0;
    public AnonymousClass3B<E, E> A01;
    public int[] A02 = A08;
    public Object[] A03 = A09;

    private void A02(int i) {
        if (i == 8) {
            synchronized (A0B) {
                Object[] objArr = A07;
                if (objArr != null) {
                    try {
                        this.A03 = objArr;
                        A07 = (Object[]) objArr[0];
                        int[] iArr = (int[]) objArr[1];
                        this.A02 = iArr;
                        if (iArr != null) {
                            objArr[1] = null;
                            objArr[0] = null;
                            A05--;
                            return;
                        }
                    } catch (ClassCastException unused) {
                    }
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("ArraySet Found corrupt ArraySet cache: [0]=");
                    sb.append(objArr[0]);
                    sb.append(" [1]=");
                    sb.append(objArr[1]);
                    printStream.println(sb.toString());
                    A07 = null;
                    A05 = 0;
                }
            }
        } else if (i == 4) {
            synchronized (A0A) {
                Object[] objArr2 = A06;
                if (objArr2 != null) {
                    try {
                        this.A03 = objArr2;
                        A06 = (Object[]) objArr2[0];
                        int[] iArr2 = (int[]) objArr2[1];
                        this.A02 = iArr2;
                        if (iArr2 != null) {
                            objArr2[1] = null;
                            objArr2[0] = null;
                            A04--;
                            return;
                        }
                    } catch (ClassCastException unused2) {
                    }
                    PrintStream printStream2 = System.out;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("ArraySet Found corrupt ArraySet cache: [0]=");
                    sb2.append(objArr2[0]);
                    sb2.append(" [1]=");
                    sb2.append(objArr2[1]);
                    printStream2.println(sb2.toString());
                    A06 = null;
                    A04 = 0;
                }
            }
        }
        this.A02 = new int[i];
        this.A03 = new Object[i];
    }

    public static void A03(int[] iArr, Object[] objArr, int i) {
        int length = iArr.length;
        if (length == 8) {
            synchronized (A0B) {
                int i2 = A05;
                if (i2 < 10) {
                    objArr[0] = A07;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    A07 = objArr;
                    A05 = i2 + 1;
                }
            }
        } else if (length == 4) {
            synchronized (A0A) {
                int i4 = A04;
                if (i4 < 10) {
                    objArr[0] = A06;
                    objArr[1] = iArr;
                    for (int i5 = i - 1; i5 >= 2; i5--) {
                        objArr[i5] = null;
                    }
                    A06 = objArr;
                    A04 = i4 + 1;
                }
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Set) {
                Set set = (Set) obj;
                if (size() == set.size()) {
                    for (int i = 0; i < this.A00; i++) {
                        try {
                            if (set.contains(this.A03[i])) {
                            }
                        } catch (ClassCastException | NullPointerException unused) {
                        }
                    }
                }
                return false;
            }
            return false;
        }
        return true;
    }

    public static int A00(AnonymousClass30 r6) {
        int i = r6.A00;
        if (i == 0) {
            return -1;
        }
        int[] iArr = r6.A02;
        try {
            int A002 = AnonymousClass33.A00(iArr, i, 0);
            if (A002 >= 0) {
                Object[] objArr = r6.A03;
                if (objArr[A002] != null) {
                    int i2 = A002 + 1;
                    while (i2 < i && iArr[i2] == 0) {
                        if (objArr[i2] == null) {
                            return i2;
                        }
                        i2++;
                    }
                    do {
                        A002--;
                        if (A002 < 0 || iArr[A002] != 0) {
                            return i2 ^ -1;
                        }
                    } while (objArr[A002] != null);
                    return A002;
                }
            }
            return A002;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static int A01(AnonymousClass30 r4, Object obj, int i) {
        int i2 = r4.A00;
        if (i2 == 0) {
            return -1;
        }
        try {
            int A002 = AnonymousClass33.A00(r4.A02, i2, i);
            if (A002 < 0 || obj.equals(r4.A03[A002])) {
                return A002;
            }
            int i3 = A002 + 1;
            while (i3 < i2 && r4.A02[i3] == i) {
                if (obj.equals(r4.A03[i3])) {
                    return i3;
                }
                i3++;
            }
            do {
                A002--;
                if (A002 < 0 || r4.A02[A002] != i) {
                    return i3 ^ -1;
                }
            } while (!obj.equals(r4.A03[A002]));
            return A002;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (I)TE; */
    public final void A04(int i) {
        int i2 = this.A00;
        Object[] objArr = this.A03;
        if (i2 <= 1) {
            clear();
            return;
        }
        int i3 = i2 - 1;
        int[] iArr = this.A02;
        int length = iArr.length;
        int i4 = 8;
        if (length <= 8 || i2 >= length / 3) {
            if (i < i3) {
                int i5 = i + 1;
                int i6 = i3 - i;
                System.arraycopy(iArr, i5, iArr, i, i6);
                Object[] objArr2 = this.A03;
                System.arraycopy(objArr2, i5, objArr2, i, i6);
            }
            this.A03[i3] = null;
        } else {
            if (i2 > 8) {
                i4 = i2 + (i2 >> 1);
            }
            A02(i4);
            if (i > 0) {
                System.arraycopy(iArr, 0, this.A02, 0, i);
                System.arraycopy(objArr, 0, this.A03, 0, i);
            }
            if (i < i3) {
                int i7 = i + 1;
                int i8 = i3 - i;
                System.arraycopy(iArr, i7, this.A02, i, i8);
                System.arraycopy(objArr, i7, this.A03, i, i8);
            }
        }
        if (i2 == this.A00) {
            this.A00 = i3;
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(@Nullable E e) {
        int hashCode;
        int A012;
        int i = this.A00;
        if (e == null) {
            A012 = A00(this);
            hashCode = 0;
        } else {
            hashCode = e.hashCode();
            A012 = A01(this, e, hashCode);
        }
        if (A012 >= 0) {
            return false;
        }
        int i2 = A012 ^ -1;
        int[] iArr = this.A02;
        if (i >= iArr.length) {
            int i3 = 4;
            if (i >= 8) {
                i3 = (i >> 1) + i;
            } else if (i >= 4) {
                i3 = 8;
            }
            Object[] objArr = this.A03;
            A02(i3);
            if (i == this.A00) {
                int[] iArr2 = this.A02;
                if (iArr2.length > 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    System.arraycopy(objArr, 0, this.A03, 0, objArr.length);
                }
                A03(iArr, objArr, i);
            }
            throw new ConcurrentModificationException();
        }
        if (i2 < i) {
            int[] iArr3 = this.A02;
            int i4 = i2 + 1;
            int i5 = i - i2;
            System.arraycopy(iArr3, i2, iArr3, i4, i5);
            Object[] objArr2 = this.A03;
            System.arraycopy(objArr2, i2, objArr2, i4, i5);
        }
        int i6 = this.A00;
        if (i == i6) {
            int[] iArr4 = this.A02;
            if (i2 < iArr4.length) {
                iArr4[i2] = hashCode;
                this.A03[i2] = e;
                this.A00 = i6 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: X.30<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(@NonNull Collection<? extends E> collection) {
        int size = this.A00 + collection.size();
        int i = this.A00;
        int[] iArr = this.A02;
        if (iArr.length < size) {
            Object[] objArr = this.A03;
            A02(size);
            int i2 = this.A00;
            if (i2 > 0) {
                System.arraycopy(iArr, 0, this.A02, 0, i2);
                System.arraycopy(objArr, 0, this.A03, 0, this.A00);
            }
            A03(iArr, objArr, this.A00);
        }
        if (this.A00 == i) {
            Iterator<? extends E> it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                z |= add(it.next());
            }
            return z;
        }
        throw new ConcurrentModificationException();
    }

    public final void clear() {
        int i = this.A00;
        if (i != 0) {
            int[] iArr = this.A02;
            Object[] objArr = this.A03;
            this.A02 = A08;
            this.A03 = A09;
            this.A00 = 0;
            A03(iArr, objArr, i);
        }
        if (this.A00 != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean contains(@Nullable Object obj) {
        int A012;
        if (obj == null) {
            A012 = A00(this);
        } else {
            A012 = A01(this, obj, obj.hashCode());
        }
        if (A012 >= 0) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int[] iArr = this.A02;
        int i = this.A00;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public final boolean isEmpty() {
        if (this.A00 <= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<E> iterator() {
        AnonymousClass3B r1 = this.A01;
        if (r1 == null) {
            r1 = new UA(this);
            this.A01 = r1;
        }
        AnonymousClass38 r0 = r1.A01;
        if (r0 == null) {
            r0 = new AnonymousClass38(r1);
            r1.A01 = r0;
        }
        return r0.iterator();
    }

    public final boolean remove(@Nullable Object obj) {
        int A012;
        if (obj == null) {
            A012 = A00(this);
        } else {
            A012 = A01(this, obj, obj.hashCode());
        }
        if (A012 < 0) {
            return false;
        }
        A04(A012);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(@NonNull Collection<?> collection) {
        boolean z = false;
        for (int i = this.A00 - 1; i >= 0; i--) {
            if (!collection.contains(this.A03[i])) {
                A04(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    public final int size() {
        return this.A00;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.A00 * 14);
        sb.append('{');
        for (int i = 0; i < this.A00; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object obj = this.A03[i];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @NonNull
    public final Object[] toArray() {
        int i = this.A00;
        Object[] objArr = new Object[i];
        System.arraycopy(this.A03, 0, objArr, 0, i);
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public final <T> T[] toArray(@NonNull T[] tArr) {
        if (tArr.length < this.A00) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.A00));
        }
        System.arraycopy(this.A03, 0, tArr, 0, this.A00);
        int length = tArr.length;
        int i = this.A00;
        if (length > i) {
            tArr[i] = null;
        }
        return tArr;
    }
}
