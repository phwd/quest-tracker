package X;

import java.util.HashMap;

/* renamed from: X.Os  reason: case insensitive filesystem */
public final class C0271Os {
    public final HashMap A00;
    public final C0270Or[] A01;
    public final JD[] A02;
    public final String[] A03;

    public final void A01(AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj) {
        NX nx;
        Class cls;
        C0270Or[] orArr = this.A01;
        int length = orArr.length;
        for (int i = 0; i < length; i++) {
            String str = this.A03[i];
            JD jd = this.A02[i];
            if (str != null) {
                if (jd == null) {
                    C0270Or or = orArr[i];
                    throw C1025qv.A00(null, AnonymousClass08.A06("Missing property '", or.A00._propName, "' for external type id '", or.A02));
                }
                A00(this, qiVar, qrVar, obj, i, str);
            } else if (jd == null) {
                continue;
            } else {
                QK qk = jd.A02;
                if (!(qk == null || (nx = QK.A03[((int) qk.A00) & 15]) == null || !nx.isScalarValue())) {
                    AbstractC1014qi A0W = jd.A0W(qiVar);
                    A0W.A0o();
                    AbstractC1034r7 r7Var = orArr[i].A00;
                    Object A012 = PR.A01(A0W, r7Var.A34());
                    if (A012 != null) {
                        r7Var.A09(obj, A012);
                    } else {
                        C0270Or or2 = orArr[i];
                        AbstractC1024qt qtVar = ((AbstractC1055rV) or2.A01)._defaultImpl;
                        if (qtVar == null || qtVar._class == null) {
                            throw C1025qv.A00(null, AnonymousClass08.A05("Missing external type id property '", or2.A02, "'"));
                        }
                        PR pr = orArr[i].A01;
                        AbstractC1024qt qtVar2 = ((AbstractC1055rV) pr)._defaultImpl;
                        if (qtVar2 == null) {
                            cls = null;
                        } else {
                            cls = qtVar2._class;
                        }
                        str = null;
                        if (cls != null) {
                            str = ((AbstractC1055rV) pr)._idResolver.A3G(null, cls);
                        }
                    }
                }
                A00(this, qiVar, qrVar, obj, i, str);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0055, code lost:
        if (r2[r8] != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0031, code lost:
        if (r1[r8] != null) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(X.AbstractC1014qi r11, X.AbstractC1022qr r12, java.lang.String r13, java.lang.Object r14) {
        /*
            r10 = this;
            r4 = r10
            java.util.HashMap r0 = r10.A00
            java.lang.Object r1 = r0.get(r13)
            java.lang.Number r1 = (java.lang.Number) r1
            r0 = 0
            if (r1 != 0) goto L_0x000d
            return r0
        L_0x000d:
            int r8 = r1.intValue()
            X.Or[] r0 = r10.A01
            r0 = r0[r8]
            java.lang.String r0 = r0.A02
            boolean r0 = r13.equals(r0)
            r3 = 1
            r5 = r11
            r7 = r14
            if (r0 == 0) goto L_0x003f
            java.lang.String[] r2 = r10.A03
            java.lang.String r0 = r11.A0p()
            r2[r8] = r0
            r11.A0T()
            if (r14 == 0) goto L_0x003e
            X.JD[] r1 = r10.A02
            r0 = r1[r8]
            if (r0 == 0) goto L_0x003e
        L_0x0033:
            r9 = r2[r8]
            r0 = 0
            r2[r8] = r0
            r6 = r12
            A00(r4, r5, r6, r7, r8, r9)
            r1[r8] = r0
        L_0x003e:
            return r3
        L_0x003f:
            X.NY r1 = r11.A0W()
            X.JD r0 = new X.JD
            r0.<init>(r1)
            r0.A0X(r11)
            X.JD[] r1 = r10.A02
            r1[r8] = r0
            if (r14 == 0) goto L_0x003e
            java.lang.String[] r2 = r10.A03
            r0 = r2[r8]
            if (r0 == 0) goto L_0x003e
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0271Os.A02(X.qi, X.qr, java.lang.String, java.lang.Object):boolean");
    }

    public static final void A00(C0271Os os, AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj, int i, String str) {
        JD jd = new JD(qiVar.A0W());
        jd.A0B();
        jd.A0P(str);
        AbstractC1014qi A0W = os.A02[i].A0W(qiVar);
        A0W.A0o();
        jd.A0X(A0W);
        jd.A08();
        AbstractC1014qi A0W2 = jd.A0W(qiVar);
        A0W2.A0o();
        os.A01[i].A00.A07(A0W2, qrVar, obj);
    }

    public C0271Os(C0271Os os) {
        C0270Or[] orArr = os.A01;
        this.A01 = orArr;
        this.A00 = os.A00;
        int length = orArr.length;
        this.A03 = new String[length];
        this.A02 = new JD[length];
    }

    public C0271Os(C0270Or[] orArr, HashMap hashMap) {
        this.A01 = orArr;
        this.A00 = hashMap;
        this.A03 = null;
        this.A02 = null;
    }
}
