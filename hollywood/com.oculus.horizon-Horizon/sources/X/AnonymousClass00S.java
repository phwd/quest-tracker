package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.math.BigDecimal;

/* renamed from: X.00S  reason: invalid class name */
public final class AnonymousClass00S extends AnonymousClass038 {
    public static final AnonymousClass00S A01 = new AnonymousClass00S(BigDecimal.ZERO);
    public static final BigDecimal A02 = BigDecimal.valueOf(2147483647L);
    public static final BigDecimal A03 = BigDecimal.valueOf(Long.MAX_VALUE);
    public static final BigDecimal A04 = BigDecimal.valueOf((long) LibraryDBContract.VERSION_NOT_INSTALLED);
    public static final BigDecimal A05 = BigDecimal.valueOf(Long.MIN_VALUE);
    public final BigDecimal A00;

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass00S) obj).A00.compareTo(this.A00) == 0;
        }
        return true;
    }

    @Override // X.AbstractC03980gY, X.AnonymousClass038
    public final String A02() {
        return this.A00.toString();
    }

    public final int hashCode() {
        return Double.valueOf(this.A00.doubleValue()).hashCode();
    }

    public AnonymousClass00S(BigDecimal bigDecimal) {
        this.A00 = bigDecimal;
    }
}
