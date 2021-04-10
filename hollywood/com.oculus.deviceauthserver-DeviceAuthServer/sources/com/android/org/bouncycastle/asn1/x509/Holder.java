package com.android.org.bouncycastle.asn1.x509;

import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.ASN1TaggedObject;
import com.android.org.bouncycastle.asn1.DERSequence;
import com.android.org.bouncycastle.asn1.DERTaggedObject;

public class Holder extends ASN1Object {
    public static final int V1_CERTIFICATE_HOLDER = 0;
    public static final int V2_CERTIFICATE_HOLDER = 1;
    IssuerSerial baseCertificateID;
    GeneralNames entityName;
    ObjectDigestInfo objectDigestInfo;
    private int version;

    public static Holder getInstance(Object obj) {
        if (obj instanceof Holder) {
            return (Holder) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Holder(ASN1TaggedObject.getInstance(obj));
        }
        if (obj != null) {
            return new Holder(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private Holder(ASN1TaggedObject tagObj) {
        this.version = 1;
        int tagNo = tagObj.getTagNo();
        if (tagNo == 0) {
            this.baseCertificateID = IssuerSerial.getInstance(tagObj, true);
        } else if (tagNo == 1) {
            this.entityName = GeneralNames.getInstance(tagObj, true);
        } else {
            throw new IllegalArgumentException("unknown tag in Holder");
        }
        this.version = 0;
    }

    private Holder(ASN1Sequence seq) {
        this.version = 1;
        if (seq.size() <= 3) {
            for (int i = 0; i != seq.size(); i++) {
                ASN1TaggedObject tObj = ASN1TaggedObject.getInstance(seq.getObjectAt(i));
                int tagNo = tObj.getTagNo();
                if (tagNo == 0) {
                    this.baseCertificateID = IssuerSerial.getInstance(tObj, false);
                } else if (tagNo == 1) {
                    this.entityName = GeneralNames.getInstance(tObj, false);
                } else if (tagNo == 2) {
                    this.objectDigestInfo = ObjectDigestInfo.getInstance(tObj, false);
                } else {
                    throw new IllegalArgumentException("unknown tag in Holder");
                }
            }
            this.version = 1;
            return;
        }
        throw new IllegalArgumentException("Bad sequence size: " + seq.size());
    }

    public Holder(IssuerSerial baseCertificateID2) {
        this(baseCertificateID2, 1);
    }

    public Holder(IssuerSerial baseCertificateID2, int version2) {
        this.version = 1;
        this.baseCertificateID = baseCertificateID2;
        this.version = version2;
    }

    public int getVersion() {
        return this.version;
    }

    public Holder(GeneralNames entityName2) {
        this(entityName2, 1);
    }

    public Holder(GeneralNames entityName2, int version2) {
        this.version = 1;
        this.entityName = entityName2;
        this.version = version2;
    }

    public Holder(ObjectDigestInfo objectDigestInfo2) {
        this.version = 1;
        this.objectDigestInfo = objectDigestInfo2;
    }

    public IssuerSerial getBaseCertificateID() {
        return this.baseCertificateID;
    }

    public GeneralNames getEntityName() {
        return this.entityName;
    }

    public ObjectDigestInfo getObjectDigestInfo() {
        return this.objectDigestInfo;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (this.version == 1) {
            ASN1EncodableVector v = new ASN1EncodableVector();
            IssuerSerial issuerSerial = this.baseCertificateID;
            if (issuerSerial != null) {
                v.add(new DERTaggedObject(false, 0, issuerSerial));
            }
            GeneralNames generalNames = this.entityName;
            if (generalNames != null) {
                v.add(new DERTaggedObject(false, 1, generalNames));
            }
            ObjectDigestInfo objectDigestInfo2 = this.objectDigestInfo;
            if (objectDigestInfo2 != null) {
                v.add(new DERTaggedObject(false, 2, objectDigestInfo2));
            }
            return new DERSequence(v);
        }
        GeneralNames generalNames2 = this.entityName;
        if (generalNames2 != null) {
            return new DERTaggedObject(true, 1, generalNames2);
        }
        return new DERTaggedObject(true, 0, this.baseCertificateID);
    }
}
