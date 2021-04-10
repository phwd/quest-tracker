package com.android.org.bouncycastle.crypto.generators;

import com.android.org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import com.android.org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import com.android.org.bouncycastle.crypto.KeyGenerationParameters;
import com.android.org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import com.android.org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import com.android.org.bouncycastle.crypto.params.RSAKeyParameters;
import com.android.org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import com.android.org.bouncycastle.math.Primes;
import com.android.org.bouncycastle.math.ec.WNafUtil;
import com.android.org.bouncycastle.util.BigIntegers;
import java.math.BigInteger;

public class RSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private RSAKeyGenerationParameters param;

    @Override // com.android.org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters param2) {
        this.param = (RSAKeyGenerationParameters) param2;
    }

    @Override // com.android.org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger q;
        BigInteger n;
        BigInteger q2;
        BigInteger gcd;
        RSAKeyPairGenerator rSAKeyPairGenerator = this;
        AsymmetricCipherKeyPair result = null;
        boolean done = false;
        int strength = rSAKeyPairGenerator.param.getStrength();
        int pbitlength = (strength + 1) / 2;
        int qbitlength = strength - pbitlength;
        int mindiffbits = (strength / 2) - 100;
        if (mindiffbits < strength / 3) {
            mindiffbits = strength / 3;
        }
        int minWeight = strength >> 2;
        BigInteger dLowerBound = BigInteger.valueOf(2).pow(strength / 2);
        BigInteger squaredBound = ONE.shiftLeft(strength - 1);
        BigInteger minDiff = ONE.shiftLeft(mindiffbits);
        while (!done) {
            BigInteger e = rSAKeyPairGenerator.param.getPublicExponent();
            BigInteger p = rSAKeyPairGenerator.chooseRandomPrime(pbitlength, e, squaredBound);
            while (true) {
                q = rSAKeyPairGenerator.chooseRandomPrime(qbitlength, e, squaredBound);
                BigInteger diff = q.subtract(p).abs();
                if (diff.bitLength() >= mindiffbits && diff.compareTo(minDiff) > 0) {
                    n = p.multiply(q);
                    if (n.bitLength() == strength) {
                        if (WNafUtil.getNafWeight(n) >= minWeight) {
                            break;
                        }
                        p = rSAKeyPairGenerator.chooseRandomPrime(pbitlength, e, squaredBound);
                        done = done;
                    } else {
                        p = p.max(q);
                        done = done;
                    }
                } else {
                    rSAKeyPairGenerator = this;
                    done = done;
                    strength = strength;
                    pbitlength = pbitlength;
                    qbitlength = qbitlength;
                }
            }
            if (p.compareTo(q) < 0) {
                gcd = q;
                q2 = p;
            } else {
                gcd = p;
                q2 = q;
            }
            BigInteger pSub1 = gcd.subtract(ONE);
            BigInteger qSub1 = q2.subtract(ONE);
            BigInteger d = e.modInverse(pSub1.divide(pSub1.gcd(qSub1)).multiply(qSub1));
            if (d.compareTo(dLowerBound) <= 0) {
                rSAKeyPairGenerator = this;
                done = done;
                strength = strength;
            } else {
                result = new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RSAKeyParameters(false, n, e), (AsymmetricKeyParameter) new RSAPrivateCrtKeyParameters(n, e, d, gcd, q2, d.remainder(pSub1), d.remainder(qSub1), q2.modInverse(gcd)));
                rSAKeyPairGenerator = this;
                strength = strength;
                done = true;
                pbitlength = pbitlength;
                qbitlength = qbitlength;
            }
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public BigInteger chooseRandomPrime(int bitlength, BigInteger e, BigInteger sqrdBound) {
        for (int i = 0; i != bitlength * 5; i++) {
            BigInteger p = BigIntegers.createRandomPrime(bitlength, 1, this.param.getRandom());
            if (!p.mod(e).equals(ONE) && p.multiply(p).compareTo(sqrdBound) >= 0 && isProbablePrime(p) && e.gcd(p.subtract(ONE)).equals(ONE)) {
                return p;
            }
        }
        throw new IllegalStateException("unable to generate prime number for RSA key");
    }

    /* access modifiers changed from: protected */
    public boolean isProbablePrime(BigInteger x) {
        return !Primes.hasAnySmallFactors(x) && Primes.isMRProbablePrime(x, this.param.getRandom(), getNumberOfIterations(x.bitLength(), this.param.getCertainty()));
    }

    private static int getNumberOfIterations(int bits, int certainty) {
        if (bits >= 1536) {
            if (certainty <= 100) {
                return 3;
            }
            if (certainty <= 128) {
                return 4;
            }
            return 4 + (((certainty - 128) + 1) / 2);
        } else if (bits >= 1024) {
            if (certainty <= 100) {
                return 4;
            }
            if (certainty <= 112) {
                return 5;
            }
            return (((certainty - 112) + 1) / 2) + 5;
        } else if (bits >= 512) {
            if (certainty <= 80) {
                return 5;
            }
            if (certainty <= 100) {
                return 7;
            }
            return (((certainty - 100) + 1) / 2) + 7;
        } else if (certainty <= 80) {
            return 40;
        } else {
            return 40 + (((certainty - 80) + 1) / 2);
        }
    }
}
