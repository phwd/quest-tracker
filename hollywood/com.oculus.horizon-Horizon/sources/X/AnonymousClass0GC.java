package X;

/* renamed from: X.0GC  reason: invalid class name */
public final class AnonymousClass0GC extends AnonymousClass0g5 {
    public final AbstractC04040gi A00;
    public final AnonymousClass0g9 A01;
    public final String A02;

    @Override // X.AnonymousClass0g5
    public final AnonymousClass0GX A08() {
        AnonymousClass0g9 r1 = this.A01;
        if (r1 instanceof AnonymousClass0GX) {
            return (AnonymousClass0GX) r1;
        }
        return null;
    }

    @Override // X.AnonymousClass0g5
    public final AnonymousClass07O A0A() {
        AnonymousClass0g9 r1 = this.A01;
        if (!(r1 instanceof AnonymousClass07O)) {
            return null;
        }
        AnonymousClass07O r12 = (AnonymousClass07O) r1;
        if (r12.A0W() == 0) {
            return r12;
        }
        return null;
    }

    @Override // X.AnonymousClass0g5
    public final AnonymousClass07O A0B() {
        AnonymousClass0g9 r2 = this.A01;
        if (!(r2 instanceof AnonymousClass07O)) {
            return null;
        }
        AnonymousClass07O r22 = (AnonymousClass07O) r2;
        if (r22.A0W() == 1) {
            return r22;
        }
        return null;
    }

    @Override // X.AnonymousClass0g5
    public final AnonymousClass0GW A0C() {
        AnonymousClass0g9 r1 = this.A01;
        if (r1 instanceof AnonymousClass0GW) {
            return (AnonymousClass0GW) r1;
        }
        return null;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0F() {
        return this.A01 instanceof AnonymousClass0GW;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0G() {
        return this.A01 instanceof AnonymousClass0GX;
    }

    public AnonymousClass0GC(AnonymousClass0g9 r1, String str, AbstractC04040gi r3) {
        this.A00 = r3;
        this.A01 = r1;
        this.A02 = str;
    }

    @Override // X.AnonymousClass0g5
    public final C05280kg A07() {
        return null;
    }

    @Override // X.AnonymousClass0g5
    public final AnonymousClass0g9 A09() {
        AnonymousClass0GW A0C = A0C();
        if (A0C != null) {
            return A0C;
        }
        AnonymousClass07O A0B = A0B();
        if (A0B == null) {
            return A08();
        }
        return A0B;
    }

    @Override // X.AnonymousClass0g5
    public final String A0D() {
        return this.A02;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0H() {
        if (A0A() != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0I() {
        if (A0B() != null) {
            return true;
        }
        return false;
    }
}
