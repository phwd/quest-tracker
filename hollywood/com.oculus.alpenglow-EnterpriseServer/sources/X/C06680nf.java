package X;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* renamed from: X.0nf  reason: invalid class name and case insensitive filesystem */
public final class C06680nf implements Iterable<AnonymousClass0EC> {
    public LinkedHashMap<C06710nj, AnonymousClass0EC> A00;

    public final void A00(AnonymousClass0EC r4) {
        LinkedHashMap<C06710nj, AnonymousClass0EC> linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            this.A00 = linkedHashMap;
        }
        linkedHashMap.put(new C06710nj(r4.A00), r4);
    }

    @Override // java.lang.Iterable
    public final Iterator<AnonymousClass0EC> iterator() {
        LinkedHashMap<C06710nj, AnonymousClass0EC> linkedHashMap = this.A00;
        if (linkedHashMap != null) {
            return linkedHashMap.values().iterator();
        }
        return Collections.emptyList().iterator();
    }
}
