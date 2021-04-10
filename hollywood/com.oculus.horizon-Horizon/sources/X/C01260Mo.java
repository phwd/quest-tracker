package X;

/* renamed from: X.0Mo  reason: invalid class name and case insensitive filesystem */
public final class C01260Mo implements AbstractC08380wS {
    public final AnonymousClass0N1 A00;

    public C01260Mo(AnonymousClass0N1 r1) {
        this.A00 = r1;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r26v0, types: [java.net.Socket, javax.net.ssl.SSLSocket] */
    /* JADX WARN: Type inference failed for: r0v81, types: [X.0vM] */
    /* JADX WARN: Type inference failed for: r26v1 */
    /* JADX WARN: Type inference failed for: r26v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0252, code lost:
        r5.A06 = X.EnumC08350wP.HTTP_1_1;
        r5.A04 = r5.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x025c, code lost:
        r27 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r8 = r5.A03;
        r0 = r0.A0A;
        r12 = r0.A02;
        r26 = (javax.net.ssl.SSLSocket) r0.createSocket(r8, r12, r0.A00, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r1 = r4.A00;
        r8 = r4.A03;
        r0 = r8.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x027d, code lost:
        if (r1 >= r0) goto L_0x0611;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x027f, code lost:
        r9 = r8.get(r1);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x028d, code lost:
        if (r9.A01(r26) == false) goto L_0x027d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x028f, code lost:
        r4.A00 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0295, code lost:
        if (r1 >= r8.size()) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02a3, code lost:
        if (r8.get(r1).A01(r26) == false) goto L_0x02a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02a6, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02a9, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02ab, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02ac, code lost:
        r4.A02 = r0;
        X.AbstractC08180w8.A00.A05(r9, r26, r4.A01);
        r0 = r9.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02bb, code lost:
        if (r0 == false) goto L_0x02c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02bd, code lost:
        X.C07790vM.A00.A08(r26, r12, r0.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02c7, code lost:
        r26.startHandshake();
        r10 = r26.getSession();
        r0 = r10.getCipherSuite();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02d4, code lost:
        if (r0 == null) goto L_0x0601;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02d6, code lost:
        r9 = X.C08530wj.A00(r0);
        r0 = r10.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02de, code lost:
        if (r0 == null) goto L_0x0609;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02e0, code lost:
        r8 = X.EnumC08190w9.forJavaName(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r0 = r10.getPeerCertificates();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02e8, code lost:
        if (r0 == null) goto L_0x02ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02ea, code lost:
        r1 = X.C08160w5.A05(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0611, code lost:
        r1 = new java.lang.StringBuilder("Unable to find acceptable protocols. isFallback=");
        r1.append(r4.A01);
        r1.append(", modes=");
        r1.append(r8);
        r1.append(", supported protocols=");
        r1.append(java.util.Arrays.toString(r26.getEnabledProtocols()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x0640, code lost:
        throw new java.net.UnknownServiceException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0641, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0642, code lost:
        r27 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0645, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x064a, code lost:
        if (X.C08160w5.A08(r1) == false) goto L_0x0652;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x0651, code lost:
        throw new java.io.IOException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0652, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0653, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0654, code lost:
        r26 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0657, code lost:
        r1 = th;
        r26 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0658, code lost:
        if (r26 != 0) goto L_0x065a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x065a, code lost:
        X.C07790vM.A00.A07(r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0661, code lost:
        X.C08160w5.A07(r26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:439:?, code lost:
        throw new java.lang.IllegalStateException("tlsVersion == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:440:?, code lost:
        throw new java.lang.IllegalStateException("cipherSuite == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:443:?, code lost:
        throw new java.io.IOException("TLS tunnel buffered too many bytes!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:444:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01b7, code lost:
        if (r0 == -1) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0249, code lost:
        X.C01250Mm.A00(r5, r0, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0250, code lost:
        if (r0 != null) goto L_0x025c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x064c A[Catch:{ all -> 0x0653 }] */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x0652 A[Catch:{ all -> 0x0653 }] */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x065a  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x06f0 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @Override // X.AbstractC08380wS
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C08220wC intercept(X.AbstractC08390wT r42) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1969
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01260Mo.intercept(X.0wT):X.0wC");
    }
}
