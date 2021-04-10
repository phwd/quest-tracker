package com.android.org.bouncycastle.crypto.signers;

import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.BigIntegers;
import java.math.BigInteger;

public class PlainDSAEncoding implements DSAEncoding {
    public static final PlainDSAEncoding INSTANCE = new PlainDSAEncoding();

    @Override // com.android.org.bouncycastle.crypto.signers.DSAEncoding
    public byte[] encode(BigInteger n, BigInteger r, BigInteger s) {
        int valueLength = BigIntegers.getUnsignedByteLength(n);
        byte[] result = new byte[(valueLength * 2)];
        encodeValue(n, r, result, 0, valueLength);
        encodeValue(n, s, result, valueLength, valueLength);
        return result;
    }

    @Override // com.android.org.bouncycastle.crypto.signers.DSAEncoding
    public BigInteger[] decode(BigInteger n, byte[] encoding) {
        int valueLength = BigIntegers.getUnsignedByteLength(n);
        if (encoding.length == valueLength * 2) {
            return new BigInteger[]{decodeValue(n, encoding, 0, valueLength), decodeValue(n, encoding, valueLength, valueLength)};
        }
        throw new IllegalArgumentException("Encoding has incorrect length");
    }

    /* access modifiers changed from: protected */
    public BigInteger checkValue(BigInteger n, BigInteger x) {
        if (x.signum() >= 0 && x.compareTo(n) < 0) {
            return x;
        }
        throw new IllegalArgumentException("Value out of range");
    }

    /* access modifiers changed from: protected */
    public BigInteger decodeValue(BigInteger n, byte[] buf, int off, int len) {
        return checkValue(n, new BigInteger(1, Arrays.copyOfRange(buf, off, off + len)));
    }

    private void encodeValue(BigInteger n, BigInteger x, byte[] buf, int off, int len) {
        byte[] bs = checkValue(n, x).toByteArray();
        int bsOff = Math.max(0, bs.length - len);
        int bsLen = bs.length - bsOff;
        int pos = len - bsLen;
        Arrays.fill(buf, off, off + pos, (byte) 0);
        System.arraycopy(bs, bsOff, buf, off + pos, bsLen);
    }
}
