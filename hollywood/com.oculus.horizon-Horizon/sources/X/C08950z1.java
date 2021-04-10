package X;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

/* renamed from: X.0z1  reason: invalid class name and case insensitive filesystem */
public final class C08950z1 extends Number {
    public final String value;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C08950z1)) {
            return false;
        }
        String str = this.value;
        String str2 = ((C08950z1) obj).value;
        return str == str2 || str.equals(str2);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new BigDecimal(this.value);
    }

    public final double doubleValue() {
        return Double.parseDouble(this.value);
    }

    public final float floatValue() {
        return Float.parseFloat(this.value);
    }

    public final int hashCode() {
        return this.value.hashCode();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
        return (int) java.lang.Long.parseLong(r3.value);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        return new java.math.BigDecimal(r3.value).intValue();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int intValue() {
        /*
            r3 = this;
            java.lang.String r0 = r3.value     // Catch:{ NumberFormatException -> 0x0007 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0007 }
            return r0
        L_0x0007:
            java.lang.String r0 = r3.value     // Catch:{ NumberFormatException -> 0x000f }
            long r1 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x000f }
            int r0 = (int) r1     // Catch:{ NumberFormatException -> 0x000f }
            return r0
        L_0x000f:
            java.lang.String r1 = r3.value
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r0.<init>(r1)
            int r0 = r0.intValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08950z1.intValue():int");
    }

    public final long longValue() {
        try {
            return Long.parseLong(this.value);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.value).longValue();
        }
    }

    public C08950z1(String str) {
        this.value = str;
    }

    public final String toString() {
        return this.value;
    }
}
