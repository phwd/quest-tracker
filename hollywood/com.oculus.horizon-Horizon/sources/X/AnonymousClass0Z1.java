package X;

/* renamed from: X.0Z1  reason: invalid class name */
public class AnonymousClass0Z1 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.DefaultMqttClientCore$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C05890m2 A02;
    public final /* synthetic */ AnonymousClass0ZX A03;
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
                return "UNKNOWN";
        }
    }

    public AnonymousClass0Z1(C05890m2 r1, String str, int i, boolean z, AnonymousClass0ZX r5, int i2, boolean z2) {
        this.A02 = r1;
        this.A04 = str;
        this.A01 = i;
        this.A05 = z;
        this.A03 = r5;
        this.A00 = i2;
        this.A06 = z2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0546, code lost:
        if (r9 == null) goto L_0x0657;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0561, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0577, code lost:
        r6 = X.C06530na.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x059f, code lost:
        r6 = new X.AnonymousClass0nZ<>(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x05a7, code lost:
        r0.A03 = r10.getLocalAddress();
        r0.A04 = r10.getInetAddress();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:?, code lost:
        r7 = r1.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x07ac, code lost:
        r9 = r7.A00.A03;
        r6 = r0.A0G.A00;
        r16 = X.C01610Wd.A01;
        r14 = android.os.SystemClock.elapsedRealtime();
        r5 = r6.A0W;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x07c0, code lost:
        switch(r9.ordinal()) {
            case 2: goto L_0x07c5;
            case 3: goto L_0x08ce;
            case 4: goto L_0x07c3;
            case 5: goto L_0x07c3;
            case 6: goto L_0x07c3;
            case 7: goto L_0x07c3;
            case 8: goto L_0x08d5;
            case 9: goto L_0x07c3;
            case 10: goto L_0x08dc;
            case 11: goto L_0x08e3;
            default: goto L_0x07c3;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x07c5, code lost:
        r2 = (X.C05440l8) r7;
        r3 = r6.A0B;
        r11 = r2.A02().A01;
        r1 = X.AnonymousClass0WL.A00(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x07d4, code lost:
        if (r1 != null) goto L_0x07f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x07d6, code lost:
        r8 = r3.A0G;
        new java.lang.Object[1][0] = r11;
        r8.A03(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x07e2, code lost:
        r13 = r2.A02().A00;
        r4 = r2.A00.A02;
        r2.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x07f0, code lost:
        r11 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x07f4, code lost:
        if (r5 == null) goto L_0x08bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x07f6, code lost:
        r12 = r2.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x0800, code lost:
        if ("/send_message_response".equals(r11) != false) goto L_0x080a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x0808, code lost:
        if ("/t_sm_rp".equals(r11) == false) goto L_0x0821;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x080a, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass0nJ) r5.A01.A0B.A06(X.AnonymousClass0IW.class)).A00(X.AnonymousClass0nF.MessageSendSuccess)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x0827, code lost:
        if ("/push_notification".equals(r11) != false) goto L_0x0831;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x082f, code lost:
        if ("/t_push".equals(r11) == false) goto L_0x0848;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x0831, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass0nJ) r5.A01.A0B.A06(X.AnonymousClass0IW.class)).A00(X.AnonymousClass0nF.FbnsNotificationReceived)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x084e, code lost:
        if ("/fbns_msg".equals(r11) == false) goto L_0x0867;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x0850, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass0nJ) r5.A01.A0B.A06(X.AnonymousClass0IW.class)).A00(X.AnonymousClass0nF.FbnsLiteNotificationReceived)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x0867, code lost:
        r8 = r5.A01;
        ((java.util.concurrent.atomic.AtomicLong) ((X.AnonymousClass0nJ) r8.A0B.A06(X.AnonymousClass0IW.class)).A00(X.AnonymousClass0nF.PublishReceived)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x0880, code lost:
        if (r8.A06 == null) goto L_0x08b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x0888, code lost:
        if (X.AnonymousClass0WB.A00.contains(r11) == false) goto L_0x08b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x088a, code lost:
        r3 = r8.A06;
        r2 = new java.lang.StringBuilder("PUBLISH(topic=");
        r2.append(r11);
        r2.append(", msgId=");
        r2.append(r13);
        r2.append(", time=");
        r2.append(r14);
        r2.append(")");
        r3.A5J("received", r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x08b4, code lost:
        r8.A0J.A00.A0V(r11, r12, r13, r14, r16, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x08c1, code lost:
        if (r4 != X.EnumC02170Zn.ACKNOWLEDGED_DELIVERY.getValue()) goto L_0x08ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:0x08c3, code lost:
        r6.A0G.execute(new X.AnonymousClass0ZD(r6, r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x08ce, code lost:
        ((X.C05750ln) r7).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x08d5, code lost:
        ((X.AnonymousClass0l7) r7).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x08dc, code lost:
        ((X.C05270kf) r7).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x08e3, code lost:
        r6.A0G.execute(new X.AnonymousClass0Z5(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x08ed, code lost:
        if (r5 == null) goto L_0x08fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x08ef, code lost:
        r5.A01.A05.post(new X.AnonymousClass0YW(r5, r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x08fb, code lost:
        r6.A0R = android.os.SystemClock.elapsedRealtime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x0903, code lost:
        if ((r7 instanceof X.C05440l8) == false) goto L_0x0944;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x0905, code lost:
        r1 = X.AnonymousClass0W8.A00(((X.C05440l8) r7).A02().A01);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x0911, code lost:
        r5 = r6.A08;
        r4 = new java.lang.Object[2];
        r4[0] = r9.name();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x0921, code lost:
        if (r1.A02() == false) goto L_0x0941;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x0923, code lost:
        r2 = X.AnonymousClass006.A05(" ", (java.lang.String) r1.A01());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x0941, code lost:
        r2 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x0944, code lost:
        r1 = X.C06530na.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x0947, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0948, code lost:
        r0.A0G.A01(X.EnumC01660Wk.getFromReadException(r4), X.AnonymousClass0ZP.NETWORK_THREAD_LOOP, r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:222:0x0568 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:38:0x0187 */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x0561 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:48:0x019d] */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0577  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x059f  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x05a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 2456
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Z1.run():void");
    }
}
