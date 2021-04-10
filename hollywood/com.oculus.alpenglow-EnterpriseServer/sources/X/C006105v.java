package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.math.BigInteger;

/* renamed from: X.05v  reason: invalid class name and case insensitive filesystem */
public final class C006105v extends AnonymousClass07j {
    public static final BigInteger A01 = BigInteger.valueOf(2147483647L);
    public static final BigInteger A02 = BigInteger.valueOf(Long.MAX_VALUE);
    public static final BigInteger A03 = BigInteger.valueOf(LibraryDBContract.VERSION_NOT_INSTALLED);
    public static final BigInteger A04 = BigInteger.valueOf(Long.MIN_VALUE);
    public final BigInteger A00;

    @Override // X.AnonymousClass07j, X.AnonymousClass0aF
    public final String A02() {
        return this.A00.toString();
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C05910ld {
        r2.A0U(this.A00);
    }

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((C006105v) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public C006105v(BigInteger bigInteger) {
        this.A00 = bigInteger;
    }
}
