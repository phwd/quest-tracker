package X;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* renamed from: X.0lk  reason: invalid class name and case insensitive filesystem */
public final class C05720lk implements Iterable<AnonymousClass07O> {
    public LinkedHashMap<C05760lo, AnonymousClass07O> A00;

    public final void A00(AnonymousClass07O r4) {
        LinkedHashMap<C05760lo, AnonymousClass07O> linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            this.A00 = linkedHashMap;
        }
        linkedHashMap.put(new C05760lo(r4.A00), r4);
    }

    @Override // java.lang.Iterable
    public final Iterator<AnonymousClass07O> iterator() {
        LinkedHashMap<C05760lo, AnonymousClass07O> linkedHashMap = this.A00;
        if (linkedHashMap != null) {
            return linkedHashMap.values().iterator();
        }
        return Collections.emptyList().iterator();
    }
}
