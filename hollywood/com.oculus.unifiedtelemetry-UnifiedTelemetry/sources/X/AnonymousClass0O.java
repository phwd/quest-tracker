package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.math.BigDecimal;

/* renamed from: X.0O  reason: invalid class name */
public final class AnonymousClass0O extends AnonymousClass30 {
    public static final AnonymousClass0O A01 = new AnonymousClass0O(BigDecimal.ZERO);
    public static final BigDecimal A02 = BigDecimal.valueOf(2147483647L);
    public static final BigDecimal A03 = BigDecimal.valueOf(Long.MAX_VALUE);
    public static final BigDecimal A04 = BigDecimal.valueOf((long) LibraryDBContract.VERSION_NOT_INSTALLED);
    public static final BigDecimal A05 = BigDecimal.valueOf(Long.MIN_VALUE);
    public final BigDecimal A00;

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass0O) obj).A00.compareTo(this.A00) == 0;
        }
        return true;
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final String A06() {
        return this.A00.toString();
    }

    public final int hashCode() {
        return Double.valueOf(this.A00.doubleValue()).hashCode();
    }

    public AnonymousClass0O(BigDecimal bigDecimal) {
        this.A00 = bigDecimal;
    }

    @Override // X.AbstractC0222Wi, X.AnonymousClass30
    public final Number A01() {
        return this.A00;
    }
}
