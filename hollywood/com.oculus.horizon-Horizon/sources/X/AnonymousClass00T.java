package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.math.BigInteger;

/* renamed from: X.00T  reason: invalid class name */
public final class AnonymousClass00T extends AnonymousClass038 {
    public static final BigInteger A01 = BigInteger.valueOf(2147483647L);
    public static final BigInteger A02 = BigInteger.valueOf(Long.MAX_VALUE);
    public static final BigInteger A03 = BigInteger.valueOf(LibraryDBContract.VERSION_NOT_INSTALLED);
    public static final BigInteger A04 = BigInteger.valueOf(Long.MIN_VALUE);
    public final BigInteger A00;

    @Override // X.AbstractC03980gY, X.AnonymousClass038
    public final String A02() {
        return this.A00.toString();
    }

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((AnonymousClass00T) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass00T(BigInteger bigInteger) {
        this.A00 = bigInteger;
    }
}
