package X;

/* renamed from: X.Pf  reason: case insensitive filesystem */
public final class C0283Pf {
    public int A00;
    public AbstractC1024qt A01;
    public Class A02;
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
        C0283Pf pf = (C0283Pf) obj;
        if (pf.A03 != this.A03) {
            return false;
        }
        Class cls = this.A02;
        if (cls != null) {
            return pf.A02 == cls;
        }
        return this.A01.equals(pf.A01);
    }

    public final String toString() {
        StringBuilder sb;
        Class cls = this.A02;
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

    public C0283Pf(AbstractC1024qt qtVar, boolean z) {
        this.A01 = qtVar;
        this.A02 = null;
        this.A03 = z;
        int hashCode = qtVar.hashCode() - 1;
        this.A00 = z ? hashCode - 1 : hashCode;
    }

    public C0283Pf(Class cls, boolean z) {
        this.A02 = cls;
        this.A01 = null;
        this.A03 = z;
        int hashCode = cls.getName().hashCode();
        this.A00 = z ? hashCode + 1 : hashCode;
    }
}
