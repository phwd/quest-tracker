package X;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0z7  reason: invalid class name */
public class AnonymousClass0z7 extends AbstractSet<Map.Entry<K, V>> {
    public final /* synthetic */ C09000zB A00;

    public AnonymousClass0z7(C09000zB r1) {
        this.A00 = r1;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry) || this.A00.A05((Map.Entry) obj) == null) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new C02310aE(this);
    }

    public final boolean remove(Object obj) {
        C09000zB r1;
        C08990zA A05;
        if (!(obj instanceof Map.Entry) || (A05 = (r1 = this.A00).A05((Map.Entry) obj)) == null) {
            return false;
        }
        r1.A06(A05, true);
        return true;
    }

    public final int size() {
        return this.A00.size;
    }
}
