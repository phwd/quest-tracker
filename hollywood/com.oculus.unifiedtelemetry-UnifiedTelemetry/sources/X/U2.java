package X;

public final class U2 extends M4 {
    public final VD<String, M4> A00 = new VD<>();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof U2) || !((U2) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }
}
