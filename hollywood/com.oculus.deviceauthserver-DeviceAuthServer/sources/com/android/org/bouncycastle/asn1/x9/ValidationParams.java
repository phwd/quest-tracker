package com.android.org.bouncycastle.asn1.x9;

import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.ASN1TaggedObject;
import com.android.org.bouncycastle.asn1.DERBitString;
import com.android.org.bouncycastle.asn1.DERSequence;
import java.math.BigInteger;

public class ValidationParams extends ASN1Object {
    private ASN1Integer pgenCounter;
    private DERBitString seed;

    public static ValidationParams getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static ValidationParams getInstance(Object obj) {
        if (obj instanceof ValidationParams) {
            return (ValidationParams) obj;
        }
        if (obj != null) {
            return new ValidationParams(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ValidationParams(byte[] seed2, int pgenCounter2) {
        if (seed2 != null) {
            this.seed = new DERBitString(seed2);
            this.pgenCounter = new ASN1Integer((long) pgenCounter2);
            return;
        }
        throw new IllegalArgumentException("'seed' cannot be null");
    }

    public ValidationParams(DERBitString seed2, ASN1Integer pgenCounter2) {
        if (seed2 == null) {
            throw new IllegalArgumentException("'seed' cannot be null");
        } else if (pgenCounter2 != null) {
            this.seed = seed2;
            this.pgenCounter = pgenCounter2;
        } else {
            throw new IllegalArgumentException("'pgenCounter' cannot be null");
        }
    }

    private ValidationParams(ASN1Sequence seq) {
        if (seq.size() == 2) {
            this.seed = DERBitString.getInstance(seq.getObjectAt(0));
            this.pgenCounter = ASN1Integer.getInstance(seq.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + seq.size());
    }

    public byte[] getSeed() {
        return this.seed.getBytes();
    }

    public BigInteger getPgenCounter() {
        return this.pgenCounter.getPositiveValue();
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.seed);
        v.add(this.pgenCounter);
        return new DERSequence(v);
    }
}
