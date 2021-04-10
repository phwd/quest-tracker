package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.util.Map;

/* renamed from: X.0qv  reason: invalid class name */
public final class AnonymousClass0qv {
    public final C04650qu[] A00;

    public AnonymousClass0qv(Map<AnonymousClass0qp, JsonSerializer<Object>> map) {
        int size = map.size();
        int i = 8;
        while (i < (size > 64 ? size + (size >> 2) : size + size)) {
            i += i;
        }
        int i2 = i - 1;
        C04650qu[] r6 = new C04650qu[i];
        for (Map.Entry<AnonymousClass0qp, JsonSerializer<Object>> entry : map.entrySet()) {
            AnonymousClass0qp key = entry.getKey();
            int hashCode = key.hashCode() & i2;
            r6[hashCode] = new C04650qu(r6[hashCode], key, entry.getValue());
        }
        this.A00 = r6;
    }

    public final JsonSerializer<Object> A00(AnonymousClass0qp r4) {
        int hashCode = r4.hashCode();
        C04650qu[] r1 = this.A00;
        C04650qu r2 = r1[hashCode & (r1.length - 1)];
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
