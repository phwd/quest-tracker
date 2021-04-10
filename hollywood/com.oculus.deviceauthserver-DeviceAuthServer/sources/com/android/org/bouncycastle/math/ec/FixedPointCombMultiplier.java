package com.android.org.bouncycastle.math.ec;

import com.android.org.bouncycastle.math.raw.Nat;
import java.math.BigInteger;

public class FixedPointCombMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        ECCurve c = p.getCurve();
        int size = FixedPointUtil.getCombSize(c);
        if (k.bitLength() <= size) {
            FixedPointPreCompInfo info = FixedPointUtil.precompute(p);
            ECLookupTable lookupTable = info.getLookupTable();
            int width = info.getWidth();
            int d = ((size + width) - 1) / width;
            ECPoint R = c.getInfinity();
            int fullComb = d * width;
            int[] K = Nat.fromBigInteger(fullComb, k);
            int top = fullComb - 1;
            for (int i = 0; i < d; i++) {
                int secretIndex = 0;
                for (int j = top - i; j >= 0; j -= d) {
                    int secretBit = K[j >>> 5] >>> (j & 31);
                    secretIndex = ((secretIndex ^ (secretBit >>> 1)) << 1) ^ secretBit;
                }
                R = R.twicePlus(lookupTable.lookup(secretIndex));
            }
            return R.add(info.getOffset());
        }
        throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
    }
}
