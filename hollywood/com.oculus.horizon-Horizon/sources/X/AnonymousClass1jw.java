package X;

import com.facebook.common.time.RealtimeSinceBootClock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: X.1jw  reason: invalid class name */
public final class AnonymousClass1jw extends AnonymousClass1k9<AnonymousClass1k0> {
    public int A00;
    public final AnonymousClass0LE A01;
    public final ExecutorService A02 = Executors.newFixedThreadPool(3);

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
        if (r5 >= 300) goto L_0x001b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.HttpURLConnection A00(X.AnonymousClass1jw r7, android.net.Uri r8, int r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 188
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1jw.A00(X.1jw, android.net.Uri, int):java.net.HttpURLConnection");
    }

    public AnonymousClass1jw() {
        RealtimeSinceBootClock realtimeSinceBootClock = RealtimeSinceBootClock.A00;
        this.A01 = realtimeSinceBootClock;
    }

    public AnonymousClass1jw(int i) {
        RealtimeSinceBootClock realtimeSinceBootClock = RealtimeSinceBootClock.A00;
        this.A01 = realtimeSinceBootClock;
        this.A00 = 30000;
    }
}
