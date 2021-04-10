package X;

/* renamed from: X.06t  reason: invalid class name and case insensitive filesystem */
public final class C001406t {
    public final Object A00;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Object obj2 = this.A00;
        Object obj3 = ((C001406t) obj).A00;
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
        StringBuilder sb = new StringBuilder("DisplayCutoutCompat{");
        sb.append(this.A00);
        sb.append("}");
        return sb.toString();
    }

    public C001406t(Object obj) {
        this.A00 = obj;
    }
}
