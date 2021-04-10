package X;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0C8  reason: invalid class name */
public final class AnonymousClass0C8 extends AnonymousClass0LO {
    public int A00;
    public C05880lZ A01 = null;
    public C07300p6 A02;
    public AbstractC05940lg A03;
    public C02610aR A04;
    public boolean A05;
    public transient C06190mJ A06;

    public AnonymousClass0C8(C07300p6 r4, AbstractC05940lg r5) {
        super(0);
        this.A02 = r4;
        this.A00 = -1;
        this.A03 = r5;
        this.A04 = new C02610aR(null, 0);
    }

    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT
    public final int A08() {
        return 0;
    }

    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT
    public final boolean A0X() {
        return false;
    }

    @Override // X.AnonymousClass0aT
    public final int A06() throws IOException, C02630aU {
        Number A0K;
        if (super.A00 == EnumC05930lf.VALUE_NUMBER_INT) {
            C07300p6 r0 = this.A02;
            A0K = (Number) r0.A02[this.A00];
        } else {
            A0K = A0K();
        }
        return A0K.intValue();
    }

    @Override // X.AnonymousClass0aT
    public final C05880lZ A0D() {
        C05880lZ r0 = this.A01;
        if (r0 == null) {
            return C05880lZ.A01;
        }
        return r0;
    }

    @Override // X.AnonymousClass0aT
    public final AbstractC05940lg A0I() {
        return this.A03;
    }

