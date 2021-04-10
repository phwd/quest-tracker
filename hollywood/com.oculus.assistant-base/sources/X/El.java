package X;

public final class El {
    public final byte A00;
    public final El A01;
    public final El A02;
    public final String A03;

    public El(byte b, String str, El el, El el2) {
        this.A00 = b;
        this.A03 = str;
        this.A01 = el;
        this.A02 = el2;
    }
}
