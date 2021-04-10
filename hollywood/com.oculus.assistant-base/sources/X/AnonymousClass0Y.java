package X;

import java.util.Arrays;

/* renamed from: X.0Y  reason: invalid class name */
public final class AnonymousClass0Y extends PV {
    public static final AnonymousClass0Y A01 = new AnonymousClass0Y(new byte[0]);
    public final byte[] A00;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Arrays.equals(((AnonymousClass0Y) obj).A00, this.A00);
    }

    public final int hashCode() {
        byte[] bArr = this.A00;
        if (bArr == null) {
            return -1;
        }
        return bArr.length;
    }

    @Override // X.OA, X.PV
    public final String toString() {
        return NQ.A01.A02(this.A00, true);
    }

    public AnonymousClass0Y(byte[] bArr) {
        this.A00 = bArr;
    }
}
