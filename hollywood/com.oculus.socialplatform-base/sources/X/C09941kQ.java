package X;

import com.facebook.common.time.RealtimeSinceBootClock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: X.1kQ  reason: invalid class name and case insensitive filesystem */
public final class C09941kQ extends AbstractC10031kh<AnonymousClass1lJ> {
    public int A00;
    public final AnonymousClass0K8 A01;
    public final ExecutorService A02 = Executors.newFixedThreadPool(3);

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
        if (r4 >= 300) goto L_0x001b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.HttpURLConnection A00(X.C09941kQ r5, android.net.Uri r6, int r7) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09941kQ.A00(X.1kQ, android.net.Uri, int):java.net.HttpURLConnection");
    }

    public C09941kQ() {
        RealtimeSinceBootClock realtimeSinceBootClock = RealtimeSinceBootClock.A00;
        this.A01 = realtimeSinceBootClock;
    }

    public C09941kQ(int i) {
        RealtimeSinceBootClock realtimeSinceBootClock = RealtimeSinceBootClock.A00;
        this.A01 = realtimeSinceBootClock;
        this.A00 = 30000;
    }
}
