package com.android.org.bouncycastle.math.ec;

import java.math.BigInteger;

public abstract class AbstractECMultiplier implements ECMultiplier {
    /* access modifiers changed from: protected */
    public abstract ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger);

    @Override // com.android.org.bouncycastle.math.ec.ECMultiplier
    public ECPoint multiply(ECPoint p, BigInteger k) {
        int sign = k.signum();
        if (sign == 0 || p.isInfinity()) {
            return p.getCurve().getInfinity();
        }
        ECPoint positive = multiplyPositive(p, k.abs());
        return checkResult(sign > 0 ? positive : positive.negate());
    }

    /* access modifiers changed from: protected */
    public ECPoint checkResult(ECPoint p) {
        return ECAlgorithms.implCheckResult(p);
    }
}
