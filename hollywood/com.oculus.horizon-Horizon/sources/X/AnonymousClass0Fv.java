package X;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0Fv  reason: invalid class name */
public final class AnonymousClass0Fv extends AbstractC04120gr {
    public AnonymousClass0jj A00;
    public C06480nD A01;
    public int A02;
    public C04070gm A03 = new C04070gm(0, null);
    public C06480nD A04;

    @Override // X.AbstractC04120gr, java.io.Flushable
    public final void flush() throws IOException {
    }

    private final void A00(EnumC04820ji r8) {
        C06480nD r4;
        C06480nD r6 = this.A04;
        int i = this.A02;
        if (i < 16) {
            long ordinal = (long) r8.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            r6.A00 |= ordinal;
            r4 = null;
        } else {
            r4 = new C06480nD();
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

    private final void A01(EnumC04820ji r8, Object obj) {
        C06480nD r4;
        C06480nD r6 = this.A04;
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
            r4 = new C06480nD();
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
    public static final void A02(AnonymousClass0Fv r4, AbstractC04100gp r5) throws IOException, AnonymousClass0jg {
        EnumC04820ji r0;
        switch (C06470nC.A00[r5.A0a().ordinal()]) {
            case 1:
                r4.A07();
                return;
            case 2:
                r4.A05();
                return;
            case 3:
                r4.A06();
                return;
            case 4:
                r4.A04();
                return;
            case 5:
                r4.A09(r5.A0d());
                return;
            case 6:
                if (r5.A0h()) {
                    r4.A0A(new String(r5.A0k(), r5.A0W(), r5.A0V()));
                    return;
                } else {
                    r4.A0A(r5.A0e());
                    return;
                }
            case 7:
                switch (r5.A0Q().intValue()) {
                    case 0:
                        r4.A01(EnumC04820ji.VALUE_NUMBER_INT, Integer.valueOf(r5.A0M()));
                        return;
                    case 1:
                    default:
                        r4.A01(EnumC04820ji.VALUE_NUMBER_INT, Long.valueOf(r5.A0N()));
                        return;
                    case 2:
                        BigInteger A0U = r5.A0U();
                        if (A0U != null) {
                            r4.A01(EnumC04820ji.VALUE_NUMBER_INT, A0U);
                            return;
                        }
                        break;
                }
                r4.A00(EnumC04820ji.VALUE_NULL);
                return;
            case 8:
                switch (r5.A0Q().intValue()) {
                    case 3:
                        r4.A01(EnumC04820ji.VALUE_NUMBER_FLOAT, Float.valueOf(r5.A0L()));
                        return;
                    case 4:
                    default:
                        r4.A01(EnumC04820ji.VALUE_NUMBER_FLOAT, Double.valueOf(r5.A0K()));
                        return;
                    case 5:
                        BigDecimal A0T = r5.A0T();
                        if (A0T != null) {
                            r4.A01(EnumC04820ji.VALUE_NUMBER_FLOAT, A0T);
                            return;
                        }
                        break;
                }
                r4.A00(EnumC04820ji.VALUE_NULL);
                return;
            case 9:
                r0 = EnumC04820ji.VALUE_TRUE;
                r4.A00(r0);
                return;
            case 10:
                r0 = EnumC04820ji.VALUE_FALSE;
                r4.A00(r0);
                return;
            case 11:
                r4.A00(EnumC04820ji.VALUE_NULL);
                return;
            case 12:
                r4.A01(EnumC04820ji.VALUE_EMBEDDED_OBJECT, r5.A0S());
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
    }

    public final AbstractC04100gp A03(AbstractC04100gp r4) {
        AnonymousClass070 r1 = new AnonymousClass070(this.A01, r4.A0I());
        r1.A01 = r4.A0P();
        return r1;
    }

    public final void A04() throws IOException, C04130gs {
        A00(EnumC04820ji.END_ARRAY);
        C04070gm r0 = this.A03.A02;
        if (r0 != null) {
            this.A03 = r0;
        }
    }

    public final void A05() throws IOException, C04130gs {
        A00(EnumC04820ji.END_OBJECT);
        C04070gm r0 = this.A03.A02;
        if (r0 != null) {
            this.A03 = r0;
        }
    }

    public final void A06() throws IOException, C04130gs {
        A00(EnumC04820ji.START_ARRAY);
        C04070gm r2 = this.A03;
        C04070gm r1 = r2.A00;
        if (r1 == null) {
            r1 = new C04070gm(1, r2);
            r2.A00 = r1;
        } else {
            ((AnonymousClass0jh) r1).A01 = 1;
            ((AnonymousClass0jh) r1).A00 = -1;
            r1.A01 = null;
        }
        this.A03 = r1;
    }

    public final void A07() throws IOException, C04130gs {
        A00(EnumC04820ji.START_OBJECT);
        C04070gm r2 = this.A03;
        C04070gm r1 = r2.A00;
        if (r1 == null) {
            r1 = new C04070gm(2, r2);
            r2.A00 = r1;
        } else {
            ((AnonymousClass0jh) r1).A01 = 2;
            ((AnonymousClass0jh) r1).A00 = -1;
            r1.A01 = null;
        }
        this.A03 = r1;
    }

    public final void A09(String str) throws IOException, C04130gs {
        A01(EnumC04820ji.FIELD_NAME, str);
        C04070gm r2 = this.A03;
        if (((AnonymousClass0jh) r2).A01 == 2 && r2.A01 == null) {
            r2.A01 = str;
        }
    }

    public final void A0A(String str) throws IOException, C04130gs {
        if (str == null) {
            A00(EnumC04820ji.VALUE_NULL);
        } else {
            A01(EnumC04820ji.VALUE_STRING, str);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[TokenBuffer: ");
        AnonymousClass070 r4 = new AnonymousClass070(this.A01, this.A00);
        int i = 0;
        while (true) {
            try {
                EnumC04820ji A0b = r4.A0b();
                if (A0b == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(A0b.toString());
                    if (A0b == EnumC04820ji.FIELD_NAME) {
                        sb.append('(');
                        sb.append(r4.A0d());
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
        AnonymousClass0je.collectDefaults();
    }

    public AnonymousClass0Fv(AnonymousClass0jj r4) {
        this.A00 = r4;
        C06480nD r0 = new C06480nD();
        this.A04 = r0;
        this.A01 = r0;
        this.A02 = 0;
    }

    public final void A08(AbstractC04100gp r4) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.FIELD_NAME) {
            A09(r4.A0d());
            A0a = r4.A0b();
        }
        int i = C06470nC.A00[A0a.ordinal()];
        if (i == 1) {
            A07();
            while (r4.A0b() != EnumC04820ji.END_OBJECT) {
                A08(r4);
            }
            A05();
        } else if (i != 3) {
            A02(this, r4);
        } else {
            A06();
            while (r4.A0b() != EnumC04820ji.END_ARRAY) {
                A08(r4);
            }
            A04();
        }
    }

    @Override // java.io.Closeable, X.AbstractC04120gr, java.lang.AutoCloseable
    public final void close() throws IOException {
    }
}
