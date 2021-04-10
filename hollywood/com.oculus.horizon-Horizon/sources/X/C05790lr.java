package X;

/* renamed from: X.0lr  reason: invalid class name and case insensitive filesystem */
public final class C05790lr<T> {
    public final C05790lr<T> A00;
    public final T A01;
    public final String A02;
    public final boolean A03;
    public final boolean A04;

    /* JADX WARN: Incorrect args count in method signature: (LX/0lr<TT;>;)LX/0lr<TT;>; */
    public static C05790lr A00(C05790lr r1, C05790lr r2) {
        C05790lr<T> r0 = r1.A00;
        if (r0 != null) {
            r2 = A00(r0, r2);
        }
        return A01(r1, r2);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0lr<TT;>;)LX/0lr<TT;>; */
    public static final C05790lr A01(C05790lr r5, C05790lr r6) {
        if (r6 == r5.A00) {
            return r5;
        }
        return new C05790lr(r5.A01, r6, r5.A02, r5.A04, r5.A03);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (r3.A02 == null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C05790lr<T> A02() {
        /*
            r4 = this;
            X.0lr<T> r0 = r4.A00
            if (r0 != 0) goto L_0x0005
            return r4
        L_0x0005:
            X.0lr r3 = r0.A02()
            java.lang.String r0 = r4.A02
            r2 = 0
            if (r0 == 0) goto L_0x0017
            java.lang.String r0 = r3.A02
            if (r0 != 0) goto L_0x0024
        L_0x0012:
            X.0lr r3 = A01(r4, r2)
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
            X.0lr r3 = A01(r4, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05790lr.A02():X.0lr");
    }

    public final C05790lr<T> A03() {
        C05790lr<T> A032;
        if (this.A03) {
            C05790lr<T> r0 = this.A00;
            if (r0 == null) {
                return null;
            }
            return r0.A03();
        }
        C05790lr<T> r1 = this.A00;
        if (r1 == null || (A032 = r1.A03()) == r1) {
            return this;
        }
        return A01(this, A032);
    }

    public final C05790lr<T> A04() {
        C05790lr<T> A042;
        C05790lr<T> r0 = this.A00;
        if (r0 == null) {
            A042 = null;
        } else {
            A042 = r0.A04();
        }
        if (this.A04) {
            return A01(this, A042);
        }
        return A042;
    }

    public final C05790lr<T> A05(T t) {
        if (t == this.A01) {
            return this;
        }
        return new C05790lr<>(t, this.A00, this.A02, this.A04, this.A03);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A01.toString());
        sb.append("[visible=");
        sb.append(this.A04);
        sb.append("]");
        String obj = sb.toString();
        C05790lr<T> r0 = this.A00;
        if (r0 != null) {
            return AnonymousClass006.A07(obj, ", ", r0.toString());
        }
        return obj;
    }

    public C05790lr(T t, C05790lr<T> r4, String str, boolean z, boolean z2) {
        this.A01 = t;
        this.A00 = r4;
        String str2 = null;
        if (str != null) {
            this.A02 = str.length() != 0 ? str : str2;
        }
        this.A04 = z;
        this.A03 = z2;
    }
}
