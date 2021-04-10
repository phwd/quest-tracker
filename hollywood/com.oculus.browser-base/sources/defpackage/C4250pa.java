package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/* renamed from: pa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4250pa implements Set {
    public final /* synthetic */ C4931ta F;

    public C4250pa(C4931ta taVar) {
        this.F = taVar;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        this.F.clear();
    }

    public boolean contains(Object obj) {
        return this.F.e(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        C4931ta taVar = this.F;
        Objects.requireNonNull(taVar);
        for (Object obj : collection) {
            if (!taVar.containsKey(obj)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        return C4931ta.l(this, obj);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = this.F.L - 1; i2 >= 0; i2--) {
            Object h = this.F.h(i2);
            i += h == null ? 0 : h.hashCode();
        }
        return i;
    }

    public boolean isEmpty() {
        return this.F.isEmpty();
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return new C4079oa(this.F);
    }

    public boolean remove(Object obj) {
        int e = this.F.e(obj);
        if (e < 0) {
            return false;
        }
        this.F.i(e);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection collection) {
        C4931ta taVar = this.F;
        int i = taVar.L;
        for (Object obj : collection) {
            taVar.remove(obj);
        }
        return i != taVar.L;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        return this.F.m(collection);
    }

    public int size() {
        return this.F.L;
    }

    public Object[] toArray() {
        int i = this.F.L;
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = this.F.h(i2);
        }
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray(Object[] objArr) {
        return this.F.n(objArr, 0);
    }
}
