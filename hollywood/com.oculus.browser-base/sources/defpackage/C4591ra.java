package defpackage;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: ra  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4591ra implements Collection {
    public final /* synthetic */ C4931ta F;

    public C4591ra(C4931ta taVar) {
        this.F = taVar;
    }

    @Override // java.util.Collection
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        this.F.clear();
    }

    public boolean contains(Object obj) {
        return this.F.g(obj) >= 0;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return this.F.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new C4761sa(this.F);
    }

    public boolean remove(Object obj) {
        int g = this.F.g(obj);
        if (g < 0) {
            return false;
        }
        this.F.i(g);
        return true;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        int i = this.F.L;
        int i2 = 0;
        boolean z = false;
        while (i2 < i) {
            if (collection.contains(this.F.k(i2))) {
                this.F.i(i2);
                i2--;
                i--;
                z = true;
            }
            i2++;
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection collection) {
        int i = this.F.L;
        int i2 = 0;
        boolean z = false;
        while (i2 < i) {
            if (!collection.contains(this.F.k(i2))) {
                this.F.i(i2);
                i2--;
                i--;
                z = true;
            }
            i2++;
        }
        return z;
    }

    public int size() {
        return this.F.L;
    }

    public Object[] toArray() {
        int i = this.F.L;
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = this.F.k(i2);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return this.F.n(objArr, 1);
    }
}
