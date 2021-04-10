package X;

import java.util.Iterator;
import java.util.Map;

/* renamed from: X.3E  reason: invalid class name */
public final class AnonymousClass3E extends AnonymousClass2a {
    public static final void A01(Iterable iterable, Map map) {
        C0514bB.A02(iterable, "$this$toMap");
        C0514bB.A02(map, "destination");
        C0514bB.A02(map, "$this$putAll");
        C0514bB.A02(iterable, "pairs");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            C0467aC aCVar = (C0467aC) it.next();
            map.put(aCVar.first, aCVar.second);
        }
    }
}
