package X;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.2m  reason: invalid class name */
public final class AnonymousClass2m implements Collection<V> {
    public final /* synthetic */ AnonymousClass2n A00;

    public AnonymousClass2m(AnonymousClass2n r1) {
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
        this.A00.A06();
    }

    public final boolean contains(Object obj) {
        if (this.A00.A02(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final boolean isEmpty() {
        if (this.A00.A00() == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator<V> iterator() {
        return new AnonymousClass2i(this.A00, 1);
    }

    public final boolean remove(Object obj) {
        AnonymousClass2n r1 = this.A00;
        int A02 = r1.A02(obj);
        if (A02 < 0) {
            return false;
        }
        r1.A07(A02);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection<?> collection) {
        AnonymousClass2n r5 = this.A00;
        int A002 = r5.A00();
        int i = 0;
        boolean z = false;
        while (i < A002) {
            if (collection.contains(r5.A03(i, 1))) {
                r5.A07(i);
                i--;
                A002--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection<?> collection) {
        AnonymousClass2n r5 = this.A00;
        int A002 = r5.A00();
        int i = 0;
        boolean z = false;
        while (i < A002) {
            if (!collection.contains(r5.A03(i, 1))) {
                r5.A07(i);
                i--;
                A002--;
                z = true;
            }
            i++;
        }
        return z;
    }

    public final int size() {
        return this.A00.A00();
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
        AnonymousClass2n r5 = this.A00;
        int A002 = r5.A00();
        Object[] objArr = new Object[A002];
        for (int i = 0; i < A002; i++) {
            objArr[i] = r5.A03(i, 1);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final <T> T[] toArray(T[] tArr) {
        return (T[]) this.A00.A09(tArr, 1);
    }
}
