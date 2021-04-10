package X;

/* renamed from: X.24P  reason: invalid class name */
public final class AnonymousClass24P {
    public byte[] A00;
    public AnonymousClass24S A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder("WtCertificateEntry{certData=");
        sb.append(AnonymousClass24K.A03(this.A00));
        sb.append(", wtExtensions=");
        sb.append(this.A01);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass24P(byte[] bArr, AnonymousClass24S r2) {
        this.A00 = bArr;
        this.A01 = r2;
    }
}
