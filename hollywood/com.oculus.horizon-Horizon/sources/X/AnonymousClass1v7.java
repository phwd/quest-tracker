package X;

/* renamed from: X.1v7  reason: invalid class name */
public final class AnonymousClass1v7 {
    public byte[] A00;
    public AnonymousClass1vA A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder("WtCertificateEntry{certData=");
        sb.append(AnonymousClass1ut.A03(this.A00));
        sb.append(", wtExtensions=");
        sb.append(this.A01);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass1v7(byte[] bArr, AnonymousClass1vA r2) {
        this.A00 = bArr;
        this.A01 = r2;
    }
}
