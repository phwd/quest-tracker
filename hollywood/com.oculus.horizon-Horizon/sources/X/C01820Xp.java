package X;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;

/* renamed from: X.0Xp  reason: invalid class name and case insensitive filesystem */
public class C01820Xp extends LinkedHashMap<C01840Xr, SSLSession> {
    public final /* synthetic */ C01850Xs this$0;

    public C01820Xp(C01850Xs r1) {
        this.this$0 = r1;
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry<C01840Xr, SSLSession> entry) {
        if (this.this$0.A02 <= 0 || size() <= this.this$0.A02) {
            return false;
        }
        remove(entry.getKey());
        C06200mh r1 = this.this$0.A00;
        if (r1 == null) {
            return false;
        }
        r1.A00(entry.getKey().A01);
        return false;
    }
}
