package com.android.org.bouncycastle.asn1.pkcs;

import com.android.org.bouncycastle.asn1.ASN1BitString;
import com.android.org.bouncycastle.asn1.ASN1Encodable;
import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1OctetString;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.ASN1Set;
import com.android.org.bouncycastle.asn1.ASN1TaggedObject;
import com.android.org.bouncycastle.asn1.DERBitString;
import com.android.org.bouncycastle.asn1.DEROctetString;
import com.android.org.bouncycastle.asn1.DERSequence;
import com.android.org.bouncycastle.asn1.DERTaggedObject;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.util.BigIntegers;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;

public class PrivateKeyInfo extends ASN1Object {
    private ASN1Set attributes;
    private ASN1OctetString privateKey;
    private AlgorithmIdentifier privateKeyAlgorithm;
    private ASN1BitString publicKey;
    private ASN1Integer version;

    public static PrivateKeyInfo getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static PrivateKeyInfo getInstance(Object obj) {
        if (obj instanceof PrivateKeyInfo) {
            return (PrivateKeyInfo) obj;
        }
        if (obj != null) {
            return new PrivateKeyInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private static int getVersionValue(ASN1Integer version2) {
        BigInteger bigValue = version2.getValue();
        if (bigValue.compareTo(BigIntegers.ZERO) >= 0 && bigValue.compareTo(BigIntegers.ONE) <= 0) {
            return bigValue.intValue();
        }
        throw new IllegalArgumentException("invalid version for private key info");
    }

    public PrivateKeyInfo(AlgorithmIdentifier privateKeyAlgorithm2, ASN1Encodable privateKey2) throws IOException {
        this(privateKeyAlgorithm2, privateKey2, null, null);
    }

    public PrivateKeyInfo(AlgorithmIdentifier privateKeyAlgorithm2, ASN1Encodable privateKey2, ASN1Set attributes2) throws IOException {
        this(privateKeyAlgorithm2, privateKey2, attributes2, null);
    }

    public PrivateKeyInfo(AlgorithmIdentifier privateKeyAlgorithm2, ASN1Encodable privateKey2, ASN1Set attributes2, byte[] publicKey2) throws IOException {
        this.version = new ASN1Integer(publicKey2 != null ? BigIntegers.ONE : BigIntegers.ZERO);
        this.privateKeyAlgorithm = privateKeyAlgorithm2;
        this.privateKey = new DEROctetString(privateKey2);
        this.attributes = attributes2;
        this.publicKey = publicKey2 == null ? null : new DERBitString(publicKey2);
    }

    private PrivateKeyInfo(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.version = ASN1Integer.getInstance(e.nextElement());
        int versionValue = getVersionValue(this.version);
        this.privateKeyAlgorithm = AlgorithmIdentifier.getInstance(e.nextElement());
        this.privateKey = ASN1OctetString.getInstance(e.nextElement());
        int lastTag = -1;
        while (e.hasMoreElements()) {
            ASN1TaggedObject tagged = (ASN1TaggedObject) e.nextElement();
            int tag = tagged.getTagNo();
            if (tag > lastTag) {
                lastTag = tag;
                if (tag == 0) {
                    this.attributes = ASN1Set.getInstance(tagged, false);
                } else if (tag != 1) {
                    throw new IllegalArgumentException("unknown optional field in private key info");
                } else if (versionValue >= 1) {
                    this.publicKey = DERBitString.getInstance(tagged, false);
                } else {
                    throw new IllegalArgumentException("'publicKey' requires version v2(1) or later");
                }
            } else {
                throw new IllegalArgumentException("invalid optional field in private key info");
            }
        }
    }

    public ASN1Set getAttributes() {
        return this.attributes;
    }

    public AlgorithmIdentifier getPrivateKeyAlgorithm() {
        return this.privateKeyAlgorithm;
    }

    public ASN1Encodable parsePrivateKey() throws IOException {
        return ASN1Primitive.fromByteArray(this.privateKey.getOctets());
    }

    public boolean hasPublicKey() {
        return this.publicKey != null;
    }

    public ASN1Encodable parsePublicKey() throws IOException {
        ASN1BitString aSN1BitString = this.publicKey;
        if (aSN1BitString == null) {
            return null;
        }
        return ASN1Primitive.fromByteArray(aSN1BitString.getOctets());
    }

    public ASN1BitString getPublicKeyData() {
        return this.publicKey;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.version);
        v.add(this.privateKeyAlgorithm);
        v.add(this.privateKey);
        ASN1Set aSN1Set = this.attributes;
        if (aSN1Set != null) {
            v.add(new DERTaggedObject(false, 0, aSN1Set));
        }
        ASN1BitString aSN1BitString = this.publicKey;
        if (aSN1BitString != null) {
            v.add(new DERTaggedObject(false, 1, aSN1BitString));
        }
        return new DERSequence(v);
    }
}
