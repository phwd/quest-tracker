package X;

import java.math.BigInteger;

public final class M1 extends AbstractC0241hm {
    public static final Class<?>[] A01 = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    public Object A00;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            M1 m1 = (M1) obj;
            Object obj2 = this.A00;
            if (obj2 == null) {
                return m1.A00 == null;
            }
            if (A01(this) && A01(m1)) {
                return A02().longValue() == m1.A02().longValue();
            }
            if (!(obj2 instanceof Number) || !(m1.A00 instanceof Number)) {
                return obj2.equals(m1.A00);
            }
            double doubleValue = A02().doubleValue();
            double doubleValue2 = m1.A02().doubleValue();
            if (doubleValue != doubleValue2) {
                return Double.isNaN(doubleValue) && Double.isNaN(doubleValue2);
            }
        }
    }

    private final void A00(Object obj) {
        boolean z;
        if (obj instanceof Character) {
            this.A00 = String.valueOf(((Character) obj).charValue());
            return;
        }
        if (!(obj instanceof Number) && !(obj instanceof String)) {
            Class<?> cls = obj.getClass();
            Class<?>[] clsArr = A01;
            int length = clsArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (clsArr[i].isAssignableFrom(cls)) {
                        break;
                    }
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        C0236ha.A00(z);
        this.A00 = obj;
    }

    public static boolean A01(M1 m1) {
        Object obj = m1.A00;
        if (!(obj instanceof Number)) {
            return false;
        }
        if ((obj instanceof BigInteger) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
            return true;
        }
        return false;
    }

    public final Number A02() {
        Object obj = this.A00;
        if (obj instanceof String) {
            return new hS((String) obj);
        }
        return (Number) obj;
    }

    public final String A03() {
        Object obj = this.A00;
        if (obj instanceof Number) {
            return A02().toString();
        }
        if (obj instanceof Boolean) {
            return obj.toString();
        }
        return (String) obj;
    }

    public final int hashCode() {
        long doubleToLongBits;
        Object obj = this.A00;
        if (obj == null) {
            return 31;
        }
        if (A01(this)) {
            doubleToLongBits = A02().longValue();
        } else if (!(obj instanceof Number)) {
            return obj.hashCode();
        } else {
            doubleToLongBits = Double.doubleToLongBits(A02().doubleValue());
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public M1(Boolean bool) {
        A00(bool);
    }

    public M1(Number number) {
        A00(number);
    }

    public M1(String str) {
        A00(str);
    }
}
