package X;

import com.facebook.common.time.RealtimeSinceBootClock;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.hI  reason: case insensitive filesystem */
public final class C0784hI implements AbstractC00869q {
    public LinkedHashMap A00 = new LinkedHashMap();

    public final synchronized void A00(String str, C0113Ar ar) {
        this.A00.put(str, Long.valueOf(RealtimeSinceBootClock.A00.now() - ar.A03));
    }

    @Override // X.AbstractC00869q
    public final synchronized void A3u() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : this.A00.entrySet()) {
            if (sb.length() != 0) {
                sb.append(",");
            }
            sb.append((String) entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
        }
        C00879r.A00.A03(EnumC00899t.MESSAGE_BUS_LAG, sb.toString());
    }

    @Override // X.AbstractC00869q
    public final synchronized void A4F() {
        this.A00 = new LinkedHashMap();
    }
}
