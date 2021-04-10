package X;

public final class M2 extends AbstractC0241hm {
    public final hM<String, AbstractC0241hm> A00 = new hM<>();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof M2) || !((M2) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }
}
