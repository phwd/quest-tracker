package X;

public final class LV {
    public int A00 = 0;
    public int A01;
    public byte[] A02;

    public final byte A00() {
        int i = this.A00;
        if (i < this.A01) {
            byte[] bArr = this.A02;
            this.A00 = i + 1;
            return bArr[i];
        }
        throw new IllegalStateException();
    }

    public LV(byte[] bArr, int i) {
        this.A02 = bArr;
        this.A01 = i;
    }
}
