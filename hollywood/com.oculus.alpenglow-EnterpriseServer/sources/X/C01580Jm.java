package X;

/* renamed from: X.0Jm  reason: invalid class name and case insensitive filesystem */
public final class C01580Jm extends AbstractC02410Zn {
    public final AbstractC02590aM A00;
    public final AbstractC02450Zr A01;
    public final String A02;

    @Override // X.AbstractC02410Zn
    public final boolean A0L() {
        return false;
    }

    @Override // X.AbstractC02410Zn
    public final C06350mc A07() {
        return null;
    }

    @Override // X.AbstractC02410Zn
    public final AnonymousClass0KC A08() {
        AbstractC02450Zr r1 = this.A01;
        if (r1 instanceof AnonymousClass0KC) {
            return (AnonymousClass0KC) r1;
        }
        return null;
    }

    @Override // X.AbstractC02410Zn
    public final AnonymousClass0EC A0B() {
        AbstractC02450Zr r1 = this.A01;
        if (!(r1 instanceof AnonymousClass0EC)) {
            return null;
        }
        AnonymousClass0EC r12 = (AnonymousClass0EC) r1;
        if (r12.A0Z() == 0) {
            return r12;
        }
        return null;
    }

    @Override // X.AbstractC02410Zn
    public final AnonymousClass0EC A0C() {
        AbstractC02450Zr r2 = this.A01;
        if (!(r2 instanceof AnonymousClass0EC)) {
            return null;
        }
        AnonymousClass0EC r22 = (AnonymousClass0EC) r2;
        if (r22.A0Z() == 1) {
            return r22;
        }
        return null;
    }

    @Override // X.AbstractC02410Zn
    public final AnonymousClass0KB A0D() {
        AbstractC02450Zr r1 = this.A01;
        if (r1 instanceof AnonymousClass0KB) {
            return (AnonymousClass0KB) r1;
        }
        return null;
    }

    @Override // X.AbstractC02410Zn
    public final String A0E() {
        return this.A02;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0H() {
        return this.A01 instanceof AnonymousClass0KB;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0I() {
        return this.A01 instanceof AnonymousClass0KC;
    }

    public C01580Jm(AbstractC02450Zr r1, String str, AbstractC02590aM r3) {
        this.A00 = r3;
        this.A01 = r1;
        this.A02 = str;
    }

    @Override // X.AbstractC02410Zn
    public final AbstractC02450Zr A09() {
        AnonymousClass0EC A0B = A0B();
        if (A0B == null) {
            return A08();
        }
        return A0B;
    }

    @Override // X.AbstractC02410Zn
    public final AbstractC02450Zr A0A() {
        AnonymousClass0KB A0D = A0D();
        if (A0D != null) {
            return A0D;
        }
        AnonymousClass0EC A0C = A0C();
        if (A0C == null) {
            return A08();
        }
        return A0C;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0J() {
        if (A0B() != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0K() {
        if (A0C() != null) {
            return true;
        }
        return false;
    }
}
