package com.android.org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

class LazyEncodedSequence extends ASN1Sequence {
    private byte[] encoded;

    LazyEncodedSequence(byte[] encoded2) throws IOException {
        this.encoded = encoded2;
    }

    private void parse() {
        Enumeration en = new LazyConstructionEnumeration(this.encoded);
        while (en.hasMoreElements()) {
            this.seq.addElement(en.nextElement());
        }
        this.encoded = null;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Sequence
    public synchronized ASN1Encodable getObjectAt(int index) {
        if (this.encoded != null) {
            parse();
        }
        return super.getObjectAt(index);
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Sequence
    public synchronized Enumeration getObjects() {
        if (this.encoded == null) {
            return super.getObjects();
        }
        return new LazyConstructionEnumeration(this.encoded);
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Sequence
    public synchronized int size() {
        if (this.encoded != null) {
            parse();
        }
        return super.size();
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Sequence, com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        if (this.encoded != null) {
            parse();
        }
        return super.toDERObject();
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Sequence, com.android.org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        if (this.encoded != null) {
            parse();
        }
        return super.toDLObject();
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() throws IOException {
        byte[] bArr = this.encoded;
        if (bArr != null) {
            return StreamUtil.calculateBodyLength(bArr.length) + 1 + this.encoded.length;
        }
        return super.toDLObject().encodedLength();
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Sequence, com.android.org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream out) throws IOException {
        byte[] bArr = this.encoded;
        if (bArr != null) {
            out.writeEncoded(48, bArr);
        } else {
            super.toDLObject().encode(out);
        }
    }
}
