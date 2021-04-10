package X;

/* renamed from: X.0T  reason: invalid class name */
public final class AnonymousClass0T extends PV {
    public final Object A00;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Object obj2 = this.A00;
        Object obj3 = ((AnonymousClass0T) obj).A00;
        if (obj2 == null) {
            return obj3 == null;
        }
        return obj2.equals(obj3);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.OA, X.PV
    public final String toString() {
        return String.valueOf(this.A00);
    }

    public AnonymousClass0T(Object obj) {
        this.A00 = obj;
    }
}
