package X;

/* renamed from: X.oC  reason: case insensitive filesystem */
public abstract class AbstractC0890oC implements AbstractC0166Fr {
    public volatile AbstractC0168Ft A00;

    public abstract long A04(long j, long j2, boolean z);

    public abstract String A05(long j, String str, boolean z);

    public abstract void A06(long j, EnumC0165Fq fq);

    public abstract boolean A08(long j, boolean z, boolean z2);

    public final boolean A07() {
        if (!(this instanceof C0516bD) || ((C0516bD) this).A03 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC0166Fr
    public final boolean A2I(long j, boolean z, C0167Fs fs) {
        if (this.A00 == null || !this.A00.hasBoolOverrideForParam(j)) {
            if (fs.A02) {
                EnumC0169Fu A03 = A03(j);
                fs.A00 = A03;
                if (!(A03 == EnumC0169Fu.SERVER || A03 == EnumC0169Fu.DEFAULT__SERVER_RETURNED_NULL)) {
                    return z;
                }
            }
            return A08(j, z, fs.A03);
        }
        if (fs.A02) {
            fs.A00 = EnumC0169Fu.OVERRIDE;
        }
        return this.A00.boolOverrideForParam(j, z);
    }

    @Override // X.AbstractC0166Fr
    public final long A2c(long j, long j2, C0167Fs fs) {
        if (this.A00 == null || !this.A00.hasIntOverrideForParam(j)) {
            if (fs.A02) {
                EnumC0169Fu A03 = A03(j);
                fs.A00 = A03;
                if (!(A03 == EnumC0169Fu.SERVER || A03 == EnumC0169Fu.DEFAULT__SERVER_RETURNED_NULL)) {
                    return j2;
                }
            }
            return A04(j, j2, fs.A03);
        }
        if (fs.A02) {
            fs.A00 = EnumC0169Fu.OVERRIDE;
        }
        return this.A00.intOverrideForParam(j, j2);
    }

    @Override // X.AbstractC0166Fr
    public final String A2y(long j, String str, C0167Fs fs) {
        if (this.A00 == null || !this.A00.hasStringOverrideForParam(j)) {
            if (fs.A02) {
                EnumC0169Fu A03 = A03(j);
                fs.A00 = A03;
                if (!(A03 == EnumC0169Fu.SERVER || A03 == EnumC0169Fu.DEFAULT__SERVER_RETURNED_NULL)) {
                    return str;
                }
            }
            return A05(j, str, fs.A03);
        }
        if (fs.A02) {
            fs.A00 = EnumC0169Fu.OVERRIDE;
        }
        String stringOverrideForParam = this.A00.stringOverrideForParam(j, str);
        if ("__fbt_null__".equals(stringOverrideForParam)) {
            return str;
        }
        return stringOverrideForParam;
    }

    public AbstractC0890oC(AbstractC0168Ft ft) {
        this.A00 = ft;
    }

    private EnumC0169Fu A03(long j) {
        if (!A07()) {
            return EnumC0169Fu.DEFAULT__NO_DATA_ON_DISK;
        }
        boolean z = this instanceof C0516bD;
        if (z) {
            C0516bD bDVar = (C0516bD) this;
            if (bDVar.A07() && (C0516bD.A01(bDVar, j) & 1) == 0) {
                return EnumC0169Fu.SERVER;
            }
        }
        if (z) {
            C0516bD bDVar2 = (C0516bD) this;
            if (bDVar2.A07() && (C0516bD.A01(bDVar2, j) & 16) == 0) {
                return EnumC0169Fu.DEFAULT__SERVER_RETURNED_NULL;
            }
        }
        return EnumC0169Fu.DEFAULT__MISSING_SERVER_VALUE;
    }
}
