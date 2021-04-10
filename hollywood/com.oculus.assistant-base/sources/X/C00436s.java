package X;

import java.math.BigDecimal;

/* renamed from: X.6s  reason: invalid class name and case insensitive filesystem */
public final class C00436s extends AnonymousClass0V {
    public static final C00436s A01 = new C00436s(BigDecimal.ZERO);
    public static final BigDecimal A02 = BigDecimal.valueOf(2147483647L);
    public static final BigDecimal A03 = BigDecimal.valueOf(Long.MAX_VALUE);
    public static final BigDecimal A04 = BigDecimal.valueOf(-2147483648L);
    public static final BigDecimal A05 = BigDecimal.valueOf(Long.MIN_VALUE);
    public final BigDecimal A00;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((C00436s) obj).A00.compareTo(this.A00) == 0;
        }
        return true;
    }

    public final int hashCode() {
        return Double.valueOf(this.A00.doubleValue()).hashCode();
    }

    public C00436s(BigDecimal bigDecimal) {
        this.A00 = bigDecimal;
    }
}
