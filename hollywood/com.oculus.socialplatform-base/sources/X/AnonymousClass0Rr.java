package X;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.0Rr  reason: invalid class name */
public final class AnonymousClass0Rr<T> extends AbstractCollection<T> implements Set<T> {
    public static final Object A04 = new Object();
    @GuardedBy("mItems")
    public int A00 = 0;
    public final AnonymousClass0lg A01;
    public final int[] A02;
    public final Object[] A03;

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<T> iterator() {
        return new AnonymousClass0Rq(this);
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.A03.length;
    }

    public AnonymousClass0Rr(AnonymousClass0lg r2, int[] iArr) {
        this.A01 = r2.getScopeAwareInjector();
        this.A02 = iArr;
        this.A03 = new Object[iArr.length];
    }

    public final boolean contains(Object obj) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E next = it.next();
            if (obj == null) {
                if (next == null) {
                    return true;
                }
            } else if (obj.equals(next)) {
                return true;
            }
        }
        return false;
    }
}
