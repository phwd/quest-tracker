package X;

/* renamed from: X.ti  reason: case insensitive filesystem */
public final class C1142ti implements AbstractC0545bi {
    public final C0548bl A00;

    public C1142ti(C0548bl blVar) {
        this.A00 = blVar;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:271:0x05f0 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r24v0, types: [java.net.Socket] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.reflect.Method] */
    /* JADX WARN: Type inference failed for: r1v28, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r24v1 */
    /* JADX WARN: Type inference failed for: r25v0 */
    /* JADX WARN: Type inference failed for: r25v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r25v2, types: [java.util.AbstractCollection, java.util.AbstractList, java.lang.Object, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v275, types: [java.lang.StringBuilder, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r25v4 */
    /* JADX WARN: Type inference failed for: r24v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x025b, code lost:
        X.C1141th.A00(r5, r0, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0262, code lost:
        if (r0 != null) goto L_0x026e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0264, code lost:
        r5.A06 = X.EnumC0549bm.HTTP_1_1;
        r5.A04 = r5.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x026e, code lost:
        r20 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r1 = r5.A03;
        r0 = r0.A09;
        r7 = r0.A02;
        r24 = (javax.net.ssl.SSLSocket) r0.createSocket(r1, r7, r0.A00, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        r1 = r4.A00;
        r9 = r4.A03;
        r0 = r9.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x028f, code lost:
        if (r1 >= r0) goto L_0x090b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0291, code lost:
        r14 = (X.C0535bY) r9.get(r1);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x029f, code lost:
        if (r14.A01(r24) == false) goto L_0x028f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02a1, code lost:
        r4.A00 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02a7, code lost:
        if (r1 >= r9.size()) goto L_0x02bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02b3, code lost:
        if (((X.C0535bY) r9.get(r1)).A01(r24) == false) goto L_0x02b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02b6, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02b9, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02bb, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02bc, code lost:
        r4.A02 = r0;
        r1 = r4.A01;
        r9 = r14.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02c2, code lost:
        if (r9 == null) goto L_0x02dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02c4, code lost:
        r13 = (java.lang.String[]) X.C0561by.A0B(r9, r24.getEnabledCipherSuites());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02ce, code lost:
        r9 = r14.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02d0, code lost:
        if (r9 == null) goto L_0x02e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02d2, code lost:
        r12 = (java.lang.String[]) X.C0561by.A0B(r9, r24.getEnabledProtocols());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02dd, code lost:
        r13 = r24.getEnabledCipherSuites();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02e2, code lost:
        r12 = r24.getEnabledProtocols();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02e6, code lost:
        if (r1 == false) goto L_0x030f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02e8, code lost:
        r10 = r24.getSupportedCipherSuites();
        r9 = r10.length;
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02f0, code lost:
        if (r1 >= r9) goto L_0x030f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02f8, code lost:
        if (X.C0561by.A09(r10[r1], "TLS_FALLBACK_SCSV") == false) goto L_0x02fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x02fb, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x02ff, code lost:
        if (r1 == -1) goto L_0x030f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0301, code lost:
        r10 = r13.length;
        r9 = r10 + 1;
        r1 = new java.lang.String[r9];
        java.lang.System.arraycopy(r13, 0, r1, 0, r10);
        r1[r9 - 1] = "TLS_FALLBACK_SCSV";
        r13 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x030f, code lost:
        r0 = new X.C0534bX(r14);
        r0.A00(r13);
        r0.A01(r12);
        r1 = new X.C0535bY(r0);
        r0 = r1.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0321, code lost:
        if (r0 == null) goto L_0x0328;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0323, code lost:
        r24.setEnabledProtocols(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0328, code lost:
        r0 = r1.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x032a, code lost:
        if (r0 == null) goto L_0x0331;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x032c, code lost:
        r24.setEnabledCipherSuites(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0331, code lost:
        r0 = r14.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0335, code lost:
        if (r0 == false) goto L_0x0408;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0337, code lost:
        r9 = X.C0595ce.A01;
        r14 = r0.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x033f, code lost:
        if ((r9 instanceof X.C1115tD) != false) goto L_0x03c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0343, code lost:
        if ((r9 instanceof X.C1116tE) != false) goto L_0x039d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0347, code lost:
        if ((r9 instanceof X.C1117tF) == false) goto L_0x0408;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0349, code lost:
        r9 = (X.C1117tF) r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x034c, code lost:
        if (r7 == null) goto L_0x0366;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x034e, code lost:
        r9.A04.A02(r24, true);
        r9.A03.A02(r24, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0366, code lost:
        r12 = r9.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0368, code lost:
        if (r12 == null) goto L_0x0408;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0374, code lost:
        if (X.C0594cd.A00(r12, r24.getClass()) == null) goto L_0x0408;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0376, code lost:
        r11 = new java.lang.Object[1];
        r10 = new X.AnonymousClass33();
        r9 = r14.size();
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0382, code lost:
        if (r8 >= r9) goto L_0x03fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0384, code lost:
        r1 = r14.get(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x038a, code lost:
        if (r1 == X.EnumC0549bm.HTTP_1_0) goto L_0x039a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x038c, code lost:
        r1 = r1.toString();
        r0 = r1.length();
        r10.A08(r0);
        r10.A0D(r1, 0, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x039a, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x039d, code lost:
        r9 = (X.C1116tE) r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:?, code lost:
        r1 = r24.getSSLParameters();
        r10 = X.C0595ce.A00(r14);
        r9.A01.invoke(r1, r10.toArray(new java.lang.String[r10.size()]));
        r24.setSSLParameters(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x03c8, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x03c9, code lost:
        r9 = (X.C1115tD) r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:?, code lost:
        r9.A03.invoke(null, r24, java.lang.reflect.Proxy.newProxyInstance(X.C0595ce.class.getClassLoader(), new java.lang.Class[]{r9.A00, r9.A01}, new X.C0593cc(X.C0595ce.A00(r14))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x03f6, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x03fc, code lost:
        throw new java.lang.AssertionError(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x03fd, code lost:
        r11[0] = r10.A4d();
        r12.A01(r24, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0408, code lost:
        r24.startHandshake();
        r1 = r24.getSession();
        r0 = r1.getCipherSuite();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0415, code lost:
        if (r0 == null) goto L_0x0903;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0417, code lost:
        r10 = X.C0531bU.A00(r0);
        r0 = r1.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x041f, code lost:
        if (r0 == null) goto L_0x08fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0421, code lost:
        r9 = X.EnumC0557bu.forJavaName(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:?, code lost:
        r0 = r1.getPeerCertificates();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x0429, code lost:
        if (r0 == null) goto L_0x0430;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x042b, code lost:
        r8 = X.C0561by.A05(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:402:0x0902, code lost:
        throw new java.lang.IllegalStateException("tlsVersion == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:404:0x090a, code lost:
        throw new java.lang.IllegalStateException("cipherSuite == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:0x090b, code lost:
        r1 = new java.lang.StringBuilder("Unable to find acceptable protocols. isFallback=");
        r1.append(r4.A01);
        r1.append(", modes=");
        r1.append(r9);
        r1.append(", supported protocols=");
        r1.append(java.util.Arrays.toString(r24.getEnabledProtocols()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x093a, code lost:
        throw new java.net.UnknownServiceException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x093b, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x093c, code lost:
        r20 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x093f, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:412:0x0944, code lost:
        if (X.C0561by.A08(r1) == false) goto L_0x094c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:414:0x094b, code lost:
        throw new java.io.IOException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:415:0x094c, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:416:0x094d, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x094e, code lost:
        r24 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x0951, code lost:
        r8 = th;
        r24 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x0952, code lost:
        if (r24 != 0) goto L_0x0954;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:420:0x0954, code lost:
        r1 = X.C0595ce.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:421:0x0958, code lost:
        if ((r1 instanceof X.C1115tD) != false) goto L_0x095a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:424:?, code lost:
        ((X.C1115tD) r1).A04.invoke(null, new java.lang.Object[]{r24});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:427:0x096d, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:428:0x096e, code lost:
        X.C0561by.A07(r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:429:0x0973, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01b9, code lost:
        if (r0 == -1) goto L_0x01bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x023a, code lost:
        throw new java.io.IOException("TLS tunnel buffered too many bytes!");
     */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x055f A[LOOP:11: B:236:0x055f->B:237:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x0574  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x05a7  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x0612  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x06a1 A[LOOP:15: B:296:0x069d->B:298:0x06a1, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x06d9 A[LOOP:16: B:300:0x06d7->B:301:0x06d9, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:413:0x0946 A[Catch:{ all -> 0x094d }] */
    /* JADX WARNING: Removed duplicated region for block: B:415:0x094c A[Catch:{ all -> 0x094d }] */
    /* JADX WARNING: Removed duplicated region for block: B:420:0x0954  */
    /* JADX WARNING: Removed duplicated region for block: B:503:0x0a73 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:613:0x05da A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:616:0x05f2 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 5 */
    @Override // X.AbstractC0545bi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0554br A3L(X.C1138te r44) {
        /*
        // Method dump skipped, instructions count: 2939
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1142ti.A3L(X.te):X.br");
    }
}
