package X;

import java.io.IOException;
import java.util.Arrays;

/* renamed from: X.04W  reason: invalid class name */
public final class AnonymousClass04W extends AnonymousClass0Cj {
    public static final AnonymousClass04W A01 = new AnonymousClass04W(new byte[0]);
    public final byte[] A00;

    @Override // X.AbstractC02170iC
    public final String A02() {
        return AnonymousClass0o3.A01.A02(this.A00, false);
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r5, AbstractC02120i3 r6) throws IOException, C03620oC {
        AnonymousClass0o2 r3 = r6._config._base._defaultBase64;
        byte[] bArr = this.A00;
        r5.A0O(r3, bArr, 0, bArr.length);
    }

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Arrays.equals(((AnonymousClass04W) obj).A00, this.A00);
    }

    public final int hashCode() {
        byte[] bArr = this.A00;
        if (bArr == null) {
            return -1;
        }
        return bArr.length;
    }

    @Override // X.AbstractC02170iC, X.AnonymousClass0Cj
    public final String toString() {
        return AnonymousClass0o3.A01.A02(this.A00, true);
    }

    public AnonymousClass04W(byte[] bArr) {
        this.A00 = bArr;
    }
}
