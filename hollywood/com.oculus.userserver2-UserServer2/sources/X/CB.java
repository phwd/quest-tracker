package X;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class CB {
    public final HashMap<String, C7> A00 = new HashMap<>();

    public final void A00() {
        HashMap<String, C7> hashMap = this.A00;
        for (C7 c7 : hashMap.values()) {
            c7.A01 = true;
            Map<String, Object> map = c7.A00;
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
            c7.A00();
        }
        hashMap.clear();
    }
}
