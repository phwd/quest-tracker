package X;

import java.util.Map;
import java.util.Set;

/* renamed from: X.Dl  reason: case insensitive filesystem */
public final class C0146Dl extends AbstractC1156tw<K, V>.Entries implements Set<Map.Entry<K, V>> {
    public final /* synthetic */ AbstractC1156tw A00;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                return size() == set.size() && containsAll(set);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0146Dl(AbstractC1156tw twVar) {
        super(twVar);
        this.A00 = twVar;
    }

    public final int hashCode() {
        return UX.A00(this);
    }
}
