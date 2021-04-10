package X;

import java.math.BigInteger;

/* renamed from: X.0XR  reason: invalid class name */
public final class AnonymousClass0XR extends AnonymousClass0AU {
    public static final Class<?>[] A01 = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    public Object A00;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AnonymousClass0XR r7 = (AnonymousClass0XR) obj;
            Object obj2 = this.A00;
            if (obj2 == null) {
                return r7.A00 == null;
            }
            if (A01(this) && A01(r7)) {
                return A02().longValue() == r7.A02().longValue();
            }
            if (!(obj2 instanceof Number) || !(r7.A00 instanceof Number)) {
                return obj2.equals(r7.A00);
            }
            double doubleValue = A02().doubleValue();
            double doubleValue2 = r7.A02().doubleValue();
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
        AnonymousClass0CT.A00(z);
        this.A00 = obj;
    }

    public static boolean A01(AnonymousClass0XR r2) {
        Object obj = r2.A00;
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
            return new AnonymousClass0DB((String) obj);
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
            doubleToLongBits = A02().longValue();
        } else if (!(obj instanceof Number)) {
            return obj.hashCode();
        } else {
            doubleToLongBits = Double.doubleToLongBits(A02().doubleValue());
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public AnonymousClass0XR(Boolean bool) {
        A00(bool);
    }

    public AnonymousClass0XR(Number number) {
        A00(number);
    }

    public AnonymousClass0XR(String str) {
        A00(str);
    }
}
