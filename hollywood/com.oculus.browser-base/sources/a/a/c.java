package a.a;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/* compiled from: chromium-webapk7.dex */
public final class c implements Set {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f9394a;

    public c(g gVar) {
        this.f9394a = gVar;
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
        this.f9394a.clear();
    }

    public boolean contains(Object obj) {
        return this.f9394a.f(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        g gVar = this.f9394a;
        Objects.requireNonNull(gVar);
        for (Object obj : collection) {
            if (!gVar.containsKey(obj)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        return g.c(this, obj);
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        for (int i3 = this.f9394a.g - 1; i3 >= 0; i3--) {
            Object i4 = this.f9394a.i(i3);
            if (i4 == null) {
                i = 0;
            } else {
                i = i4.hashCode();
            }
            i2 += i;
        }
        return i2;
    }

    public boolean isEmpty() {
        return this.f9394a.isEmpty();
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return new b(this.f9394a);
    }

    public boolean remove(Object obj) {
        int f = this.f9394a.f(obj);
        if (f < 0) {
            return false;
        }
        this.f9394a.j(f);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection collection) {
        g gVar = this.f9394a;
        int i = gVar.g;
        for (Object obj : collection) {
            gVar.remove(obj);
        }
        return i != gVar.g;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        g gVar = this.f9394a;
        int i = gVar.g;
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (!collection.contains(gVar.i(i2))) {
                gVar.j(i2);
            }
        }
        return i != gVar.g;
    }

    public int size() {
        return this.f9394a.g;
    }

    public Object[] toArray() {
        int i = this.f9394a.g;
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = this.f9394a.i(i2);
        }
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray(Object[] objArr) {
        return this.f9394a.l(objArr, 0);
    }
}
