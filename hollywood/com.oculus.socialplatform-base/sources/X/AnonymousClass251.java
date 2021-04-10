package X;

/* renamed from: X.251  reason: invalid class name */
public final class AnonymousClass251 {
    public short A00;
    public byte[] A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder("WtExtension{extensionType=");
        sb.append((int) this.A00);
        sb.append(", extensionData=");
        sb.append(AnonymousClass24K.A03(this.A01));
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass251(short s, byte[] bArr) {
        this.A00 = s;
        this.A01 = bArr;
    }
}
