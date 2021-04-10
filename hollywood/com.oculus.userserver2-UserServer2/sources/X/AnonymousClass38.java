package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* renamed from: X.38  reason: invalid class name */
public final class AnonymousClass38 implements Set<K> {
    public final /* synthetic */ AnonymousClass3B A00;

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

    public AnonymousClass38(AnonymousClass3B r1) {
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
        this.A00.A04();
    }

    public final boolean contains(Object obj) {
        if (this.A00.A01(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<?> collection) {
        AnonymousClass3B r1 = this.A00;
        if (!(r1 instanceof UA)) {
            UB ub = ((UC) r1).A00;
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!ub.containsKey(it.next())) {
                    return false;
                }
            }
            return true;
        }
        throw new UnsupportedOperationException("not a map");
    }

    public final int hashCode() {
        int hashCode;
        AnonymousClass3B r4 = this.A00;
        int i = 0;
        for (int A002 = r4.A00() - 1; A002 >= 0; A002--) {
            Object A03 = r4.A03(A002, 0);
            if (A03 == null) {
                hashCode = 0;
            } else {
                hashCode = A03.hashCode();
            }
            i += hashCode;
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
    public final Iterator<K> iterator() {
        return new AnonymousClass36(this.A00, 0);
    }

    public final boolean remove(Object obj) {
        AnonymousClass3B r1 = this.A00;
        int A01 = r1.A01(obj);
        if (A01 < 0) {
            return false;
        }
        r1.A05(A01);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection<?> collection) {
        AnonymousClass3B r1 = this.A00;
        if (!(r1 instanceof UA)) {
            UB ub = ((UC) r1).A00;
            int size = ub.size();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                ub.remove(it.next());
            }
            if (size != ub.size()) {
                return true;
            }
            return false;
        }
        throw new UnsupportedOperationException("not a map");
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<?> collection) {
        AnonymousClass3B r1 = this.A00;
        if (!(r1 instanceof UA)) {
            UB ub = ((UC) r1).A00;
            int size = ub.size();
            Iterator it = ub.keySet().iterator();
            while (it.hasNext()) {
                if (!collection.contains(it.next())) {
                    it.remove();
                }
            }
            if (size != ub.size()) {
                return true;
            }
            return false;
        }
        throw new UnsupportedOperationException("not a map");
    }

    public final int size() {
        return this.A00.A00();
    }

    public final Object[] toArray() {
        AnonymousClass3B r5 = this.A00;
        int A002 = r5.A00();
        Object[] objArr = new Object[A002];
        for (int i = 0; i < A002; i++) {
            objArr[i] = r5.A03(i, 0);
        }
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        return (T[]) this.A00.A06(tArr, 0);
    }
}
