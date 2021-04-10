package X;

import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0fB  reason: invalid class name */
public class AnonymousClass0fB extends AnonymousClass0wW<Map.Entry<K, V>, V> {
    @Override // X.AnonymousClass0wW
    public final Object A00(Object obj) {
        return ((Map.Entry) obj).getValue();
    }

    public AnonymousClass0fB(Iterator it) {
        super(it);
    }
}
