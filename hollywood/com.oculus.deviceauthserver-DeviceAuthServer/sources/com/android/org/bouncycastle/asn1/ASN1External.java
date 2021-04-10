package com.android.org.bouncycastle.asn1;

import java.io.IOException;

public abstract class ASN1External extends ASN1Primitive {
    protected ASN1Primitive dataValueDescriptor;
    protected ASN1ObjectIdentifier directReference;
    protected int encoding;
    protected ASN1Primitive externalContent;
    protected ASN1Integer indirectReference;

    public ASN1External(ASN1EncodableVector vector) {
        int offset = 0;
        ASN1Primitive enc = getObjFromVector(vector, 0);
        if (enc instanceof ASN1ObjectIdentifier) {
            this.directReference = (ASN1ObjectIdentifier) enc;
            offset = 0 + 1;
            enc = getObjFromVector(vector, offset);
        }
        if (enc instanceof ASN1Integer) {
            this.indirectReference = (ASN1Integer) enc;
            offset++;
            enc = getObjFromVector(vector, offset);
        }
        if (!(enc instanceof ASN1TaggedObject)) {
            this.dataValueDescriptor = enc;
            offset++;
            enc = getObjFromVector(vector, offset);
        }
        if (vector.size() != offset + 1) {
            throw new IllegalArgumentException("input vector too large");
        } else if (enc instanceof ASN1TaggedObject) {
            ASN1TaggedObject obj = (ASN1TaggedObject) enc;
            setEncoding(obj.getTagNo());
            this.externalContent = obj.getObject();
        } else {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
    }

    private ASN1Primitive getObjFromVector(ASN1EncodableVector v, int index) {
        if (v.size() > index) {
            return v.get(index).toASN1Primitive();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    public ASN1External(ASN1ObjectIdentifier directReference2, ASN1Integer indirectReference2, ASN1Primitive dataValueDescriptor2, DERTaggedObject externalData) {
        this(directReference2, indirectReference2, dataValueDescriptor2, externalData.getTagNo(), externalData.toASN1Primitive());
    }

    public ASN1External(ASN1ObjectIdentifier directReference2, ASN1Integer indirectReference2, ASN1Primitive dataValueDescriptor2, int encoding2, ASN1Primitive externalData) {
        setDirectReference(directReference2);
        setIndirectReference(indirectReference2);
        setDataValueDescriptor(dataValueDescriptor2);
        setEncoding(encoding2);
        setExternalContent(externalData.toASN1Primitive());
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        if (this instanceof DERExternal) {
            return this;
        }
        return new DERExternal(this.directReference, this.indirectReference, this.dataValueDescriptor, this.encoding, this.externalContent);
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Primitive
    public int hashCode() {
        int ret = 0;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.directReference;
        if (aSN1ObjectIdentifier != null) {
            ret = aSN1ObjectIdentifier.hashCode();
        }
        ASN1Integer aSN1Integer = this.indirectReference;
        if (aSN1Integer != null) {
            ret ^= aSN1Integer.hashCode();
        }
        ASN1Primitive aSN1Primitive = this.dataValueDescriptor;
        if (aSN1Primitive != null) {
            ret ^= aSN1Primitive.hashCode();
        }
        return ret ^ this.externalContent.hashCode();
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() throws IOException {
        return getEncoded().length;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive o) {
        ASN1Primitive aSN1Primitive;
        ASN1Integer aSN1Integer;
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        if (!(o instanceof ASN1External)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        ASN1External other = (ASN1External) o;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = this.directReference;
        if (aSN1ObjectIdentifier2 != null && ((aSN1ObjectIdentifier = other.directReference) == null || !aSN1ObjectIdentifier.equals(aSN1ObjectIdentifier2))) {
            return false;
        }
        ASN1Integer aSN1Integer2 = this.indirectReference;
        if (aSN1Integer2 != null && ((aSN1Integer = other.indirectReference) == null || !aSN1Integer.equals(aSN1Integer2))) {
            return false;
        }
        ASN1Primitive aSN1Primitive2 = this.dataValueDescriptor;
        if (aSN1Primitive2 == null || ((aSN1Primitive = other.dataValueDescriptor) != null && aSN1Primitive.equals(aSN1Primitive2))) {
            return this.externalContent.equals(other.externalContent);
        }
        return false;
    }

    public ASN1Primitive getDataValueDescriptor() {
        return this.dataValueDescriptor;
    }

    public ASN1ObjectIdentifier getDirectReference() {
        return this.directReference;
    }

    public int getEncoding() {
        return this.encoding;
    }

    public ASN1Primitive getExternalContent() {
        return this.externalContent;
    }

    public ASN1Integer getIndirectReference() {
        return this.indirectReference;
    }

    private void setDataValueDescriptor(ASN1Primitive dataValueDescriptor2) {
        this.dataValueDescriptor = dataValueDescriptor2;
    }

    private void setDirectReference(ASN1ObjectIdentifier directReferemce) {
        this.directReference = directReferemce;
    }

    private void setEncoding(int encoding2) {
        if (encoding2 < 0 || encoding2 > 2) {
            throw new IllegalArgumentException("invalid encoding value: " + encoding2);
        }
        this.encoding = encoding2;
    }

    private void setExternalContent(ASN1Primitive externalContent2) {
        this.externalContent = externalContent2;
    }

    private void setIndirectReference(ASN1Integer indirectReference2) {
        this.indirectReference = indirectReference2;
    }
}
