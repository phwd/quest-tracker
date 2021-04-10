package X;

/* renamed from: X.0qp  reason: invalid class name */
public final class AnonymousClass0qp {
    public int A00;
    public AbstractC02190iF A01;
    public Class<?> A02;
    public boolean A03;

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        AnonymousClass0qp r4 = (AnonymousClass0qp) obj;
        if (r4.A03 != this.A03) {
            return false;
        }
        Class<?> cls = this.A02;
        if (cls != null) {
            return r4.A02 == cls;
        }
        return this.A01.equals(r4.A01);
    }

    public final String toString() {
        StringBuilder sb;
        Class<?> cls = this.A02;
        if (cls != null) {
            sb = new StringBuilder("{class: ");
            sb.append(cls.getName());
        } else {
            sb = new StringBuilder("{type: ");
            sb.append(this.A01);
        }
        sb.append(", typed? ");
        sb.append(this.A03);
        sb.append("}");
        return sb.toString();
    }

    public final int hashCode() {
        return this.A00;
    }

    public AnonymousClass0qp(AbstractC02190iF r2, boolean z) {
        this.A01 = r2;
        this.A02 = null;
        this.A03 = z;
        int hashCode = r2.hashCode() - 1;
        this.A00 = z ? hashCode - 1 : hashCode;
    }

    public AnonymousClass0qp(Class<?> cls, boolean z) {
        this.A02 = cls;
        this.A01 = null;
        this.A03 = z;
        int hashCode = cls.getName().hashCode();
        this.A00 = z ? hashCode + 1 : hashCode;
    }
}
