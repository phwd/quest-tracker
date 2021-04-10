package X;

/* renamed from: X.1kZ  reason: invalid class name */
public class AnonymousClass1kZ implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$1";
    public final /* synthetic */ AnonymousClass1lJ A00;
    public final /* synthetic */ C09941kQ A01;
    public final /* synthetic */ C09951kS A02;

    public AnonymousClass1kZ(C09941kQ r1, AnonymousClass1lJ r2, C09951kS r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        r2 = null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030 A[SYNTHETIC, Splitter:B:15:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            X.1kQ r6 = r7.A01
            X.1lJ r5 = r7.A00
            X.1kS r4 = r7.A02
            r3 = 0
            X.1kv r0 = r5.A04     // Catch:{ IOException -> 0x0029, all -> 0x0027 }
            X.1kA r0 = r0.A07     // Catch:{ IOException -> 0x0029, all -> 0x0027 }
            android.net.Uri r1 = r0.A03     // Catch:{ IOException -> 0x0029, all -> 0x0027 }
            r0 = 5
            java.net.HttpURLConnection r2 = X.C09941kQ.A00(r6, r1, r0)     // Catch:{ IOException -> 0x0029, all -> 0x0027 }
            X.0K8 r0 = r6.A01     // Catch:{ IOException -> 0x0025 }
            long r0 = r0.now()     // Catch:{ IOException -> 0x0025 }
            r5.A01 = r0     // Catch:{ IOException -> 0x0025 }
            if (r2 == 0) goto L_0x0038
            java.io.InputStream r3 = r2.getInputStream()     // Catch:{ IOException -> 0x0025 }
            r0 = -1
            r4.A01(r3, r0)     // Catch:{ IOException -> 0x0025 }
            goto L_0x002e
        L_0x0025:
            r0 = move-exception
            goto L_0x002b
        L_0x0027:
            r0 = move-exception
            throw r0
        L_0x0029:
            r0 = move-exception
            r2 = r3
        L_0x002b:
            r4.A02(r0)     // Catch:{ all -> 0x0039 }
        L_0x002e:
            if (r3 == 0) goto L_0x0033
            r3.close()     // Catch:{ IOException -> 0x0033 }
        L_0x0033:
            if (r2 == 0) goto L_0x0038
            r2.disconnect()
        L_0x0038:
            return
        L_0x0039:
            r0 = move-exception
            if (r3 == 0) goto L_0x003f
            r3.close()     // Catch:{ IOException -> 0x003f }
        L_0x003f:
            if (r2 == 0) goto L_0x0044
            r2.disconnect()
        L_0x0044:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1kZ.run():void");
    }
}
