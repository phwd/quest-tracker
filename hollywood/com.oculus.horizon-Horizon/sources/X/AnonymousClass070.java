package X;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.070  reason: invalid class name */
public final class AnonymousClass070 extends AnonymousClass0HQ {
    public int A00;
    public AnonymousClass0jc A01 = null;
    public C06480nD A02;
    public AnonymousClass0jj A03;
    public C04080gn A04;
    public boolean A05;
    public transient C05110kM A06;

    public AnonymousClass070(C06480nD r5, AnonymousClass0jj r6) {
        super(0);
        this.A02 = r5;
        this.A00 = -1;
        this.A03 = r6;
        this.A04 = new C04080gn(null, 0, -1, -1);
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final int A0W() {
        return 0;
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final boolean A0h() {
        return false;
    }

    @Override // X.AbstractC04100gp
    public final int A0M() throws IOException, C04110gq {
        Number A0R;
        if (((AnonymousClass0HQ) this).A00 == EnumC04820ji.VALUE_NUMBER_INT) {
            C06480nD r0 = this.A02;
            A0R = (Number) r0.A02[this.A00];
        } else {
            A0R = A0R();
        }
        return A0R.intValue();
    }

    @Override // X.AbstractC04100gp
    public final AnonymousClass0jc A0O() {
        AnonymousClass0jc r0 = this.A01;
        if (r0 == null) {
            return AnonymousClass0jc.A01;
        }
        return r0;
    }

    @Override // X.AbstractC04100gp
    public final Number A0R() throws IOException, C04110gq {
        EnumC04820ji r2 = ((AnonymousClass0HQ) this).A00;
        if (r2 == null || !r2.isNumeric()) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(r2);
            sb.append(") not numeric, can not use numeric value accessors");
            throw new C04110gq(sb.toString(), A0O());
        }
        C06480nD r0 = this.A02;
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

    @Override // X.AbstractC04100gp
    public final Object A0S() {
        if (((AnonymousClass0HQ) this).A00 != EnumC04820ji.VALUE_EMBEDDED_OBJECT) {
            return null;
        }
        C06480nD r0 = this.A02;
        return r0.A02[this.A00];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        if (r3 == null) goto L_0x001c;
     */
    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.EnumC04820ji A0b() throws java.io.IOException, X.C04110gq {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass070.A0b():X.0ji");
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final String A0d() {
        return this.A04.A02;
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final String A0e() {
        Object obj;
        EnumC04820ji r3 = ((AnonymousClass0HQ) this).A00;
        if (r3 == EnumC04820ji.VALUE_STRING || r3 == EnumC04820ji.FIELD_NAME) {
            C06480nD r0 = this.A02;
            obj = r0.A02[this.A00];
            if (obj instanceof String) {
                return (String) obj;
            }
        } else if (r3 == null) {
            return null;
        } else {
            int i = C06470nC.A00[r3.ordinal()];
            if (i != 7 && i != 8) {
                return r3.asString();
            }
            C06480nD r02 = this.A02;
            obj = r02.A02[this.A00];
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final byte[] A0j(C04780jW r4) throws IOException, C04110gq {
        EnumC04820ji r2 = ((AnonymousClass0HQ) this).A00;
        if (r2 == EnumC04820ji.VALUE_EMBEDDED_OBJECT) {
            C06480nD r0 = this.A02;
            Object obj = r0.A02[this.A00];
            if (obj instanceof byte[]) {
                return (byte[]) obj;
            }
        }
        if (r2 == EnumC04820ji.VALUE_STRING) {
            String A0e = A0e();
            if (A0e == null) {
                return null;
            }
            C05110kM r1 = this.A06;
            if (r1 == null) {
                r1 = new C05110kM(100);
                this.A06 = r1;
            } else {
                r1.A01();
            }
            A0r(A0e, r1, r4);
            return r1.A05();
        }
        StringBuilder sb = new StringBuilder("Current token (");
        sb.append(r2);
        sb.append(") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
        throw new C04110gq(sb.toString(), A0O());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.AnonymousClass0HQ, X.AbstractC04100gp
    public final void close() throws IOException {
        if (!this.A05) {
            this.A05 = true;
        }
    }

    @Override // X.AbstractC04100gp
    public final AnonymousClass0jj A0I() {
        return this.A03;
    }

    @Override // X.AbstractC04100gp
    public final double A0K() throws IOException, C04110gq {
        return A0R().doubleValue();
    }

    @Override // X.AbstractC04100gp
    public final float A0L() throws IOException, C04110gq {
        return A0R().floatValue();
    }

    @Override // X.AbstractC04100gp
    public final long A0N() throws IOException, C04110gq {
        return A0R().longValue();
    }

    @Override // X.AbstractC04100gp
    public final AnonymousClass0jc A0P() {
        return A0O();
    }

    @Override // X.AbstractC04100gp
    public final Integer A0Q() throws IOException, C04110gq {
        Number A0R = A0R();
        if (!(A0R instanceof Integer)) {
            if (A0R instanceof Long) {
                return AnonymousClass007.A01;
            }
            if (A0R instanceof Double) {
                return AnonymousClass007.A0E;
            }
            if (A0R instanceof BigDecimal) {
                return AnonymousClass007.A0F;
            }
            if (A0R instanceof BigInteger) {
                return AnonymousClass007.A0C;
            }
            if (A0R instanceof Float) {
                return AnonymousClass007.A0D;
            }
            if (!(A0R instanceof Short)) {
                return null;
            }
        }
        return AnonymousClass007.A00;
    }

    @Override // X.AbstractC04100gp
    public final BigDecimal A0T() throws IOException, C04110gq {
        Number A0R = A0R();
        if (A0R instanceof BigDecimal) {
            return (BigDecimal) A0R;
        }
        switch (A0Q().intValue()) {
            case 0:
            case 1:
                return BigDecimal.valueOf(A0R.longValue());
            case 2:
                return new BigDecimal((BigInteger) A0R);
            default:
                return BigDecimal.valueOf(A0R.doubleValue());
        }
    }

    @Override // X.AbstractC04100gp
    public final BigInteger A0U() throws IOException, C04110gq {
        Number A0R = A0R();
        if (A0R instanceof BigInteger) {
            return (BigInteger) A0R;
        }
        if (A0Q() == AnonymousClass007.A0F) {
            return ((BigDecimal) A0R).toBigInteger();
        }
        return BigInteger.valueOf(A0R.longValue());
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final int A0V() {
        String A0e = A0e();
        if (A0e == null) {
            return 0;
        }
        return A0e.length();
    }

    @Override // X.AnonymousClass0HQ, X.AbstractC04100gp
    public final char[] A0k() {
        String A0e = A0e();
        if (A0e == null) {
            return null;
        }
        return A0e.toCharArray();
    }

    @Override // X.AnonymousClass0HQ
    public final void A0l() throws C04110gq {
        C05150kQ.A02();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
