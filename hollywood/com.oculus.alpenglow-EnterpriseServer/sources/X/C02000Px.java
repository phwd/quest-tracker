package X;

/* renamed from: X.0Px  reason: invalid class name and case insensitive filesystem */
public final class C02000Px implements AbstractC05850lW {
    public final AnonymousClass0Qs A00;

    public C02000Px(AnonymousClass0Qs r1) {
        this.A00 = r1;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r24v0, types: [java.net.Socket, javax.net.ssl.SSLSocket] */
    /* JADX WARN: Type inference failed for: r0v85, types: [X.0hG] */
    /* JADX WARN: Type inference failed for: r24v1 */
    /* JADX WARN: Type inference failed for: r24v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0250, code lost:
        X.C01990Pw.A00(r5, r0, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0257, code lost:
        if (r0 != null) goto L_0x0263;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0259, code lost:
        r5.A06 = X.AnonymousClass0kh.HTTP_1_1;
        r5.A04 = r5.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0263, code lost:
        r26 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
        r8 = r5.A03;
        r0 = r0.A09;
        r1 = r0.A02;
        r24 = (javax.net.ssl.SSLSocket) r0.createSocket(r8, r1, r0.A00, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r9 = r4.A00;
        r7 = r4.A03;
        r10 = r7.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0284, code lost:
        if (r9 >= r10) goto L_0x0609;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0286, code lost:
        r8 = r7.get(r9);
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0294, code lost:
        if (r8.A01(r24) == false) goto L_0x0284;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0296, code lost:
        r4.A00 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x029c, code lost:
        if (r9 >= r7.size()) goto L_0x02b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02a8, code lost:
        if (r7.get(r9).A01(r24) == false) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02ab, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02ae, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02b0, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02b1, code lost:
        r4.A02 = r0;
        X.AbstractC05620k9.A00.A05(r8, r24, r4.A01);
        r0 = r8.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02be, code lost:
        if (r0 == false) goto L_0x02c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02c0, code lost:
        X.C04670hG.A00.A08(r24, r1, r0.A02);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02c9, code lost:
        r24.startHandshake();
        r8 = r24.getSession();
        r0 = r8.getCipherSuite();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02d4, code lost:
        if (r0 == null) goto L_0x05f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02d6, code lost:
        r10 = X.C06320mZ.A00(r0);
        r0 = r8.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02de, code lost:
        if (r0 == null) goto L_0x0601;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02e0, code lost:
        r9 = X.EnumC05630kA.forJavaName(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:?, code lost:
        r0 = r8.getPeerCertificates();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02e8, code lost:
        if (r0 == null) goto L_0x02ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02ea, code lost:
        r7 = X.C05570jz.A05(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0638, code lost:
        throw new java.net.UnknownServiceException("Unable to find acceptable protocols. isFallback=" + r4.A01 + ", modes=" + r7 + ", supported protocols=" + java.util.Arrays.toString(r24.getEnabledProtocols()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0639, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x063a, code lost:
        r26 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x063d, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0642, code lost:
        if (X.C05570jz.A08(r1) == false) goto L_0x064a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0649, code lost:
        throw new java.io.IOException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x064a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x064b, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x064c, code lost:
        r24 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x064f, code lost:
        r1 = th;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0650, code lost:
        if (r24 != 0) goto L_0x0652;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0652, code lost:
        X.C04670hG.A00.A07(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x0659, code lost:
        X.C05570jz.A07(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:441:?, code lost:
        throw new java.lang.IllegalStateException("tlsVersion == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:442:?, code lost:
        throw new java.lang.IllegalStateException("cipherSuite == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:445:?, code lost:
        throw new java.io.IOException("TLS tunnel buffered too many bytes!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:446:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01b7, code lost:
        if (r0 == -1) goto L_0x01b9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0644 A[Catch:{ all -> 0x064b }] */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x064a A[Catch:{ all -> 0x064b }] */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0652  */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x06e6 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @Override // X.AbstractC05850lW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C05660kD A5K(X.AnonymousClass0PX r43) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1962
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02000Px.A5K(X.0PX):X.0kD");
    }
}
