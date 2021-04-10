package com.android.org.bouncycastle.crypto.params;

import com.android.org.bouncycastle.math.ec.ECAlgorithms;
import com.android.org.bouncycastle.math.ec.ECConstants;
import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.util.Arrays;
import java.math.BigInteger;

public class ECDomainParameters implements ECConstants {
    private ECPoint G;
    private ECCurve curve;
    private BigInteger h;
    private BigInteger hInv;
    private BigInteger n;
    private byte[] seed;

    public ECDomainParameters(ECCurve curve2, ECPoint G2, BigInteger n2) {
        this(curve2, G2, n2, ONE, null);
    }

    public ECDomainParameters(ECCurve curve2, ECPoint G2, BigInteger n2, BigInteger h2) {
        this(curve2, G2, n2, h2, null);
    }

    public ECDomainParameters(ECCurve curve2, ECPoint G2, BigInteger n2, BigInteger h2, byte[] seed2) {
        this.hInv = null;
        if (curve2 == null) {
            throw new NullPointerException("curve");
        } else if (n2 != null) {
            this.curve = curve2;
            this.G = validate(curve2, G2);
            this.n = n2;
            this.h = h2;
            this.seed = Arrays.clone(seed2);
        } else {
            throw new NullPointerException("n");
        }
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.G;
    }

    public BigInteger getN() {
        return this.n;
    }

    public BigInteger getH() {
        return this.h;
    }

    public synchronized BigInteger getHInv() {
        if (this.hInv == null) {
            this.hInv = this.h.modInverse(this.n);
        }
        return this.hInv;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ECDomainParameters)) {
            return false;
        }
        ECDomainParameters other = (ECDomainParameters) obj;
        if (!this.curve.equals(other.curve) || !this.G.equals(other.G) || !this.n.equals(other.n) || !this.h.equals(other.h)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((this.curve.hashCode() * 37) ^ this.G.hashCode()) * 37) ^ this.n.hashCode()) * 37) ^ this.h.hashCode();
    }

    static ECPoint validate(ECCurve c, ECPoint q) {
        if (q != null) {
            ECPoint q2 = ECAlgorithms.importPoint(c, q).normalize();
            if (q2.isInfinity()) {
                throw new IllegalArgumentException("Point at infinity");
            } else if (q2.isValid()) {
                return q2;
            } else {
                throw new IllegalArgumentException("Point not on curve");
            }
        } else {
            throw new IllegalArgumentException("Point has null value");
        }
    }
}
