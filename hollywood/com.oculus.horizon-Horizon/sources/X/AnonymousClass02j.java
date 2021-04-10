package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: X.02j  reason: invalid class name */
public final class AnonymousClass02j implements Set<Map.Entry<K, V>> {
    public final /* synthetic */ AnonymousClass02n A00;

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

    public AnonymousClass02j(AnonymousClass02n r1) {
        this.A00 = r1;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
        AnonymousClass02n r4 = this.A00;
        int A01 = r4.A01();
        Iterator<? extends Map.Entry<K, V>> it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            r4.A09(entry.getKey(), entry.getValue());
        }
        if (A01 != r4.A01()) {
            return true;
        }
        return false;
    }

    public final void clear() {
        this.A00.A07();
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            AnonymousClass02n r2 = this.A00;
            int A02 = r2.A02(entry.getKey());
            if (A02 >= 0) {
                Object A04 = r2.A04(A02, 1);
                Object value = entry.getValue();
                if (A04 == value) {
                    return true;
                }
                if (A04 == null || !A04.equals(value)) {
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
        AnonymousClass02n r6 = this.A00;
        int i = 0;
        for (int A01 = r6.A01() - 1; A01 >= 0; A01--) {
            Object A04 = r6.A04(A01, 0);
            Object A042 = r6.A04(A01, 1);
            if (A04 == null) {
                hashCode = 0;
            } else {
                hashCode = A04.hashCode();
            }
            if (A042 == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = A042.hashCode();
            }
            i += hashCode ^ hashCode2;
        }
        return i;
    }

    public final boolean isEmpty() {
        if (this.A00.A01() == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new AnonymousClass02l(this.A00);
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
        return this.A00.A01();
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
