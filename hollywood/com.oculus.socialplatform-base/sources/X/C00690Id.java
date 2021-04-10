package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0Id  reason: invalid class name and case insensitive filesystem */
public final class C00690Id<K, V> extends HashMap<K, V> {
    public static <K, V> Map<K, V> A00(K k, V v) {
        HashMap hashMap = new HashMap(1);
        hashMap.put(k, v);
        return Collections.unmodifiableMap(hashMap);
    }

    public static <K, V> Map<K, V> A01(K k, V v, K k2, V v2) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(k, v);
        hashMap.put(k2, v2);
        return Collections.unmodifiableMap(hashMap);
    }

    public C00690Id(Map<? extends K, ? extends V> map) {
        super(map);
    }
}
