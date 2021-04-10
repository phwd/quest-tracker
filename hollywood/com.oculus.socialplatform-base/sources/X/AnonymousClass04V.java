package X;

import java.io.IOException;

/* renamed from: X.04V  reason: invalid class name */
public final class AnonymousClass04V extends AnonymousClass0Cj {
    public static final AnonymousClass04V A01 = new AnonymousClass04V(false);
    public static final AnonymousClass04V A02 = new AnonymousClass04V(true);
    public final boolean A00;

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && this.A00 == ((AnonymousClass04V) obj).A00;
        }
        return true;
    }

    @Override // X.AbstractC02170iC
    public final String A02() {
        if (this.A00) {
            return "true";
        }
        return "false";
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C03620oC {
        r2.A0Y(this.A00);
    }

    public AnonymousClass04V(boolean z) {
        this.A00 = z;
    }
}
