package X;

import com.oculus.aidl.IUnifiedTelemetryService;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class Br extends Wy {
    public AbstractC0471q3 A00;
    public I4 A01;
    public int A02;
    public C0229Wt A03 = new C0229Wt(0, null);
    public I4 A04;

    @Override // X.Wy, java.io.Flushable
    public final void flush() throws IOException {
    }

    private final void A00(EnumC0470q2 q2Var) {
        I4 i4;
        I4 i42 = this.A04;
        int i = this.A02;
        if (i < 16) {
            long ordinal = (long) q2Var.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            i42.A00 |= ordinal;
            i4 = null;
        } else {
            i4 = new I4();
            i42.A01 = i4;
            i4.A00 |= (long) q2Var.ordinal();
        }
        if (i4 == null) {
            this.A02 = i + 1;
            return;
        }
        this.A04 = i4;
        this.A02 = 1;
    }

    private final void A01(EnumC0470q2 q2Var, Object obj) {
        I4 i4;
        I4 i42 = this.A04;
        int i = this.A02;
        if (i < 16) {
            i42.A02[i] = obj;
            long ordinal = (long) q2Var.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            i42.A00 = ordinal | i42.A00;
            i4 = null;
        } else {
            i4 = new I4();
            i42.A01 = i4;
            i4.A02[0] = obj;
            i4.A00 = ((long) q2Var.ordinal()) | i4.A00;
        }
        if (i4 == null) {
            this.A02 = i + 1;
            return;
        }
        this.A04 = i4;
        this.A02 = 1;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static final void A02(Br br, AbstractC0232Ww ww) throws IOException, q0 {
        EnumC0470q2 q2Var;
        switch (IC.A00[ww.A0Z().ordinal()]) {
            case 1:
                br.A07();
                return;
            case 2:
                br.A05();
                return;
            case 3:
                br.A06();
                return;
            case 4:
                br.A04();
                return;
            case 5:
                br.A09(ww.A0c());
                return;
            case 6:
                if (ww.A0h()) {
                    br.A0A(new String(ww.A0j(), ww.A0V(), ww.A0U()));
                    return;
                } else {
                    br.A0A(ww.A0d());
                    return;
                }
            case 7:
                switch (ww.A0P().intValue()) {
                    case 0:
                        br.A01(EnumC0470q2.VALUE_NUMBER_INT, Integer.valueOf(ww.A0L()));
                        return;
                    case 1:
                    default:
                        br.A01(EnumC0470q2.VALUE_NUMBER_INT, Long.valueOf(ww.A0M()));
                        return;
                    case 2:
                        BigInteger A0T = ww.A0T();
                        if (A0T != null) {
                            br.A01(EnumC0470q2.VALUE_NUMBER_INT, A0T);
                            return;
                        }
                        break;
                }
                br.A00(EnumC0470q2.VALUE_NULL);
                return;
            case 8:
                switch (ww.A0P().intValue()) {
                    case 3:
                        br.A01(EnumC0470q2.VALUE_NUMBER_FLOAT, Float.valueOf(ww.A0K()));
                        return;
                    case 4:
                    default:
                        br.A01(EnumC0470q2.VALUE_NUMBER_FLOAT, Double.valueOf(ww.A0J()));
                        return;
                    case 5:
                        BigDecimal A0S = ww.A0S();
                        if (A0S != null) {
                            br.A01(EnumC0470q2.VALUE_NUMBER_FLOAT, A0S);
                            return;
                        }
                        break;
                }
                br.A00(EnumC0470q2.VALUE_NULL);
                return;
            case 9:
                q2Var = EnumC0470q2.VALUE_TRUE;
                br.A00(q2Var);
                return;
            case 10:
                q2Var = EnumC0470q2.VALUE_FALSE;
                br.A00(q2Var);
                return;
            case 11:
                br.A00(EnumC0470q2.VALUE_NULL);
                return;
            case IUnifiedTelemetryService.Stub.TRANSACTION_startSession /*{ENCODED_INT: 12}*/:
                br.A01(EnumC0470q2.VALUE_EMBEDDED_OBJECT, ww.A0R());
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
    }

    public final AbstractC0232Ww A03(AbstractC0232Ww ww) {
        AnonymousClass72 r1 = new AnonymousClass72(this.A01, ww.A0H());
        r1.A01 = ww.A0O();
        return r1;
    }

    public final void A04() throws IOException, C0233Wz {
        A00(EnumC0470q2.END_ARRAY);
        C0229Wt wt = this.A03.A02;
        if (wt != null) {
            this.A03 = wt;
        }
    }

    public final void A05() throws IOException, C0233Wz {
        A00(EnumC0470q2.END_OBJECT);
        C0229Wt wt = this.A03.A02;
        if (wt != null) {
            this.A03 = wt;
        }
    }

    public final void A06() throws IOException, C0233Wz {
        A00(EnumC0470q2.START_ARRAY);
        C0229Wt wt = this.A03;
        C0229Wt wt2 = wt.A00;
        if (wt2 == null) {
            wt2 = new C0229Wt(1, wt);
            wt.A00 = wt2;
        } else {
            ((AbstractC0469q1) wt2).A01 = 1;
            ((AbstractC0469q1) wt2).A00 = -1;
            wt2.A01 = null;
        }
        this.A03 = wt2;
    }

    public final void A07() throws IOException, C0233Wz {
        A00(EnumC0470q2.START_OBJECT);
        C0229Wt wt = this.A03;
        C0229Wt wt2 = wt.A00;
        if (wt2 == null) {
            wt2 = new C0229Wt(2, wt);
            wt.A00 = wt2;
        } else {
            ((AbstractC0469q1) wt2).A01 = 2;
            ((AbstractC0469q1) wt2).A00 = -1;
            wt2.A01 = null;
        }
        this.A03 = wt2;
    }

    public final void A09(String str) throws IOException, C0233Wz {
        A01(EnumC0470q2.FIELD_NAME, str);
        C0229Wt wt = this.A03;
        if (((AbstractC0469q1) wt).A01 == 2 && wt.A01 == null) {
            wt.A01 = str;
        }
    }

    public final void A0A(String str) throws IOException, C0233Wz {
        if (str == null) {
            A00(EnumC0470q2.VALUE_NULL);
        } else {
            A01(EnumC0470q2.VALUE_STRING, str);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[TokenBuffer: ");
        AnonymousClass72 r4 = new AnonymousClass72(this.A01, this.A00);
        int i = 0;
        while (true) {
            try {
                EnumC0470q2 A0a = r4.A0a();
                if (A0a == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(A0a.toString());
                    if (A0a == EnumC0470q2.FIELD_NAME) {
                        sb.append('(');
                        sb.append(r4.A0c());
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

    public Br(AbstractC0471q3 q3Var) {
        this.A00 = q3Var;
        I4 i4 = new I4();
        this.A04 = i4;
        this.A01 = i4;
        this.A02 = 0;
    }

    public final void A08(AbstractC0232Ww ww) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.FIELD_NAME) {
            A09(ww.A0c());
            A0Z = ww.A0a();
        }
        int i = IC.A00[A0Z.ordinal()];
        if (i == 1) {
            A07();
            while (ww.A0a() != EnumC0470q2.END_OBJECT) {
                A08(ww);
            }
            A05();
        } else if (i != 3) {
            A02(this, ww);
        } else {
            A06();
            while (ww.A0a() != EnumC0470q2.END_ARRAY) {
                A08(ww);
            }
            A04();
        }
    }

    static {
        py.collectDefaults();
    }

    @Override // X.Wy, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }
}
