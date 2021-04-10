package X;

public final class LF {
    public final byte A00;
    public final byte A01;
    public final int A02;

    public LF() {
        this((byte) 0, (byte) 0, 0);
    }

    public LF(byte b, byte b2, int i) {
        this.A00 = b;
        this.A01 = b2;
        this.A02 = i;
    }
}
