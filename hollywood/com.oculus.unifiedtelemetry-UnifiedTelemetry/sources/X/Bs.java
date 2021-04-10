package X;

public final class Bs extends WF {
    public final Wp A00;
    public final WJ A01;
    public final String A02;

    @Override // X.WF
    public final CD A08() {
        WJ wj = this.A01;
        if (wj instanceof CD) {
            return (CD) wj;
        }
        return null;
    }

    @Override // X.WF
    public final AnonymousClass7P A0A() {
        WJ wj = this.A01;
        if (!(wj instanceof AnonymousClass7P)) {
            return null;
        }
        AnonymousClass7P r1 = (AnonymousClass7P) wj;
        if (r1.A0W() == 0) {
            return r1;
        }
        return null;
    }

    @Override // X.WF
    public final AnonymousClass7P A0B() {
        WJ wj = this.A01;
        if (!(wj instanceof AnonymousClass7P)) {
            return null;
        }
        AnonymousClass7P r2 = (AnonymousClass7P) wj;
        if (r2.A0W() == 1) {
            return r2;
        }
        return null;
    }

    @Override // X.WF
    public final CC A0C() {
        WJ wj = this.A01;
        if (wj instanceof CC) {
            return (CC) wj;
        }
        return null;
    }

    @Override // X.WF
    public final boolean A0F() {
        return this.A01 instanceof CC;
    }

    @Override // X.WF
    public final boolean A0G() {
        return this.A01 instanceof CD;
    }

    public Bs(WJ wj, String str, Wp wp) {
        this.A00 = wp;
        this.A01 = wj;
        this.A02 = str;
    }

    @Override // X.WF
    public final WJ A09() {
        CC A0C = A0C();
        if (A0C != null) {
            return A0C;
        }
        AnonymousClass7P A0B = A0B();
        if (A0B == null) {
            return A08();
        }
        return A0B;
    }

    @Override // X.WF
    public final boolean A0H() {
        if (A0A() != null) {
            return true;
        }
        return false;
    }

    @Override // X.WF
    public final boolean A0I() {
        if (A0B() != null) {
            return true;
        }
        return false;
    }

    @Override // X.WF
    public final C0417hQ A07() {
        return null;
    }

    @Override // X.WF
    public final String A0D() {
        return this.A02;
    }
}
