package X;

/* renamed from: X.2y  reason: invalid class name */
public final class AnonymousClass2y extends AnonymousClass7I {
    public final Object A00;

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Object obj2 = this.A00;
        Object obj3 = ((AnonymousClass2y) obj).A00;
        if (obj2 == null) {
            return obj3 == null;
        }
        return obj2.equals(obj3);
    }

    @Override // X.AbstractC0222Wi
    public final String A06() {
        Object obj = this.A00;
        if (obj == null) {
            return "null";
        }
        return obj.toString();
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AnonymousClass7I, X.AbstractC0222Wi
    public final String toString() {
        return String.valueOf(this.A00);
    }

    public AnonymousClass2y(Object obj) {
        this.A00 = obj;
    }

    @Override // X.AbstractC0222Wi
    public final Integer A04() {
        return AnonymousClass07.A07;
    }
}
