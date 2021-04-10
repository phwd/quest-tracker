package defpackage;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* renamed from: eI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2333eI0 extends AbstractC2961i implements RandomAccess {
    public static final C2333eI0 G;
    public Object[] H;
    public int I;

    static {
        C2333eI0 ei0 = new C2333eI0(new Object[0], 0);
        G = ei0;
        ei0.F = false;
    }

    public C2333eI0(Object[] objArr, int i) {
        this.H = objArr;
        this.I = i;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, defpackage.AbstractC2961i, java.util.AbstractList
    public boolean add(Object obj) {
        a();
        int i = this.I;
        Object[] objArr = this.H;
        if (i == objArr.length) {
            this.H = Arrays.copyOf(objArr, ((i * 3) / 2) + 1);
        }
        Object[] objArr2 = this.H;
        int i2 = this.I;
        this.I = i2 + 1;
        objArr2[i2] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void b(int i) {
        if (i < 0 || i >= this.I) {
            throw new IndexOutOfBoundsException(c(i));
        }
    }

    public final String c(int i) {
        return AbstractC2531fV.t(35, "Index:", i, ", Size:", this.I);
    }

    @Override // defpackage.E30
    public E30 d(int i) {
        if (i >= this.I) {
            return new C2333eI0(Arrays.copyOf(this.H, i), this.I);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.List, java.util.AbstractList
    public Object get(int i) {
        b(i);
        return this.H[i];
    }

    @Override // java.util.List, java.util.AbstractList
    public Object remove(int i) {
        a();
        b(i);
        Object[] objArr = this.H;
        Object obj = objArr[i];
        int i2 = this.I;
        if (i < i2 - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (i2 - i) - 1);
        }
        this.I--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object set(int i, Object obj) {
        a();
        b(i);
        Object[] objArr = this.H;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    public int size() {
        return this.I;
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int i, Object obj) {
        int i2;
        a();
        if (i < 0 || i > (i2 = this.I)) {
            throw new IndexOutOfBoundsException(c(i));
        }
        Object[] objArr = this.H;
        if (i2 < objArr.length) {
            System.arraycopy(objArr, i, objArr, i + 1, i2 - i);
        } else {
            Object[] objArr2 = new Object[AbstractC2531fV.b(i2, 3, 2, 1)];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.H, i, objArr2, i + 1, this.I - i);
            this.H = objArr2;
        }
        this.H[i] = obj;
        this.I++;
        ((AbstractList) this).modCount++;
    }
}
