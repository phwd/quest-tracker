package X;

/* renamed from: X.1jx  reason: invalid class name */
public class AnonymousClass1jx implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$1";
    public final /* synthetic */ AnonymousClass1k0 A00;
    public final /* synthetic */ AnonymousClass1jw A01;
    public final /* synthetic */ AnonymousClass1pX A02;

    public AnonymousClass1jx(AnonymousClass1jw r1, AnonymousClass1k0 r2, AnonymousClass1pX r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0027, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
        r2 = null;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003d A[SYNTHETIC, Splitter:B:22:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            X.1jw r6 = r7.A01
            X.1k0 r5 = r7.A00
            X.1pX r4 = r7.A02
            r3 = 0
            X.1qU r0 = r5.A04     // Catch:{ IOException -> 0x0027, all -> 0x0039 }
            X.1pd r0 = r0.A07     // Catch:{ IOException -> 0x0027, all -> 0x0039 }
            android.net.Uri r1 = r0.A03     // Catch:{ IOException -> 0x0027, all -> 0x0039 }
            r0 = 5
            java.net.HttpURLConnection r2 = X.AnonymousClass1jw.A00(r6, r1, r0)     // Catch:{ IOException -> 0x0027, all -> 0x0039 }
            X.0LE r0 = r6.A01     // Catch:{ IOException -> 0x0025 }
            long r0 = r0.now()     // Catch:{ IOException -> 0x0025 }
            r5.A01 = r0     // Catch:{ IOException -> 0x0025 }
            if (r2 == 0) goto L_0x0045
            java.io.InputStream r3 = r2.getInputStream()     // Catch:{ IOException -> 0x0025 }
            r0 = -1
            r4.A01(r3, r0)     // Catch:{ IOException -> 0x0025 }
            goto L_0x003b
        L_0x0025:
            r0 = move-exception
            goto L_0x0029
        L_0x0027:
            r0 = move-exception
            r2 = r3
        L_0x0029:
            r4.A02(r0)     // Catch:{ all -> 0x002d }
            goto L_0x003b
        L_0x002d:
            r0 = move-exception
            if (r3 == 0) goto L_0x0033
            r3.close()     // Catch:{ IOException -> 0x0033 }
        L_0x0033:
            if (r2 == 0) goto L_0x003a
            r2.disconnect()
            throw r0
        L_0x0039:
            r0 = move-exception
        L_0x003a:
            throw r0
        L_0x003b:
            if (r3 == 0) goto L_0x0040
            r3.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            if (r2 == 0) goto L_0x0045
            r2.disconnect()
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1jx.run():void");
    }
}
