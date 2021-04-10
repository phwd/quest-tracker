package X;

import com.facebook.acra.CrashTimeDataCollector;

/* renamed from: X.22L  reason: invalid class name */
public class AnonymousClass22L implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.DefaultMqttClientCore$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ AnonymousClass22M A02;
    public final /* synthetic */ C143022s A03;
    public final /* synthetic */ String A04;
    public final /* synthetic */ boolean A05;
    public final /* synthetic */ boolean A06;

    public static String A00(Integer num) {
        switch (num.intValue()) {
            case 1:
                return "DEFAULT";
            case 2:
                return "SEQ_PREFERRED";
            case 3:
                return "SEQ_NONPREFERRED";
            case 4:
                return "HE_PREFERRED";
            case 5:
                return "HE_NONPREFERRED";
            default:
                return CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
        }
    }

    public AnonymousClass22L(AnonymousClass22M r1, String str, int i, boolean z, C143022s r5, int i2, boolean z2) {
        this.A02 = r1;
        this.A04 = str;
        this.A01 = i;
        this.A05 = z;
        this.A03 = r5;
        this.A00 = i2;
        this.A06 = z2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0596, code lost:
        if (r20 == null) goto L_0x06a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x05b4, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x05cd, code lost:
        r6 = X.AnonymousClass1QP.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x05f3, code lost:
        r6 = new X.AnonymousClass1QL<>(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x05fd, code lost:
        r0.A02 = r9.getLocalAddress();
        r0.A03 = r9.getInetAddress();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:?, code lost:
        r6 = r1.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x07d3, code lost:
        r3 = r6.A00.A03;
        r2 = r0.A0F.A00;
        r15 = X.C143823a.A01;
        r13 = android.os.SystemClock.elapsedRealtime();
        r5 = r2.A0W;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x07e7, code lost:
        switch(r3.ordinal()) {
            case 2: goto L_0x07ec;
            case 3: goto L_0x08c0;
            case 4: goto L_0x07ea;
            case 5: goto L_0x07ea;
            case 6: goto L_0x07ea;
            case 7: goto L_0x07ea;
            case 8: goto L_0x08c7;
            case 9: goto L_0x07ea;
            case 10: goto L_0x08ce;
            case 11: goto L_0x08d5;
            default: goto L_0x07ea;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x07ec, code lost:
        r9 = (X.AnonymousClass23A) r6;
        r4 = r2.A0D;
        r11 = r9.A02().A01;
        r1 = X.AnonymousClass1N0.A00(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x07fb, code lost:
        if (r1 != null) goto L_0x0817;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x07fd, code lost:
        r10 = r4.A0F;
        new java.lang.Object[1][0] = r11;
        r10.A03(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:0x0809, code lost:
        r7 = r9.A02().A00;
        r4 = r9.A00.A02;
        r9.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x0817, code lost:
        r11 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x0819, code lost:
        if (r5 == null) goto L_0x08ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x081b, code lost:
        r12 = r9.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x0825, code lost:
        if ("/send_message_response".equals(r11) != false) goto L_0x082f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x082d, code lost:
        if ("/t_sm_rp".equals(r11) == false) goto L_0x0846;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x082f, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass1Q3) r5.A01.A0F.A04(X.AnonymousClass1Q5.class)).A00(X.AnonymousClass1QD.MessageSendSuccess)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x084c, code lost:
        if ("/push_notification".equals(r11) != false) goto L_0x0856;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x0854, code lost:
        if ("/t_push".equals(r11) == false) goto L_0x086d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x0856, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass1Q3) r5.A01.A0F.A04(X.AnonymousClass1Q5.class)).A00(X.AnonymousClass1QD.FbnsNotificationReceived)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x0873, code lost:
        if ("/fbns_msg".equals(r11) == false) goto L_0x088c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x0875, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass1Q3) r5.A01.A0F.A04(X.AnonymousClass1Q5.class)).A00(X.AnonymousClass1QD.FbnsLiteNotificationReceived)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x088c, code lost:
        r9 = r5.A01;
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass1Q3) r9.A0F.A04(X.AnonymousClass1Q5.class)).A00(X.AnonymousClass1QD.PublishReceived)).incrementAndGet();
        r10 = r9.A0A;
        X.AnonymousClass22G.A03(r10, new X.AnonymousClass234(r10, r11, r12, r13, r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x08b3, code lost:
        if (r4 != X.EnumC143322v.ACKNOWLEDGED_DELIVERY.getValue()) goto L_0x08df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x08b5, code lost:
        r2.A0H.execute(new X.AnonymousClass22e(r2, r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x08c0, code lost:
        ((X.AnonymousClass235) r6).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x08c7, code lost:
        ((X.C143722z) r6).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x08ce, code lost:
        ((X.AnonymousClass23D) r6).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x08d5, code lost:
        r2.A0H.execute(new X.RunnableC143222u(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x08df, code lost:
        if (r5 == null) goto L_0x08ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x08e1, code lost:
        r5.A01.A06.post(new X.AnonymousClass22Q(r5, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x08ed, code lost:
        r2.A0R = android.os.SystemClock.elapsedRealtime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x08f5, code lost:
        if ((r6 instanceof X.AnonymousClass23A) == false) goto L_0x092d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x08f7, code lost:
        r1 = X.AnonymousClass1QO.A00(((X.AnonymousClass23A) r6).A02().A01);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x0903, code lost:
        r4 = r2.A0A;
        r3 = r3.name();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x090d, code lost:
        if (r1.A02() == false) goto L_0x092a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x090f, code lost:
        r1 = X.AnonymousClass006.A07(org.apache.commons.cli.HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, (java.lang.String) r1.A01());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x092a, code lost:
        r1 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x092d, code lost:
        r1 = X.AnonymousClass1QP.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:388:0x0930, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x0931, code lost:
        r0.A0F.A01(X.EnumC141822g.getFromReadException(r4), X.EnumC142922r.NETWORK_THREAD_LOOP, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:390:0x093d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x093f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x0940, code lost:
        r0.A0F.A01(X.EnumC141822g.getFromReadException(r4), X.EnumC142922r.NETWORK_THREAD_LOOP, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x094c, code lost:
        r0.A0F.A01(X.EnumC141822g.getFromReadException(r4), X.EnumC142922r.NETWORK_THREAD_LOOP, r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:249:0x05bd */
    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0177 */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x05b4 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:48:0x018d] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x05cd  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x05f3  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x05fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 2458
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass22L.run():void");
    }
}
