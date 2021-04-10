package X;

import java.sql.Date;

public class T4 implements AbstractC0132Os {
    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        if (lQVar.rawType == Date.class) {
            return new T3();
        }
        return null;
    }
}
