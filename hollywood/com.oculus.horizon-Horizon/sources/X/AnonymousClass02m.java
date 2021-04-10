package X;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.02m  reason: invalid class name */
public final class AnonymousClass02m implements Collection<V> {
    public final /* synthetic */ AnonymousClass02n A00;

    public AnonymousClass02m(AnonymousClass02n r1) {
        this.A00 = r1;
    }

    @Override // java.util.Collection
    public final boolean add(V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection<? extends V> collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        this.A00.A07();
    }

    public final boolean contains(Object obj) {
        if (this.A00.A03(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final boolean isEmpty() {
        if (this.A00.A01() == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator<V> iterator() {
        return new AnonymousClass02i(this.A00, 1);
    }

    public final boolean remove(Object obj) {
        AnonymousClass02n r1 = this.A00;
        int A03 = r1.A03(obj);
        if (A03 < 0) {
            return false;
        }
        r1.A08(A03);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection<?> collection) {
        AnonymousClass02n r5 = this.A00;
        int A01 = r5.A01();
        int i = 0;
        boolean z = false;
        while (i < A01) {
            if (collection.contains(r5.A04(i, 1))) {
                r5.A08(i);
                i--;
                A01--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection<?> collection) {
        AnonymousClass02n r5 = this.A00;
        int A01 = r5.A01();
        int i = 0;
        boolean z = false;
        while (i < A01) {
            if (!collection.contains(r5.A04(i, 1))) {
                r5.A08(i);
                i--;
                A01--;
                z = true;
            }
            i++;
        }
        return z;
    }

    public final int size() {
        return this.A00.A01();
    }

    @Override // java.util.Collection
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
        AnonymousClass02n r5 = this.A00;
        int A01 = r5.A01();
        Object[] objArr = new Object[A01];
        for (int i = 0; i < A01; i++) {
            objArr[i] = r5.A04(i, 1);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final <T> T[] toArray(T[] tArr) {
        return (T[]) this.A00.A0A(tArr, 1);
    }
}
