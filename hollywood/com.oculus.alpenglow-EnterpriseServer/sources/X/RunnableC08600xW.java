package X;

/* renamed from: X.0xW  reason: invalid class name and case insensitive filesystem */
public class RunnableC08600xW implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.rti.mqtt.protocol.DefaultMqttClientCore$1";
    public final /* synthetic */ int A00;
    public final /* synthetic */ int A01;
    public final /* synthetic */ C08610xX A02;
    public final /* synthetic */ C08840xu A03;
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

    public RunnableC08600xW(C08610xX r1, String str, int i, boolean z, C08840xu r5, int i2, boolean z2) {
        this.A02 = r1;
        this.A04 = str;
        this.A01 = i;
        this.A05 = z;
        this.A03 = r5;
        this.A00 = i2;
        this.A06 = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v19, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r1v329, types: [java.net.Socket] */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:36|37|38|39|40) */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x07f5, code lost:
        if (r20 == null) goto L_0x090c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x0810, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x0826, code lost:
        r5 = X.C09340zG.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x084e, code lost:
        r5 = new X.AnonymousClass0yR<>(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x0856, code lost:
        r0.A03 = r9.getLocalAddress();
        r0.A04 = r9.getInetAddress();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:416:?, code lost:
        r10 = r1.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x0a5f, code lost:
        r13 = r10.A00.A03;
        r9 = r0.A0G.A00;
        r20 = X.C09440zS.A01;
        r1 = android.os.SystemClock.elapsedRealtime();
        r8 = r9.A0W;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x0a73, code lost:
        switch(r13.ordinal()) {
            case 2: goto L_0x0a78;
            case 3: goto L_0x0b85;
            case 4: goto L_0x0a76;
            case 5: goto L_0x0a76;
            case 6: goto L_0x0a76;
            case 7: goto L_0x0a76;
            case 8: goto L_0x0b8c;
            case 9: goto L_0x0a76;
            case 10: goto L_0x0b93;
            case 11: goto L_0x0b9a;
            default: goto L_0x0a76;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x0a78, code lost:
        r4 = (X.AnonymousClass0yP) r10;
        r5 = r9.A0B;
        r15 = r4.A02().A01;
        r3 = X.C09070yc.A00(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:420:0x0a87, code lost:
        if (r3 != null) goto L_0x0aa3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:421:0x0a89, code lost:
        r11 = r5.A0G;
        new java.lang.Object[1][0] = r15;
        r11.A03(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:422:0x0a95, code lost:
        r6 = r4.A02().A00;
        r12 = r4.A00.A02;
        r4.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x0aa3, code lost:
        r15 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:425:0x0aa7, code lost:
        if (r8 == null) goto L_0x0b72;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:426:0x0aa9, code lost:
        r16 = r4.A03();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:427:0x0ab3, code lost:
        if ("/send_message_response".equals(r15) != false) goto L_0x0abd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:429:0x0abb, code lost:
        if ("/t_sm_rp".equals(r15) == false) goto L_0x0ad4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:430:0x0abd, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AbstractC08430xE) r8.A01.A0B.A06(X.C08560xS.class)).A00(X.EnumC08570xT.MessageSendSuccess)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:432:0x0ada, code lost:
        if ("/push_notification".equals(r15) != false) goto L_0x0ae4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:434:0x0ae2, code lost:
        if ("/t_push".equals(r15) == false) goto L_0x0afb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:435:0x0ae4, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AbstractC08430xE) r8.A01.A0B.A06(X.C08560xS.class)).A00(X.EnumC08570xT.FbnsNotificationReceived)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:437:0x0b01, code lost:
        if ("/fbns_msg".equals(r15) == false) goto L_0x0b1a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:438:0x0b03, code lost:
        ((java.util.concurrent.atomic.AtomicLong) ((X.AbstractC08430xE) r8.A01.A0B.A06(X.C08560xS.class)).A00(X.EnumC08570xT.FbnsLiteNotificationReceived)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:439:0x0b1a, code lost:
        r11 = r8.A01;
        ((java.util.concurrent.atomic.AtomicLong) ((X.AbstractC08430xE) r11.A0B.A06(X.C08560xS.class)).A00(X.EnumC08570xT.PublishReceived)).incrementAndGet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:440:0x0b33, code lost:
        if (r11.A06 == null) goto L_0x0b67;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:442:0x0b3b, code lost:
        if (X.AbstractC09080yd.A00.contains(r15) == false) goto L_0x0b67;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:443:0x0b3d, code lost:
        r11.A06.A5g("received", "PUBLISH(topic=" + r15 + ", msgId=" + r6 + ", time=" + r1 + ")");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:444:0x0b67, code lost:
        r11.A0J.A00.A0O(r15, r16, r6, r1, r20, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:446:0x0b78, code lost:
        if (r12 != X.EnumC08980yG.ACKNOWLEDGED_DELIVERY.getValue()) goto L_0x0ba4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:447:0x0b7a, code lost:
        r9.A0G.execute(new X.RunnableC08700xg(r9, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:448:0x0b85, code lost:
        ((X.AnonymousClass0yW) r10).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:449:0x0b8c, code lost:
        ((X.C09040yO) r10).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:450:0x0b93, code lost:
        ((X.C09090ye) r10).A02();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:451:0x0b9a, code lost:
        r9.A0G.execute(new X.RunnableC08900y1(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:452:0x0ba4, code lost:
        if (r8 == null) goto L_0x0bb2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:453:0x0ba6, code lost:
        r8.A01.A05.post(new X.RunnableC08360x6(r8, r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:454:0x0bb2, code lost:
        r9.A0R = android.os.SystemClock.elapsedRealtime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:455:0x0bba, code lost:
        if ((r10 instanceof X.AnonymousClass0yP) == false) goto L_0x0bf6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:456:0x0bbc, code lost:
        r1 = X.AbstractC09150yk.A00(((X.AnonymousClass0yP) r10).A02().A01);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:457:0x0bc8, code lost:
        r4 = r9.A08;
        r3 = r13.name();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:458:0x0bd2, code lost:
        if (r1.A02() == false) goto L_0x0bf3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:459:0x0bd4, code lost:
        r1 = X.AnonymousClass006.A05(" ", (java.lang.String) r1.A01());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:461:0x0bf3, code lost:
        r1 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:462:0x0bf6, code lost:
        r1 = X.C09340zG.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:463:0x0bf9, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:464:0x0bfb, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:465:0x0bfc, code lost:
        r0.A0G.A01(X.EnumC08720xi.getFromReadException(r4), X.AnonymousClass0y3.NETWORK_THREAD_LOOP, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:466:0x0c08, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:467:0x0c09, code lost:
        r0.A0G.A01(X.EnumC08720xi.getFromReadException(r4), X.AnonymousClass0y3.NETWORK_THREAD_LOOP, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:468:0x0c15, code lost:
        r0.A0G.A01(X.EnumC08720xi.getFromReadException(r4), X.AnonymousClass0y3.NETWORK_THREAD_LOOP, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:485:?, code lost:
        throw new java.io.IOException(X.AnonymousClass006.A05("Failed to connect to both sockets: ", r1.getMessage()), r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01be, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:316:0x0817 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x017d */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x0619  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x0682  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x0699  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x0788  */
    /* JADX WARNING: Removed duplicated region for block: B:311:0x0810 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:49:0x0193] */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0826  */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x084e  */
    /* JADX WARNING: Removed duplicated region for block: B:326:0x0856  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0917  */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x091e  */
    /* JADX WARNING: Removed duplicated region for block: B:376:0x0937  */
    /* JADX WARNING: Removed duplicated region for block: B:379:0x0979  */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x0986  */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x09ac  */
    /* JADX WARNING: Removed duplicated region for block: B:388:0x09e2  */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x09f0  */
    /* JADX WARNING: Removed duplicated region for block: B:390:0x09f3  */
    /* JADX WARNING: Removed duplicated region for block: B:404:0x0a2b  */
    /* JADX WARNING: Removed duplicated region for block: B:408:0x0a47 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 3170
        */
        throw new UnsupportedOperationException("Method not decompiled: X.RunnableC08600xW.run():void");
    }
}
