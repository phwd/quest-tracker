package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.util.Map;

/* renamed from: X.0oP  reason: invalid class name */
public final class AnonymousClass0oP {
    public final C06920oO[] A00;

    public AnonymousClass0oP(Map<C06880oJ, JsonSerializer<Object>> map) {
        int size = map.size();
        int i = 8;
        while (i < (size > 64 ? size + (size >> 2) : size + size)) {
            i += i;
        }
        int i2 = i - 1;
        C06920oO[] r6 = new C06920oO[i];
        for (Map.Entry<C06880oJ, JsonSerializer<Object>> entry : map.entrySet()) {
            C06880oJ key = entry.getKey();
            int hashCode = key.hashCode() & i2;
            r6[hashCode] = new C06920oO(r6[hashCode], key, entry.getValue());
        }
        this.A00 = r6;
    }

    public final JsonSerializer<Object> A00(C06880oJ r4) {
        int hashCode = r4.hashCode();
        C06920oO[] r1 = this.A00;
        C06920oO r2 = r1[hashCode & (r1.length - 1)];
        if (r2 == null) {
            return null;
        }
        while (!r4.equals(r2.A01)) {
            r2 = r2.A02;
            if (r2 == null) {
            }
        }
        return r2.A00;
        return null;
    }
}
