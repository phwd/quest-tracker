package X;

import java.math.BigInteger;

/* renamed from: X.6v  reason: invalid class name and case insensitive filesystem */
public final class C00466v extends AnonymousClass0V {
    public static final BigInteger A01 = BigInteger.valueOf(2147483647L);
    public static final BigInteger A02 = BigInteger.valueOf(Long.MAX_VALUE);
    public static final BigInteger A03 = BigInteger.valueOf(-2147483648L);
    public static final BigInteger A04 = BigInteger.valueOf(Long.MIN_VALUE);
    public final BigInteger A00;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((C00466v) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public C00466v(BigInteger bigInteger) {
        this.A00 = bigInteger;
    }
}
