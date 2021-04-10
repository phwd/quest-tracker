package X;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.Aj  reason: case insensitive filesystem */
public final class C0046Aj {
    public final HashMap<String, Af> A00 = new HashMap<>();

    public final void A00() {
        HashMap<String, Af> hashMap = this.A00;
        for (Af af : hashMap.values()) {
            af.A01 = true;
            Map<String, Object> map = af.A00;
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
            af.A00();
        }
        hashMap.clear();
    }
}
