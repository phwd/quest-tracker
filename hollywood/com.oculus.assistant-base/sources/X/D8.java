package X;

public final class D8 {
    public final D6 A00;
    public final D6 A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder("{oldColumn=");
        sb.append(this.A01);
        sb.append(";newColumn=");
        sb.append(this.A00);
        sb.append("}");
        return sb.toString();
    }

    public D8(D6 d6, D6 d62) {
        this.A01 = d6;
        this.A00 = d62;
    }
}
