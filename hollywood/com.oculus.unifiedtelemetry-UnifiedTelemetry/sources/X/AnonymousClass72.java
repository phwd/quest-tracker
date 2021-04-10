package X;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.72  reason: invalid class name */
public final class AnonymousClass72 extends DO {
    public int A00;
    public pw A01 = null;
    public I4 A02;
    public AbstractC0471q3 A03;
    public C0230Wu A04;
    public boolean A05;
    public transient C0440k1 A06;

    public AnonymousClass72(I4 i4, AbstractC0471q3 q3Var) {
        super(0);
        this.A02 = i4;
        this.A00 = -1;
        this.A03 = q3Var;
        this.A04 = new C0230Wu(null, 0, -1, -1);
    }

    @Override // X.DO, X.AbstractC0232Ww
    public final int A0V() {
        return 0;
    }

    @Override // X.DO, X.AbstractC0232Ww
    public final boolean A0h() {
        return false;
    }

    @Override // X.AbstractC0232Ww
    public final int A0L() throws IOException, Wx {
        Number A0Q;
        if (((DO) this).A00 == EnumC0470q2.VALUE_NUMBER_INT) {
            I4 i4 = this.A02;
            A0Q = (Number) i4.A02[this.A00];
        } else {
            A0Q = A0Q();
        }
        return A0Q.intValue();
    }

    @Override // X.AbstractC0232Ww
    public final pw A0N() {
        pw pwVar = this.A01;
        if (pwVar == null) {
            return pw.A01;
        }
        return pwVar;
    }

    @Override // X.AbstractC0232Ww
    public final Number A0Q() throws IOException, Wx {
        EnumC0470q2 q2Var = ((DO) this).A00;
        if (q2Var == null || !q2Var.isNumeric()) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(q2Var);
            sb.append(") not numeric, can not use numeric value accessors");
            throw new Wx(sb.toString(), A0N());
        }
        I4 i4 = this.A02;
        Object obj = i4.A02[this.A00];
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
            throw new IllegalStateException(AnonymousClass06.A04("Internal error: entry should be a Number, but is of type ", obj.getClass().getName()));
        }
    }

    @Override // X.AbstractC0232Ww
    public final Object A0R() {
        if (((DO) this).A00 != EnumC0470q2.VALUE_EMBEDDED_OBJECT) {
            return null;
        }
        I4 i4 = this.A02;
        return i4.A02[this.A00];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        if (r3 == null) goto L_0x001c;
     */
    @Override // X.DO, X.AbstractC0232Ww
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.EnumC0470q2 A0a() throws java.io.IOException, X.Wx {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass72.A0a():X.q2");
    }

    @Override // X.DO, X.AbstractC0232Ww
    public final String A0c() {
        return this.A04.A02;
    }

    @Override // X.DO, X.AbstractC0232Ww
    public final String A0d() {
        Object obj;
        EnumC0470q2 q2Var = ((DO) this).A00;
        if (q2Var == EnumC0470q2.VALUE_STRING || q2Var == EnumC0470q2.FIELD_NAME) {
            I4 i4 = this.A02;
            obj = i4.A02[this.A00];
            if (obj instanceof String) {
                return (String) obj;
            }
        } else if (q2Var == null) {
            return null;
        } else {
            int i = IC.A00[q2Var.ordinal()];
            if (i != 7 && i != 8) {
                return q2Var.asString();
            }
            I4 i42 = this.A02;
            obj = i42.A02[this.A00];
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override // X.DO, X.AbstractC0232Ww
    public final byte[] A0i(C0465pq pqVar) throws IOException, Wx {
        EnumC0470q2 q2Var = ((DO) this).A00;
        if (q2Var == EnumC0470q2.VALUE_EMBEDDED_OBJECT) {
            I4 i4 = this.A02;
            Object obj = i4.A02[this.A00];
            if (obj instanceof byte[]) {
                return (byte[]) obj;
            }
        }
        if (q2Var == EnumC0470q2.VALUE_STRING) {
            String A0d = A0d();
            if (A0d == null) {
                return null;
            }
            C0440k1 k1Var = this.A06;
            if (k1Var == null) {
                k1Var = new C0440k1(100);
                this.A06 = k1Var;
            } else {
                k1Var.A01();
            }
            A0q(A0d, k1Var, pqVar);
            return k1Var.A05();
        }
        StringBuilder sb = new StringBuilder("Current token (");
        sb.append(q2Var);
        sb.append(") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
        throw new Wx(sb.toString(), A0N());
    }

    @Override // java.io.Closeable, X.DO, java.lang.AutoCloseable, X.AbstractC0232Ww
    public final void close() throws IOException {
        if (!this.A05) {
            this.A05 = true;
        }
    }

    @Override // X.AbstractC0232Ww
    public final double A0J() throws IOException, Wx {
        return A0Q().doubleValue();
    }

    @Override // X.AbstractC0232Ww
    public final float A0K() throws IOException, Wx {
        return A0Q().floatValue();
    }

    @Override // X.AbstractC0232Ww
    public final long A0M() throws IOException, Wx {
        return A0Q().longValue();
    }

    @Override // X.AbstractC0232Ww
    public final pw A0O() {
        return A0N();
    }

    @Override // X.AbstractC0232Ww
    public final Integer A0P() throws IOException, Wx {
        Number A0Q = A0Q();
        if (!(A0Q instanceof Integer)) {
            if (A0Q instanceof Long) {
                return AnonymousClass07.A01;
            }
            if (A0Q instanceof Double) {
                return AnonymousClass07.A04;
            }
            if (A0Q instanceof BigDecimal) {
                return AnonymousClass07.A05;
            }
            if (A0Q instanceof BigInteger) {
                return AnonymousClass07.A02;
            }
            if (A0Q instanceof Float) {
                return AnonymousClass07.A03;
            }
            if (!(A0Q instanceof Short)) {
                return null;
            }
        }
        return AnonymousClass07.A00;
    }

    @Override // X.AbstractC0232Ww
    public final BigDecimal A0S() throws IOException, Wx {
        Number A0Q = A0Q();
        if (A0Q instanceof BigDecimal) {
            return (BigDecimal) A0Q;
        }
        switch (A0P().intValue()) {
            case 0:
            case 1:
                return BigDecimal.valueOf(A0Q.longValue());
            case 2:
                return new BigDecimal((BigInteger) A0Q);
            default:
                return BigDecimal.valueOf(A0Q.doubleValue());
        }
    }

    @Override // X.AbstractC0232Ww
    public final BigInteger A0T() throws IOException, Wx {
        Number A0Q = A0Q();
        if (A0Q instanceof BigInteger) {
            return (BigInteger) A0Q;
        }
        if (A0P() == AnonymousClass07.A05) {
            return ((BigDecimal) A0Q).toBigInteger();
        }
        return BigInteger.valueOf(A0Q.longValue());
    }

    @Override // X.DO, X.AbstractC0232Ww
    public final int A0U() {
        String A0d = A0d();
        if (A0d == null) {
            return 0;
        }
        return A0d.length();
    }

    @Override // X.DO, X.AbstractC0232Ww
    public final char[] A0j() {
        String A0d = A0d();
        if (A0d == null) {
            return null;
        }
        return A0d.toCharArray();
    }

    @Override // X.DO
    public final void A0k() throws Wx {
        jv.A02();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC0232Ww
    public final AbstractC0471q3 A0H() {
        return this.A03;
    }
}
