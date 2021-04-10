package X;

public final class L7 implements Cdo {
    public final LD A00;

    public L7(LD ld) {
        this.A00 = ld;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for r24v0, resolved type: javax.net.ssl.SSLSocket */
    /* JADX DEBUG: Multi-variable search result rejected for r24v1, resolved type: javax.net.ssl.SSLSocket */
    /* JADX DEBUG: Multi-variable search result rejected for r24v4, resolved type: javax.net.ssl.SSLSocket */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0252, code lost:
        r5.A06 = X.EnumC0364dl.HTTP_1_1;
        r5.A04 = r5.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x025c, code lost:
        r25 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r8 = r5.A03;
        r0 = r0.A0A;
        r1 = r0.A02;
        r24 = (javax.net.ssl.SSLSocket) r0.createSocket(r8, r1, r0.A00, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r9 = r4.A00;
        r7 = r4.A03;
        r10 = r7.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x027d, code lost:
        if (r9 >= r10) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x027f, code lost:
        r8 = r7.get(r9);
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x028d, code lost:
        if (r8.A01(r24) == false) goto L_0x027d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x028f, code lost:
        r4.A00 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0295, code lost:
        if (r9 >= r7.size()) goto L_0x02a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02a1, code lost:
        if (r7.get(r9).A01(r24) == false) goto L_0x02a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02a4, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02a7, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02a9, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02aa, code lost:
        r4.A02 = r0;
        X.AbstractC0355dc.A00.A05(r8, r24, r4.A01);
        r0 = r8.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02b7, code lost:
        if (r0 == false) goto L_0x02c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02b9, code lost:
        X.C0324cr.A00.A08(r24, r1, r0.A03);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02c2, code lost:
        r24.startHandshake();
        r8 = r24.getSession();
        r0 = r8.getCipherSuite();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02cd, code lost:
        if (r0 == null) goto L_0x05f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02cf, code lost:
        r10 = X.e3.A00(r0);
        r0 = r8.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02d7, code lost:
        if (r0 == null) goto L_0x05fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02d9, code lost:
        r9 = X.EnumC0356dd.forJavaName(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r0 = r8.getPeerCertificates();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02e1, code lost:
        if (r0 == null) goto L_0x02e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02e3, code lost:
        r7 = X.dZ.A05(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0602, code lost:
        r1 = new java.lang.StringBuilder("Unable to find acceptable protocols. isFallback=");
        r1.append(r4.A01);
        r1.append(", modes=");
        r1.append(r7);
        r1.append(", supported protocols=");
        r1.append(java.util.Arrays.toString(r24.getEnabledProtocols()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x0631, code lost:
        throw new java.net.UnknownServiceException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0632, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0633, code lost:
        r25 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0636, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x063b, code lost:
        if (X.dZ.A08(r1) == false) goto L_0x0643;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x0642, code lost:
        throw new java.io.IOException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0643, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0644, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0645, code lost:
        r24 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0648, code lost:
        r1 = th;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0649, code lost:
        if (r24 != 0) goto L_0x064b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x064b, code lost:
        X.C0324cr.A00.A07(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0652, code lost:
        X.dZ.A07(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:438:?, code lost:
        throw new java.lang.IllegalStateException("tlsVersion == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:439:?, code lost:
        throw new java.lang.IllegalStateException("cipherSuite == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:442:?, code lost:
        throw new java.io.IOException("TLS tunnel buffered too many bytes!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:443:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01b7, code lost:
        if (r0 == -1) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0249, code lost:
        X.L6.A00(r5, r0, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0250, code lost:
        if (r0 != null) goto L_0x025c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x063d A[Catch:{ all -> 0x0644 }] */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x0643 A[Catch:{ all -> 0x0644 }] */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x064b  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x06df A[SYNTHETIC] */
    @Override // X.Cdo
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0359dg A38(X.L3 r43) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1955
        */
        throw new UnsupportedOperationException("Method not decompiled: X.L7.A38(X.L3):X.dg");
    }
}
