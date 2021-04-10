package X;

import java.util.Arrays;

/* renamed from: X.16x  reason: invalid class name and case insensitive filesystem */
public final class C098816x {
    public byte A00;
    public short A01;
    public byte[] A02;

    public final String toString() {
        StringBuilder sb = new StringBuilder("WtTlsInnerPlainText{content= [");
        byte[] bArr = this.A02;
        sb.append(bArr.length);
        sb.append("] ");
        sb.append(AnonymousClass11e.A03(bArr));
        sb.append(", contentType=");
        sb.append((int) this.A00);
        sb.append(", zeros=");
        sb.append((int) this.A01);
        sb.append('}');
        return sb.toString();
    }

    public C098816x(byte[] bArr) {
        byte b;
        int length = bArr.length;
        do {
            length--;
            b = bArr[length];
        } while (b == 0);
        this.A01 = (short) ((length - length) - 1);
        this.A00 = b;
        this.A02 = Arrays.copyOfRange(bArr, 0, length);
    }

    public C098816x(byte[] bArr, byte b) {
        this.A02 = bArr;
        this.A00 = b;
        this.A01 = 0;
    }
}
