package X;

/* renamed from: X.0AH  reason: invalid class name */
public final class AnonymousClass0AH {
    public final Object A00;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Object obj2 = this.A00;
        Object obj3 = ((AnonymousClass0AH) obj).A00;
        if (obj2 == null) {
            return obj3 == null;
        }
        return obj2.equals(obj3);
    }

    public final int hashCode() {
        Object obj = this.A00;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.A00 + "}";
    }

    public AnonymousClass0AH(Object obj) {
        this.A00 = obj;
    }
}
