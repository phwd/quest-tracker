package X;

import java.io.IOException;

/* renamed from: X.04K  reason: invalid class name */
public final class AnonymousClass04K extends AnonymousClass0Cj {
    public final Object A00;

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Object obj2 = this.A00;
        Object obj3 = ((AnonymousClass04K) obj).A00;
        if (obj2 == null) {
            return obj3 == null;
        }
        return obj2.equals(obj3);
    }

    @Override // X.AbstractC02170iC
    public final String A02() {
        Object obj = this.A00;
        if (obj == null) {
            return "null";
        }
        return obj.toString();
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C03620oC {
        Object obj = this.A00;
        if (obj == null) {
            r3.A0E(r2);
        } else {
            r2.A0C(obj);
        }
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC02170iC, X.AnonymousClass0Cj
    public final String toString() {
        return String.valueOf(this.A00);
    }

    public AnonymousClass04K(Object obj) {
        this.A00 = obj;
    }
}
