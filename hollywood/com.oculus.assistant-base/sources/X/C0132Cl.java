package X;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: X.Cl  reason: case insensitive filesystem */
public final class C0132Cl extends AbstractC1174uV<K, Collection<V>> {
    public final /* synthetic */ C1177uY A00;

    public C0132Cl(C1177uY uYVar) {
        this.A00 = uYVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        Set keySet = this.A00.A00.keySet();
        return new C1172uT(keySet.iterator(), new C1176uX(this));
    }

    @Override // X.AbstractC1174uV
    public final boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        C1177uY uYVar = this.A00;
        uYVar.A00.keySet().remove(((Map.Entry) obj).getKey());
        return true;
    }
}
