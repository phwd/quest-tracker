package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;

/* renamed from: X.05T  reason: invalid class name */
public final class AnonymousClass05T extends AnonymousClass07j {
    public final long A00;

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass05T) obj).A00 == this.A00;
        }
        return true;
    }

    @Override // X.AnonymousClass07j, X.AnonymousClass0aF
    public final String A02() {
        long j = this.A00;
        if (j > 2147483647L || j < LibraryDBContract.VERSION_NOT_INSTALLED) {
            return Long.toString(j);
        }
        int i = (int) j;
        String[] strArr = C06130m0.A02;
        if (i < strArr.length) {
            if (i >= 0) {
                return strArr[i];
            }
            int i2 = (-i) - 1;
            String[] strArr2 = C06130m0.A03;
            if (i2 < strArr2.length) {
                return strArr2[i2];
            }
        }
        return Integer.toString(i);
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C05910ld {
        r3.A0K(this.A00);
    }

    public final int hashCode() {
        long j = this.A00;
        return ((int) j) ^ ((int) (j >> 32));
    }

    public AnonymousClass05T(long j) {
        this.A00 = j;
    }
}
