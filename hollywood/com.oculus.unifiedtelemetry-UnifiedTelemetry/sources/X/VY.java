package X;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

public final class VY {
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
