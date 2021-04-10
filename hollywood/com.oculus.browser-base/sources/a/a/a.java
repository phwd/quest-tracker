package a.a;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: chromium-webapk7.dex */
public final class a implements Set {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f9393a;

    public a(g gVar) {
        this.f9393a = gVar;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        int i = this.f9393a.g;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            this.f9393a.put(entry.getKey(), entry.getValue());
        }
        return i != this.f9393a.g;
    }

    public void clear() {
        this.f9393a.clear();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        int f = this.f9393a.f(entry.getKey());
        if (f < 0) {
            return false;
        }
        return h.a(this.f9393a.m(f), entry.getValue());
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

    public boolean equals(Object obj) {
        return g.c(this, obj);
    }

    public int hashCode() {
        int i;
        int i2;
        int i3 = 0;
        for (int i4 = this.f9393a.g - 1; i4 >= 0; i4--) {
            Object i5 = this.f9393a.i(i4);
            Object m = this.f9393a.m(i4);
            if (i5 == null) {
                i = 0;
            } else {
                i = i5.hashCode();
            }
            if (m == null) {
                i2 = 0;
            } else {
                i2 = m.hashCode();
            }
            i3 += i ^ i2;
        }
        return i3;
    }

    public boolean isEmpty() {
        return this.f9393a.isEmpty();
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return new d(this.f9393a);
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.f9393a.g;
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray(Object[] objArr) {
        throw new UnsupportedOperationException();
    }
}
