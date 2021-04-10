package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: X.02k  reason: invalid class name */
public final class AnonymousClass02k implements Set<K> {
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

    public AnonymousClass02k(AnonymousClass02n r1) {
        this.A00 = r1;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(K k) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection<? extends K> collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        this.A00.A07();
    }

    public final boolean contains(Object obj) {
        if (this.A00.A02(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<?> collection) {
        Map A06 = this.A00.A06();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!A06.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int hashCode;
        AnonymousClass02n r4 = this.A00;
        int i = 0;
        for (int A01 = r4.A01() - 1; A01 >= 0; A01--) {
            Object A04 = r4.A04(A01, 0);
            if (A04 == null) {
                hashCode = 0;
            } else {
                hashCode = A04.hashCode();
            }
            i += hashCode;
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
    public final Iterator<K> iterator() {
        return new AnonymousClass02i(this.A00, 0);
    }

    public final boolean remove(Object obj) {
        AnonymousClass02n r1 = this.A00;
        int A02 = r1.A02(obj);
        if (A02 < 0) {
            return false;
        }
        r1.A08(A02);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        Map A06 = this.A00.A06();
        int size = A06.size();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            A06.remove(it.next());
        }
        if (size != A06.size()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<?> collection) {
        return AnonymousClass02n.A00(this.A00.A06(), collection);
    }

    public final int size() {
        return this.A00.A01();
    }

    public final Object[] toArray() {
        AnonymousClass02n r5 = this.A00;
        int A01 = r5.A01();
        Object[] objArr = new Object[A01];
        for (int i = 0; i < A01; i++) {
            objArr[i] = r5.A04(i, 0);
        }
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        return (T[]) this.A00.A0A(tArr, 0);
    }
}
