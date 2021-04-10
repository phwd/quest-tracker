package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.math.BigInteger;

/* renamed from: X.0P  reason: invalid class name */
public final class AnonymousClass0P extends AnonymousClass30 {
    public static final BigInteger A01 = BigInteger.valueOf(2147483647L);
    public static final BigInteger A02 = BigInteger.valueOf(Long.MAX_VALUE);
    public static final BigInteger A03 = BigInteger.valueOf(LibraryDBContract.VERSION_NOT_INSTALLED);
    public static final BigInteger A04 = BigInteger.valueOf(Long.MIN_VALUE);
    public final BigInteger A00;

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final String A06() {
        return this.A00.toString();
    }

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((AnonymousClass0P) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass0P(BigInteger bigInteger) {
        this.A00 = bigInteger;
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final Number A01() {
        return this.A00;
    }
}
