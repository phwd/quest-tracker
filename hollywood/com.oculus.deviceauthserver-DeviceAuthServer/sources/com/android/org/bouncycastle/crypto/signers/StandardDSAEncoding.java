package com.android.org.bouncycastle.crypto.signers;

import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1Encoding;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.DERSequence;
import com.android.org.bouncycastle.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;

public class StandardDSAEncoding implements DSAEncoding {
    public static final StandardDSAEncoding INSTANCE = new StandardDSAEncoding();

    @Override // com.android.org.bouncycastle.crypto.signers.DSAEncoding
    public byte[] encode(BigInteger n, BigInteger r, BigInteger s) throws IOException {
        ASN1EncodableVector v = new ASN1EncodableVector();
        encodeValue(n, v, r);
        encodeValue(n, v, s);
        return new DERSequence(v).getEncoded(ASN1Encoding.DER);
    }

    @Override // com.android.org.bouncycastle.crypto.signers.DSAEncoding
    public BigInteger[] decode(BigInteger n, byte[] encoding) throws IOException {
        ASN1Sequence seq = (ASN1Sequence) ASN1Primitive.fromByteArray(encoding);
        if (seq.size() == 2) {
            BigInteger r = decodeValue(n, seq, 0);
            BigInteger s = decodeValue(n, seq, 1);
            if (Arrays.areEqual(encode(n, r, s), encoding)) {
                return new BigInteger[]{r, s};
            }
        }
        throw new IllegalArgumentException("Malformed signature");
    }

    /* access modifiers changed from: protected */
    public BigInteger checkValue(BigInteger n, BigInteger x) {
        if (x.signum() >= 0 && (n == null || x.compareTo(n) < 0)) {
            return x;
        }
        throw new IllegalArgumentException("Value out of range");
    }

    /* access modifiers changed from: protected */
    public BigInteger decodeValue(BigInteger n, ASN1Sequence s, int pos) {
        return checkValue(n, ((ASN1Integer) s.getObjectAt(pos)).getValue());
    }

    /* access modifiers changed from: protected */
    public void encodeValue(BigInteger n, ASN1EncodableVector v, BigInteger x) {
        v.add(new ASN1Integer(checkValue(n, x)));
    }
}
