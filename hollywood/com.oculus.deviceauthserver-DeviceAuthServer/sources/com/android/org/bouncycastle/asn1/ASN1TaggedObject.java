package com.android.org.bouncycastle.asn1;

import java.io.IOException;

public abstract class ASN1TaggedObject extends ASN1Primitive implements ASN1TaggedObjectParser {
    boolean empty = false;
    boolean explicit = true;
    ASN1Encodable obj = null;
    int tagNo;

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    public static ASN1TaggedObject getInstance(ASN1TaggedObject obj2, boolean explicit2) {
        if (explicit2) {
            return (ASN1TaggedObject) obj2.getObject();
        }
        throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    public static ASN1TaggedObject getInstance(Object obj2) {
        if (obj2 == null || (obj2 instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj2;
        }
        if (obj2 instanceof byte[]) {
            try {
                return getInstance(fromByteArray((byte[]) obj2));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("unknown object in getInstance: " + obj2.getClass().getName());
        }
    }

    public ASN1TaggedObject(boolean explicit2, int tagNo2, ASN1Encodable obj2) {
        if (obj2 instanceof ASN1Choice) {
            this.explicit = true;
        } else {
            this.explicit = explicit2;
        }
        this.tagNo = tagNo2;
        if (this.explicit) {
            this.obj = obj2;
            return;
        }
        if (obj2.toASN1Primitive() instanceof ASN1Set) {
        }
        this.obj = obj2;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof ASN1TaggedObject)) {
            return false;
        }
        ASN1TaggedObject other = (ASN1TaggedObject) o;
        if (this.tagNo != other.tagNo || this.empty != other.empty || this.explicit != other.explicit) {
            return false;
        }
        ASN1Encodable aSN1Encodable = this.obj;
        if (aSN1Encodable == null) {
            if (other.obj != null) {
                return false;
            }
            return true;
        } else if (!aSN1Encodable.toASN1Primitive().equals(other.obj.toASN1Primitive())) {
            return false;
        } else {
            return true;
        }
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Primitive
    public int hashCode() {
        int code = this.tagNo;
        ASN1Encodable aSN1Encodable = this.obj;
        if (aSN1Encodable != null) {
            return code ^ aSN1Encodable.hashCode();
        }
        return code;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1TaggedObjectParser
    public int getTagNo() {
        return this.tagNo;
    }

    public boolean isExplicit() {
        return this.explicit;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public ASN1Primitive getObject() {
        ASN1Encodable aSN1Encodable = this.obj;
        if (aSN1Encodable != null) {
            return aSN1Encodable.toASN1Primitive();
        }
        return null;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1TaggedObjectParser
    public ASN1Encodable getObjectParser(int tag, boolean isExplicit) throws IOException {
        if (tag == 4) {
            return ASN1OctetString.getInstance(this, isExplicit).parser();
        }
        if (tag == 16) {
            return ASN1Sequence.getInstance(this, isExplicit).parser();
        }
        if (tag == 17) {
            return ASN1Set.getInstance(this, isExplicit).parser();
        }
        if (isExplicit) {
            return getObject();
        }
        throw new ASN1Exception("implicit tagging not implemented for tag: " + tag);
    }

    @Override // com.android.org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return new DERTaggedObject(this.explicit, this.tagNo, this.obj);
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return new DLTaggedObject(this.explicit, this.tagNo, this.obj);
    }

    public String toString() {
        return "[" + this.tagNo + "]" + this.obj;
    }
}
