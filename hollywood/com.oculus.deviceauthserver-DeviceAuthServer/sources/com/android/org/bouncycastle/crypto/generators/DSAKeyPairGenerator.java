package com.android.org.bouncycastle.crypto.generators;

import com.android.org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import com.android.org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import com.android.org.bouncycastle.crypto.KeyGenerationParameters;
import com.android.org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import com.android.org.bouncycastle.crypto.params.DSAKeyGenerationParameters;
import com.android.org.bouncycastle.crypto.params.DSAParameters;
import com.android.org.bouncycastle.crypto.params.DSAPrivateKeyParameters;
import com.android.org.bouncycastle.crypto.params.DSAPublicKeyParameters;
import com.android.org.bouncycastle.math.ec.WNafUtil;
import com.android.org.bouncycastle.util.BigIntegers;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private DSAKeyGenerationParameters param;

    @Override // com.android.org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters param2) {
        this.param = (DSAKeyGenerationParameters) param2;
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        DSAParameters dsaParams = this.param.getParameters();
        BigInteger x = generatePrivateKey(dsaParams.getQ(), this.param.getRandom());
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new DSAPublicKeyParameters(calculatePublicKey(dsaParams.getP(), dsaParams.getG(), x), dsaParams), (AsymmetricKeyParameter) new DSAPrivateKeyParameters(x, dsaParams));
    }

    private static BigInteger generatePrivateKey(BigInteger q, SecureRandom random) {
        BigInteger x;
        int minWeight = q.bitLength() >>> 2;
        do {
            BigInteger bigInteger = ONE;
            x = BigIntegers.createRandomInRange(bigInteger, q.subtract(bigInteger), random);
        } while (WNafUtil.getNafWeight(x) < minWeight);
        return x;
    }

    private static BigInteger calculatePublicKey(BigInteger p, BigInteger g, BigInteger x) {
        return g.modPow(x, p);
    }
}
