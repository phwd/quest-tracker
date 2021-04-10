package X;

import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.Nz  reason: case insensitive filesystem */
public final class C0261Nz extends LinkedHashMap<String, String> {
    public static final C0261Nz A00 = new C0261Nz();

    public C0261Nz() {
        super(100, 0.8f, true);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.util.Map$Entry] */
    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry<String, String> entry) {
        if (size() > 100) {
            return true;
        }
        return false;
    }
}
