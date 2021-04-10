package X;

import java.io.IOException;

public abstract class DO extends AbstractC0232Ww {
    public EnumC0470q2 A00;

    public static final String A0A(int i) {
        StringBuilder sb;
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return AnonymousClass06.A02("(CTRL-CHAR, code ", i, ")");
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

    @Override // X.AbstractC0232Ww
    public abstract int A0U() throws IOException, Wx;

    @Override // X.AbstractC0232Ww
    public abstract int A0V() throws IOException, Wx;

    @Override // X.AbstractC0232Ww
    public abstract EnumC0470q2 A0a() throws IOException, Wx;

    @Override // X.AbstractC0232Ww
    public abstract String A0c() throws IOException, Wx;

    @Override // X.AbstractC0232Ww
    public abstract String A0d() throws IOException, Wx;

    @Override // X.AbstractC0232Ww
    public abstract boolean A0h();

    @Override // X.AbstractC0232Ww
    public abstract byte[] A0i(C0465pq pqVar) throws IOException, Wx;

    @Override // X.AbstractC0232Ww
    public abstract char[] A0j() throws IOException, Wx;

    public abstract void A0k() throws Wx;

    public final void A0l(int i) throws Wx {
        A0o(AnonymousClass06.A05("Illegal character (", A0A((char) i), "): only regular white space (\\r, \\n, \\t) is allowed between tokens"));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC0232Ww
    public abstract void close() throws IOException;

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r3 < r4) goto L_0x0045;
     */
    @Override // X.AbstractC0232Ww
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A0W(int r7) throws java.io.IOException, X.Wx {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DO.A0W(int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r3 < r4) goto L_0x0045;
     */
    @Override // X.AbstractC0232Ww
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A0X(long r7) throws java.io.IOException, X.Wx {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DO.A0X(long):long");
    }

    @Override // X.AbstractC0232Ww
    public final AbstractC0232Ww A0Y() throws IOException, Wx {
        EnumC0470q2 q2Var = this.A00;
        if (q2Var != EnumC0470q2.START_OBJECT && q2Var != EnumC0470q2.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            EnumC0470q2 A0a = A0a();
            if (A0a == null) {
                A0k();
                return this;
            }
            int i2 = qA.A00[A0a.ordinal()];
            if (i2 == 1 || i2 == 2) {
                i++;
            } else if ((i2 == 3 || i2 == 4) && i - 1 == 0) {
                return this;
            }
        }
    }

    @Override // X.AbstractC0232Ww
    public String A0e(String str) throws IOException, Wx {
        EnumC0470q2 q2Var = this.A00;
        if (q2Var == EnumC0470q2.VALUE_STRING || (q2Var != null && q2Var != EnumC0470q2.VALUE_NULL && q2Var.isScalarValue())) {
            return A0d();
        }
        return str;
    }

    @Override // X.AbstractC0232Ww
    public final void A0f() {
        if (this.A00 != null) {
            this.A00 = null;
        }
    }

    @Override // X.AbstractC0232Ww
    public final boolean A0g() {
        if (this.A00 != null) {
            return true;
        }
        return false;
    }

    public final void A0m(int i, String str) throws Wx {
        String A05 = AnonymousClass06.A05("Unexpected character (", A0A(i), ")");
        if (str != null) {
            A05 = AnonymousClass06.A05(A05, ": ", str);
        }
        A0o(A05);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A0n(int i, String str) throws Wx {
        if (!A0G(py.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            A0o(AnonymousClass06.A06("Illegal unquoted character (", A0A((char) i), "): has to be escaped using backslash to be included in ", str));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public final void A0p(String str) throws Wx {
        A0o(AnonymousClass06.A04("Unexpected end-of-input", str));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC0232Ww
    public final EnumC0470q2 A0b() throws IOException, Wx {
        EnumC0470q2 A0a = A0a();
        if (A0a == EnumC0470q2.FIELD_NAME) {
            return A0a();
        }
        return A0a;
    }

    public final void A0o(String str) throws Wx {
        throw new Wx(str, A0N());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r3 >= 0) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        X.C0465pq.A00(r14, r1, 0, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r4 < r7) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        r2 = r4 + 1;
        r1 = r12.charAt(r4);
        r0 = r14.A01(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r0 >= 0) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        X.C0465pq.A00(r14, r1, 1, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        r10 = (r3 << 6) | r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        if (r2 < r7) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0052, code lost:
        if (r14.A01 != false) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        r13.A02(r10 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0063, code lost:
        r9 = r2 + 1;
        r2 = r12.charAt(r2);
        r1 = r14.A01(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0070, code lost:
        if (r1 >= 0) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0072, code lost:
        if (r1 == -2) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0074, code lost:
        X.C0465pq.A00(r14, r2, 2, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007f, code lost:
        if (r9 < r7) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0089, code lost:
        r2 = r9 + 1;
        r4 = r12.charAt(r9);
        r3 = r14.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0091, code lost:
        if (r4 != r3) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0093, code lost:
        r13.A02(r10 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009a, code lost:
        X.C0465pq.A00(r14, r4, 3, X.AnonymousClass06.A00("expected padding character '", r3, "'"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ae, code lost:
        r3 = (r10 << 6) | r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b1, code lost:
        if (r9 < r7) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b5, code lost:
        if (r14.A01 != false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b7, code lost:
        r13.A04(r3 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c6, code lost:
        r2 = r9 + 1;
        r0 = r12.charAt(r9);
        r1 = r14.A01(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d0, code lost:
        if (r1 >= 0) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d2, code lost:
        if (r1 == -2) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d4, code lost:
        X.C0465pq.A00(r14, r0, 3, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e0, code lost:
        r13.A04(r3 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e7, code lost:
        r13.A03((r3 << 6) | r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ef, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f0, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r3 = r14.A01(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0q(java.lang.String r12, X.C0440k1 r13, X.C0465pq r14) throws java.io.IOException, X.Wx {
        /*
        // Method dump skipped, instructions count: 258
        */
        throw new UnsupportedOperationException("Method not decompiled: X.DO.A0q(java.lang.String, X.k1, X.pq):void");
    }

    @Override // X.AbstractC0232Ww
    public final EnumC0470q2 A0Z() {
        return this.A00;
    }

    public DO() {
    }

    public DO(int i) {
        super(0);
    }
}
