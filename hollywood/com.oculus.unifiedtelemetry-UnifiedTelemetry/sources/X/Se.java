package X;

import java.sql.Timestamp;
import java.util.Date;

public class Se implements AbstractC0132Os {
    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        if (lQVar.rawType != Timestamp.class) {
            return null;
        }
        return new C0146Sf(this, hy.A04(new lQ<>(Date.class)));
    }
}
