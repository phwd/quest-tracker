package X;

import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0C6  reason: invalid class name */
public final class AnonymousClass0C6 extends AbstractC01190Sv {
    public int A00;
    public AnonymousClass0o8 A01 = null;
    public C04960rc A02;
    public AbstractC03650oF A03;
    public AnonymousClass0iO A04;
    public boolean A05;
    public transient C03940or A06;

    public AnonymousClass0C6(C04960rc r5, AbstractC03650oF r6) {
        super(0);
        this.A02 = r5;
        this.A00 = -1;
        this.A03 = r6;
        this.A04 = new AnonymousClass0iO(null, 0, -1, -1);
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final int A0e() {
        return 0;
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final boolean A0p() {
        return false;
    }

    @Override // X.AbstractC02280iQ
    public final int A0T() throws IOException, C02290iR {
        Number A0Y;
        if (((AbstractC01190Sv) this).A00 == EnumC03640oE.VALUE_NUMBER_INT) {
            C04960rc r0 = this.A02;
            A0Y = (Number) r0.A02[this.A00];
        } else {
            A0Y = A0Y();
        }
        return A0Y.intValue();
    }

    @Override // X.AbstractC02280iQ
    public final AnonymousClass0o8 A0V() {
        AnonymousClass0o8 r0 = this.A01;
        if (r0 == null) {
            return AnonymousClass0o8.A01;
        }
        return r0;
    }

    @Override // X.AbstractC02280iQ
    public final Number A0Y() throws IOException, C02290iR {
        EnumC03640oE r2 = ((AbstractC01190Sv) this).A00;
        if (r2 == null || !r2.isNumeric()) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(r2);
            sb.append(") not numeric, can not use numeric value accessors");
            throw new C02290iR(sb.toString(), A0V());
        }
        C04960rc r0 = this.A02;
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
            throw new IllegalStateException(AnonymousClass006.A07("Internal error: entry should be a Number, but is of type ", obj.getClass().getName()));
        }
    }

    @Override // X.AbstractC02280iQ
    public final Object A0Z() {
        if (((AbstractC01190Sv) this).A00 != EnumC03640oE.VALUE_EMBEDDED_OBJECT) {
            return null;
        }
        C04960rc r0 = this.A02;
        return r0.A02[this.A00];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        if (r3 == null) goto L_0x001c;
     */
    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.EnumC03640oE A0j() throws java.io.IOException, X.C02290iR {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0C6.A0j():X.0oE");
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final String A0l() {
        return this.A04.A02;
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final String A0m() {
        Object obj;
        EnumC03640oE r3 = ((AbstractC01190Sv) this).A00;
        if (r3 == EnumC03640oE.VALUE_STRING || r3 == EnumC03640oE.FIELD_NAME) {
            C04960rc r0 = this.A02;
            obj = r0.A02[this.A00];
            if (obj instanceof String) {
                return (String) obj;
            }
        } else if (r3 == null) {
            return null;
        } else {
            int i = C04950rb.A00[r3.ordinal()];
            if (i != 7 && i != 8) {
                return r3.asString();
            }
            C04960rc r02 = this.A02;
            obj = r02.A02[this.A00];
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final byte[] A0r(AnonymousClass0o2 r4) throws IOException, C02290iR {
        EnumC03640oE r2 = ((AbstractC01190Sv) this).A00;
        if (r2 == EnumC03640oE.VALUE_EMBEDDED_OBJECT) {
            C04960rc r0 = this.A02;
            Object obj = r0.A02[this.A00];
            if (obj instanceof byte[]) {
                return (byte[]) obj;
            }
        }
        if (r2 == EnumC03640oE.VALUE_STRING) {
            String A0m = A0m();
            if (A0m == null) {
                return null;
            }
            C03940or r1 = this.A06;
            if (r1 == null) {
                r1 = new C03940or(100);
                this.A06 = r1;
            } else {
                r1.A01();
            }
            A0z(A0m, r1, r4);
            return r1.A05();
        }
        StringBuilder sb = new StringBuilder("Current token (");
        sb.append(r2);
        sb.append(") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
        throw new C02290iR(sb.toString(), A0V());
    }

    @Override // X.AbstractC01190Sv, java.io.Closeable, java.lang.AutoCloseable, X.AbstractC02280iQ
    public final void close() throws IOException {
        if (!this.A05) {
            this.A05 = true;
        }
    }

    @Override // X.AbstractC02280iQ
    public final AbstractC03650oF A0N() {
        return this.A03;
    }

    @Override // X.AbstractC02280iQ
    public final double A0R() throws IOException, C02290iR {
        return A0Y().doubleValue();
    }

    @Override // X.AbstractC02280iQ
    public final float A0S() throws IOException, C02290iR {
        return A0Y().floatValue();
    }

    @Override // X.AbstractC02280iQ
    public final long A0U() throws IOException, C02290iR {
        return A0Y().longValue();
    }

    @Override // X.AbstractC02280iQ
    public final AnonymousClass0o8 A0W() {
        return A0V();
    }

    @Override // X.AbstractC02280iQ
    public final Integer A0X() throws IOException, C02290iR {
        Number A0Y = A0Y();
        if (!(A0Y instanceof Integer)) {
            if (A0Y instanceof Long) {
                return AnonymousClass007.A01;
            }
            if (A0Y instanceof Double) {
                return AnonymousClass007.A05;
            }
            if (A0Y instanceof BigDecimal) {
                return AnonymousClass007.A06;
            }
            if (A0Y instanceof BigInteger) {
                return AnonymousClass007.A03;
            }
            if (A0Y instanceof Float) {
                return AnonymousClass007.A04;
            }
            if (!(A0Y instanceof Short)) {
                return null;
            }
        }
        return AnonymousClass007.A00;
    }

    @Override // X.AbstractC02280iQ
    public final BigDecimal A0a() throws IOException, C02290iR {
        Number A0Y = A0Y();
        if (A0Y instanceof BigDecimal) {
            return (BigDecimal) A0Y;
        }
        switch (A0X().intValue()) {
            case 0:
            case 1:
                return BigDecimal.valueOf(A0Y.longValue());
            case 2:
                return new BigDecimal((BigInteger) A0Y);
            default:
                return BigDecimal.valueOf(A0Y.doubleValue());
        }
    }

    @Override // X.AbstractC02280iQ
    public final BigInteger A0b() throws IOException, C02290iR {
        Number A0Y = A0Y();
        if (A0Y instanceof BigInteger) {
            return (BigInteger) A0Y;
        }
        if (A0X() == AnonymousClass007.A06) {
            return ((BigDecimal) A0Y).toBigInteger();
        }
        return BigInteger.valueOf(A0Y.longValue());
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final int A0d() {
        String A0m = A0m();
        if (A0m == null) {
            return 0;
        }
        return A0m.length();
    }

    @Override // X.AbstractC01190Sv, X.AbstractC02280iQ
    public final char[] A0s() {
        String A0m = A0m();
        if (A0m == null) {
            return null;
        }
        return A0m.toCharArray();
    }

    @Override // X.AbstractC01190Sv
    public final void A0t() throws C02290iR {
        C03980ov.A03();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    @Override // X.AbstractC01190Sv, X.AbstractC03700oK, X.AbstractC02280iQ
    public final C03690oJ version() {
        return PackageVersion.VERSION;
    }
}
