package X;

public final class VL<T> {
    public final VL<T> A00;
    public final T A01;
    public final String A02;
    public final boolean A03;
    public final boolean A04;

    /* JADX WARN: Incorrect args count in method signature: (LX/VL<TT;>;)LX/VL<TT;>; */
    public static VL A00(VL vl, VL vl2) {
        VL<T> vl3 = vl.A00;
        if (vl3 != null) {
            vl2 = A00(vl3, vl2);
        }
        return A01(vl, vl2);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/VL<TT;>;)LX/VL<TT;>; */
    public static final VL A01(VL vl, VL vl2) {
        if (vl2 == vl.A00) {
            return vl;
        }
        return new VL(vl.A01, vl2, vl.A02, vl.A04, vl.A03);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (r3.A02 == null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.VL<T> A02() {
        /*
            r4 = this;
            X.VL<T> r0 = r4.A00
            if (r0 != 0) goto L_0x0005
            return r4
        L_0x0005:
            X.VL r3 = r0.A02()
            java.lang.String r0 = r4.A02
            r2 = 0
            if (r0 == 0) goto L_0x0017
            java.lang.String r0 = r3.A02
            if (r0 != 0) goto L_0x0024
        L_0x0012:
            X.VL r3 = A01(r4, r2)
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
            X.VL r3 = A01(r4, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.VL.A02():X.VL");
    }

    public final VL<T> A03() {
        VL<T> A032;
        if (this.A03) {
            VL<T> vl = this.A00;
            if (vl == null) {
                return null;
            }
            return vl.A03();
        }
        VL<T> vl2 = this.A00;
        if (vl2 == null || (A032 = vl2.A03()) == vl2) {
            return this;
        }
        return A01(this, A032);
    }

    public final VL<T> A04() {
        VL<T> A042;
        VL<T> vl = this.A00;
        if (vl == null) {
            A042 = null;
        } else {
            A042 = vl.A04();
        }
        if (this.A04) {
            return A01(this, A042);
        }
        return A042;
    }

    public final VL<T> A05(T t) {
        if (t == this.A01) {
            return this;
        }
        return new VL<>(t, this.A00, this.A02, this.A04, this.A03);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A01.toString());
        sb.append("[visible=");
        sb.append(this.A04);
        sb.append("]");
        String obj = sb.toString();
        VL<T> vl = this.A00;
        if (vl != null) {
            return AnonymousClass06.A05(obj, ", ", vl.toString());
        }
        return obj;
    }

    public VL(T t, VL<T> vl, String str, boolean z, boolean z2) {
        this.A01 = t;
        this.A00 = vl;
        String str2 = null;
        if (str != null) {
            this.A02 = str.length() != 0 ? str : str2;
        }
        this.A04 = z;
        this.A03 = z2;
    }
}
