package X;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

/* renamed from: X.0nY  reason: invalid class name and case insensitive filesystem */
public final class C06620nY {
    public static final HashSet<String> A00 = new HashSet<>();

    static {
        int i = 0;
        Class[] clsArr = {Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};
        do {
            A00.add(clsArr[i].getName());
            i++;
        } while (i < 11);
    }
}
