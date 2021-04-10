package defpackage;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: na  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3908na implements Set {
    public final /* synthetic */ C4931ta F;

    public C3908na(C4931ta taVar) {
        this.F = taVar;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        int i = this.F.L;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            this.F.put(entry.getKey(), entry.getValue());
        }
        return i != this.F.L;
    }

    public void clear() {
        this.F.clear();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        int e = this.F.e(entry.getKey());
        if (e < 0) {
            return false;
        }
        return AbstractC0179Cy.c(this.F.k(e), entry.getValue());
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
        return C4931ta.l(this, obj);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = this.F.L - 1; i2 >= 0; i2--) {
            Object h = this.F.h(i2);
            Object k = this.F.k(i2);
            i += (h == null ? 0 : h.hashCode()) ^ (k == null ? 0 : k.hashCode());
        }
        return i;
    }

    public boolean isEmpty() {
        return this.F.isEmpty();
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public Iterator iterator() {
        return new C4421qa(this.F);
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
        return this.F.L;
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray(Object[] objArr) {
        throw new UnsupportedOperationException();
    }
}
