package X;

import com.oculus.aidl.OVRServiceInterface;
import java.io.IOException;

public final class JD extends AbstractC1012qg {
    public NY A00;
    public C1017ql A01 = new C1017ql(0, null);
    public QK A02;
    public int A03;
    public QK A04;

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static final void A00(JD jd, AbstractC1014qi qiVar) {
        boolean z = true;
        switch (QJ.A00[qiVar.A0U().ordinal()]) {
            case 1:
                jd.A0C();
                return;
            case 2:
                jd.A09();
                return;
            case 3:
                jd.A0B();
                return;
            case 4:
                jd.A08();
                return;
            case 5:
                jd.A0M(qiVar.A0b());
                return;
            case 6:
                if (qiVar.A0h()) {
                    jd.A0V(qiVar.A0m(), qiVar.A0L(), qiVar.A0K());
                    return;
                } else {
                    jd.A0P(qiVar.A0p());
                    return;
                }
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                switch (qiVar.A0X().intValue()) {
                    case 0:
                        jd.A0G(qiVar.A0J());
                        return;
                    case 1:
                    default:
                        jd.A0H(qiVar.A0O());
                        return;
                    case 2:
                        jd.A0R(qiVar.A0d());
                        return;
                }
            case 8:
                switch (qiVar.A0X().intValue()) {
                    case 3:
                        jd.A0F(qiVar.A0I());
                        return;
                    case 4:
                    default:
                        jd.A0E(qiVar.A0F());
                        return;
                    case 5:
                        jd.A0Q(qiVar.A0c());
                        return;
                }
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                break;
            case 10:
                z = false;
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                jd.A0A();
                return;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                jd.A0L(qiVar.A0Z());
                return;
            default:
                throw new RuntimeException("Internal error: should never end up through this code path");
        }
        jd.A0T(z);
    }

    public static final void A01(JD jd, NX nx) {
        QK qk;
        QK qk2 = jd.A04;
        int i = jd.A03;
        if (i < 16) {
            long ordinal = (long) nx.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            qk2.A00 |= ordinal;
            qk = null;
        } else {
            qk = new QK();
            qk2.A01 = qk;
            qk.A00 |= (long) nx.ordinal();
        }
        if (qk == null) {
            jd.A03 = i + 1;
            return;
        }
        jd.A04 = qk;
        jd.A03 = 1;
    }

    public static final void A02(JD jd, NX nx, Object obj) {
        QK qk;
        QK qk2 = jd.A04;
        int i = jd.A03;
        if (i < 16) {
            qk2.A02[i] = obj;
            long ordinal = (long) nx.ordinal();
            if (i > 0) {
                ordinal <<= i << 2;
            }
            qk2.A00 = ordinal | qk2.A00;
            qk = null;
        } else {
            qk = new QK();
            qk2.A01 = qk;
            qk.A02[0] = obj;
            qk.A00 = ((long) nx.ordinal()) | qk.A00;
        }
        if (qk == null) {
            jd.A03 = i + 1;
            return;
        }
        jd.A04 = qk;
        jd.A03 = 1;
    }

    public final AbstractC1014qi A0W(AbstractC1014qi qiVar) {
        C0680fB fBVar = new C0680fB(this.A02, qiVar.A0W());
        fBVar.A01 = qiVar.A0S();
        return fBVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[TokenBuffer: ");
        C0680fB fBVar = new C0680fB(this.A02, this.A00);
        int i = 0;
        while (true) {
            try {
                NX A0o = fBVar.A0o();
                if (A0o == null) {
                    break;
                }
                if (i < 100) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(A0o.toString());
                    if (A0o == NX.FIELD_NAME) {
                        sb.append('(');
                        sb.append(fBVar.A0b());
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
        NU.collectDefaults();
    }

    public JD(NY ny) {
        this.A00 = ny;
        QK qk = new QK();
        this.A04 = qk;
        this.A02 = qk;
        this.A03 = 0;
    }

    public final void A0X(AbstractC1014qi qiVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.FIELD_NAME) {
            A0M(qiVar.A0b());
            A0U = qiVar.A0o();
        }
        int i = QJ.A00[A0U.ordinal()];
        if (i == 1) {
            A0C();
            while (qiVar.A0o() != NX.END_OBJECT) {
                A0X(qiVar);
            }
            A09();
        } else if (i != 3) {
            A00(this, qiVar);
        } else {
            A0B();
            while (qiVar.A0o() != NX.END_ARRAY) {
                A0X(qiVar);
            }
            A08();
        }
    }

    @Override // X.AbstractC1012qg, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
