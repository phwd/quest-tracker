package X;

import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: X.0Db  reason: invalid class name and case insensitive filesystem */
public final class C01040Db extends AbstractSet<K> {
    public final /* synthetic */ C01100Dk A00;

    public C01040Db(C01100Dk r1) {
        this.A00 = r1;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new AnonymousClass0X7(this);
    }

    public final boolean remove(Object obj) {
        C01100Dk r2 = this.A00;
        C01090Di r1 = null;
        if (obj == null) {
            return false;
        }
        try {
            r1 = C01100Dk.A00(r2, obj, false);
        } catch (ClassCastException unused) {
        }
        if (r1 == null) {
            return false;
        }
        r2.A06(r1, true);
        return true;
    }

    public final int size() {
        return this.A00.size;
    }
}
