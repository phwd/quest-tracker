package X;

/* renamed from: X.1vm  reason: invalid class name and case insensitive filesystem */
public final class C10871vm {
    public final int A00;
    public final int A01;
    public final byte[] A02;

    public final String toString() {
        StringBuilder sb = new StringBuilder("WtWriteParams{offset=");
        sb.append(this.A01);
        sb.append(", len=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }

    public C10871vm(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            this.A02 = (byte[]) bArr.clone();
        }
        this.A01 = i;
        this.A00 = i2;
    }
}
