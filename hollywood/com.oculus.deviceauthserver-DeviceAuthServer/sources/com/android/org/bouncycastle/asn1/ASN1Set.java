package com.android.org.bouncycastle.asn1;

import com.android.org.bouncycastle.util.Arrays;
import com.android.org.bouncycastle.util.Iterable;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public abstract class ASN1Set extends ASN1Primitive implements Iterable<ASN1Encodable> {
    private boolean isSorted = false;
    private Vector set = new Vector();

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    public static ASN1Set getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        if (obj instanceof ASN1SetParser) {
            return getInstance(((ASN1SetParser) obj).toASN1Primitive());
        }
        if (obj instanceof byte[]) {
            try {
                return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e.getMessage());
            }
        } else {
            if (obj instanceof ASN1Encodable) {
                ASN1Primitive primitive = ((ASN1Encodable) obj).toASN1Primitive();
                if (primitive instanceof ASN1Set) {
                    return (ASN1Set) primitive;
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Set getInstance(ASN1TaggedObject obj, boolean explicit) {
        if (!explicit) {
            ASN1Primitive o = obj.getObject();
            if (obj.isExplicit()) {
                if (obj instanceof BERTaggedObject) {
                    return new BERSet(o);
                }
                return new DLSet(o);
            } else if (o instanceof ASN1Set) {
                return (ASN1Set) o;
            } else {
                if (o instanceof ASN1Sequence) {
                    ASN1Sequence s = (ASN1Sequence) o;
                    if (obj instanceof BERTaggedObject) {
                        return new BERSet(s.toArray());
                    }
                    return new DLSet(s.toArray());
                }
                throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
            }
        } else if (obj.isExplicit()) {
            return (ASN1Set) obj.getObject();
        } else {
            throw new IllegalArgumentException("object implicit - explicit expected.");
        }
    }

    protected ASN1Set() {
    }

    protected ASN1Set(ASN1Encodable obj) {
        this.set.addElement(obj);
    }

    protected ASN1Set(ASN1EncodableVector v, boolean doSort) {
        for (int i = 0; i != v.size(); i++) {
            this.set.addElement(v.get(i));
        }
        if (doSort) {
            sort();
        }
    }

    protected ASN1Set(ASN1Encodable[] array, boolean doSort) {
        for (int i = 0; i != array.length; i++) {
            this.set.addElement(array[i]);
        }
        if (doSort) {
            sort();
        }
    }

    public Enumeration getObjects() {
        return this.set.elements();
    }

    public ASN1Encodable getObjectAt(int index) {
        return (ASN1Encodable) this.set.elementAt(index);
    }

    public int size() {
        return this.set.size();
    }

    public ASN1Encodable[] toArray() {
        ASN1Encodable[] values = new ASN1Encodable[size()];
        for (int i = 0; i != size(); i++) {
            values[i] = getObjectAt(i);
        }
        return values;
    }

    public ASN1SetParser parser() {
        return new ASN1SetParser() {
            /* class com.android.org.bouncycastle.asn1.ASN1Set.AnonymousClass1 */
            private int index;
            private final int max = ASN1Set.this.size();

            @Override // com.android.org.bouncycastle.asn1.ASN1SetParser
            public ASN1Encodable readObject() throws IOException {
                int i = this.index;
                if (i == this.max) {
                    return null;
                }
                ASN1Set aSN1Set = ASN1Set.this;
                this.index = i + 1;
                ASN1Encodable obj = aSN1Set.getObjectAt(i);
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
    public ASN1Primitive toDERObject() {
        if (this.isSorted) {
            ASN1Set derSet = new DERSet();
            derSet.set = this.set;
            return derSet;
        }
        Vector v = new Vector();
        for (int i = 0; i != this.set.size(); i++) {
            v.addElement(this.set.elementAt(i));
        }
        ASN1Set derSet2 = new DERSet();
        derSet2.set = v;
        derSet2.sort();
        return derSet2;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        ASN1Set derSet = new DLSet();
        derSet.set = this.set;
        return derSet;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof ASN1Set)) {
            return false;
        }
        ASN1Set other = (ASN1Set) o;
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
        ASN1Encodable encObj = (ASN1Encodable) e.nextElement();
        if (encObj == null) {
            return DERNull.INSTANCE;
        }
        return encObj;
    }

    private boolean lessThanOrEqual(byte[] a, byte[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i != len; i++) {
            if (a[i] != b[i]) {
                return (a[i] & UnsignedBytes.MAX_VALUE) < (b[i] & UnsignedBytes.MAX_VALUE);
            }
        }
        return len == a.length;
    }

    private byte[] getDEREncoded(ASN1Encodable obj) {
        try {
            return obj.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    /* access modifiers changed from: protected */
    public void sort() {
        if (!this.isSorted) {
            this.isSorted = true;
            if (this.set.size() > 1) {
                boolean swapped = true;
                int lastSwap = this.set.size() - 1;
                while (swapped) {
                    int swapIndex = 0;
                    byte[] a = getDEREncoded((ASN1Encodable) this.set.elementAt(0));
                    swapped = false;
                    for (int index = 0; index != lastSwap; index++) {
                        byte[] b = getDEREncoded((ASN1Encodable) this.set.elementAt(index + 1));
                        if (lessThanOrEqual(a, b)) {
                            a = b;
                        } else {
                            Object o = this.set.elementAt(index);
                            Vector vector = this.set;
                            vector.setElementAt(vector.elementAt(index + 1), index);
                            this.set.setElementAt(o, index + 1);
                            swapped = true;
                            swapIndex = index;
                        }
                    }
                    lastSwap = swapIndex;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }

    public String toString() {
        return this.set.toString();
    }

    @Override // com.android.org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(toArray());
    }
}
