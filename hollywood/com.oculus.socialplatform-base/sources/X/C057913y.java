package X;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.13y  reason: invalid class name and case insensitive filesystem */
public class C057913y extends AbstractSet<Map.Entry<K, V>> {
    public final /* synthetic */ AnonymousClass142 A00;

    public C057913y(AnonymousClass142 r1) {
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
        return new AnonymousClass0e9(this);
    }

    public final boolean remove(Object obj) {
        AnonymousClass142 r1;
        AnonymousClass141 A05;
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
