package X;

import javax.annotation.Nullable;

public abstract class XZ implements Rc {
    @Nullable
    public volatile Re A00;

    public abstract long A03(long j, long j2, boolean z);

    public abstract String A04(long j, String str, boolean z);

    public abstract void A05(long j, Ra ra);

    public abstract boolean A06();

    public abstract boolean A07(long j);

    public abstract boolean A08(long j);

    public abstract boolean A09(long j, boolean z, boolean z2);

    @Override // X.Rc
    public final long A2c(long j, Rd rd) {
        return A2b(j, C0478qP.A00(j), rd);
    }

    @Override // X.Rc
    public final boolean A2L(long j) {
        return A2M(j, Rd.A04);
    }

    @Override // X.Rc
    public final boolean A2N(long j, boolean z, Rd rd) {
        Rf A02;
        if (this.A00 != null && this.A00.hasBoolOverrideForParam(j)) {
            return this.A00.boolOverrideForParam(j, z);
        }
        if (!rd.A01 || (A02 = A02(j)) == Rf.SERVER || A02 == Rf.DEFAULT__SERVER_RETURNED_NULL) {
            return A09(j, z, rd.A02);
        }
        return z;
    }

    @Override // X.Rc
    public final long A2b(long j, long j2, Rd rd) {
        Rf A02;
        if (this.A00 != null && this.A00.hasIntOverrideForParam(j)) {
            return this.A00.intOverrideForParam(j, j2);
        }
        if (!rd.A01 || (A02 = A02(j)) == Rf.SERVER || A02 == Rf.DEFAULT__SERVER_RETURNED_NULL) {
            return A03(j, j2, rd.A02);
        }
        return j2;
    }

    @Override // X.Rc
    public final String A2v(long j, String str, Rd rd) {
        Rf A02;
        if (this.A00 != null && this.A00.hasStringOverrideForParam(j)) {
            String stringOverrideForParam = this.A00.stringOverrideForParam(j, str);
            if ("__fbt_null__".equals(stringOverrideForParam)) {
                return str;
            }
            return stringOverrideForParam;
        } else if (!rd.A01 || (A02 = A02(j)) == Rf.SERVER || A02 == Rf.DEFAULT__SERVER_RETURNED_NULL) {
            return A04(j, str, rd.A02);
        } else {
            return str;
        }
    }

    public XZ(Re re) {
        this.A00 = re;
    }

    private Rf A02(long j) {
        if (!A06()) {
            return Rf.DEFAULT__NO_DATA_ON_DISK;
        }
        if (A08(j)) {
            return Rf.SERVER;
        }
        if (A07(j)) {
            return Rf.DEFAULT__SERVER_RETURNED_NULL;
        }
        return Rf.DEFAULT__MISSING_SERVER_VALUE;
    }

    @Override // X.Rc
    public final boolean A2M(long j, Rd rd) {
        return A2N(j, SL.A01(j), rd);
    }

    @Override // X.Rc
    public final String A2u(long j, Rd rd) {
        return A2v(j, C0478qP.A01(j), rd);
    }
}
