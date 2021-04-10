package X;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0DU  reason: invalid class name */
public class AnonymousClass0DU extends AbstractSet<Map.Entry<K, V>> {
    public final /* synthetic */ C01100Dk A00;

    public AnonymousClass0DU(C01100Dk r1) {
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
        return new AnonymousClass0X8(this);
    }

    public final boolean remove(Object obj) {
        C01100Dk r1;
        C01090Di A05;
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
