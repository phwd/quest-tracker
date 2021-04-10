package X;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* renamed from: X.0qE  reason: invalid class name */
public final class AnonymousClass0qE implements Iterable<AnonymousClass0Cr> {
    public LinkedHashMap<C04470qI, AnonymousClass0Cr> A00;

    public final void A00(AnonymousClass0Cr r4) {
        LinkedHashMap<C04470qI, AnonymousClass0Cr> linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            this.A00 = linkedHashMap;
        }
        linkedHashMap.put(new C04470qI(r4.A00), r4);
    }

    @Override // java.lang.Iterable
    public final Iterator<AnonymousClass0Cr> iterator() {
        LinkedHashMap<C04470qI, AnonymousClass0Cr> linkedHashMap = this.A00;
        if (linkedHashMap != null) {
            return linkedHashMap.values().iterator();
        }
        return Collections.emptyList().iterator();
    }
}
