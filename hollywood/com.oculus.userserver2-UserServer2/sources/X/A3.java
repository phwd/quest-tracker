package X;

import com.oculus.userserver.managerservice.IOculusUserManager;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class A3 extends Rp {
    public Rl A00 = new Rl(0, null);
    public C00239v A01;
    public int A02;
    public AbstractC00229o A03;
    public C00239v A04;

    public final void A07(Rn rn) throws IOException, AnonymousClass9r {
        AnonymousClass9p r0;
        AnonymousClass9p r2 = ((B3) rn).A00;
        if (r2 == AnonymousClass9p.FIELD_NAME) {
            A08(rn.A08());
            r2 = rn.A04();
        }
        int i = AnonymousClass9t.A00[r2.ordinal()];
        if (i == 1) {
            A06();
            while (true) {
                AnonymousClass9p A042 = rn.A04();
                r0 = AnonymousClass9p.END_OBJECT;
                if (A042 == r0) {
                    break;
                }
                A07(rn);
            }
            A01(this, r0);
            Rl rl = this.A00.A02;
            if (rl != null) {
                this.A00 = rl;
            }
        } else if (i != 3) {
            A00(this, rn);
        } else {
            A05();
            while (rn.A04() != AnonymousClass9p.END_ARRAY) {
                A07(rn);
            }
            A04();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static final void A00(A3 a3, Rn rn) throws IOException, AnonymousClass9r {
        AnonymousClass9p r0;
        BigInteger valueOf;
        switch (AnonymousClass9t.A00[((B3) rn).A00.ordinal()]) {
            case 1:
                a3.A06();
                return;
            case 2:
                A01(a3, AnonymousClass9p.END_OBJECT);
                Rl rl = a3.A00.A02;
                if (rl != null) {
                    a3.A00 = rl;
                    return;
                }
                return;
            case 3:
                a3.A05();
                return;
            case 4:
                a3.A04();
                return;
            case 5:
                a3.A08(rn.A08());
                return;
            case 6:
                if (rn.A0B()) {
                    A02(a3, AnonymousClass9p.VALUE_STRING, new String(rn.A0C(), rn.A02(), rn.A01()));
                    return;
                }
                String A09 = rn.A09();
                if (A09 != null) {
                    A02(a3, AnonymousClass9p.VALUE_STRING, A09);
                    return;
                }
                A01(a3, AnonymousClass9p.VALUE_NULL);
                return;
            case 7:
                switch (rn.A05().intValue()) {
                    case 0:
                        A02(a3, AnonymousClass9p.VALUE_NUMBER_INT, Integer.valueOf(rn.A00()));
                        return;
                    case 1:
                    default:
                        A02(a3, AnonymousClass9p.VALUE_NUMBER_INT, Long.valueOf(rn.A06().longValue()));
                        return;
                    case 2:
                        Number A06 = rn.A06();
                        if (A06 instanceof BigInteger) {
                            valueOf = (BigInteger) A06;
                        } else if (rn.A05() == AnonymousClass07.A05) {
                            valueOf = ((BigDecimal) A06).toBigInteger();
                        } else {
                            valueOf = BigInteger.valueOf(A06.longValue());
                        }
                        if (valueOf != null) {
                            A02(a3, AnonymousClass9p.VALUE_NUMBER_INT, valueOf);
                            return;
                        }
                        break;
                }
                A01(a3, AnonymousClass9p.VALUE_NULL);
                return;
            case 8:
                switch (rn.A05().intValue()) {
                    case 3:
                        A02(a3, AnonymousClass9p.VALUE_NUMBER_FLOAT, Float.valueOf(rn.A06().floatValue()));
                        return;
                    case 4:
                    default:
                        A02(a3, AnonymousClass9p.VALUE_NUMBER_FLOAT, Double.valueOf(rn.A06().doubleValue()));
                        return;
                    case 5:
                        BigDecimal A0A = rn.A0A();
                        if (A0A != null) {
                            A02(a3, AnonymousClass9p.VALUE_NUMBER_FLOAT, A0A);
                            return;
                        }
                        break;
                }
                A01(a3, AnonymousClass9p.VALUE_NULL);
                return;
            case 9:
                r0 = AnonymousClass9p.VALUE_TRUE;
                A01(a3, r0);
                return;
            case 10:
                r0 = AnonymousClass9p.VALUE_FALSE;
                A01(a3, r0);
                return;
            case IOculusUserManager.Stub.TRANSACTION_refreshUsers /*{ENCODED_INT: 11}*/:
                A01(a3, AnonymousClass9p.VALUE_NULL);
                return;
            case IOculusUserManager.Stub.TRANSACTION_getSelf /*{ENCODED_INT: 12}*/:
                A02(a3, AnonymousClass9p.VALUE_EMBEDDED_OBJECT, rn.A07());
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
    }

    public static final void A01(A3 a3, AnonymousClass9p r8) {
        C00239v r4;
        C00239v r6 = a3.A04;
        int i = a3.A02;
        if (i < 16) {
            long ordinal = (long) r8.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            r6.A00 |= ordinal;
            r4 = null;
        } else {
            r4 = new C00239v();
            r6.A01 = r4;
            r4.A00 |= (long) r8.ordinal();
        }
        if (r4 == null) {
            a3.A02 = i + 1;
            return;
        }
        a3.A04 = r4;
        a3.A02 = 1;
    }

    public static final void A02(A3 a3, AnonymousClass9p r8, Object obj) {
        C00239v r4;
        C00239v r6 = a3.A04;
        int i = a3.A02;
        if (i < 16) {
            r6.A02[i] = obj;
            long ordinal = (long) r8.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            r6.A00 = ordinal | r6.A00;
            r4 = null;
        } else {
            r4 = new C00239v();
            r6.A01 = r4;
            r4.A02[0] = obj;
            r4.A00 = ((long) r8.ordinal()) | r4.A00;
        }
        if (r4 == null) {
            a3.A02 = i + 1;
            return;
        }
        a3.A04 = r4;
        a3.A02 = 1;
    }

    public final Rn A03(Rn rn) {
        AnonymousClass6z r1 = new AnonymousClass6z(this.A01, null);
        AnonymousClass9u r0 = ((AnonymousClass6z) rn).A01;
        if (r0 == null) {
            r0 = AnonymousClass9u.A01;
        }
        r1.A01 = r0;
        return r1;
    }

    public final void A04() throws IOException, C0125Rq {
        A01(this, AnonymousClass9p.END_ARRAY);
        Rl rl = this.A00.A02;
        if (rl != null) {
            this.A00 = rl;
        }
    }

    public final void A05() throws IOException, C0125Rq {
        A01(this, AnonymousClass9p.START_ARRAY);
        Rl rl = this.A00;
        Rl rl2 = rl.A00;
        if (rl2 == null) {
            rl2 = new Rl(1, rl);
            rl.A00 = rl2;
        } else {
            ((AnonymousClass9q) rl2).A01 = 1;
            ((AnonymousClass9q) rl2).A00 = -1;
            rl2.A01 = null;
        }
        this.A00 = rl2;
    }

    public final void A06() throws IOException, C0125Rq {
        A01(this, AnonymousClass9p.START_OBJECT);
        Rl rl = this.A00;
        Rl rl2 = rl.A00;
        if (rl2 == null) {
            rl2 = new Rl(2, rl);
            rl.A00 = rl2;
        } else {
            ((AnonymousClass9q) rl2).A01 = 2;
            ((AnonymousClass9q) rl2).A00 = -1;
            rl2.A01 = null;
        }
        this.A00 = rl2;
    }

    public final void A08(String str) throws IOException, C0125Rq {
        A02(this, AnonymousClass9p.FIELD_NAME, str);
        Rl rl = this.A00;
        if (((AnonymousClass9q) rl).A01 == 2 && rl.A01 == null) {
            rl.A01 = str;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[TokenBuffer: ");
        AnonymousClass6z r4 = new AnonymousClass6z(this.A01, null);
        int i = 0;
        while (true) {
            try {
                AnonymousClass9p A042 = r4.A04();
                if (A042 == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(A042.toString());
                    if (A042 == AnonymousClass9p.FIELD_NAME) {
                        sb.append('(');
                        sb.append(r4.A08());
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
        AnonymousClass9s.collectDefaults();
    }

    public A3(AbstractC00229o r4) {
        this.A03 = r4;
        C00239v r0 = new C00239v();
        this.A04 = r0;
        this.A01 = r0;
        this.A02 = 0;
    }
}
