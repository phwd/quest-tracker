package X;

import java.util.Arrays;

/* renamed from: X.03B  reason: invalid class name */
public final class AnonymousClass03B extends AnonymousClass07G {
    public static final AnonymousClass03B A01 = new AnonymousClass03B(new byte[0]);
    public final byte[] A00;

    @Override // X.AbstractC03980gY
    public final String A02() {
        return AnonymousClass0jX.A01.A02(this.A00, false);
    }

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Arrays.equals(((AnonymousClass03B) obj).A00, this.A00);
    }

    public final int hashCode() {
        byte[] bArr = this.A00;
        if (bArr == null) {
            return -1;
        }
        return bArr.length;
    }

    @Override // X.AbstractC03980gY, X.AnonymousClass07G
    public final String toString() {
        return AnonymousClass0jX.A01.A02(this.A00, true);
    }

    public AnonymousClass03B(byte[] bArr) {
        this.A00 = bArr;
    }
}
