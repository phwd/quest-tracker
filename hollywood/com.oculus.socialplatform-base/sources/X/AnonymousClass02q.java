package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: X.02q  reason: invalid class name */
public final class AnonymousClass02q implements Set<Map.Entry<K, V>> {
    public final /* synthetic */ AnonymousClass02u A00;

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

    public AnonymousClass02q(AnonymousClass02u r1) {
        this.A00 = r1;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
        AnonymousClass02u r4 = this.A00;
        int A002 = r4.A00();
        Iterator<? extends Map.Entry<K, V>> it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            r4.A08(entry.getKey(), entry.getValue());
        }
        if (A002 != r4.A00()) {
            return true;
        }
        return false;
    }

    public final void clear() {
        this.A00.A06();
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            AnonymousClass02u r2 = this.A00;
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
        AnonymousClass02u r6 = this.A00;
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
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new AnonymousClass02s(this.A00);
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.A00.A00();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        throw new UnsupportedOperationException();
    }
}
