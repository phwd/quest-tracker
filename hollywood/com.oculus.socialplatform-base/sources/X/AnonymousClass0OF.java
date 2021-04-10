package X;

/* renamed from: X.0OF  reason: invalid class name */
public final class AnonymousClass0OF extends AbstractC01960hi {
    public final AbstractC02230iJ A00;
    public final AbstractC01990hm A01;
    public final String A02;

    @Override // X.AbstractC01960hi
    public final boolean A0L() {
        return false;
    }

    @Override // X.AbstractC01960hi
    public final AnonymousClass0Oy A08() {
        AbstractC01990hm r1 = this.A01;
        if (r1 instanceof AnonymousClass0Oy) {
            return (AnonymousClass0Oy) r1;
        }
        return null;
    }

    @Override // X.AbstractC01960hi
    public final AnonymousClass0Cr A0B() {
        AbstractC01990hm r1 = this.A01;
        if (!(r1 instanceof AnonymousClass0Cr)) {
            return null;
        }
        AnonymousClass0Cr r12 = (AnonymousClass0Cr) r1;
        if (r12.A0Z() == 0) {
            return r12;
        }
        return null;
    }

    @Override // X.AbstractC01960hi
    public final AnonymousClass0Cr A0C() {
        AbstractC01990hm r2 = this.A01;
        if (!(r2 instanceof AnonymousClass0Cr)) {
            return null;
        }
        AnonymousClass0Cr r22 = (AnonymousClass0Cr) r2;
        if (r22.A0Z() == 1) {
            return r22;
        }
        return null;
    }

    @Override // X.AbstractC01960hi
    public final AnonymousClass0Ox A0D() {
        AbstractC01990hm r1 = this.A01;
        if (r1 instanceof AnonymousClass0Ox) {
            return (AnonymousClass0Ox) r1;
        }
        return null;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0H() {
        return this.A01 instanceof AnonymousClass0Ox;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0I() {
        return this.A01 instanceof AnonymousClass0Oy;
    }

    public AnonymousClass0OF(AbstractC01990hm r1, String str, AbstractC02230iJ r3) {
        this.A00 = r3;
        this.A01 = r1;
        this.A02 = str;
    }

    @Override // X.AbstractC01960hi
    public final C04070pB A07() {
        return null;
    }

    @Override // X.AbstractC01960hi
    public final AbstractC01990hm A09() {
        AnonymousClass0Cr A0B = A0B();
        if (A0B == null) {
            return A08();
        }
        return A0B;
    }

    @Override // X.AbstractC01960hi
    public final AbstractC01990hm A0A() {
        AnonymousClass0Ox A0D = A0D();
        if (A0D != null) {
            return A0D;
        }
        AnonymousClass0Cr A0C = A0C();
        if (A0C == null) {
            return A08();
        }
        return A0C;
    }

    @Override // X.AbstractC01960hi
    public final String A0E() {
        return this.A02;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0J() {
        if (A0B() != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0K() {
        if (A0C() != null) {
            return true;
        }
        return false;
    }
}
