package X;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0Jk  reason: invalid class name and case insensitive filesystem */
public final class C01570Jk extends AbstractC02640aV {
    public AbstractC05940lg A00;
    public C07300p6 A01;
    public int A02;
    public AnonymousClass0aQ A03 = new AnonymousClass0aQ(0, null);
    public C07300p6 A04;

    @Override // X.AbstractC02640aV
    public final AbstractC02640aV A08() {
        return this;
    }

    @Override // X.AbstractC02640aV, java.io.Flushable
    public final void flush() throws IOException {
    }

    private final void A00(EnumC05930lf r8) {
        C07300p6 r4;
        C07300p6 r6 = this.A04;
        int i = this.A02;
        if (i < 16) {
            long ordinal = (long) r8.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            r6.A00 |= ordinal;
            r4 = null;
        } else {
            r4 = new C07300p6();
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

    private final void A01(EnumC05930lf r8, Object obj) {
        C07300p6 r4;
        C07300p6 r6 = this.A04;
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
            r4 = new C07300p6();
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
    public static final void A02(C01570Jk r3, AnonymousClass0aT r4) throws IOException, C05910ld {
        boolean z = true;
        switch (C07290p5.A00[r4.A0G().ordinal()]) {
            case 1:
                r3.A0F();
                return;
            case 2:
                r3.A0C();
                return;
            case 3:
                r3.A0E();
                return;
            case 4:
                r3.A0B();
                return;
            case 5:
                r3.A0P(r4.A0O());
                return;
            case 6:
                if (r4.A0X()) {
                    r3.A0Y(r4.A0Z(), r4.A08(), r4.A07());
                    return;
                } else {
                    r3.A0S(r4.A0P());
                    return;
                }
            case 7:
                switch (r4.A0J().intValue()) {
                    case 0:
                        r3.A0J(r4.A06());
                        return;
                    case 1:
                    default:
                        r3.A0K(r4.A0B());
                        return;
                    case 2:
                        r3.A0U(r4.A0S());
                        return;
                }
            case 8:
                switch (r4.A0J().intValue()) {
                    case 3:
                        r3.A0I(r4.A04());
                        return;
                    case 4:
                    default:
                        r3.A0H(r4.A03());
                        return;
                    case 5:
                        r3.A0T(r4.A0R());
                        return;
                }
            case 9:
                break;
            case 10:
                z = false;
                break;
            case 11:
                r3.A0D();
                return;
            case 12:
                r3.A09(r4.A0M());
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
        r3.A0W(z);
    }

    @Override // X.AbstractC02640aV
    public final void A09(Object obj) throws IOException, C05910ld {
        A01(EnumC05930lf.VALUE_EMBEDDED_OBJECT, obj);
    }

    @Override // X.AbstractC02640aV
    public final void A0A(String str) throws IOException, C02650aW {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02640aV
    public final void A0B() throws IOException, C02650aW {
        A00(EnumC05930lf.END_ARRAY);
        AnonymousClass0aQ r0 = this.A03.A02;
        if (r0 != null) {
            this.A03 = r0;
        }
    }

    @Override // X.AbstractC02640aV
    public final void A0C() throws IOException, C02650aW {
        A00(EnumC05930lf.END_OBJECT);
        AnonymousClass0aQ r0 = this.A03.A02;
        if (r0 != null) {
            this.A03 = r0;
        }
    }

    @Override // X.AbstractC02640aV
    public final void A0D() throws IOException, C02650aW {
        A00(EnumC05930lf.VALUE_NULL);
    }

    @Override // X.AbstractC02640aV
    public final void A0E() throws IOException, C02650aW {
        A00(EnumC05930lf.START_ARRAY);
        AnonymousClass0aQ r2 = this.A03;
        AnonymousClass0aQ r1 = r2.A00;
        if (r1 == null) {
            r1 = new AnonymousClass0aQ(1, r2);
            r2.A00 = r1;
        } else {
            ((AbstractC05920le) r1).A01 = 1;
            ((AbstractC05920le) r1).A00 = -1;
            r1.A01 = null;
        }
        this.A03 = r1;
    }

    @Override // X.AbstractC02640aV
    public final void A0F() throws IOException, C02650aW {
        A00(EnumC05930lf.START_OBJECT);
        AnonymousClass0aQ r2 = this.A03;
        AnonymousClass0aQ r1 = r2.A00;
        if (r1 == null) {
            r1 = new AnonymousClass0aQ(2, r2);
            r2.A00 = r1;
        } else {
            ((AbstractC05920le) r1).A01 = 2;
            ((AbstractC05920le) r1).A00 = -1;
            r1.A01 = null;
        }
        this.A03 = r1;
    }

    @Override // X.AbstractC02640aV
    public final void A0G(char c) throws IOException, C02650aW {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02640aV
    public final void A0H(double d) throws IOException, C02650aW {
        A01(EnumC05930lf.VALUE_NUMBER_FLOAT, Double.valueOf(d));
    }

    @Override // X.AbstractC02640aV
    public final void A0I(float f) throws IOException, C02650aW {
        A01(EnumC05930lf.VALUE_NUMBER_FLOAT, Float.valueOf(f));
    }

    @Override // X.AbstractC02640aV
    public final void A0J(int i) throws IOException, C02650aW {
        A01(EnumC05930lf.VALUE_NUMBER_INT, Integer.valueOf(i));
    }

    @Override // X.AbstractC02640aV
    public final void A0K(long j) throws IOException, C02650aW {
        A01(EnumC05930lf.VALUE_NUMBER_INT, Long.valueOf(j));
    }

    @Override // X.AbstractC02640aV
    public final void A0L(C05830lU r3, byte[] bArr, int i, int i2) throws IOException, C02650aW {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        A09(bArr2);
    }

    @Override // X.AbstractC02640aV
    public final void A0M(AbstractC05960li r3) throws IOException, C02650aW {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02640aV
    public final void A0N(AbstractC05960li r3) throws IOException, C02650aW {
        A01(EnumC05930lf.FIELD_NAME, r3);
        this.A03.A00(r3.getValue());
    }

    @Override // X.AbstractC02640aV
    public final void A0O(AbstractC05960li r2) throws IOException, C02650aW {
        if (r2 == null) {
            A0D();
        } else {
            A01(EnumC05930lf.VALUE_STRING, r2);
        }
    }

    @Override // X.AbstractC02640aV
    public final void A0P(String str) throws IOException, C02650aW {
        A01(EnumC05930lf.FIELD_NAME, str);
        this.A03.A00(str);
    }

    @Override // X.AbstractC02640aV
    public final void A0Q(String str) throws IOException, C02650aW {
        A01(EnumC05930lf.VALUE_NUMBER_FLOAT, str);
    }

    @Override // X.AbstractC02640aV
    public final void A0R(String str) throws IOException, C02650aW {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02640aV
    public final void A0S(String str) throws IOException, C02650aW {
        if (str == null) {
            A0D();
        } else {
            A01(EnumC05930lf.VALUE_STRING, str);
        }
    }

    @Override // X.AbstractC02640aV
    public final void A0T(BigDecimal bigDecimal) throws IOException, C02650aW {
        if (bigDecimal == null) {
            A0D();
        } else {
            A01(EnumC05930lf.VALUE_NUMBER_FLOAT, bigDecimal);
        }
    }

    @Override // X.AbstractC02640aV
    public final void A0U(BigInteger bigInteger) throws IOException, C02650aW {
        if (bigInteger == null) {
            A0D();
        } else {
            A01(EnumC05930lf.VALUE_NUMBER_INT, bigInteger);
        }
    }

    @Override // X.AbstractC02640aV
    public final void A0V(short s) throws IOException, C02650aW {
        A01(EnumC05930lf.VALUE_NUMBER_INT, Short.valueOf(s));
    }

    @Override // X.AbstractC02640aV
    public final void A0W(boolean z) throws IOException, C02650aW {
        EnumC05930lf r0;
        if (z) {
            r0 = EnumC05930lf.VALUE_TRUE;
        } else {
            r0 = EnumC05930lf.VALUE_FALSE;
        }
        A00(r0);
    }

    @Override // X.AbstractC02640aV
    public final void A0X(char[] cArr, int i, int i2) throws IOException, C02650aW {
        throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
    }

    @Override // X.AbstractC02640aV
    public final void A0Y(char[] cArr, int i, int i2) throws IOException, C02650aW {
        A0S(new String(cArr, i, i2));
    }

    public final AnonymousClass0aT A0Z(AnonymousClass0aT r4) {
        AnonymousClass0C8 r1 = new AnonymousClass0C8(this.A01, r4.A0I());
        r1.A01 = r4.A0E();
        return r1;
    }

    @Override // X.AbstractC02640aV, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[TokenBuffer: ");
        AnonymousClass0C8 r4 = new AnonymousClass0C8(this.A01, this.A00);
        int i = 0;
        while (true) {
            try {
                EnumC05930lf A0a = r4.A0a();
                if (A0a == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(A0a.toString());
                    if (A0a == EnumC05930lf.FIELD_NAME) {
                        sb.append('(');
                        sb.append(r4.A0O());
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

    public C01570Jk(AbstractC05940lg r4) {
        this.A00 = r4;
        C07300p6 r0 = new C07300p6();
        this.A04 = r0;
        this.A01 = r0;
        this.A02 = 0;
    }

    public final void A0a(AnonymousClass0aT r4) throws IOException, C05910ld {
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.FIELD_NAME) {
            A0P(r4.A0O());
            A0G = r4.A0a();
        }
        int i = C07290p5.A00[A0G.ordinal()];
        if (i == 1) {
            A0F();
            while (r4.A0a() != EnumC05930lf.END_OBJECT) {
                A0a(r4);
            }
            A0C();
        } else if (i != 3) {
            A02(this, r4);
        } else {
            A0E();
            while (r4.A0a() != EnumC05930lf.END_ARRAY) {
                A0a(r4);
            }
            A0B();
        }
    }

    static {
        EnumC05900lb.collectDefaults();
    }
}
