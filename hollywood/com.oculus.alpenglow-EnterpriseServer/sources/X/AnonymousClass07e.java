package X;

import java.io.IOException;

/* renamed from: X.07e  reason: invalid class name */
public final class AnonymousClass07e extends AnonymousClass0E2 {
    public final Object A00;

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Object obj2 = this.A00;
        Object obj3 = ((AnonymousClass07e) obj).A00;
        if (obj2 == null) {
            return obj3 == null;
        }
        return obj2.equals(obj3);
    }

    @Override // X.AnonymousClass0aF
    public final String A02() {
        Object obj = this.A00;
        if (obj == null) {
            return "null";
        }
        return obj.toString();
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C05910ld {
        Object obj = this.A00;
        if (obj == null) {
            r3.A0D(r2);
        } else {
            r2.A09(obj);
        }
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AnonymousClass0aF, X.AnonymousClass0E2
    public final String toString() {
        return String.valueOf(this.A00);
    }

    public AnonymousClass07e(Object obj) {
        this.A00 = obj;
    }
}
