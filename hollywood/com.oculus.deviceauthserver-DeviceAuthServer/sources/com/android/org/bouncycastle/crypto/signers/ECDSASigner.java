package com.android.org.bouncycastle.crypto.signers;

import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.CryptoServicesRegistrar;
import com.android.org.bouncycastle.crypto.DSAExt;
import com.android.org.bouncycastle.crypto.params.ECDomainParameters;
import com.android.org.bouncycastle.crypto.params.ECKeyParameters;
import com.android.org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import com.android.org.bouncycastle.crypto.params.ECPublicKeyParameters;
import com.android.org.bouncycastle.crypto.params.ParametersWithRandom;
import com.android.org.bouncycastle.math.ec.ECAlgorithms;
import com.android.org.bouncycastle.math.ec.ECConstants;
import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.math.ec.ECFieldElement;
import com.android.org.bouncycastle.math.ec.ECMultiplier;
import com.android.org.bouncycastle.math.ec.ECPoint;
import com.android.org.bouncycastle.math.ec.FixedPointCombMultiplier;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ECDSASigner implements ECConstants, DSAExt {
    private final DSAKCalculator kCalculator;
    private ECKeyParameters key;
    private SecureRandom random;

    public ECDSASigner() {
        this.kCalculator = new RandomDSAKCalculator();
    }

    public ECDSASigner(DSAKCalculator kCalculator2) {
        this.kCalculator = kCalculator2;
    }

    @Override // com.android.org.bouncycastle.crypto.DSA
    public void init(boolean forSigning, CipherParameters param) {
        SecureRandom providedRandom = null;
        if (!forSigning) {
            this.key = (ECPublicKeyParameters) param;
        } else if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.key = (ECPrivateKeyParameters) rParam.getParameters();
            providedRandom = rParam.getRandom();
        } else {
            this.key = (ECPrivateKeyParameters) param;
        }
        this.random = initSecureRandom(forSigning && !this.kCalculator.isDeterministic(), providedRandom);
    }

    @Override // com.android.org.bouncycastle.crypto.DSAExt
    public BigInteger getOrder() {
        return this.key.getParameters().getN();
    }

    /* JADX INFO: Multiple debug info for r6v3 java.math.BigInteger: [D('r' java.math.BigInteger), D('p' com.android.org.bouncycastle.math.ec.ECPoint)] */
    /* JADX INFO: Multiple debug info for r5v2 java.math.BigInteger: [D('k' java.math.BigInteger), D('s' java.math.BigInteger)] */
    @Override // com.android.org.bouncycastle.crypto.DSA
    public BigInteger[] generateSignature(byte[] message) {
        ECDomainParameters ec = this.key.getParameters();
        BigInteger n = ec.getN();
        BigInteger e = calculateE(n, message);
        BigInteger d = ((ECPrivateKeyParameters) this.key).getD();
        if (this.kCalculator.isDeterministic()) {
            this.kCalculator.init(n, d, message);
        } else {
            this.kCalculator.init(n, this.random);
        }
        ECMultiplier basePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger k = this.kCalculator.nextK();
            BigInteger r = basePointMultiplier.multiply(ec.getG(), k).normalize().getAffineXCoord().toBigInteger().mod(n);
            if (!r.equals(ZERO)) {
                BigInteger s = k.modInverse(n).multiply(e.add(d.multiply(r))).mod(n);
                if (!s.equals(ZERO)) {
                    return new BigInteger[]{r, s};
                }
            }
        }
    }

    @Override // com.android.org.bouncycastle.crypto.DSA
    public boolean verifySignature(byte[] message, BigInteger r, BigInteger s) {
        BigInteger cofactor;
        ECFieldElement D;
        BigInteger r2 = r;
        ECDomainParameters ec = this.key.getParameters();
        BigInteger n = ec.getN();
        BigInteger e = calculateE(n, message);
        if (r2.compareTo(ONE) < 0) {
            return false;
        }
        if (r2.compareTo(n) >= 0) {
            return false;
        }
        if (s.compareTo(ONE) < 0 || s.compareTo(n) >= 0) {
            return false;
        }
        BigInteger c = s.modInverse(n);
        ECPoint point = ECAlgorithms.sumOfTwoMultiplies(ec.getG(), e.multiply(c).mod(n), ((ECPublicKeyParameters) this.key).getQ(), r2.multiply(c).mod(n));
        if (point.isInfinity()) {
            return false;
        }
        ECCurve curve = point.getCurve();
        if (curve == null || (cofactor = curve.getCofactor()) == null || cofactor.compareTo(EIGHT) > 0 || (D = getDenominator(curve.getCoordinateSystem(), point)) == null || D.isZero()) {
            return point.normalize().getAffineXCoord().toBigInteger().mod(n).equals(r2);
        }
        ECFieldElement X = point.getXCoord();
        while (curve.isValidFieldElement(r2)) {
            if (curve.fromBigInteger(r2).multiply(D).equals(X)) {
                return true;
            }
            r2 = r2.add(n);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public BigInteger calculateE(BigInteger n, byte[] message) {
        int log2n = n.bitLength();
        int messageBitLength = message.length * 8;
        BigInteger e = new BigInteger(1, message);
        if (log2n < messageBitLength) {
            return e.shiftRight(messageBitLength - log2n);
        }
        return e;
    }

    /* access modifiers changed from: protected */
    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    /* access modifiers changed from: protected */
    public ECFieldElement getDenominator(int coordinateSystem, ECPoint p) {
        if (coordinateSystem != 1) {
            if (coordinateSystem == 2 || coordinateSystem == 3 || coordinateSystem == 4) {
                return p.getZCoord(0).square();
            }
            if (!(coordinateSystem == 6 || coordinateSystem == 7)) {
                return null;
            }
        }
        return p.getZCoord(0);
    }

    /* access modifiers changed from: protected */
    public SecureRandom initSecureRandom(boolean needed, SecureRandom provided) {
        if (!needed) {
            return null;
        }
        return provided != null ? provided : CryptoServicesRegistrar.getSecureRandom();
    }
}
