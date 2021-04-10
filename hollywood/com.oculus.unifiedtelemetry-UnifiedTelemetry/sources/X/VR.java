package X;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;

public final class VR implements Iterable<AnonymousClass7P> {
    public LinkedHashMap<VO, AnonymousClass7P> A00;

    public final void A00(AnonymousClass7P r4) {
        LinkedHashMap<VO, AnonymousClass7P> linkedHashMap = this.A00;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            this.A00 = linkedHashMap;
        }
        linkedHashMap.put(new VO(r4.A00), r4);
    }

    @Override // java.lang.Iterable
    public final Iterator<AnonymousClass7P> iterator() {
        LinkedHashMap<VO, AnonymousClass7P> linkedHashMap = this.A00;
        if (linkedHashMap != null) {
            return linkedHashMap.values().iterator();
        }
        return Collections.emptyList().iterator();
    }
}
