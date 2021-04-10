package a.a;

import java.util.Collection;
import java.util.Iterator;

/* compiled from: chromium-webapk7.dex */
public final class e implements Collection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f9396a;

    public e(g gVar) {
        this.f9396a = gVar;
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
        this.f9396a.clear();
    }

    public boolean contains(Object obj) {
        return this.f9396a.h(obj) >= 0;
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
        return this.f9396a.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new f(this.f9396a);
    }

    public boolean remove(Object obj) {
        int h = this.f9396a.h(obj);
        if (h < 0) {
            return false;
        }
        this.f9396a.j(h);
        return true;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection collection) {
        int i = this.f9396a.g;
        int i2 = 0;
        boolean z = false;
        while (i2 < i) {
            if (collection.contains(this.f9396a.m(i2))) {
                this.f9396a.j(i2);
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
        int i = this.f9396a.g;
        int i2 = 0;
        boolean z = false;
        while (i2 < i) {
            if (!collection.contains(this.f9396a.m(i2))) {
                this.f9396a.j(i2);
                i2--;
                i--;
                z = true;
            }
            i2++;
        }
        return z;
    }

    public int size() {
        return this.f9396a.g;
    }

    public Object[] toArray() {
        int i = this.f9396a.g;
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = this.f9396a.m(i2);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public Object[] toArray(Object[] objArr) {
        return this.f9396a.l(objArr, 1);
    }
}
