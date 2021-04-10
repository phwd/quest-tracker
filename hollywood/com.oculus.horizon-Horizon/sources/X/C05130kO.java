package X;

import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.0kO  reason: invalid class name and case insensitive filesystem */
public final class C05130kO extends LinkedHashMap<String, String> {
    public static final C05130kO A00 = new C05130kO();

    public C05130kO() {
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
