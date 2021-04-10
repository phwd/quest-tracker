package X;

import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0fC  reason: invalid class name */
public class AnonymousClass0fC extends AnonymousClass0wW<Map.Entry<K, V>, K> {
    @Override // X.AnonymousClass0wW
    public final Object A00(Object obj) {
        return ((Map.Entry) obj).getKey();
    }

    public AnonymousClass0fC(Iterator it) {
        super(it);
    }
}
