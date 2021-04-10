package X;

import java.sql.Time;

public class T2 implements AbstractC0132Os {
    @Override // X.AbstractC0132Os
    public final <T> AbstractC0131Ob<T> A1e(HY hy, lQ<T> lQVar) {
        if (lQVar.rawType == Time.class) {
            return new T1();
        }
        return null;
    }
}
