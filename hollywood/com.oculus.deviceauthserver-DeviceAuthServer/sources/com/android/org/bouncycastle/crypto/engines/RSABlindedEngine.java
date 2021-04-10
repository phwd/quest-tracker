package com.android.org.bouncycastle.crypto.engines;

import com.android.org.bouncycastle.crypto.AsymmetricBlockCipher;
import com.android.org.bouncycastle.crypto.CipherParameters;
import com.android.org.bouncycastle.crypto.CryptoServicesRegistrar;
import com.android.org.bouncycastle.crypto.params.ParametersWithRandom;
import com.android.org.bouncycastle.crypto.params.RSAKeyParameters;
import com.android.org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import com.android.org.bouncycastle.util.BigIntegers;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSABlindedEngine implements AsymmetricBlockCipher {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private RSACoreEngine core = new RSACoreEngine();
    private RSAKeyParameters key;
    private SecureRandom random;

    @Override // com.android.org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean forEncryption, CipherParameters param) {
        this.core.init(forEncryption, param);
        if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.key = (RSAKeyParameters) rParam.getParameters();
            this.random = rParam.getRandom();
            return;
        }
        this.key = (RSAKeyParameters) param;
        this.random = CryptoServicesRegistrar.getSecureRandom();
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.core.getInputBlockSize();
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return this.core.getOutputBlockSize();
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] in, int inOff, int inLen) {
        BigInteger result;
        if (this.key != null) {
            BigInteger input = this.core.convertInput(in, inOff, inLen);
            RSAKeyParameters rSAKeyParameters = this.key;
            if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
                RSAPrivateCrtKeyParameters k = (RSAPrivateCrtKeyParameters) rSAKeyParameters;
                BigInteger e = k.getPublicExponent();
                if (e != null) {
                    BigInteger m = k.getModulus();
                    BigInteger bigInteger = ONE;
                    BigInteger r = BigIntegers.createRandomInRange(bigInteger, m.subtract(bigInteger), this.random);
                    result = this.core.processBlock(r.modPow(e, m).multiply(input).mod(m)).multiply(r.modInverse(m)).mod(m);
                    if (!input.equals(result.modPow(e, m))) {
                        throw new IllegalStateException("RSA engine faulty decryption/signing detected");
                    }
                } else {
                    result = this.core.processBlock(input);
                }
            } else {
                result = this.core.processBlock(input);
            }
            return this.core.convertOutput(result);
        }
        throw new IllegalStateException("RSA engine not initialised");
    }
}
