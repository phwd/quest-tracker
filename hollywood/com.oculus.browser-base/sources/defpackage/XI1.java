package defpackage;

/* renamed from: XI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XI1 implements Runnable {
    public final FI1 F;

    public XI1(FI1 fi1) {
        this.F = fi1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        if (android.util.Log.isLoggable("MessengerIpcClient", 3) == false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        java.lang.String.valueOf(r1).length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r3 = r0.f.f11335a;
        r4 = r0.b;
        r5 = android.os.Message.obtain();
        r5.what = r1.c;
        r5.arg1 = r1.f11733a;
        r5.replyTo = r4;
        r4 = new android.os.Bundle();
        r4.putBoolean("oneWay", false);
        r4.putString("pkg", r3.getPackageName());
        r4.putBundle("data", r1.d);
        r5.setData(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1 = r0.c;
        r3 = r1.f10130a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007d, code lost:
        if (r3 == null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007f, code lost:
        r3.send(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0084, code lost:
        r1 = r1.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0086, code lost:
        if (r1 == null) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0088, code lost:
        r1 = r1.F;
        java.util.Objects.requireNonNull(r1);
        r1.send(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0099, code lost:
        throw new java.lang.IllegalStateException("Both messengers are null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009b, code lost:
        r0.a(2, r1.getMessage());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 167
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.XI1.run():void");
    }
}
