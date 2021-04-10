package X;

import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0OD  reason: invalid class name */
public final class AnonymousClass0OD extends AbstractC02300iS {
    public AbstractC03650oF A00;
    public C04960rc A01;
    public int A02;
    public C02260iN A03 = new C02260iN(0, null);
    public C04960rc A04;

    @Override // X.AbstractC02300iS
    public final AbstractC02300iS A08() {
        return this;
    }

    @Override // X.AbstractC02300iS, java.io.Flushable
    public final void flush() throws IOException {
    }

    private final void A00(EnumC03640oE r8) {
        C04960rc r4;
        C04960rc r6 = this.A04;
        int i = this.A02;
        if (i < 16) {
            long ordinal = (long) r8.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            r6.A00 |= ordinal;
            r4 = null;
        } else {
            r4 = new C04960rc();
            r6.A01 = r4;
            r4.A00 |= (long) r8.ordinal();
        }
        if (r4 == null) {
            this.A02 = i + 1;
            return;
        }
        this.A04 = r4;
        this.A02 = 1;
    }

    private final void A01(EnumC03640oE r8, Object obj) {
        C04960rc r4;
        C04960rc r6 = this.A04;
        int i = this.A02;
        if (i < 16) {
            r6.A02[i] = obj;
            long ordinal = (long) r8.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            r6.A00 = ordinal | r6.A00;
            r4 = null;
        } else {
            r4 = new C04960rc();
            r6.A01 = r4;
            r4.A02[0] = obj;
            r4.A00 = ((long) r8.ordinal()) | r4.A00;
        }
        if (r4 == null) {
            this.A02 = i + 1;
            return;
        }
        this.A04 = r4;
        this.A02 = 1;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static final void A02(AnonymousClass0OD r3, AbstractC02280iQ r4) throws IOException, C03620oC {
        boolean z = true;
        switch (C04950rb.A00[r4.A0i().ordinal()]) {
            case 1:
                r3.A0I();
                return;
            case 2:
                r3.A0F();
                return;
            case 3:
                r3.A0H();
                return;
            case 4:
                r3.A0E();
                return;
            case 5:
                r3.A0R(r4.A0l());
                return;
            case 6:
                if (r4.A0p()) {
                    r3.A0a(r4.A0s(), r4.A0e(), r4.A0d());
                    return;
                } else {
                    r3.A0U(r4.A0m());
                    return;
                }
            case 7:
                switch (r4.A0X().intValue()) {
                    case 0:
                        r3.A0M(r4.A0T());
                        return;
                    case 1:
                    default:
                        r3.A0N(r4.A0U());
                        return;
                    case 2:
                        r3.A0W(r4.A0b());
                        return;
                }
            case 8:
                switch (r4.A0X().intValue()) {
                    case 3:
                        r3.A0L(r4.A0S());
                        return;
                    case 4:
                    default:
                        r3.A0K(r4.A0R());
                        return;
                    case 5:
                        r3.A0V(r4.A0a());
                        return;
                }
            case 9:
                break;
            case 10:
                z = false;
                break;
            case 11:
                r3.A0G();
                return;
            case 12:
                r3.A0C(r4.A0Z());
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
        r3.A0Y(z);
    }

    @Override // X.AbstractC02300iS
    public final void A0B(AbstractC03670oH r3) throws IOException, C02310iT {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02300iS
    public final void A0C(Object obj) throws IOException, C03620oC {
        A01(EnumC03640oE.VALUE_EMBEDDED_OBJECT, obj);
    }

    @Override // X.AbstractC02300iS
    public final void A0D(String str) throws IOException, C02310iT {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02300iS
    public final void A0E() throws IOException, C02310iT {
        A00(EnumC03640oE.END_ARRAY);
        C02260iN r0 = this.A03.A02;
        if (r0 != null) {
            this.A03 = r0;
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0F() throws IOException, C02310iT {
        A00(EnumC03640oE.END_OBJECT);
        C02260iN r0 = this.A03.A02;
        if (r0 != null) {
            this.A03 = r0;
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0G() throws IOException, C02310iT {
        A00(EnumC03640oE.VALUE_NULL);
    }

    @Override // X.AbstractC02300iS
    public final void A0H() throws IOException, C02310iT {
        A00(EnumC03640oE.START_ARRAY);
        C02260iN r2 = this.A03;
        C02260iN r1 = r2.A00;
        if (r1 == null) {
            r1 = new C02260iN(1, r2);
            r2.A00 = r1;
        } else {
            ((AbstractC03630oD) r1).A01 = 1;
            ((AbstractC03630oD) r1).A00 = -1;
            r1.A01 = null;
        }
        this.A03 = r1;
    }

    @Override // X.AbstractC02300iS
    public final void A0I() throws IOException, C02310iT {
        A00(EnumC03640oE.START_OBJECT);
        C02260iN r2 = this.A03;
        C02260iN r1 = r2.A00;
        if (r1 == null) {
            r1 = new C02260iN(2, r2);
            r2.A00 = r1;
        } else {
            ((AbstractC03630oD) r1).A01 = 2;
            ((AbstractC03630oD) r1).A00 = -1;
            r1.A01 = null;
        }
        this.A03 = r1;
    }

    @Override // X.AbstractC02300iS
    public final void A0J(char c) throws IOException, C02310iT {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02300iS
    public final void A0K(double d) throws IOException, C02310iT {
        A01(EnumC03640oE.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    @Override // X.AbstractC02300iS
    public final void A0L(float f) throws IOException, C02310iT {
        A01(EnumC03640oE.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    @Override // X.AbstractC02300iS
    public final void A0M(int i) throws IOException, C02310iT {
        A01(EnumC03640oE.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    @Override // X.AbstractC02300iS
    public final void A0N(long j) throws IOException, C02310iT {
        A01(EnumC03640oE.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    @Override // X.AbstractC02300iS
    public final void A0O(AnonymousClass0o2 r3, byte[] bArr, int i, int i2) throws IOException, C02310iT {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        A0C(bArr2);
    }

    @Override // X.AbstractC02300iS
    public final void A0P(AbstractC03670oH r3) throws IOException, C02310iT {
        A01(EnumC03640oE.FIELD_NAME, r3);
        this.A03.A01(r3.getValue());
    }

    @Override // X.AbstractC02300iS
    public final void A0Q(AbstractC03670oH r2) throws IOException, C02310iT {
        if (r2 == null) {
            A0G();
        } else {
            A01(EnumC03640oE.VALUE_STRING, r2);
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0R(String str) throws IOException, C02310iT {
        A01(EnumC03640oE.FIELD_NAME, str);
        this.A03.A01(str);
    }

    @Override // X.AbstractC02300iS
    public final void A0S(String str) throws IOException, C02310iT {
        A01(EnumC03640oE.VALUE_NUMBER_FLOAT, str);
    }

    @Override // X.AbstractC02300iS
    public final void A0T(String str) throws IOException, C02310iT {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02300iS
    public final void A0U(String str) throws IOException, C02310iT {
        if (str == null) {
            A0G();
        } else {
            A01(EnumC03640oE.VALUE_STRING, str);
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0V(BigDecimal bigDecimal) throws IOException, C02310iT {
        if (bigDecimal == null) {
            A0G();
        } else {
            A01(EnumC03640oE.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0W(BigInteger bigInteger) throws IOException, C02310iT {
        if (bigInteger == null) {
            A0G();
        } else {
            A01(EnumC03640oE.VALUE_NUMBER_INT, bigInteger);
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0X(short s) throws IOException, C02310iT {
        A01(EnumC03640oE.VALUE_NUMBER_INT, Short.valueOf(s));
    }

    @Override // X.AbstractC02300iS
    public final void A0Y(boolean z) throws IOException, C02310iT {
        EnumC03640oE r0;
        if (z) {
            r0 = EnumC03640oE.VALUE_TRUE;
        } else {
            r0 = EnumC03640oE.VALUE_FALSE;
        }
        A00(r0);
    }

    @Override // X.AbstractC02300iS
    public final void A0Z(char[] cArr, int i, int i2) throws IOException, C02310iT {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02300iS
    public final void A0a(char[] cArr, int i, int i2) throws IOException, C02310iT {
        A0U(new String(cArr, i, i2));
    }

    public final AbstractC02280iQ A0b(AbstractC02280iQ r4) {
        AnonymousClass0C6 r1 = new AnonymousClass0C6(this.A01, r4.A0N());
        r1.A01 = r4.A0W();
        return r1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[TokenBuffer: ");
        AnonymousClass0C6 r4 = new AnonymousClass0C6(this.A01, this.A00);
        int i = 0;
        while (true) {
            try {
                EnumC03640oE A0j = r4.A0j();
                if (A0j == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(A0j.toString());
                    if (A0j == EnumC03640oE.FIELD_NAME) {
                        sb.append('(');
                        sb.append(r4.A0l());
                        sb.append(')');
                    }
                }
                i++;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        if (i >= 100) {
            sb.append(" ... (truncated ");
            sb.append(i - 100);
            sb.append(" entries)");
        }
        sb.append(']');
        return sb.toString();
    }

    static {
        EnumC03610oA.collectDefaults();
    }

    public AnonymousClass0OD(AbstractC03650oF r4) {
        this.A00 = r4;
        C04960rc r0 = new C04960rc();
        this.A04 = r0;
        this.A01 = r0;
        this.A02 = 0;
    }

    @Override // X.AbstractC02300iS
    public final AbstractC03650oF A0A() {
        return this.A00;
    }

    public final void A0c(AbstractC02280iQ r4) throws IOException, C03620oC {
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.FIELD_NAME) {
            A0R(r4.A0l());
            A0i = r4.A0j();
        }
        int i = C04950rb.A00[A0i.ordinal()];
        if (i == 1) {
            A0I();
            while (r4.A0j() != EnumC03640oE.END_OBJECT) {
                A0c(r4);
            }
            A0F();
        } else if (i != 3) {
            A02(this, r4);
        } else {
            A0H();
            while (r4.A0j() != EnumC03640oE.END_ARRAY) {
                A0c(r4);
            }
            A0E();
        }
    }

    @Override // java.io.Closeable, X.AbstractC02300iS, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    @Override // X.AbstractC02300iS, X.AbstractC03700oK
    public final C03690oJ version() {
        return PackageVersion.VERSION;
    }

    @Override // X.AbstractC02300iS
    public final AbstractC02300iS A09(AbstractC03650oF r1) {
        this.A00 = r1;
        return this;
    }
}
