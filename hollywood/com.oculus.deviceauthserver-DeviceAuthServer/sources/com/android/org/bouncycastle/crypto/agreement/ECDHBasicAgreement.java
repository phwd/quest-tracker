package com.android.org.bouncycastle.crypto.agreement;

import com.android.org.bouncycastle.crypto.BasicAgreement;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.params.ECDomainParameters;
import com.android.org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import com.android.org.bouncycastle.crypto.params.ECPublicKeyParameters;
import com.android.org.bouncycastle.math.ec.ECAlgorithms;
import com.android.org.bouncycastle.math.ec.ECConstants;
import com.android.org.bouncycastle.math.ec.ECPoint;
import java.math.BigInteger;

public class ECDHBasicAgreement implements BasicAgreement {
    private ECPrivateKeyParameters key;

    @Override // com.android.org.bouncycastle.crypto.BasicAgreement
    public void init(CipherParameters key2) {
        this.key = (ECPrivateKeyParameters) key2;
    }

    @Override // com.android.org.bouncycastle.crypto.BasicAgreement
    public int getFieldSize() {
        return (this.key.getParameters().getCurve().getFieldSize() + 7) / 8;
    }

    @Override // com.android.org.bouncycastle.crypto.BasicAgreement
    public BigInteger calculateAgreement(CipherParameters pubKey) {
        ECPublicKeyParameters pub = (ECPublicKeyParameters) pubKey;
        ECDomainParameters params = this.key.getParameters();
        if (params.equals(pub.getParameters())) {
            BigInteger d = this.key.getD();
            ECPoint Q = ECAlgorithms.cleanPoint(params.getCurve(), pub.getQ());
            if (!Q.isInfinity()) {
                BigInteger h = params.getH();
                if (!h.equals(ECConstants.ONE)) {
                    d = params.getHInv().multiply(d).mod(params.getN());
                    Q = ECAlgorithms.referenceMultiply(Q, h);
                }
                ECPoint P = Q.multiply(d).normalize();
                if (!P.isInfinity()) {
                    return P.getAffineXCoord().toBigInteger();
                }
                throw new IllegalStateException("Infinity is not a valid agreement value for ECDH");
            }
            throw new IllegalStateException("Infinity is not a valid public key for ECDH");
        }
        throw new IllegalStateException("ECDH public key has wrong domain parameters");
    }
}
