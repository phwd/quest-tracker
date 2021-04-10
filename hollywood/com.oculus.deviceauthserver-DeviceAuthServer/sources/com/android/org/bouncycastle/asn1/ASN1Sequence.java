package com.android.org.bouncycastle.asn1;

import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Iterable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public abstract class ASN1Sequence extends ASN1Primitive implements Iterable<ASN1Encodable> {
    protected Vector seq = new Vector();

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    public static ASN1Sequence getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Sequence)) {
            return (ASN1Sequence) obj;
        }
        if (obj instanceof ASN1SequenceParser) {
            return getInstance(((ASN1SequenceParser) obj).toASN1Primitive());
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(fromByteArray((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e.getMessage());
            }
        } else {
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive primitive = ((ASN1Encodable) obj).toASN1Primitive();
                if (primitive instanceof ASN1Sequence) {
                    return (ASN1Sequence) primitive;
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Sequence getInstance(ASN1TaggedObject obj, boolean explicit) {
        if (!explicit) {
            ASN1Primitive o = obj.getObject();
            if (obj.isExplicit()) {
                if (obj instanceof BERTaggedObject) {
                    return new BERSequence(o);
                }
                return new DLSequence(o);
            } else if (o instanceof ASN1Sequence) {
                return (ASN1Sequence) o;
            } else {
                throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
            }
        } else if (obj.isExplicit()) {
            return getInstance(obj.getObject().toASN1Primitive());
        } else {
            throw new IllegalArgumentException("object implicit - explicit expected.");
        }
    }

    protected ASN1Sequence() {
    }

    protected ASN1Sequence(ASN1Encodable obj) {
        this.seq.addElement(obj);
    }

    protected ASN1Sequence(ASN1EncodableVector v) {
        for (int i = 0; i != v.size(); i++) {
            this.seq.addElement(v.get(i));
        }
    }

    protected ASN1Sequence(ASN1Encodable[] array) {
        for (int i = 0; i != array.length; i++) {
            this.seq.addElement(array[i]);
        }
    }

    public ASN1Encodable[] toArray() {
        ASN1Encodable[] values = new ASN1Encodable[size()];
        for (int i = 0; i != size(); i++) {
            values[i] = getObjectAt(i);
        }
        return values;
    }

    public Enumeration getObjects() {
        return this.seq.elements();
    }

    public ASN1SequenceParser parser() {
        return new ASN1SequenceParser() {
            /* class com.android.org.bouncycastle.asn1.ASN1Sequence.AnonymousClass1 */
            private int index;
            private final int max = ASN1Sequence.this.size();

            @Override // com.android.org.bouncycastle.asn1.ASN1SequenceParser
            public ASN1Encodable readObject() throws IOException {
                int i = this.index;
                if (i == this.max) {
                    return null;
                }
                ASN1Sequence aSN1Sequence = ASN1Sequence.this;
                this.index = i + 1;
                ASN1Encodable obj = aSN1Sequence.getObjectAt(i);
                if (obj instanceof ASN1Sequence) {
                    return ((ASN1Sequence) obj).parser();
                }
                if (obj instanceof ASN1Set) {
                    return ((ASN1Set) obj).parser();
                }
                return obj;
            }

            @Override // com.android.org.bouncycastle.asn1.InMemoryRepresentable
            public ASN1Primitive getLoadedObject() {
                return this;
            }

            @Override // com.android.org.bouncycastle.asn1.ASN1Encodable
            public ASN1Primitive toASN1Primitive() {
                return this;
            }
        };
    }

    public ASN1Encodable getObjectAt(int index) {
        return (ASN1Encodable) this.seq.elementAt(index);
    }

    public int size() {
        return this.seq.size();
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Primitive
    public int hashCode() {
        Enumeration e = getObjects();
        int hashCode = size();
        while (e.hasMoreElements()) {
            hashCode = (hashCode * 17) ^ getNext(e).hashCode();
        }
        return hashCode;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof ASN1Sequence)) {
            return false;
        }
        ASN1Sequence other = (ASN1Sequence) o;
        if (size() != other.size()) {
            return false;
        }
        Enumeration s1 = getObjects();
        Enumeration s2 = other.getObjects();
        while (s1.hasMoreElements()) {
            ASN1Encodable obj1 = getNext(s1);
            ASN1Encodable obj2 = getNext(s2);
            ASN1Primitive o1 = obj1.toASN1Primitive();
            ASN1Primitive o2 = obj2.toASN1Primitive();
            if (o1 != o2 && !o1.equals(o2)) {
                return false;
            }
        }
        return true;
    }

    private ASN1Encodable getNext(Enumeration e) {
        return (ASN1Encodable) e.nextElement();
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        ASN1Sequence derSeq = new DERSequence();
        derSeq.seq = this.seq;
        return derSeq;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        ASN1Sequence dlSeq = new DLSequence();
        dlSeq.seq = this.seq;
        return dlSeq;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }

    public String toString() {
        return this.seq.toString();
    }

    @Override // com.android.org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(toArray());
    }
}
