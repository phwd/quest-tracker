package X;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;

/* renamed from: X.173  reason: invalid class name */
public class AnonymousClass173 extends LinkedHashMap<AnonymousClass17K, SSLSession> {
    public final /* synthetic */ AnonymousClass16J this$0;

    public AnonymousClass173(AnonymousClass16J r1) {
        this.this$0 = r1;
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry<AnonymousClass17K, SSLSession> entry) {
        if (this.this$0.A02 <= 0 || size() <= this.this$0.A02) {
            return false;
        }
        remove(entry.getKey());
        AnonymousClass10j r1 = this.this$0.A00;
        if (r1 == null) {
            return false;
        }
        r1.A00(entry.getKey().A01);
        return false;
    }
}
