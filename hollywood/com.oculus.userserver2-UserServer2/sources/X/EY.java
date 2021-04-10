package X;

public final class EY implements XS {
    public final AbstractC0054Ej A00;

    public EY(AbstractC0054Ej ej) {
        this.A00 = ej;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:272:0x05f6 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r25v0, types: [java.net.Socket, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r25v1 */
    /* JADX WARN: Type inference failed for: r24v0 */
    /* JADX WARN: Type inference failed for: r24v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r24v2, types: [java.util.AbstractCollection, java.util.AbstractList, java.lang.Object, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v266, types: [java.lang.StringBuilder, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r24v4 */
    /* JADX WARN: Type inference failed for: r25v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0271, code lost:
        X.EX.A00(r5, r0, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0278, code lost:
        if (r0 != null) goto L_0x0284;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x027a, code lost:
        r5.A06 = X.XP.HTTP_1_1;
        r5.A04 = r5.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0284, code lost:
        r20 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r1 = r5.A03;
        r0 = r0.A0A;
        r7 = r0.A02;
        r25 = (javax.net.ssl.SSLSocket) r0.createSocket(r1, r7, r0.A00, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:?, code lost:
        r1 = r4.A00;
        r8 = r4.A03;
        r0 = r8.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02a5, code lost:
        if (r1 >= r0) goto L_0x0929;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02a7, code lost:
        r13 = r8.get(r1);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02b5, code lost:
        if (r13.A01(r25) == false) goto L_0x02a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02b7, code lost:
        r4.A00 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02bd, code lost:
        if (r1 >= r8.size()) goto L_0x02d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02c9, code lost:
        if (r8.get(r1).A01(r25) == false) goto L_0x02cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02cc, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02cf, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02d1, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02d2, code lost:
        r4.A02 = r0;
        r1 = r4.A01;
        r8 = r13.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02d8, code lost:
        if (r8 == null) goto L_0x02f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02da, code lost:
        r12 = (java.lang.String[]) X.XD.A0B(r8, r25.getEnabledCipherSuites());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02e4, code lost:
        r8 = r13.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02e6, code lost:
        if (r8 == null) goto L_0x02f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02e8, code lost:
        r11 = (java.lang.String[]) X.XD.A0B(r8, r25.getEnabledProtocols());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02f3, code lost:
        r12 = r25.getEnabledCipherSuites();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02f8, code lost:
        r11 = r25.getEnabledProtocols();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02fc, code lost:
        if (r1 == false) goto L_0x0325;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02fe, code lost:
        r9 = r25.getSupportedCipherSuites();
        r8 = r9.length;
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0306, code lost:
        if (r1 >= r8) goto L_0x0325;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x030e, code lost:
        if (X.XD.A09(r9[r1], "TLS_FALLBACK_SCSV") == false) goto L_0x0311;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0311, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0315, code lost:
        if (r1 == -1) goto L_0x0325;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0317, code lost:
        r9 = r12.length;
        r8 = r9 + 1;
        r1 = new java.lang.String[r8];
        java.lang.System.arraycopy(r12, 0, r1, 0, r9);
        r1[r8 - 1] = "TLS_FALLBACK_SCSV";
        r12 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0325, code lost:
        r0 = new X.C0179Xe(r13);
        r0.A00(r12);
        r0.A01(r11);
        r1 = new X.C0178Xd(r0);
        r0 = r1.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0337, code lost:
        if (r0 == null) goto L_0x033e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0339, code lost:
        r25.setEnabledProtocols(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x033e, code lost:
        r0 = r1.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0340, code lost:
        if (r0 == null) goto L_0x0347;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0342, code lost:
        r25.setEnabledCipherSuites(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0347, code lost:
        r0 = r13.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x034b, code lost:
        if (r0 == false) goto L_0x0418;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x034d, code lost:
        r8 = X.WV.A01;
        r12 = r0.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0355, code lost:
        if ((r8 instanceof X.E1) != false) goto L_0x03db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0359, code lost:
        if ((r8 instanceof X.E2) != false) goto L_0x03b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x035d, code lost:
        if ((r8 instanceof X.E3) == false) goto L_0x0418;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x035f, code lost:
        r8 = (X.E3) r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0361, code lost:
        if (r7 == null) goto L_0x037b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0363, code lost:
        r8.A04.A02(r25, true);
        r8.A03.A02(r25, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x037b, code lost:
        r11 = r8.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x037d, code lost:
        if (r11 == null) goto L_0x0418;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0389, code lost:
        if (X.WW.A00(r11, r25.getClass()) == null) goto L_0x0418;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x038b, code lost:
        r10 = new X.AnonymousClass8k();
        r9 = r12.size();
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0395, code lost:
        if (r8 >= r9) goto L_0x040b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0397, code lost:
        r1 = r12.get(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x039d, code lost:
        if (r1 == X.XP.HTTP_1_0) goto L_0x03ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x039f, code lost:
        r1 = r1.toString();
        r10.A09(r1.length());
        r10.A0F(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03ad, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03b0, code lost:
        r8 = (X.E2) r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:?, code lost:
        r1 = r25.getSSLParameters();
        r0 = X.WV.A00(r12);
        r8.A01.invoke(r1, r0.toArray(new java.lang.String[r0.size()]));
        r25.setSSLParameters(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x03da, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x03db, code lost:
        r8 = (X.E1) r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:?, code lost:
        r8.A03.invoke(null, r25, java.lang.reflect.Proxy.newProxyInstance(X.WV.class.getClassLoader(), new java.lang.Class[]{r8.A00, r8.A01}, new X.WX(X.WV.A00(r12))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0404, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x040a, code lost:
        throw new java.lang.AssertionError(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x040b, code lost:
        r11.A01(r25, r10.A31());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0418, code lost:
        r25.startHandshake();
        r1 = r25.getSession();
        r0 = r1.getCipherSuite();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0425, code lost:
        if (r0 == null) goto L_0x0919;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0427, code lost:
        r10 = X.C0182Xh.A00(r0);
        r0 = r1.getProtocol();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x042f, code lost:
        if (r0 == null) goto L_0x0921;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0431, code lost:
        r9 = X.XH.forJavaName(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:?, code lost:
        r0 = r1.getPeerCertificates();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0439, code lost:
        if (r0 == null) goto L_0x0440;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x043b, code lost:
        r8 = X.XD.A05(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:404:0x0929, code lost:
        r1 = new java.lang.StringBuilder("Unable to find acceptable protocols. isFallback=");
        r1.append(r4.A01);
        r1.append(", modes=");
        r1.append(r8);
        r1.append(", supported protocols=");
        r1.append(java.util.Arrays.toString(r25.getEnabledProtocols()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:0x0958, code lost:
        throw new java.net.UnknownServiceException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x0959, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x095a, code lost:
        r20 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x095d, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x0962, code lost:
        if (X.XD.A08(r1) == false) goto L_0x096a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:413:0x0969, code lost:
        throw new java.io.IOException(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:414:0x096a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:415:0x096b, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:416:0x096c, code lost:
        r25 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:417:0x096f, code lost:
        r7 = th;
        r25 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:418:0x0970, code lost:
        if (r25 != 0) goto L_0x0972;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:419:0x0972, code lost:
        r1 = X.WV.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:420:0x0976, code lost:
        if ((r1 instanceof X.E1) != false) goto L_0x0978;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:?, code lost:
        ((X.E1) r1).A04.invoke(null, r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:427:0x0998, code lost:
        X.XD.A07(r25);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:654:?, code lost:
        throw new java.lang.IllegalStateException("tlsVersion == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:655:?, code lost:
        throw new java.lang.IllegalStateException("cipherSuite == null");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:660:?, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:664:?, code lost:
        throw new java.io.IOException("TLS tunnel buffered too many bytes!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:665:?, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01df, code lost:
        if (r0 == -1) goto L_0x01e1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0565 A[LOOP:11: B:237:0x0565->B:238:?, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x057a  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x05ad  */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x0618  */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x06a7 A[LOOP:15: B:297:0x06a3->B:299:0x06a7, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x06df A[LOOP:16: B:301:0x06dd->B:302:0x06df, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:412:0x0964 A[Catch:{ all -> 0x096b }] */
    /* JADX WARNING: Removed duplicated region for block: B:414:0x096a A[Catch:{ all -> 0x096b }] */
    /* JADX WARNING: Removed duplicated region for block: B:419:0x0972  */
    /* JADX WARNING: Removed duplicated region for block: B:492:0x0a8b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:564:0x05e0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:571:0x05f8 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 3 */
    @Override // X.XS
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.XK A29(X.EU r44) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 2901
        */
        throw new UnsupportedOperationException("Method not decompiled: X.EY.A29(X.EU):X.XK");
    }
}
