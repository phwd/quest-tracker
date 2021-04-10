package X;

import com.facebook.infer.annotation.NullsafeStrict;
import java.util.HashMap;
import java.util.Map;

@NullsafeStrict
/* renamed from: X.0p4  reason: invalid class name */
public class AnonymousClass0p4 {
    public final Map<String, Object> A00 = new HashMap(4);
    public volatile boolean A01;
    public volatile boolean A02 = false;
    public final /* synthetic */ C03850of A03;

    public static final synchronized void A00(AnonymousClass0p4 r1) {
        synchronized (r1) {
            r1.A02 = false;
        }
    }

    public AnonymousClass0p4(C03850of r3) {
        this.A03 = r3;
    }
}
