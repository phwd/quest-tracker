package java.security.spec;

import java.math.BigInteger;

public class RSAKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final BigInteger F0 = BigInteger.valueOf(3);
    public static final BigInteger F4 = BigInteger.valueOf(65537);
    private int keysize;
    private BigInteger publicExponent;

    public RSAKeyGenParameterSpec(int keysize2, BigInteger publicExponent2) {
        this.keysize = keysize2;
        this.publicExponent = publicExponent2;
    }

    public int getKeysize() {
        return this.keysize;
    }

    public BigInteger getPublicExponent() {
        return this.publicExponent;
    }
}
