package X;

/* renamed from: X.1vk  reason: invalid class name and case insensitive filesystem */
public final class C10851vk {
    public short A00;
    public byte[] A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder("WtExtension{extensionType=");
        sb.append((int) this.A00);
        sb.append(", extensionData=");
        sb.append(AnonymousClass1ut.A03(this.A01));
        sb.append('}');
        return sb.toString();
    }

    public C10851vk(short s, byte[] bArr) {
        this.A00 = s;
        this.A01 = bArr;
    }
}
