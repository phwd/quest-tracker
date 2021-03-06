package com.android.org.bouncycastle.asn1.x509;

import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1GeneralizedTime;
import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.DERSequence;

public class AttCertValidityPeriod extends ASN1Object {
    ASN1GeneralizedTime notAfterTime;
    ASN1GeneralizedTime notBeforeTime;

    public static AttCertValidityPeriod getInstance(Object obj) {
        if (obj instanceof AttCertValidityPeriod) {
            return (AttCertValidityPeriod) obj;
        }
        if (obj != null) {
            return new AttCertValidityPeriod(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private AttCertValidityPeriod(ASN1Sequence seq) {
        if (seq.size() == 2) {
            this.notBeforeTime = ASN1GeneralizedTime.getInstance(seq.getObjectAt(0));
            this.notAfterTime = ASN1GeneralizedTime.getInstance(seq.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + seq.size());
    }

    public AttCertValidityPeriod(ASN1GeneralizedTime notBeforeTime2, ASN1GeneralizedTime notAfterTime2) {
        this.notBeforeTime = notBeforeTime2;
        this.notAfterTime = notAfterTime2;
    }

    public ASN1GeneralizedTime getNotBeforeTime() {
        return this.notBeforeTime;
    }

    public ASN1GeneralizedTime getNotAfterTime() {
        return this.notAfterTime;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.notBeforeTime);
        v.add(this.notAfterTime);
        return new DERSequence(v);
    }
}
