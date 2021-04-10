package X;

import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.math.BigDecimal;

/* renamed from: X.05u  reason: invalid class name and case insensitive filesystem */
public final class C006005u extends AnonymousClass07j {
    public static final C006005u A01 = new C006005u(BigDecimal.ZERO);
    public static final BigDecimal A02 = BigDecimal.valueOf(2147483647L);
    public static final BigDecimal A03 = BigDecimal.valueOf(Long.MAX_VALUE);
    public static final BigDecimal A04 = BigDecimal.valueOf((long) LibraryDBContract.VERSION_NOT_INSTALLED);
    public static final BigDecimal A05 = BigDecimal.valueOf(Long.MIN_VALUE);
    public final BigDecimal A00;

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((C006005u) obj).A00.compareTo(this.A00) == 0;
        }
        return true;
    }

    @Override // X.AnonymousClass07j, X.AnonymousClass0aF
    public final String A02() {
        return this.A00.toString();
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C05910ld {
        if (!r4._config.A06(AnonymousClass0a9.WRITE_BIGDECIMAL_AS_PLAIN) || (r3 instanceof C01570Jk)) {
            r3.A0T(this.A00);
        } else {
            r3.A0Q(this.A00.toPlainString());
        }
    }

    public final int hashCode() {
        return Double.valueOf(this.A00.doubleValue()).hashCode();
    }

    public C006005u(BigDecimal bigDecimal) {
        this.A00 = bigDecimal;
    }
}
