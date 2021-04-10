package X;

import androidx.recyclerview.widget.RecyclerView;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.math.BigInteger;

/* renamed from: X.00u  reason: invalid class name */
public final class AnonymousClass00u extends AnonymousClass04R {
    public static final BigInteger A01 = BigInteger.valueOf(2147483647L);
    public static final BigInteger A02 = BigInteger.valueOf(RecyclerView.FOREVER_NS);
    public static final BigInteger A03 = BigInteger.valueOf(LibraryDBContract.VERSION_NOT_INSTALLED);
    public static final BigInteger A04 = BigInteger.valueOf(Long.MIN_VALUE);
    public final BigInteger A00;

    @Override // X.AnonymousClass04R, X.AbstractC02170iC
    public final String A02() {
        return this.A00.toString();
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C03620oC {
        r2.A0W(this.A00);
    }

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((AnonymousClass00u) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass00u(BigInteger bigInteger) {
        this.A00 = bigInteger;
    }
}
