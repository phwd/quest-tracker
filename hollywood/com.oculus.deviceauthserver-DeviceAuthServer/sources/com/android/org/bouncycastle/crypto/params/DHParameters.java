package com.android.org.bouncycastle.crypto.params;

import com.android.org.bouncycastle.crypto.CipherParameters;
import java.math.BigInteger;

public class DHParameters implements CipherParameters {
    private static final int DEFAULT_MINIMUM_LENGTH = 160;
    private BigInteger g;
    private BigInteger j;
    private int l;
    private int m;
    private BigInteger p;
    private BigInteger q;
    private DHValidationParameters validation;

    private static int getDefaultMParam(int lParam) {
        if (lParam != 0 && lParam < DEFAULT_MINIMUM_LENGTH) {
            return lParam;
        }
        return DEFAULT_MINIMUM_LENGTH;
    }

    public DHParameters(BigInteger p2, BigInteger g2) {
        this(p2, g2, null, 0);
    }

    public DHParameters(BigInteger p2, BigInteger g2, BigInteger q2) {
        this(p2, g2, q2, 0);
    }

    public DHParameters(BigInteger p2, BigInteger g2, BigInteger q2, int l2) {
        this(p2, g2, q2, getDefaultMParam(l2), l2, null, null);
    }

    public DHParameters(BigInteger p2, BigInteger g2, BigInteger q2, int m2, int l2) {
        this(p2, g2, q2, m2, l2, null, null);
    }

    public DHParameters(BigInteger p2, BigInteger g2, BigInteger q2, BigInteger j2, DHValidationParameters validation2) {
        this(p2, g2, q2, DEFAULT_MINIMUM_LENGTH, 0, j2, validation2);
    }

    public DHParameters(BigInteger p2, BigInteger g2, BigInteger q2, int m2, int l2, BigInteger j2, DHValidationParameters validation2) {
        if (l2 != 0) {
            if (l2 > p2.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            } else if (l2 < m2) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        if (m2 <= p2.bitLength()) {
            this.g = g2;
            this.p = p2;
            this.q = q2;
            this.m = m2;
            this.l = l2;
            this.j = j2;
            this.validation = validation2;
            return;
        }
        throw new IllegalArgumentException("unsafe p value so small specific l required");
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getG() {
        return this.g;
    }

    public BigInteger getQ() {
        return this.q;
    }

    public BigInteger getJ() {
        return this.j;
    }

    public int getM() {
        return this.m;
    }

    public int getL() {
        return this.l;
    }

    public DHValidationParameters getValidationParameters() {
        return this.validation;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHParameters)) {
            return false;
        }
        DHParameters pm = (DHParameters) obj;
        if (getQ() != null) {
            if (!getQ().equals(pm.getQ())) {
                return false;
            }
        } else if (pm.getQ() != null) {
            return false;
        }
        if (!pm.getP().equals(this.p) || !pm.getG().equals(this.g)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) ^ (getQ() != null ? getQ().hashCode() : 0);
    }
}
