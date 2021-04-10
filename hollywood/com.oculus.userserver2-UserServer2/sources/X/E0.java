package X;

public final class E0 extends WU {
    public final WR A00;

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof E0) && ((E0) obj).A00.equals(this.A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public E0(WR wr) {
        this.A00 = wr;
    }
}
