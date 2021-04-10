package X;

import java.util.Arrays;

/* renamed from: X.1nl  reason: invalid class name and case insensitive filesystem */
public class C10541nl {
    public final int A00;
    public final byte[] A01;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C10541nl)) {
            return false;
        }
        return Arrays.equals(this.A01, ((C10541nl) obj).A01);
    }

    public final int hashCode() {
        return this.A00;
    }

    public C10541nl(byte[] bArr) {
        this.A01 = bArr;
        this.A00 = Arrays.hashCode(bArr);
    }
}
