package X;

import java.io.IOException;

/* renamed from: X.0Sv  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC01190Sv extends AbstractC02280iQ {
    public EnumC03640oE A00;

    public static final String A0D(int i) {
        StringBuilder sb;
        char c = (char) i;
        if (Character.isISOControl(c)) {
            return AnonymousClass006.A04("(CTRL-CHAR, code ", i, ")");
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

    @Override // X.AbstractC02280iQ
    public abstract int A0d() throws IOException, C02290iR;

    @Override // X.AbstractC02280iQ
    public abstract int A0e() throws IOException, C02290iR;

    @Override // X.AbstractC02280iQ
    public abstract EnumC03640oE A0j() throws IOException, C02290iR;

    @Override // X.AbstractC02280iQ
    public abstract String A0l() throws IOException, C02290iR;

    @Override // X.AbstractC02280iQ
    public abstract String A0m() throws IOException, C02290iR;

    @Override // X.AbstractC02280iQ
    public abstract boolean A0p();

    @Override // X.AbstractC02280iQ
    public abstract byte[] A0r(AnonymousClass0o2 v) throws IOException, C02290iR;

    @Override // X.AbstractC02280iQ
    public abstract char[] A0s() throws IOException, C02290iR;

    public abstract void A0t() throws C02290iR;

    public final void A0u(int i) throws C02290iR {
        A0x(AnonymousClass006.A09("Illegal character (", A0D((char) i), "): only regular white space (\\r, \\n, \\t) is allowed between tokens"));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AbstractC02280iQ
    public abstract void close() throws IOException;

    @Override // X.AbstractC02280iQ
    public final double A0c(double d) throws IOException, C02290iR {
        EnumC03640oE r0 = this.A00;
        if (r0 != null) {
            switch (AnonymousClass0oM.A00[r0.ordinal()]) {
                case 5:
                case 11:
                    return A0R();
                case 6:
                    return 1.0d;
                case 7:
                case 8:
                    return 0.0d;
                case 9:
                    Object A0Z = A0Z();
                    if (A0Z instanceof Number) {
                        return ((Number) A0Z).doubleValue();
                    }
                    break;
                case 10:
                    String A0m = A0m();
                    if (A0m != null) {
                        String trim = A0m.trim();
                        if (trim.length() != 0) {
                            try {
                                return C03780oX.A00(trim);
                            } catch (NumberFormatException unused) {
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        return d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r3 < r4) goto L_0x0045;
     */
    @Override // X.AbstractC02280iQ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A0f(int r7) throws java.io.IOException, X.C02290iR {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC01190Sv.A0f(int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r3 < r4) goto L_0x0045;
     */
    @Override // X.AbstractC02280iQ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A0g(long r7) throws java.io.IOException, X.C02290iR {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC01190Sv.A0g(long):long");
    }

    @Override // X.AbstractC02280iQ
    public final AbstractC02280iQ A0h() throws IOException, C02290iR {
        EnumC03640oE r1 = this.A00;
        if (r1 != EnumC03640oE.START_OBJECT && r1 != EnumC03640oE.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            EnumC03640oE A0j = A0j();
            if (A0j == null) {
                A0t();
                return this;
            }
            int i2 = AnonymousClass0oM.A00[A0j.ordinal()];
            if (i2 == 1 || i2 == 2) {
                i++;
            } else if ((i2 == 3 || i2 == 4) && i - 1 == 0) {
                return this;
            }
        }
    }

    @Override // X.AbstractC02280iQ
    public String A0n(String str) throws IOException, C02290iR {
        EnumC03640oE r1 = this.A00;
        if (r1 == EnumC03640oE.VALUE_STRING || (r1 != null && r1 != EnumC03640oE.VALUE_NULL && r1.isScalarValue())) {
            return A0m();
        }
        return str;
    }

    @Override // X.AbstractC02280iQ
    public final boolean A0o() {
        if (this.A00 != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031 A[RETURN] */
    @Override // X.AbstractC02280iQ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0q(boolean r4) throws java.io.IOException, X.C02290iR {
        /*
            r3 = this;
            X.0oE r0 = r3.A00
            if (r0 == 0) goto L_0x0011
            int[] r1 = X.AnonymousClass0oM.A00
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
            java.lang.Object r1 = r3.A0Z()
            boolean r0 = r1 instanceof java.lang.Boolean
            if (r0 == 0) goto L_0x0021
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r4 = r1.booleanValue()
            return r4
        L_0x0021:
            java.lang.String r0 = r3.A0m()
            java.lang.String r1 = r0.trim()
            java.lang.String r0 = "true"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0011
        L_0x0031:
            return r2
        L_0x0032:
            int r0 = r3.A0T()
            if (r0 == 0) goto L_0x0039
            r1 = 1
        L_0x0039:
            return r1
            switch-data {5->0x0032, 6->0x0031, 7->0x0039, 8->0x0039, 9->0x0012, 10->0x0021, }
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC01190Sv.A0q(boolean):boolean");
    }

    public final void A0v(int i, String str) throws C02290iR {
        String A09 = AnonymousClass006.A09("Unexpected character (", A0D(i), ")");
        if (str != null) {
            A09 = AnonymousClass006.A09(A09, ": ", str);
        }
        A0x(A09);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public final void A0w(int i, String str) throws C02290iR {
        if (!A0L(EnumC03610oA.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            A0x(AnonymousClass006.A0B("Illegal unquoted character (", A0D((char) i), "): has to be escaped using backslash to be included in ", str));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public final void A0y(String str) throws C02290iR {
        A0x(AnonymousClass006.A07("Unexpected end-of-input", str));
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC02280iQ
    public final EnumC03640oE A0i() {
        return this.A00;
    }

    @Override // X.AbstractC02280iQ
    public final EnumC03640oE A0k() throws IOException, C02290iR {
        EnumC03640oE A0j = A0j();
        if (A0j == EnumC03640oE.FIELD_NAME) {
            return A0j();
        }
        return A0j;
    }

    public final void A0x(String str) throws C02290iR {
        throw new C02290iR(str, A0V());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r3 >= 0) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        X.AnonymousClass0o2.A00(r14, r1, 0, null);
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
        X.AnonymousClass0o2.A00(r14, r1, 1, null);
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
        X.AnonymousClass0o2.A00(r14, r2, 2, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b7, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00bf, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c0, code lost:
        X.AnonymousClass0o2.A00(r14, r4, 3, X.AnonymousClass006.A02("expected padding character '", r3, "'"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d2, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d5, code lost:
        if (r14.A02 != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d7, code lost:
        r13.A04(r3 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00dc, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e4, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e5, code lost:
        X.AnonymousClass0o2.A00(r14, r0, 3, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00ef, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r3 = r14.A01(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0z(java.lang.String r12, X.C03940or r13, X.AnonymousClass0o2 r14) throws java.io.IOException, X.C02290iR {
        /*
        // Method dump skipped, instructions count: 257
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC01190Sv.A0z(java.lang.String, X.0or, X.0o2):void");
    }

    @Override // X.AbstractC03700oK, X.AbstractC02280iQ
    public C03690oJ version() {
        return C03980ov.A01(getClass());
    }

    public AbstractC01190Sv() {
    }

    public AbstractC01190Sv(int i) {
        super(0);
    }
}
