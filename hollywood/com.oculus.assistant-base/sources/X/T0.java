package X;

public final class T0 extends AbstractC0490aa {
    public int A00;
    public final byte[] A01;

    public T0(byte[] bArr) {
        C0514bB.A02(bArr, "array");
        this.A01 = bArr;
    }

    public final boolean hasNext() {
        if (this.A00 < this.A01.length) {
            return true;
        }
        return false;
    }
}
