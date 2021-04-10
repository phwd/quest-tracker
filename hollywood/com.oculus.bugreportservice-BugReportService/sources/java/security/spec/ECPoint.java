package java.security.spec;

import java.math.BigInteger;

public class ECPoint {
    public static final ECPoint POINT_INFINITY = new ECPoint();
    private final BigInteger x = null;
    private final BigInteger y = null;

    private ECPoint() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this == POINT_INFINITY || !(obj instanceof ECPoint)) {
            return false;
        }
        ECPoint eCPoint = (ECPoint) obj;
        return this.x.equals(eCPoint.x) && this.y.equals(eCPoint.y);
    }

    public int hashCode() {
        if (this == POINT_INFINITY) {
            return 0;
        }
        return this.x.hashCode() << (this.y.hashCode() + 5);
    }
}
