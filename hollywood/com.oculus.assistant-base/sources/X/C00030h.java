package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0h  reason: invalid class name and case insensitive filesystem */
public final class C00030h implements Set {
    public final /* synthetic */ AbstractC00060l A00;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                return size() == set.size() && containsAll(set);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public C00030h(AbstractC00060l r1) {
        this.A00 = r1;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        AbstractC00060l r5 = this.A00;
        int A002 = r5.A00();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (!(r5 instanceof C0627dR)) {
                ((C0625dP) r5).A00.put(key, value);
            } else {
                ((C0627dR) r5).A00.add(key);
            }
        }
        if (A002 != r5.A00()) {
            return true;
        }
        return false;
    }

    public final void clear() {
        this.A00.A04();
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            AbstractC00060l r2 = this.A00;
            int A01 = r2.A01(entry.getKey());
            if (A01 >= 0) {
                Object A03 = r2.A03(A01, 1);
                Object value = entry.getValue();
                if (A03 == value) {
                    return true;
                }
                if (A03 == null || !A03.equals(value)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        AbstractC00060l r6 = this.A00;
        int i = 0;
        for (int A002 = r6.A00() - 1; A002 >= 0; A002--) {
            Object A03 = r6.A03(A002, 0);
            Object A032 = r6.A03(A002, 1);
            if (A03 == null) {
                hashCode = 0;
            } else {
                hashCode = A03.hashCode();
            }
            if (A032 == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = A032.hashCode();
            }
            i += hashCode ^ hashCode2;
        }
        return i;
    }

    public final boolean isEmpty() {
        if (this.A00.A00() == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return new AnonymousClass0j(this.A00);
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.A00.A00();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    public final Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        throw new UnsupportedOperationException();
    }
}