    @Override // X.AnonymousClass0aT
    public final Number A0K() throws IOException, C02630aU {
        EnumC05930lf r2 = super.A00;
        if (r2 == null || !r2.isNumeric()) {
            throw new C02630aU("Current token (" + r2 + ") not numeric, can not use numeric value accessors", A0D());
        }
        C07300p6 r0 = this.A02;
        Object obj = r0.A02[this.A00];
        if (obj instanceof Number) {
            return (Number) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.indexOf(46) >= 0) {
                return Double.valueOf(Double.parseDouble(str));
            }
            return Long.valueOf(Long.parseLong(str));
        } else if (obj == null) {
            return null;
        } else {
            throw new IllegalStateException(AnonymousClass006.A05("Internal error: entry should be a Number, but is of type ", obj.getClass().getName()));
        }
    }

    @Override // X.AnonymousClass0aT
    public final Object A0M() {
        if (super.A00 != EnumC05930lf.VALUE_EMBEDDED_OBJECT) {
            return null;
        }
        C07300p6 r0 = this.A02;
        return r0.A02[this.A00];
    }

    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT
    public final String A0O() {
        return this.A04.A01;
    }

    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT
    public final String A0P() {
        Object obj;
        EnumC05930lf r3 = super.A00;
        if (r3 == EnumC05930lf.VALUE_STRING || r3 == EnumC05930lf.FIELD_NAME) {
            C07300p6 r0 = this.A02;
            obj = r0.A02[this.A00];
            if (obj instanceof String) {
                return (String) obj;
            }
        } else if (r3 == null) {
            return null;
        } else {
            int i = C07290p5.A00[r3.ordinal()];
            if (i != 7 && i != 8) {
                return r3.asString();
            }
            C07300p6 r02 = this.A02;
            obj = r02.A02[this.A00];
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0185, code lost:
        X.C05830lU.A00(r14, r2, 2, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0198, code lost:
        X.C05830lU.A00(r14, r9, 3, X.AnonymousClass006.A00("expected padding character '", r5, "'"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01b3, code lost:
        X.C05830lU.A00(r14, r1, 3, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01be, code lost:
        X.C05830lU.A00(r14, r1, 0, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:?, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:?, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
        throw new java.lang.IllegalArgumentException("Unexpected end-of-String in base64 content");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:?, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        if (r1 > 127) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        r5 = r14.A04[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        r5 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
        if (r5 >= 0) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0066, code lost:
        if (r6 < r3) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006a, code lost:
        r2 = r6 + 1;
        r1 = r4.charAt(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0072, code lost:
        if (r1 > 127) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0074, code lost:
        r0 = r14.A04[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0079, code lost:
        r0 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007a, code lost:
        if (r0 >= 0) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007e, code lost:
        r12 = (r5 << 6) | r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0081, code lost:
        if (r2 < r3) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0085, code lost:
        r11 = r2 + 1;
        r2 = r4.charAt(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008d, code lost:
        if (r2 > 127) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008f, code lost:
        r1 = r14.A04[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0094, code lost:
        r1 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0098, code lost:
        if (r1 >= 0) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009a, code lost:
        if (r1 == -2) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x009e, code lost:
        if (r11 < r3) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a2, code lost:
        r2 = r11 + 1;
        r9 = r4.charAt(r11);
        r5 = r14.A00;
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ab, code lost:
        if (r9 != r5) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ad, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00ae, code lost:
        if (r0 != false) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00b2, code lost:
        r8.A01(r12 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00b8, code lost:
        r5 = (r12 << 6) | r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00bb, code lost:
        if (r11 < r3) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00be, code lost:
        r2 = r11 + 1;
        r1 = r4.charAt(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00c6, code lost:
        if (r1 > 127) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c8, code lost:
        r0 = r14.A04[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00cd, code lost:
        r0 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00ce, code lost:
        if (r0 >= 0) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00d0, code lost:
        if (r0 == -2) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00d4, code lost:
        r8.A02(r5 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00db, code lost:
        r5 = (r5 << 6) | r0;
        r10 = r8.A00;
        r1 = r10 + 2;
        r6 = r8.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00e5, code lost:
        if (r1 >= r6.length) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00e7, code lost:
        r9 = r10 + 1;
        r8.A00 = r9;
        r6[r10] = (byte) (r5 >> 16);
        r1 = r9 + 1;
        r8.A00 = r1;
        r6[r9] = (byte) (r5 >> 8);
        r8.A00 = r1 + 1;
        r6[r1] = (byte) r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0102, code lost:
        r8.A01(r5 >> 16);
        r8.A01(r5 >> 8);
        r8.A01(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0113, code lost:
        if (r14.A02 != false) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0115, code lost:
        r8.A01(r12 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x011d, code lost:
        if (r14.A02 != false) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x011f, code lost:
        r8.A02(r5 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0171, code lost:
        X.C05830lU.A00(r14, r1, 1, null);
     */
    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] A0Y(X.C05830lU r14) throws java.io.IOException, X.C02630aU {
        /*
        // Method dump skipped, instructions count: 534
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0C8.A0Y(X.0lU):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        if (r3 == null) goto L_0x001c;
     */
    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.EnumC05930lf A0a() throws java.io.IOException, X.C02630aU {
        /*
        // Method dump skipped, instructions count: 136
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0C8.A0a():X.0lf");
    }

    @Override // X.AnonymousClass0LO
    public final void A0b() throws C02630aU {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (!this.A05) {
            this.A05 = true;
        }
    }

    @Override // X.AnonymousClass0aT
    public final double A03() throws IOException, C02630aU {
        return A0K().doubleValue();
    }

    @Override // X.AnonymousClass0aT
    public final float A04() throws IOException, C02630aU {
        return A0K().floatValue();
    }

    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT
    public final int A07() {
        String A0P = A0P();
        if (A0P == null) {
            return 0;
        }
        return A0P.length();
    }

    @Override // X.AnonymousClass0aT
    public final long A0B() throws IOException, C02630aU {
        return A0K().longValue();
    }

    @Override // X.AnonymousClass0aT
    public final C05880lZ A0E() {
        return A0D();
    }

    @Override // X.AnonymousClass0aT
    public final Integer A0J() throws IOException, C02630aU {
        Number A0K = A0K();
        if (!(A0K instanceof Integer)) {
            if (A0K instanceof Long) {
                return AnonymousClass007.A01;
            }
            if (A0K instanceof Double) {
                return AnonymousClass007.A0E;
            }
            if (A0K instanceof BigDecimal) {
                return AnonymousClass007.A0F;
            }
            if (A0K instanceof BigInteger) {
                return AnonymousClass007.A0C;
            }
            if (A0K instanceof Float) {
                return AnonymousClass007.A0D;
            }
            if (!(A0K instanceof Short)) {
                return null;
            }
        }
        return AnonymousClass007.A00;
    }

    @Override // X.AnonymousClass0aT
    public final BigDecimal A0R() throws IOException, C02630aU {
        Number A0K = A0K();
        if (A0K instanceof BigDecimal) {
            return (BigDecimal) A0K;
        }
        switch (A0J().intValue()) {
            case 0:
            case 1:
                return BigDecimal.valueOf(A0K.longValue());
            case 2:
                return new BigDecimal((BigInteger) A0K);
            default:
                return BigDecimal.valueOf(A0K.doubleValue());
        }
    }

    @Override // X.AnonymousClass0aT
    public final BigInteger A0S() throws IOException, C02630aU {
        Number A0K = A0K();
        if (A0K instanceof BigInteger) {
            return (BigInteger) A0K;
        }
        if (A0J() == AnonymousClass007.A0F) {
            return ((BigDecimal) A0K).toBigInteger();
        }
        return BigInteger.valueOf(A0K.longValue());
    }

    @Override // X.AnonymousClass0LO, X.AnonymousClass0aT
    public final char[] A0Z() {
        String A0P = A0P();
        if (A0P == null) {
            return null;
        }
        return A0P.toCharArray();
    }
}
