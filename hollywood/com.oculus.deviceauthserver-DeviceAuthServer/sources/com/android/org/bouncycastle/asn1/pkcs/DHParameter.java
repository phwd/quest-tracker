package com.android.org.bouncycastle.asn1.pkcs;

import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.DERSequence;
import java.math.BigInteger;
import java.util.Enumeration;

public class DHParameter extends ASN1Object {
    ASN1Integer g;
    ASN1Integer l;
    ASN1Integer p;

    public DHParameter(BigInteger p2, BigInteger g2, int l2) {
        this.p = new ASN1Integer(p2);
        this.g = new ASN1Integer(g2);
        if (l2 != 0) {
            this.l = new ASN1Integer((long) l2);
        } else {
            this.l = null;
        }
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private DHParameter(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.p = ASN1Integer.getInstance(e.nextElement());
        this.g = ASN1Integer.getInstance(e.nextElement());
        if (e.hasMoreElements()) {
            this.l = (ASN1Integer) e.nextElement();
        } else {
            this.l = null;
        }
    }

    public BigInteger getP() {
        return this.p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.g.getPositiveValue();
    }

    public BigInteger getL() {
        ASN1Integer aSN1Integer = this.l;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.p);
        v.add(this.g);
        if (getL() != null) {
            v.add(this.l);
        }
        return new DERSequence(v);
    }
}
