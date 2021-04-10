package X;

import java.math.BigInteger;

public final class U1 extends M4 {
    public static final Class<?>[] A01 = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    public Object A00;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            U1 u1 = (U1) obj;
            Object obj2 = this.A00;
            if (obj2 == null) {
                return u1.A00 == null;
            }
            if (A01(this) && A01(u1)) {
                return A04().longValue() == u1.A04().longValue();
            }
            if (!(obj2 instanceof Number) || !(u1.A00 instanceof Number)) {
                return obj2.equals(u1.A00);
            }
            double doubleValue = A04().doubleValue();
            double doubleValue2 = u1.A04().doubleValue();
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
        Rn.A00(z);
        this.A00 = obj;
    }

    public static boolean A01(U1 u1) {
        Object obj = u1.A00;
        if (!(obj instanceof Number)) {
            return false;
        }
        if ((obj instanceof BigInteger) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
            return true;
        }
        return false;
    }

    @Override // X.M4
    public final String A03() {
        Object obj = this.A00;
        if (obj instanceof Number) {
            return A04().toString();
        }
        if (obj instanceof Boolean) {
            return obj.toString();
        }
        return (String) obj;
    }

    public final Number A04() {
        Object obj = this.A00;
        if (obj instanceof String) {
            return new C0190Ut((String) obj);
        }
        return (Number) obj;
    }

    public final int hashCode() {
        long doubleToLongBits;
        Object obj = this.A00;
        if (obj == null) {
            return 31;
        }
        if (A01(this)) {
            doubleToLongBits = A04().longValue();
        } else if (!(obj instanceof Number)) {
            return obj.hashCode();
        } else {
            doubleToLongBits = Double.doubleToLongBits(A04().doubleValue());
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public U1(Boolean bool) {
        A00(bool);
    }

    public U1(Number number) {
        A00(number);
    }

    public U1(String str) {
        A00(str);
    }
}
