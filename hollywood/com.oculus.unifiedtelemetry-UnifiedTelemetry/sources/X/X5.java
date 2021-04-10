package X;

import java.io.IOException;
import java.util.HashMap;

public final class X5 {
    public final HashMap<String, Integer> A00;
    public final XH[] A01;
    public final Br[] A02;
    public final String[] A03;

    public final void A01(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        String str;
        EnumC0470q2 q2Var;
        XH[] xhArr = this.A01;
        int length = xhArr.length;
        for (int i = 0; i < length; i++) {
            String str2 = this.A03[i];
            Br br = this.A02[i];
            if (str2 != null) {
                if (br == null) {
                    XH xh = xhArr[i];
                    str = AnonymousClass06.A06("Missing property '", xh.A00._propName, "' for external type id '", xh.A02);
                    throw C0223Wj.A00(wn.A00, str);
                }
                A00(this, ww, wn, obj, i, str2);
            } else if (br == null) {
                continue;
            } else {
                I4 i4 = br.A01;
                if (!(i4 == null || (q2Var = I4.A03[((int) i4.A00) & 15]) == null || !q2Var.isScalarValue())) {
                    AbstractC0232Ww A032 = br.A03(ww);
                    A032.A0a();
                    AbstractC0073Cr cr = xhArr[i].A00;
                    Object A022 = V4.A02(A032, cr._type);
                    if (A022 != null) {
                        cr.A0A(obj, A022);
                    } else {
                        XH xh2 = xhArr[i];
                        if (xh2.A01.A06() != null) {
                            V4 v4 = xhArr[i].A01;
                            Class<?> A06 = v4.A06();
                            str2 = null;
                            if (A06 != null) {
                                str2 = v4.A05().A32(null, A06);
                            }
                        } else {
                            str = AnonymousClass06.A05("Missing external type id property '", xh2.A02, "'");
                            throw C0223Wj.A00(wn.A00, str);
                        }
                    }
                }
                A00(this, ww, wn, obj, i, str2);
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
    public final boolean A02(X.AbstractC0232Ww r11, X.AbstractC0226Wn r12, java.lang.String r13, java.lang.Object r14) throws java.io.IOException, X.q0 {
        /*
            r10 = this;
            r4 = r10
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r10.A00
            java.lang.Object r1 = r0.get(r13)
            java.lang.Number r1 = (java.lang.Number) r1
            r0 = 0
            if (r1 != 0) goto L_0x000d
            return r0
        L_0x000d:
            int r8 = r1.intValue()
            X.XH[] r0 = r10.A01
            r0 = r0[r8]
            java.lang.String r0 = r0.A02
            boolean r0 = r13.equals(r0)
            r3 = 1
            r5 = r11
            r7 = r14
            if (r0 == 0) goto L_0x003f
            java.lang.String[] r2 = r10.A03
            java.lang.String r0 = r11.A0d()
            r2[r8] = r0
            r11.A0Y()
            if (r14 == 0) goto L_0x003e
            X.Br[] r1 = r10.A02
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
            X.q3 r1 = r11.A0H()
            X.Br r0 = new X.Br
            r0.<init>(r1)
            r0.A08(r11)
            X.Br[] r1 = r10.A02
            r1[r8] = r0
            if (r14 == 0) goto L_0x003e
            java.lang.String[] r2 = r10.A03
            r0 = r2[r8]
            if (r0 == 0) goto L_0x003e
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: X.X5.A02(X.Ww, X.Wn, java.lang.String, java.lang.Object):boolean");
    }

    public static final void A00(X5 x5, AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj, int i, String str) throws IOException, q0 {
        Br br = new Br(ww.A0H());
        br.A06();
        br.A0A(str);
        AbstractC0232Ww A032 = x5.A02[i].A03(ww);
        A032.A0a();
        br.A08(A032);
        br.A04();
        AbstractC0232Ww A033 = br.A03(ww);
        A033.A0a();
        x5.A01[i].A00.A08(A033, wn, obj);
    }

    public X5(X5 x5) {
        XH[] xhArr = x5.A01;
        this.A01 = xhArr;
        this.A00 = x5.A00;
        int length = xhArr.length;
        this.A03 = new String[length];
        this.A02 = new Br[length];
    }

    /* JADX WARN: Incorrect args count in method signature: ([LX/XH;Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/Integer;>;[Ljava/lang/String;[LX/Br;)V */
    public X5(XH[] xhArr, HashMap hashMap) {
        this.A01 = xhArr;
        this.A00 = hashMap;
        this.A03 = null;
        this.A02 = null;
    }
}
