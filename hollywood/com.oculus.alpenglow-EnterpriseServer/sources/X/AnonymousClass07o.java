package X;

import java.io.IOException;
import java.util.Arrays;

/* renamed from: X.07o  reason: invalid class name */
public final class AnonymousClass07o extends AnonymousClass0E2 {
    public static final AnonymousClass07o A01 = new AnonymousClass07o(new byte[0]);
    public final byte[] A00;

    @Override // X.AnonymousClass0aF
    public final String A02() {
        return C05840lV.A01.A01(this.A00, false);
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C05910ld {
        C05830lU r3 = r6._config._base._defaultBase64;
        byte[] bArr = this.A00;
        r5.A0L(r3, bArr, 0, bArr.length);
    }

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Arrays.equals(((AnonymousClass07o) obj).A00, this.A00);
    }

    public final int hashCode() {
        byte[] bArr = this.A00;
        if (bArr == null) {
            return -1;
        }
        return bArr.length;
    }

    @Override // X.AnonymousClass0aF, X.AnonymousClass0E2
    public final String toString() {
        return C05840lV.A01.A01(this.A00, true);
    }

    public AnonymousClass07o(byte[] bArr) {
        this.A00 = bArr;
    }
}
