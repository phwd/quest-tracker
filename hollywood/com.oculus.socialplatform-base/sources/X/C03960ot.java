package X;

import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.0ot  reason: invalid class name and case insensitive filesystem */
public final class C03960ot extends LinkedHashMap<String, String> {
    public static final C03960ot A00 = new C03960ot();

    public C03960ot() {
        super(100, 0.8f, true);
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry<String, String> entry) {
        if (size() > 100) {
            return true;
        }
        return false;
    }
}
