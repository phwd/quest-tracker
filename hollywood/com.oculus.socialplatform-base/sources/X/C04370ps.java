package X;

import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.0ps  reason: invalid class name and case insensitive filesystem */
public final class C04370ps {
    public final HashMap<String, Integer> A00;
    public final C04360pr[] A01;
    public final AnonymousClass0OD[] A02;
    public final String[] A03;

    public final void A01(AbstractC02280iQ r13, AbstractC02210iH r14, Object obj) throws IOException, C03620oC {
        EnumC03640oE r0;
        C04360pr[] r3 = this.A01;
        int length = r3.length;
        for (int i = 0; i < length; i++) {
            String str = this.A03[i];
            AnonymousClass0OD r5 = this.A02[i];
            if (str != null) {
                if (r5 == null) {
                    C04360pr r4 = r3[i];
                    throw C02180iD.A00(r14.A00, AnonymousClass006.A0B("Missing property '", r4.A00._propName, "' for external type id '", r4.A02));
                }
                A00(this, r13, r14, obj, i, str);
            } else if (r5 == null) {
                continue;
            } else {
                C04960rc r02 = r5.A01;
                if (!(r02 == null || (r0 = C04960rc.A03[((int) r02.A00) & 15]) == null || !r0.isScalarValue())) {
                    AbstractC02280iQ A0b = r5.A0b(r13);
                    A0b.A0j();
                    AbstractC01170Rz r1 = r3[i].A00;
                    Object A022 = AbstractC04520qa.A02(A0b, r1.A59());
                    if (A022 != null) {
                        r1.A0A(obj, A022);
                    } else {
                        C04360pr r12 = r3[i];
                        if (r12.A01.A06() != null) {
                            AbstractC04520qa r03 = r3[i].A01;
                            Class<?> A06 = r03.A06();
                            str = null;
                            if (A06 != null) {
                                str = r03.A05().A5Y(null, A06);
                            }
                        } else {
                            throw C02180iD.A00(r14.A00, AnonymousClass006.A09("Missing external type id property '", r12.A02, "'"));
                        }
                    }
                }
                A00(this, r13, r14, obj, i, str);
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
    public final boolean A02(X.AbstractC02280iQ r11, X.AbstractC02210iH r12, java.lang.String r13, java.lang.Object r14) throws java.io.IOException, X.C03620oC {
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
            X.0pr[] r0 = r10.A01
            r0 = r0[r8]
            java.lang.String r0 = r0.A02
            boolean r0 = r13.equals(r0)
            r3 = 1
            r5 = r11
            r7 = r14
            if (r0 == 0) goto L_0x003f
            java.lang.String[] r2 = r10.A03
            java.lang.String r0 = r11.A0m()
            r2[r8] = r0
            r11.A0h()
            if (r14 == 0) goto L_0x003e
            X.0OD[] r1 = r10.A02
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
            X.0oF r1 = r11.A0N()
            X.0OD r0 = new X.0OD
            r0.<init>(r1)
            r0.A0c(r11)
            X.0OD[] r1 = r10.A02
            r1[r8] = r0
            if (r14 == 0) goto L_0x003e
            java.lang.String[] r2 = r10.A03
            r0 = r2[r8]
            if (r0 == 0) goto L_0x003e
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04370ps.A02(X.0iQ, X.0iH, java.lang.String, java.lang.Object):boolean");
    }

    public static final void A00(C04370ps r2, AbstractC02280iQ r3, AbstractC02210iH r4, Object obj, int i, String str) throws IOException, C03620oC {
        AnonymousClass0OD r1 = new AnonymousClass0OD(r3.A0N());
        r1.A0H();
        r1.A0U(str);
        AbstractC02280iQ A0b = r2.A02[i].A0b(r3);
        A0b.A0j();
        r1.A0c(A0b);
        r1.A0E();
        AbstractC02280iQ A0b2 = r1.A0b(r3);
        A0b2.A0j();
        r2.A01[i].A00.A08(A0b2, r4, obj);
    }

    public C04370ps(C04370ps r3) {
        C04360pr[] r1 = r3.A01;
        this.A01 = r1;
        this.A00 = r3.A00;
        int length = r1.length;
        this.A03 = new String[length];
        this.A02 = new AnonymousClass0OD[length];
    }

    /* JADX WARN: Incorrect args count in method signature: ([LX/0pr;Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/Integer;>;[Ljava/lang/String;[LX/0OD;)V */
    public C04370ps(C04360pr[] r2, HashMap hashMap) {
        this.A01 = r2;
        this.A00 = hashMap;
        this.A03 = null;
        this.A02 = null;
    }
}
