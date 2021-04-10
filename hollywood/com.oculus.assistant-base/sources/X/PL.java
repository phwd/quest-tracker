package X;

public final class PL {
    public final PL A00;
    public final Object A01;
    public final String A02;
    public final boolean A03;
    public final boolean A04;

    public static PL A00(PL pl, PL pl2) {
        PL pl3 = pl.A00;
        if (pl3 != null) {
            pl2 = A00(pl3, pl2);
        }
        return A01(pl, pl2);
    }

    public static final PL A01(PL pl, PL pl2) {
        if (pl2 == pl.A00) {
            return pl;
        }
        return new PL(pl.A01, pl2, pl.A02, pl.A04, pl.A03);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (r3.A02 == null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.PL A02() {
        /*
            r4 = this;
            X.PL r0 = r4.A00
            if (r0 != 0) goto L_0x0005
            return r4
        L_0x0005:
            X.PL r3 = r0.A02()
            java.lang.String r0 = r4.A02
            r2 = 0
            if (r0 == 0) goto L_0x0017
            java.lang.String r0 = r3.A02
            if (r0 != 0) goto L_0x0024
        L_0x0012:
            X.PL r3 = A01(r4, r2)
        L_0x0016:
            return r3
        L_0x0017:
            java.lang.String r0 = r3.A02
            if (r0 != 0) goto L_0x0016
            boolean r1 = r4.A04
            boolean r0 = r3.A04
            if (r1 == r0) goto L_0x0024
            if (r1 == 0) goto L_0x0016
            goto L_0x0012
        L_0x0024:
            X.PL r3 = A01(r4, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.PL.A02():X.PL");
    }

    public final PL A03() {
        PL A032;
        if (this.A03) {
            PL pl = this.A00;
            if (pl == null) {
                return null;
            }
            return pl.A03();
        }
        PL pl2 = this.A00;
        if (pl2 == null || (A032 = pl2.A03()) == pl2) {
            return this;
        }
        return A01(this, A032);
    }

    public final PL A04() {
        PL A042;
        PL pl = this.A00;
        if (pl == null) {
            A042 = null;
        } else {
            A042 = pl.A04();
        }
        if (this.A04) {
            return A01(this, A042);
        }
        return A042;
    }

    public final PL A05(Object obj) {
        if (obj == this.A01) {
            return this;
        }
        return new PL(obj, this.A00, this.A02, this.A04, this.A03);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A01.toString());
        sb.append("[visible=");
        sb.append(this.A04);
        sb.append("]");
        String obj = sb.toString();
        PL pl = this.A00;
        if (pl != null) {
            return AnonymousClass08.A05(obj, ", ", pl.toString());
        }
        return obj;
    }

    public PL(Object obj, PL pl, String str, boolean z, boolean z2) {
        this.A01 = obj;
        this.A00 = pl;
        String str2 = null;
        if (str != null) {
            this.A02 = str.length() != 0 ? str : str2;
        }
        this.A04 = z;
        this.A03 = z2;
    }
}
