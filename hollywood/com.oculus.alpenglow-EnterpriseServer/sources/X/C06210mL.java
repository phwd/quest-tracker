package X;

import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.0mL  reason: invalid class name and case insensitive filesystem */
public final class C06210mL extends LinkedHashMap<String, String> {
    public static final C06210mL A00 = new C06210mL();

    public C06210mL() {
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
