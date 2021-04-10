package X;

public abstract class VG extends AbstractC1014qi {
    public NX A00;

    public static final String A0D(int i) {
        StringBuilder sb;
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return AnonymousClass08.A01("(CTRL-CHAR, code ", i, ")");
        }
        if (i > 255) {
            sb = new StringBuilder();
            sb.append("'");
            sb.append(c);
            sb.append("' (code ");
            sb.append(i);
            sb.append(" / 0x");
            sb.append(Integer.toHexString(i));
        } else {
            sb = new StringBuilder();
            sb.append("'");
            sb.append(c);
            sb.append("' (code ");
            sb.append(i);
        }
        sb.append(")");
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001d, code lost:
        if (r3 == null) goto L_0x001f;
     */
    @Override // X.AbstractC1014qi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public X.NX A0o() {
        /*
        // Method dump skipped, instructions count: 123
        */
        throw new UnsupportedOperationException("Method not decompiled: X.VG.A0o():X.NX");
    }

    public final void A0s(int i) {
        A0v(AnonymousClass08.A05("Illegal character (", A0D((char) i), "): only regular white space (\\r, \\n, \\t) is allowed between tokens"));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC1014qi
    public final String A0p() {
        Object obj;
        if (!(this instanceof C0680fB)) {
            AnonymousClass7v r3 = (AnonymousClass7v) this;
            NX nx = ((VG) r3).A00;
            if (nx == NX.VALUE_STRING) {
                if (r3.A02) {
                    r3.A02 = false;
                    AnonymousClass7v.A0A(r3);
                }
            } else if (nx == null) {
                return null;
            } else {
                int i = C0249Nn.A00[nx.ordinal()];
                if (i == 1) {
                    return r3.A0E.A02;
                }
                if (!(i == 2 || i == 3 || i == 4)) {
                    return nx.asString();
                }
            }
            return r3.A0O.A05();
        }
        C0680fB fBVar = (C0680fB) this;
        NX nx2 = ((VG) fBVar).A00;
        if (nx2 == NX.VALUE_STRING || nx2 == NX.FIELD_NAME) {
            QK qk = fBVar.A04;
            obj = qk.A02[fBVar.A00];
            if (obj instanceof String) {
                return (String) obj;
            }
        } else if (nx2 == null) {
            return null;
        } else {
            int i2 = QJ.A00[nx2.ordinal()];
            if (i2 != 7 && i2 != 8) {
                return nx2.asString();
            }
            QK qk2 = fBVar.A04;
            obj = qk2.A02[fBVar.A00];
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override // X.AbstractC1014qi
    public String A0q(String str) {
        NX nx = this.A00;
        if (nx == NX.VALUE_STRING || (nx != null && nx != NX.VALUE_NULL && nx.isScalarValue())) {
            return A0p();
        }
        return str;
    }

    public final void A0r() {
        if (!(this instanceof C0680fB)) {
            AnonymousClass2L r2 = (AnonymousClass2L) this;
            C1016qk qkVar = r2.A0E;
            if (((NW) qkVar).A01 != 0) {
                StringBuilder sb = new StringBuilder(": expected close marker for ");
                sb.append(qkVar.A00());
                sb.append(" (from ");
                sb.append(new NT(r2.A0N.A04, -1, -1, qkVar.A01, qkVar.A00));
                sb.append(")");
                r2.A0w(sb.toString());
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
            return;
        }
        O1.A02();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A0t(int i, String str) {
        String A05 = AnonymousClass08.A05("Unexpected character (", A0D(i), ")");
        if (str != null) {
            A05 = AnonymousClass08.A05(A05, ": ", str);
        }
        A0v(A05);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A0u(int i, String str) {
        if (!A0j(NU.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            A0v(AnonymousClass08.A06("Illegal unquoted character (", A0D((char) i), "): has to be escaped using backslash to be included in ", str));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public final void A0w(String str) {
        A0v(AnonymousClass08.A04("Unexpected end-of-input", str));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A0v(String str) {
        throw new C1013qh(str, A0R());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r3 >= 0) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        X.NP.A00(r14, r1, 0, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        if (r4 < r7) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        r2 = r4 + 1;
        r1 = r12.charAt(r4);
        r0 = r14.A01(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        if (r0 >= 0) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        X.NP.A00(r14, r1, 1, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r10 = (r3 << 6) | r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r2 < r7) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        r9 = r2 + 1;
        r2 = r12.charAt(r2);
        r1 = r14.A01(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        if (r1 >= 0) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
        if (r1 == -2) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005c, code lost:
        if (r9 < r7) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        r2 = r9 + 1;
        r4 = r12.charAt(r9);
        r3 = r14.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        if (r4 != r3) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0069, code lost:
        r13.A02(r10 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006f, code lost:
        r3 = (r10 << 6) | r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0072, code lost:
        if (r9 < r7) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0075, code lost:
        r2 = r9 + 1;
        r0 = r12.charAt(r9);
        r1 = r14.A01(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007f, code lost:
        if (r1 >= 0) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0081, code lost:
        if (r1 == -2) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0084, code lost:
        r13.A04(r3 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008b, code lost:
        r13.A03((r3 << 6) | r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009a, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009d, code lost:
        if (r14.A02 != false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009f, code lost:
        r13.A02(r10 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a4, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ac, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ad, code lost:
        X.NP.A00(r14, r2, 2, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b7, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00bf, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c0, code lost:
        r1 = new java.lang.StringBuilder("expected padding character '");
        r1.append(r3);
        r1.append("'");
        X.NP.A00(r14, r4, 3, r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00dd, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e0, code lost:
        if (r14.A02 != false) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e2, code lost:
        r13.A04(r3 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e7, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ef, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f0, code lost:
        X.NP.A00(r14, r0, 3, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00fa, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r3 = r14.A01(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0x(java.lang.String r12, X.C0259Nx r13, X.NP r14) {
        /*
        // Method dump skipped, instructions count: 268
        */
        throw new UnsupportedOperationException("Method not decompiled: X.VG.A0x(java.lang.String, X.Nx, X.NP):void");
    }

    public VG() {
    }

    public VG(int i) {
        super(0);
    }
}
