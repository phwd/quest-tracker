package X;

import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.0nJ  reason: invalid class name */
public final class AnonymousClass0nJ {
    public final HashMap<String, Integer> A00;
    public final AnonymousClass0nI[] A01;
    public final C01570Jk[] A02;
    public final String[] A03;

    public final void A01(AnonymousClass0aT r13, AbstractC02570aK r14, Object obj) throws IOException, C05910ld {
        String str;
        EnumC05930lf r0;
        AnonymousClass0nI[] r3 = this.A01;
        int length = r3.length;
        for (int i = 0; i < length; i++) {
            String str2 = this.A03[i];
            C01570Jk r5 = this.A02[i];
            if (str2 != null) {
                if (r5 == null) {
                    AnonymousClass0nI r4 = r3[i];
                    str = AnonymousClass006.A08("Missing property '", r4.A00._propName, "' for external type id '", r4.A02);
                    throw AnonymousClass0aG.A00(r14.A00, str);
                }
                A00(this, r13, r14, obj, i, str2);
            } else if (r5 == null) {
                continue;
            } else {
                C07300p6 r02 = r5.A01;
                if (!(r02 == null || (r0 = C07300p6.A03[((int) r02.A00) & 15]) == null || !r0.isScalarValue())) {
                    AnonymousClass0aT A0Z = r5.A0Z(r13);
                    A0Z.A0a();
                    AbstractC01680Ku r1 = r3[i].A00;
                    Object A022 = AnonymousClass0o3.A02(A0Z, r1.A4h());
                    if (A022 != null) {
                        r1.A0A(obj, A022);
                    } else {
                        AnonymousClass0nI r12 = r3[i];
                        if (r12.A01.A06() != null) {
                            AnonymousClass0o3 r03 = r3[i].A01;
                            Class<?> A06 = r03.A06();
                            str2 = null;
                            if (A06 != null) {
                                str2 = r03.A05().A57(null, A06);
                            }
                        } else {
                            str = AnonymousClass006.A07("Missing external type id property '", r12.A02, "'");
                            throw AnonymousClass0aG.A00(r14.A00, str);
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
    public final boolean A02(X.AnonymousClass0aT r11, X.AbstractC02570aK r12, java.lang.String r13, java.lang.Object r14) throws java.io.IOException, X.C05910ld {
        /*
            r10 = this;
            r4 = r10
            java.util.HashMap<java.lang.String, java.lang.Integer> r0 = r10.A00
            java.lang.Object r1 = r0.get(r13)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r0 = 0
            if (r1 != 0) goto L_0x000d
            return r0
        L_0x000d:
            int r8 = r1.intValue()
            X.0nI[] r0 = r10.A01
            r0 = r0[r8]
            java.lang.String r0 = r0.A02
            boolean r0 = r13.equals(r0)
            r3 = 1
            r5 = r11
            r7 = r14
            if (r0 == 0) goto L_0x003f
            java.lang.String[] r2 = r10.A03
            java.lang.String r0 = r11.A0P()
            r2[r8] = r0
            r11.A0F()
            if (r14 == 0) goto L_0x003e
            X.0Jk[] r1 = r10.A02
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
            X.0lg r1 = r11.A0I()
            X.0Jk r0 = new X.0Jk
            r0.<init>(r1)
            r0.A0a(r11)
            X.0Jk[] r1 = r10.A02
            r1[r8] = r0
            if (r14 == 0) goto L_0x003e
            java.lang.String[] r2 = r10.A03
            r0 = r2[r8]
            if (r0 == 0) goto L_0x003e
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0nJ.A02(X.0aT, X.0aK, java.lang.String, java.lang.Object):boolean");
    }

    public static final void A00(AnonymousClass0nJ r2, AnonymousClass0aT r3, AbstractC02570aK r4, Object obj, int i, String str) throws IOException, C05910ld {
        C01570Jk r1 = new C01570Jk(r3.A0I());
        r1.A0E();
        r1.A0S(str);
        AnonymousClass0aT A0Z = r2.A02[i].A0Z(r3);
        A0Z.A0a();
        r1.A0a(A0Z);
        r1.A0B();
        AnonymousClass0aT A0Z2 = r1.A0Z(r3);
        A0Z2.A0a();
        r2.A01[i].A00.A08(A0Z2, r4, obj);
    }

    public AnonymousClass0nJ(AnonymousClass0nJ r3) {
        AnonymousClass0nI[] r1 = r3.A01;
        this.A01 = r1;
        this.A00 = r3.A00;
        int length = r1.length;
        this.A03 = new String[length];
        this.A02 = new C01570Jk[length];
    }

    /* JADX WARN: Incorrect args count in method signature: ([LX/0nI;Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/Integer;>;[Ljava/lang/String;[LX/0Jk;)V */
    public AnonymousClass0nJ(AnonymousClass0nI[] r2, HashMap hashMap) {
        this.A01 = r2;
        this.A00 = hashMap;
        this.A03 = null;
        this.A02 = null;
    }
}
