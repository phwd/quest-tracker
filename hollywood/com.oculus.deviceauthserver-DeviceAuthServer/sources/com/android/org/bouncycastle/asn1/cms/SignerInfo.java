package com.android.org.bouncycastle.asn1.cms;

import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.ASN1Set;
import com.android.org.bouncycastle.asn1.ASN1TaggedObject;
import com.android.org.bouncycastle.asn1.DEROctetString;
import com.android.org.bouncycastle.asn1.DERSequence;
import com.android.org.bouncycastle.asn1.DERTaggedObject;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import java.util.Enumeration;

public class SignerInfo extends ASN1Object {
    private ASN1Set authenticatedAttributes;
    private AlgorithmIdentifier digAlgorithm;
    private AlgorithmIdentifier digEncryptionAlgorithm;
    private ASN1OctetString encryptedDigest;
    private SignerIdentifier sid;
    private ASN1Set unauthenticatedAttributes;
    private ASN1Integer version;

    public static SignerInfo getInstance(Object o) throws IllegalArgumentException {
        if (o instanceof SignerInfo) {
            return (SignerInfo) o;
        }
        if (o != null) {
            return new SignerInfo(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public SignerInfo(SignerIdentifier sid2, AlgorithmIdentifier digAlgorithm2, ASN1Set authenticatedAttributes2, AlgorithmIdentifier digEncryptionAlgorithm2, ASN1OctetString encryptedDigest2, ASN1Set unauthenticatedAttributes2) {
        if (sid2.isTagged()) {
            this.version = new ASN1Integer(3);
        } else {
            this.version = new ASN1Integer(1);
        }
        this.sid = sid2;
        this.digAlgorithm = digAlgorithm2;
        this.authenticatedAttributes = authenticatedAttributes2;
        this.digEncryptionAlgorithm = digEncryptionAlgorithm2;
        this.encryptedDigest = encryptedDigest2;
        this.unauthenticatedAttributes = unauthenticatedAttributes2;
    }

    public SignerInfo(SignerIdentifier sid2, AlgorithmIdentifier digAlgorithm2, Attributes authenticatedAttributes2, AlgorithmIdentifier digEncryptionAlgorithm2, ASN1OctetString encryptedDigest2, Attributes unauthenticatedAttributes2) {
        if (sid2.isTagged()) {
            this.version = new ASN1Integer(3);
        } else {
            this.version = new ASN1Integer(1);
        }
        this.sid = sid2;
        this.digAlgorithm = digAlgorithm2;
        this.authenticatedAttributes = ASN1Set.getInstance(authenticatedAttributes2);
        this.digEncryptionAlgorithm = digEncryptionAlgorithm2;
        this.encryptedDigest = encryptedDigest2;
        this.unauthenticatedAttributes = ASN1Set.getInstance(unauthenticatedAttributes2);
    }

    public SignerInfo(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.version = (ASN1Integer) e.nextElement();
        this.sid = SignerIdentifier.getInstance(e.nextElement());
        this.digAlgorithm = AlgorithmIdentifier.getInstance(e.nextElement());
        Object obj = e.nextElement();
        if (obj instanceof ASN1TaggedObject) {
            this.authenticatedAttributes = ASN1Set.getInstance((ASN1TaggedObject) obj, false);
            this.digEncryptionAlgorithm = AlgorithmIdentifier.getInstance(e.nextElement());
        } else {
            this.authenticatedAttributes = null;
            this.digEncryptionAlgorithm = AlgorithmIdentifier.getInstance(obj);
        }
        this.encryptedDigest = DEROctetString.getInstance(e.nextElement());
        if (e.hasMoreElements()) {
            this.unauthenticatedAttributes = ASN1Set.getInstance((ASN1TaggedObject) e.nextElement(), false);
        } else {
            this.unauthenticatedAttributes = null;
        }
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public SignerIdentifier getSID() {
        return this.sid;
    }

    public ASN1Set getAuthenticatedAttributes() {
        return this.authenticatedAttributes;
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digAlgorithm;
    }

    public ASN1OctetString getEncryptedDigest() {
        return this.encryptedDigest;
    }

    public AlgorithmIdentifier getDigestEncryptionAlgorithm() {
        return this.digEncryptionAlgorithm;
    }

    public ASN1Set getUnauthenticatedAttributes() {
        return this.unauthenticatedAttributes;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.version);
        v.add(this.sid);
        v.add(this.digAlgorithm);
        ASN1Set aSN1Set = this.authenticatedAttributes;
        if (aSN1Set != null) {
            v.add(new DERTaggedObject(false, 0, aSN1Set));
        }
        v.add(this.digEncryptionAlgorithm);
        v.add(this.encryptedDigest);
        ASN1Set aSN1Set2 = this.unauthenticatedAttributes;
        if (aSN1Set2 != null) {
            v.add(new DERTaggedObject(false, 1, aSN1Set2));
        }
        return new DERSequence(v);
    }
}
