package X;

import java.math.BigInteger;

/* renamed from: X.0c6  reason: invalid class name and case insensitive filesystem */
public final class C03090c6 extends AbstractC08820ye {
    public static final Class<?>[] A01 = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    public Object A00;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C03090c6 r7 = (C03090c6) obj;
            Object obj2 = this.A00;
            if (obj2 == null) {
                return r7.A00 == null;
            }
            if (A01(this) && A01(r7)) {
                return A03().longValue() == r7.A03().longValue();
            }
            if (!(obj2 instanceof Number) || !(r7.A00 instanceof Number)) {
                return obj2.equals(r7.A00);
            }
            double doubleValue = A03().doubleValue();
            double doubleValue2 = r7.A03().doubleValue();
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
        C08870ys.A00(z);
        this.A00 = obj;
    }

    public static boolean A01(C03090c6 r2) {
        Object obj = r2.A00;
        if (!(obj instanceof Number)) {
            return false;
        }
        if ((obj instanceof BigInteger) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
            return true;
        }
        return false;
    }

    public final Number A03() {
        Object obj = this.A00;
        if (obj instanceof String) {
            return new C08950z1((String) obj);
        }
        return (Number) obj;
    }

    public final String A04() {
        Object obj = this.A00;
        if (obj instanceof Number) {
            return A03().toString();
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
            doubleToLongBits = A03().longValue();
        } else if (!(obj instanceof Number)) {
            return obj.hashCode();
        } else {
            doubleToLongBits = Double.doubleToLongBits(A03().doubleValue());
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public C03090c6(Boolean bool) {
        A00(bool);
    }

    public C03090c6(Number number) {
        A00(number);
    }

    public C03090c6(String str) {
        A00(str);
    }
}
