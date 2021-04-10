package X;

/* renamed from: X.0nm  reason: invalid class name and case insensitive filesystem */
public final class C06740nm<T> {
    public final C06740nm<T> A00;
    public final T A01;
    public final String A02;
    public final boolean A03;
    public final boolean A04;

    /* JADX WARN: Incorrect args count in method signature: (LX/0nm<TT;>;)LX/0nm<TT;>; */
    public static C06740nm A00(C06740nm r1, C06740nm r2) {
        C06740nm<T> r0 = r1.A00;
        if (r0 != null) {
            r2 = A00(r0, r2);
        }
        return A01(r1, r2);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0nm<TT;>;)LX/0nm<TT;>; */
    public static final C06740nm A01(C06740nm r5, C06740nm r6) {
        if (r6 == r5.A00) {
            return r5;
        }
        return new C06740nm(r5.A01, r6, r5.A02, r5.A04, r5.A03);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        if (r3.A02 == null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C06740nm<T> A02() {
        /*
            r4 = this;
            X.0nm<T> r0 = r4.A00
            if (r0 != 0) goto L_0x0005
            return r4
        L_0x0005:
            X.0nm r3 = r0.A02()
            java.lang.String r0 = r4.A02
            r2 = 0
            if (r0 == 0) goto L_0x0017
            java.lang.String r0 = r3.A02
            if (r0 != 0) goto L_0x0024
        L_0x0012:
            X.0nm r3 = A01(r4, r2)
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
            X.0nm r3 = A01(r4, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06740nm.A02():X.0nm");
    }

    public final C06740nm<T> A03() {
        C06740nm<T> A032;
        if (this.A03) {
            C06740nm<T> r0 = this.A00;
            if (r0 == null) {
                return null;
            }
            return r0.A03();
        }
        C06740nm<T> r1 = this.A00;
        if (r1 == null || (A032 = r1.A03()) == r1) {
            return this;
        }
        return A01(this, A032);
    }

    public final C06740nm<T> A04() {
        C06740nm<T> A042;
        C06740nm<T> r0 = this.A00;
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

    public final C06740nm<T> A05(T t) {
        if (t == this.A01) {
            return this;
        }
        return new C06740nm<>(t, this.A00, this.A02, this.A04, this.A03);
    }

    public final String toString() {
        String str = this.A01.toString() + "[visible=" + this.A04 + "]";
        C06740nm<T> r0 = this.A00;
        if (r0 != null) {
            return AnonymousClass006.A07(str, ", ", r0.toString());
        }
        return str;
    }

    public C06740nm(T t, C06740nm<T> r4, String str, boolean z, boolean z2) {
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
