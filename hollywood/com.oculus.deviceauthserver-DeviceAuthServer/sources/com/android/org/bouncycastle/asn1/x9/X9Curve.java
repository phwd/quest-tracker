package com.android.org.bouncycastle.asn1.x9;

import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.DERBitString;
import com.android.org.bouncycastle.asn1.DERSequence;
import com.android.org.bouncycastle.math.ec.ECAlgorithms;
import com.android.org.bouncycastle.math.ec.ECCurve;
import com.android.org.bouncycastle.util.Arrays;
import java.math.BigInteger;

public class X9Curve extends ASN1Object implements X9ObjectIdentifiers {
    private ECCurve curve;
    private ASN1ObjectIdentifier fieldIdentifier;
    private byte[] seed;

    public X9Curve(ECCurve curve2) {
        this(curve2, null);
    }

    public X9Curve(ECCurve curve2, byte[] seed2) {
        this.fieldIdentifier = null;
        this.curve = curve2;
        this.seed = Arrays.clone(seed2);
        setFieldIdentifier();
    }

    public X9Curve(X9FieldID fieldID, BigInteger order, BigInteger cofactor, ASN1Sequence seq) {
        int k3;
        int k2;
        int k1;
        this.fieldIdentifier = null;
        this.fieldIdentifier = fieldID.getIdentifier();
        if (this.fieldIdentifier.equals(prime_field)) {
            this.curve = new ECCurve.Fp(((ASN1Integer) fieldID.getParameters()).getValue(), new BigInteger(1, ASN1OctetString.getInstance(seq.getObjectAt(0)).getOctets()), new BigInteger(1, ASN1OctetString.getInstance(seq.getObjectAt(1)).getOctets()), order, cofactor);
        } else if (this.fieldIdentifier.equals(characteristic_two_field)) {
            ASN1Sequence parameters = ASN1Sequence.getInstance(fieldID.getParameters());
            int m = ((ASN1Integer) parameters.getObjectAt(0)).getValue().intValue();
            ASN1ObjectIdentifier representation = (ASN1ObjectIdentifier) parameters.getObjectAt(1);
            if (representation.equals(tpBasis)) {
                k1 = ASN1Integer.getInstance(parameters.getObjectAt(2)).getValue().intValue();
                k2 = 0;
                k3 = 0;
            } else if (representation.equals(ppBasis)) {
                ASN1Sequence pentanomial = ASN1Sequence.getInstance(parameters.getObjectAt(2));
                k1 = ASN1Integer.getInstance(pentanomial.getObjectAt(0)).getValue().intValue();
                k2 = ASN1Integer.getInstance(pentanomial.getObjectAt(1)).getValue().intValue();
                k3 = ASN1Integer.getInstance(pentanomial.getObjectAt(2)).getValue().intValue();
            } else {
                throw new IllegalArgumentException("This type of EC basis is not implemented");
            }
            this.curve = new ECCurve.F2m(m, k1, k2, k3, new BigInteger(1, ASN1OctetString.getInstance(seq.getObjectAt(0)).getOctets()), new BigInteger(1, ASN1OctetString.getInstance(seq.getObjectAt(1)).getOctets()), order, cofactor);
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
        if (seq.size() == 3) {
            this.seed = Arrays.clone(((DERBitString) seq.getObjectAt(2)).getBytes());
        }
    }

    private void setFieldIdentifier() {
        if (ECAlgorithms.isFpCurve(this.curve)) {
            this.fieldIdentifier = prime_field;
        } else if (ECAlgorithms.isF2mCurve(this.curve)) {
            this.fieldIdentifier = characteristic_two_field;
        } else {
            throw new IllegalArgumentException("This type of ECCurve is not implemented");
        }
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.fieldIdentifier.equals(prime_field)) {
            v.add(new X9FieldElement(this.curve.getA()).toASN1Primitive());
            v.add(new X9FieldElement(this.curve.getB()).toASN1Primitive());
        } else if (this.fieldIdentifier.equals(characteristic_two_field)) {
            v.add(new X9FieldElement(this.curve.getA()).toASN1Primitive());
            v.add(new X9FieldElement(this.curve.getB()).toASN1Primitive());
        }
        byte[] bArr = this.seed;
        if (bArr != null) {
            v.add(new DERBitString(bArr));
        }
        return new DERSequence(v);
    }
}
