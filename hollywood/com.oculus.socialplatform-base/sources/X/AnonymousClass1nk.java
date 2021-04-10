package X;

import android.util.Base64;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.net.ssl.SSLSession;

/* renamed from: X.1nk  reason: invalid class name */
public class AnonymousClass1nk extends LinkedHashMap<C10541nl, SSLSession> {
    public final /* synthetic */ AnonymousClass1nm this$0;

    public AnonymousClass1nk(AnonymousClass1nm r1) {
        this.this$0 = r1;
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry<C10541nl, SSLSession> entry) {
        if (this.this$0.A02 <= 0 || size() <= this.this$0.A02) {
            return false;
        }
        remove(entry.getKey());
        AnonymousClass1RM r1 = this.this$0.A00;
        if (r1 == null) {
            return false;
        }
        new File(AnonymousClass006.A09(r1.A00, "/", Base64.encodeToString(entry.getKey().A01, 0))).delete();
        return false;
    }
}
