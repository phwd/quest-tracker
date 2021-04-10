package com.android.org.bouncycastle.crypto.generators;

import com.android.org.bouncycastle.crypto.Digest;
import com.android.org.bouncycastle.crypto.digests.AndroidDigestFactory;
import com.android.org.bouncycastle.crypto.params.DSAParameterGenerationParameters;
import com.android.org.bouncycastle.crypto.params.DSAParameters;
import com.android.org.bouncycastle.crypto.params.DSAValidationParameters;
import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.BigIntegers;
import com.android.org.bouncycastle.util.encoders.Hex;
import com.google.common.primitives.UnsignedBytes;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DSAParametersGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private int L;
    private int N;
    private int certainty;
    private Digest digest;
    private int iterations;
    private SecureRandom random;
    private int usageIndex;
    private boolean use186_3;

    public DSAParametersGenerator() {
        this(AndroidDigestFactory.getSHA1());
    }

    public DSAParametersGenerator(Digest digest2) {
        this.digest = digest2;
    }

    public void init(int size, int certainty2, SecureRandom random2) {
        this.L = size;
        this.N = getDefaultN(size);
        this.certainty = certainty2;
        this.iterations = Math.max(getMinimumIterations(this.L), (certainty2 + 1) / 2);
        this.random = random2;
        this.use186_3 = false;
        this.usageIndex = -1;
    }

    public void init(DSAParameterGenerationParameters params) {
        int L2 = params.getL();
        int N2 = params.getN();
        if (L2 < 1024 || L2 > 3072 || L2 % 1024 != 0) {
            throw new IllegalArgumentException("L values must be between 1024 and 3072 and a multiple of 1024");
        } else if (L2 == 1024 && N2 != 160) {
            throw new IllegalArgumentException("N must be 160 for L = 1024");
        } else if (L2 == 2048 && N2 != 224 && N2 != 256) {
            throw new IllegalArgumentException("N must be 224 or 256 for L = 2048");
        } else if (L2 == 3072 && N2 != 256) {
            throw new IllegalArgumentException("N must be 256 for L = 3072");
        } else if (this.digest.getDigestSize() * 8 >= N2) {
            this.L = L2;
            this.N = N2;
            this.certainty = params.getCertainty();
            this.iterations = Math.max(getMinimumIterations(L2), (this.certainty + 1) / 2);
            this.random = params.getRandom();
            this.use186_3 = true;
            this.usageIndex = params.getUsageIndex();
        } else {
            throw new IllegalStateException("Digest output size too small for value of N");
        }
    }

    public DSAParameters generateParameters() {
        if (this.use186_3) {
            return generateParameters_FIPS186_3();
        }
        return generateParameters_FIPS186_2();
    }

    private DSAParameters generateParameters_FIPS186_2() {
        byte[] seed = new byte[20];
        byte[] part1 = new byte[20];
        byte[] part2 = new byte[20];
        byte[] u = new byte[20];
        int i = this.L;
        int n = (i - 1) / 160;
        byte[] w = new byte[(i / 8)];
        if (this.digest.getAlgorithmName().equals("SHA-1")) {
            while (true) {
                this.random.nextBytes(seed);
                int i2 = 0;
                hash(this.digest, seed, part1, 0);
                System.arraycopy(seed, 0, part2, 0, seed.length);
                inc(part2);
                hash(this.digest, part2, part2, 0);
                for (int i3 = 0; i3 != u.length; i3++) {
                    u[i3] = (byte) (part1[i3] ^ part2[i3]);
                }
                u[0] = (byte) (u[0] | UnsignedBytes.MAX_POWER_OF_TWO);
                u[19] = (byte) (u[19] | 1);
                BigInteger q = new BigInteger(1, u);
                if (isProbablePrime(q)) {
                    byte[] offset = Arrays.clone(seed);
                    inc(offset);
                    int counter = 0;
                    while (counter < 4096) {
                        for (int k = 1; k <= n; k++) {
                            inc(offset);
                            hash(this.digest, offset, w, w.length - (part1.length * k));
                        }
                        int remaining = w.length - (part1.length * n);
                        inc(offset);
                        hash(this.digest, offset, part1, i2);
                        System.arraycopy(part1, part1.length - remaining, w, i2, remaining);
                        w[i2] = (byte) (w[i2] | UnsignedBytes.MAX_POWER_OF_TWO);
                        BigInteger x = new BigInteger(1, w);
                        BigInteger p = x.subtract(x.mod(q.shiftLeft(1)).subtract(ONE));
                        if (p.bitLength() == this.L && isProbablePrime(p)) {
                            return new DSAParameters(p, q, calculateGenerator_FIPS186_2(p, q, this.random), new DSAValidationParameters(seed, counter));
                        }
                        counter++;
                        i2 = 0;
                    }
                    continue;
                }
            }
        } else {
            throw new IllegalStateException("can only use SHA-1 for generating FIPS 186-2 parameters");
        }
    }

    private static BigInteger calculateGenerator_FIPS186_2(BigInteger p, BigInteger q, SecureRandom r) {
        BigInteger g;
        BigInteger e = p.subtract(ONE).divide(q);
        BigInteger pSub2 = p.subtract(TWO);
        do {
            g = BigIntegers.createRandomInRange(TWO, pSub2, r).modPow(e, p);
        } while (g.bitLength() <= 1);
        return g;
    }

    private DSAParameters generateParameters_FIPS186_3() {
        BigInteger q;
        int counter;
        BigInteger p;
        Digest d = this.digest;
        int outlen = d.getDigestSize() * 8;
        int seedlen = this.N;
        byte[] seed = new byte[(seedlen / 8)];
        int i = this.L;
        int n = (i - 1) / outlen;
        int i2 = (i - 1) % outlen;
        byte[] w = new byte[(i / 8)];
        byte[] output = new byte[d.getDigestSize()];
        loop0:
        while (true) {
            this.random.nextBytes(seed);
            hash(d, seed, output, 0);
            q = new BigInteger(1, output).mod(ONE.shiftLeft(this.N - 1)).setBit(0).setBit(this.N - 1);
            if (isProbablePrime(q)) {
                byte[] offset = Arrays.clone(seed);
                int counterLimit = this.L * 4;
                counter = 0;
                while (counter < counterLimit) {
                    int j = 1;
                    while (j <= n) {
                        inc(offset);
                        hash(d, offset, w, w.length - (output.length * j));
                        j++;
                        outlen = outlen;
                    }
                    int remaining = w.length - (output.length * n);
                    inc(offset);
                    hash(d, offset, output, 0);
                    System.arraycopy(output, output.length - remaining, w, 0, remaining);
                    w[0] = (byte) (w[0] | UnsignedBytes.MAX_POWER_OF_TWO);
                    BigInteger X = new BigInteger(1, w);
                    p = X.subtract(X.mod(q.shiftLeft(1)).subtract(ONE));
                    if (p.bitLength() == this.L && isProbablePrime(p)) {
                        break loop0;
                    }
                    counter++;
                    outlen = outlen;
                    seedlen = seedlen;
                    d = d;
                    w = w;
                }
            }
        }
        int i3 = this.usageIndex;
        if (i3 >= 0) {
            BigInteger g = calculateGenerator_FIPS186_3_Verifiable(d, p, q, seed, i3);
            if (g != null) {
                return new DSAParameters(p, q, g, new DSAValidationParameters(seed, counter, this.usageIndex));
            }
        }
        return new DSAParameters(p, q, calculateGenerator_FIPS186_3_Unverifiable(p, q, this.random), new DSAValidationParameters(seed, counter));
    }

    private boolean isProbablePrime(BigInteger x) {
        return x.isProbablePrime(this.certainty);
    }

    private static BigInteger calculateGenerator_FIPS186_3_Unverifiable(BigInteger p, BigInteger q, SecureRandom r) {
        return calculateGenerator_FIPS186_2(p, q, r);
    }

    private static BigInteger calculateGenerator_FIPS186_3_Verifiable(Digest d, BigInteger p, BigInteger q, byte[] seed, int index) {
        BigInteger e = p.subtract(ONE).divide(q);
        byte[] ggen = Hex.decode("6767656E");
        byte[] U = new byte[(seed.length + ggen.length + 1 + 2)];
        System.arraycopy(seed, 0, U, 0, seed.length);
        System.arraycopy(ggen, 0, U, seed.length, ggen.length);
        U[U.length - 3] = (byte) index;
        byte[] w = new byte[d.getDigestSize()];
        for (int count = 1; count < 65536; count++) {
            inc(U);
            hash(d, U, w, 0);
            BigInteger g = new BigInteger(1, w).modPow(e, p);
            if (g.compareTo(TWO) >= 0) {
                return g;
            }
        }
        return null;
    }

    private static void hash(Digest d, byte[] input, byte[] output, int outputPos) {
        d.update(input, 0, input.length);
        d.doFinal(output, outputPos);
    }

    private static int getDefaultN(int L2) {
        return L2 > 1024 ? 256 : 160;
    }

    private static int getMinimumIterations(int L2) {
        if (L2 <= 1024) {
            return 40;
        }
        return (((L2 - 1) / 1024) * 8) + 48;
    }

    private static void inc(byte[] buf) {
        for (int i = buf.length - 1; i >= 0; i--) {
            byte b = (byte) ((buf[i] + 1) & 255);
            buf[i] = b;
            if (b != 0) {
                return;
            }
        }
    }
}
