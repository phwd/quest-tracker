package X;

import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.0lO  reason: invalid class name and case insensitive filesystem */
public final class C05510lO {
    public final HashMap<String, Integer> A00;
    public final C05500lN[] A01;
    public final AnonymousClass0Fv[] A02;
    public final String[] A03;

    public final void A01(AbstractC04100gp r13, AbstractC04020gg r14, Object obj) throws IOException, AnonymousClass0jg {
        String str;
        EnumC04820ji r0;
        C05500lN[] r3 = this.A01;
        int length = r3.length;
        for (int i = 0; i < length; i++) {
            String str2 = this.A03[i];
            AnonymousClass0Fv r5 = this.A02[i];
            if (str2 != null) {
                if (r5 == null) {
                    C05500lN r4 = r3[i];
                    str = AnonymousClass006.A08("Missing property '", r4.A00._propName, "' for external type id '", r4.A02);
                    throw C03990gZ.A00(null, str);
                }
                A00(this, r13, r14, obj, i, str2);
            } else if (r5 == null) {
                continue;
            } else {
                C06480nD r02 = r5.A01;
                if (!(r02 == null || (r0 = C06480nD.A03[((int) r02.A00) & 15]) == null || !r0.isScalarValue())) {
                    AbstractC04100gp A032 = r5.A03(r13);
                    A032.A0b();
                    AnonymousClass0HD r1 = r3[i].A00;
                    Object A022 = AnonymousClass0m9.A02(A032, r1._type);
                    if (A022 != null) {
                        r1.A0A(obj, A022);
                    } else {
                        C05500lN r12 = r3[i];
                        if (r12.A01.A06() != null) {
                            AnonymousClass0m9 r03 = r3[i].A01;
                            Class<?> A06 = r03.A06();
                            str2 = null;
                            if (A06 != null) {
                                str2 = r03.A05().A4l(null, A06);
                            }
                        } else {
                            str = AnonymousClass006.A07("Missing external type id property '", r12.A02, "'");
                            throw C03990gZ.A00(null, str);
                        }
                    }
                }
                A00(this, r13, r14, obj, i, str2);
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
    public final boolean A02(X.AbstractC04100gp r11, X.AbstractC04020gg r12, java.lang.String r13, java.lang.Object r14) throws java.io.IOException, X.AnonymousClass0jg {
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
            X.0lN[] r0 = r10.A01
            r0 = r0[r8]
            java.lang.String r0 = r0.A02
            boolean r0 = r13.equals(r0)
            r3 = 1
            r5 = r11
            r7 = r14
            if (r0 == 0) goto L_0x003f
            java.lang.String[] r2 = r10.A03
            java.lang.String r0 = r11.A0e()
            r2[r8] = r0
            r11.A0Z()
            if (r14 == 0) goto L_0x003e
            X.0Fv[] r1 = r10.A02
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
            X.0jj r1 = r11.A0I()
            X.0Fv r0 = new X.0Fv
            r0.<init>(r1)
            r0.A08(r11)
            X.0Fv[] r1 = r10.A02
            r1[r8] = r0
            if (r14 == 0) goto L_0x003e
            java.lang.String[] r2 = r10.A03
            r0 = r2[r8]
            if (r0 == 0) goto L_0x003e
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05510lO.A02(X.0gp, X.0gg, java.lang.String, java.lang.Object):boolean");
    }

    public static final void A00(C05510lO r2, AbstractC04100gp r3, AbstractC04020gg r4, Object obj, int i, String str) throws IOException, AnonymousClass0jg {
        AnonymousClass0Fv r1 = new AnonymousClass0Fv(r3.A0I());
        r1.A06();
        r1.A0A(str);
        AbstractC04100gp A032 = r2.A02[i].A03(r3);
        A032.A0b();
        r1.A08(A032);
        r1.A04();
        AbstractC04100gp A033 = r1.A03(r3);
        A033.A0b();
        r2.A01[i].A00.A08(A033, r4, obj);
    }

    public C05510lO(C05510lO r3) {
        C05500lN[] r1 = r3.A01;
        this.A01 = r1;
        this.A00 = r3.A00;
        int length = r1.length;
        this.A03 = new String[length];
        this.A02 = new AnonymousClass0Fv[length];
    }

    /* JADX WARN: Incorrect args count in method signature: ([LX/0lN;Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/Integer;>;[Ljava/lang/String;[LX/0Fv;)V */
    public C05510lO(C05500lN[] r2, HashMap hashMap) {
        this.A01 = r2;
        this.A00 = hashMap;
        this.A03 = null;
        this.A02 = null;
    }
}
