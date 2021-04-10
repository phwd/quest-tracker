package X;

import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: X.13z  reason: invalid class name and case insensitive filesystem */
public final class C058013z extends AbstractSet<K> {
    public final /* synthetic */ AnonymousClass142 A00;

    public C058013z(AnonymousClass142 r1) {
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
        return new AnonymousClass0e8(this);
    }

    public final boolean remove(Object obj) {
        AnonymousClass142 r2 = this.A00;
        AnonymousClass141 r1 = null;
        if (obj == null) {
            return false;
        }
        try {
            r1 = AnonymousClass142.A00(r2, obj, false);
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
