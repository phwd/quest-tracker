package X;

import java.io.IOException;

/* renamed from: X.0HQ  reason: invalid class name */
public abstract class AnonymousClass0HQ extends AbstractC04100gp {
    public EnumC04820ji A00;

    public static final String A0A(int i) {
        StringBuilder sb;
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return AnonymousClass006.A02("(CTRL-CHAR, code ", i, ")");
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

    @Override // X.AbstractC04100gp
    public abstract int A0V() throws IOException, C04110gq;

    @Override // X.AbstractC04100gp
    public abstract int A0W() throws IOException, C04110gq;

    @Override // X.AbstractC04100gp
    public abstract EnumC04820ji A0b() throws IOException, C04110gq;

    @Override // X.AbstractC04100gp
    public abstract String A0d() throws IOException, C04110gq;

    @Override // X.AbstractC04100gp
    public abstract String A0e() throws IOException, C04110gq;

    @Override // X.AbstractC04100gp
    public abstract boolean A0h();

    @Override // X.AbstractC04100gp
    public abstract byte[] A0j(C04780jW v) throws IOException, C04110gq;

    @Override // X.AbstractC04100gp
    public abstract char[] A0k() throws IOException, C04110gq;

    public abstract void A0l() throws C04110gq;

    public final void A0m(int i) throws C04110gq {
        A0p(AnonymousClass006.A07("Illegal character (", A0A((char) i), "): only regular white space (\\r, \\n, \\t) is allowed between tokens"));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC04100gp
    public abstract void close() throws IOException;

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r3 < r4) goto L_0x0045;
     */
    @Override // X.AbstractC04100gp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A0X(int r7) throws java.io.IOException, X.C04110gq {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HQ.A0X(int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r3 < r4) goto L_0x0045;
     */
    @Override // X.AbstractC04100gp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A0Y(long r7) throws java.io.IOException, X.C04110gq {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HQ.A0Y(long):long");
    }

    @Override // X.AbstractC04100gp
    public final AbstractC04100gp A0Z() throws IOException, C04110gq {
        EnumC04820ji r1 = this.A00;
        if (r1 != EnumC04820ji.START_OBJECT && r1 != EnumC04820ji.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            EnumC04820ji A0b = A0b();
            if (A0b == null) {
                A0l();
                return this;
            }
            int i2 = C04890jq.A00[A0b.ordinal()];
            if (i2 == 1 || i2 == 2) {
                i++;
            } else if ((i2 == 3 || i2 == 4) && i - 1 == 0) {
                return this;
            }
        }
    }

    @Override // X.AbstractC04100gp
    public String A0f(String str) throws IOException, C04110gq {
        EnumC04820ji r1 = this.A00;
        if (r1 == EnumC04820ji.VALUE_STRING || (r1 != null && r1 != EnumC04820ji.VALUE_NULL && r1.isScalarValue())) {
            return A0e();
        }
        return str;
    }

    @Override // X.AbstractC04100gp
    public final boolean A0g() {
        if (this.A00 != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031 A[RETURN] */
    @Override // X.AbstractC04100gp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0i(boolean r4) throws java.io.IOException, X.C04110gq {
        /*
            r3 = this;
            X.0ji r0 = r3.A00
            if (r0 == 0) goto L_0x0011
            int[] r1 = X.C04890jq.A00
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r2 = 1
            switch(r0) {
                case 5: goto L_0x0032;
                case 6: goto L_0x0031;
                case 7: goto L_0x0039;
                case 8: goto L_0x0039;
                case 9: goto L_0x0012;
                case 10: goto L_0x0021;
                default: goto L_0x0011;
            }
        L_0x0011:
            return r4
        L_0x0012:
            java.lang.Object r1 = r3.A0S()
            boolean r0 = r1 instanceof java.lang.Boolean
            if (r0 == 0) goto L_0x0021
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r4 = r1.booleanValue()
            return r4
        L_0x0021:
            java.lang.String r0 = r3.A0e()
            java.lang.String r1 = r0.trim()
            java.lang.String r0 = "true"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0011
        L_0x0031:
            return r2
        L_0x0032:
            int r0 = r3.A0M()
            if (r0 == 0) goto L_0x0039
            r1 = 1
        L_0x0039:
            return r1
            switch-data {5->0x0032, 6->0x0031, 7->0x0039, 8->0x0039, 9->0x0012, 10->0x0021, }
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HQ.A0i(boolean):boolean");
    }

    public final void A0n(int i, String str) throws C04110gq {
        String A07 = AnonymousClass006.A07("Unexpected character (", A0A(i), ")");
        if (str != null) {
            A07 = AnonymousClass006.A07(A07, ": ", str);
        }
        A0p(A07);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A0o(int i, String str) throws C04110gq {
        if (!A0H(AnonymousClass0je.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            A0p(AnonymousClass006.A08("Illegal unquoted character (", A0A((char) i), "): has to be escaped using backslash to be included in ", str));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public final void A0q(String str) throws C04110gq {
        A0p(AnonymousClass006.A05("Unexpected end-of-input", str));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC04100gp
    public final EnumC04820ji A0a() {
        return this.A00;
    }

    @Override // X.AbstractC04100gp
    public final EnumC04820ji A0c() throws IOException, C04110gq {
        EnumC04820ji A0b = A0b();
        if (A0b == EnumC04820ji.FIELD_NAME) {
            return A0b();
        }
        return A0b;
    }

    public final void A0p(String str) throws C04110gq {
        throw new C04110gq(str, A0O());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r3 >= 0) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        X.C04780jW.A00(r14, r1, 0, null);
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
        X.C04780jW.A00(r14, r1, 1, null);
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
        X.C04780jW.A00(r14, r2, 2, null);
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
        X.C04780jW.A00(r14, r4, 3, X.AnonymousClass006.A00("expected padding character '", r3, "'"));
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
        X.C04780jW.A00(r14, r0, 3, null);
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
    public final void A0r(java.lang.String r12, X.C05110kM r13, X.C04780jW r14) throws java.io.IOException, X.C04110gq {
        /*
        // Method dump skipped, instructions count: 258
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HQ.A0r(java.lang.String, X.0kM, X.0jW):void");
    }

    public AnonymousClass0HQ() {
    }

    public AnonymousClass0HQ(int i) {
        super(0);
    }
}
