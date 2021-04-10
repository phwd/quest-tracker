package X;

/* renamed from: X.179  reason: invalid class name */
public final class AnonymousClass179 {
    public final int A00;
    public final int A01;
    public final byte[] A02;

    public final String toString() {
        return "WtWriteParams{offset=" + this.A01 + ", len=" + this.A00 + '}';
    }

    public AnonymousClass179(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            this.A02 = (byte[]) bArr.clone();
        }
        this.A01 = i;
        this.A00 = i2;
    }
}
