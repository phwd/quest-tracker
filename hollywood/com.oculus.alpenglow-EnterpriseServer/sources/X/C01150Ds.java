package X;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.0Ds  reason: invalid class name and case insensitive filesystem */
public final class C01150Ds {
    public final HashMap<String, AnonymousClass0Do> A00 = new HashMap<>();

    public final void A00() {
        HashMap<String, AnonymousClass0Do> hashMap = this.A00;
        for (AnonymousClass0Do r4 : hashMap.values()) {
            r4.A01 = true;
            Map<String, Object> map = r4.A00;
            if (map != null) {
                synchronized (map) {
                    for (Object obj : map.values()) {
                        if (obj instanceof Closeable) {
                            try {
                                ((Closeable) obj).close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
            r4.A00();
        }
        hashMap.clear();
    }
}
