package X;

import androidx.recyclerview.widget.RecyclerView;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.math.BigDecimal;

/* renamed from: X.00t  reason: invalid class name */
public final class AnonymousClass00t extends AnonymousClass04R {
    public static final AnonymousClass00t A01 = new AnonymousClass00t(BigDecimal.ZERO);
    public static final BigDecimal A02 = BigDecimal.valueOf(2147483647L);
    public static final BigDecimal A03 = BigDecimal.valueOf((long) RecyclerView.FOREVER_NS);
    public static final BigDecimal A04 = BigDecimal.valueOf((long) LibraryDBContract.VERSION_NOT_INSTALLED);
    public static final BigDecimal A05 = BigDecimal.valueOf(Long.MIN_VALUE);
    public final BigDecimal A00;

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((AnonymousClass00t) obj).A00.compareTo(this.A00) == 0;
        }
        return true;
    }

    @Override // X.AnonymousClass04R, X.AbstractC02170iC
    public final String A02() {
        return this.A00.toString();
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C03620oC {
        if (!r4._config.A06(AnonymousClass0i4.WRITE_BIGDECIMAL_AS_PLAIN) || (r3 instanceof AnonymousClass0OD)) {
            r3.A0V(this.A00);
        } else {
            r3.A0S(this.A00.toPlainString());
        }
    }

    public final int hashCode() {
        return Double.valueOf(this.A00.doubleValue()).hashCode();
    }

    public AnonymousClass00t(BigDecimal bigDecimal) {
        this.A00 = bigDecimal;
    }
}
