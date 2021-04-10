package X;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;

public final class PD implements Iterable {
    public LinkedHashMap A00;

    public final void A00(AnonymousClass0q r4) {
        LinkedHashMap linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap();
            this.A00 = linkedHashMap;
        }
        linkedHashMap.put(new PI(r4.A00), r4);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        LinkedHashMap linkedHashMap = this.A00;
        if (linkedHashMap != null) {
            return linkedHashMap.values().iterator();
        }
        return Collections.emptyList().iterator();
    }
}
