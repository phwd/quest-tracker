package X;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.0k  reason: invalid class name and case insensitive filesystem */
public final class C00050k implements Collection<V> {
    public final /* synthetic */ AbstractC00060l A00;

    public C00050k(AbstractC00060l r1) {
        this.A00 = r1;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        this.A00.A04();
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
    public final Iterator iterator() {
        return new AnonymousClass0g(this.A00, 1);
    }

    public final boolean remove(Object obj) {
        AbstractC00060l r1 = this.A00;
        int A02 = r1.A02(obj);
        if (A02 < 0) {
            return false;
        }
        r1.A05(A02);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        AbstractC00060l r5 = this.A00;
        int A002 = r5.A00();
        int i = 0;
        boolean z = false;
        while (i < A002) {
            if (collection.contains(r5.A03(i, 1))) {
                r5.A05(i);
                i--;
                A002--;
                z = true;
            }
            i++;
        }
        return z;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        AbstractC00060l r5 = this.A00;
        int A002 = r5.A00();
        int i = 0;
        boolean z = false;
        while (i < A002) {
            if (!collection.contains(r5.A03(i, 1))) {
                r5.A05(i);
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
    public final boolean containsAll(Collection collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    public final Object[] toArray() {
        AbstractC00060l r5 = this.A00;
        int A002 = r5.A00();
        Object[] objArr = new Object[A002];
        for (int i = 0; i < A002; i++) {
            objArr[i] = r5.A03(i, 1);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.A00.A06(objArr, 1);
    }
}
