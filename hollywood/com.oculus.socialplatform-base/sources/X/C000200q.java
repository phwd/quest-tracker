package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;

/* renamed from: X.00q  reason: invalid class name and case insensitive filesystem */
public final class C000200q extends AnonymousClass04R {
    public final long A00;

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((C000200q) obj).A00 == this.A00;
        }
        return true;
    }

    @Override // X.AnonymousClass04R, X.AbstractC02170iC
    public final String A02() {
        long j = this.A00;
        if (j > 2147483647L || j < LibraryDBContract.VERSION_NOT_INSTALLED) {
            return Long.toString(j);
        }
        int i = (int) j;
        String[] strArr = C03790oY.A02;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = C03790oY.A03;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C03620oC {
        r3.A0N(this.A00);
    }

    public final int hashCode() {
        long j = this.A00;
        return ((int) j) ^ ((int) (j >> 32));
    }

    public C000200q(long j) {
        this.A00 = j;
    }
}
