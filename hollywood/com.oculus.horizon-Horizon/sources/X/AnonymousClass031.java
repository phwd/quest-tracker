package X;

/* renamed from: X.031  reason: invalid class name */
public final class AnonymousClass031 extends AnonymousClass07G {
    public final Object A00;

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Object obj2 = this.A00;
        Object obj3 = ((AnonymousClass031) obj).A00;
        if (obj2 == null) {
            return obj3 == null;
        }
        return obj2.equals(obj3);
    }

    @Override // X.AbstractC03980gY
    public final String A02() {
        Object obj = this.A00;
        if (obj == null) {
            return "null";
        }
        return obj.toString();
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC03980gY, X.AnonymousClass07G
    public final String toString() {
        return String.valueOf(this.A00);
    }

    public AnonymousClass031(Object obj) {
        this.A00 = obj;
    }
}
