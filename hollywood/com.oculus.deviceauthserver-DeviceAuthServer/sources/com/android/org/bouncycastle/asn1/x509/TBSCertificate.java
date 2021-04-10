package com.android.org.bouncycastle.asn1.x509;

import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.ASN1TaggedObject;
import com.android.org.bouncycastle.asn1.DERBitString;
import com.android.org.bouncycastle.asn1.x500.X500Name;
import java.math.BigInteger;

public class TBSCertificate extends ASN1Object {
    Time endDate;
    Extensions extensions;
    X500Name issuer;
    DERBitString issuerUniqueId;
    ASN1Sequence seq;
    ASN1Integer serialNumber;
    AlgorithmIdentifier signature;
    Time startDate;
    X500Name subject;
    SubjectPublicKeyInfo subjectPublicKeyInfo;
    DERBitString subjectUniqueId;
    ASN1Integer version;

    public static TBSCertificate getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static TBSCertificate getInstance(Object obj) {
        if (obj instanceof TBSCertificate) {
            return (TBSCertificate) obj;
        }
        if (obj != null) {
            return new TBSCertificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private TBSCertificate(ASN1Sequence seq2) {
        int seqStart = 0;
        this.seq = seq2;
        if (seq2.getObjectAt(0) instanceof ASN1TaggedObject) {
            this.version = ASN1Integer.getInstance((ASN1TaggedObject) seq2.getObjectAt(0), true);
        } else {
            seqStart = -1;
            this.version = new ASN1Integer(0);
        }
        boolean isV1 = false;
        boolean isV2 = false;
        if (this.version.getValue().equals(BigInteger.valueOf(0))) {
            isV1 = true;
        } else if (this.version.getValue().equals(BigInteger.valueOf(1))) {
            isV2 = true;
        } else if (!this.version.getValue().equals(BigInteger.valueOf(2))) {
            throw new IllegalArgumentException("version number not recognised");
        }
        this.serialNumber = ASN1Integer.getInstance(seq2.getObjectAt(seqStart + 1));
        this.signature = AlgorithmIdentifier.getInstance(seq2.getObjectAt(seqStart + 2));
        this.issuer = X500Name.getInstance(seq2.getObjectAt(seqStart + 3));
        ASN1Sequence dates = (ASN1Sequence) seq2.getObjectAt(seqStart + 4);
        this.startDate = Time.getInstance(dates.getObjectAt(0));
        this.endDate = Time.getInstance(dates.getObjectAt(1));
        this.subject = X500Name.getInstance(seq2.getObjectAt(seqStart + 5));
        this.subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(seq2.getObjectAt(seqStart + 6));
        int extras = (seq2.size() - (seqStart + 6)) - 1;
        if (extras == 0 || !isV1) {
            while (extras > 0) {
                ASN1TaggedObject extra = (ASN1TaggedObject) seq2.getObjectAt(seqStart + 6 + extras);
                int tagNo = extra.getTagNo();
                if (tagNo == 1) {
                    this.issuerUniqueId = DERBitString.getInstance(extra, false);
                } else if (tagNo == 2) {
                    this.subjectUniqueId = DERBitString.getInstance(extra, false);
                } else if (tagNo != 3) {
                    throw new IllegalArgumentException("Unknown tag encountered in structure: " + extra.getTagNo());
                } else if (!isV2) {
                    this.extensions = Extensions.getInstance(ASN1Sequence.getInstance(extra, true));
                } else {
                    throw new IllegalArgumentException("version 2 certificate cannot contain extensions");
                }
                extras--;
            }
            return;
        }
        throw new IllegalArgumentException("version 1 certificate contains extra data");
    }

    public int getVersionNumber() {
        return this.version.getValue().intValue() + 1;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public Time getStartDate() {
        return this.startDate;
    }

    public Time getEndDate() {
        return this.endDate;
    }

    public X500Name getSubject() {
        return this.subject;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.subjectPublicKeyInfo;
    }

    public DERBitString getIssuerUniqueId() {
        return this.issuerUniqueId;
    }

    public DERBitString getSubjectUniqueId() {
        return this.subjectUniqueId;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }
}
