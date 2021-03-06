package com.android.org.bouncycastle.math.ec;

import com.android.org.bouncycastle.math.ec.endo.GLVEndomorphism;
import java.math.BigInteger;

public class GLVMultiplier extends AbstractECMultiplier {
    protected final ECCurve curve;
    protected final GLVEndomorphism glvEndomorphism;

    public GLVMultiplier(ECCurve curve2, GLVEndomorphism glvEndomorphism2) {
        if (curve2 == null || curve2.getOrder() == null) {
            throw new IllegalArgumentException("Need curve with known group order");
        }
        this.curve = curve2;
        this.glvEndomorphism = glvEndomorphism2;
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        if (this.curve.equals(p.getCurve())) {
            BigInteger[] ab = this.glvEndomorphism.decomposeScalar(k.mod(p.getCurve().getOrder()));
            BigInteger a = ab[0];
            BigInteger b = ab[1];
            ECPointMap pointMap = this.glvEndomorphism.getPointMap();
            if (this.glvEndomorphism.hasEfficientPointMap()) {
                return ECAlgorithms.implShamirsTrickWNaf(p, a, pointMap, b);
            }
            return ECAlgorithms.implShamirsTrickWNaf(p, a, pointMap.map(p), b);
        }
        throw new IllegalStateException();
    }
}
