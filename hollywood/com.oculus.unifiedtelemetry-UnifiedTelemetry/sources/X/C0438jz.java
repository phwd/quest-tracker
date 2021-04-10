package X;

import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.jz  reason: case insensitive filesystem */
public final class C0438jz extends LinkedHashMap<String, String> {
    public static final C0438jz A00 = new C0438jz();

    public C0438jz() {
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
