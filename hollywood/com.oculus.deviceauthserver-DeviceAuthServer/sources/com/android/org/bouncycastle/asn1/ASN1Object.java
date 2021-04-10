package com.android.org.bouncycastle.asn1;

import com.android.org.bouncycastle.util.Encodable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class ASN1Object implements ASN1Encodable, Encodable {
    @Override // com.android.org.bouncycastle.asn1.ASN1Encodable
    public abstract ASN1Primitive toASN1Primitive();

    @Override // com.android.org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        new ASN1OutputStream(bOut).writeObject(this);
        return bOut.toByteArray();
    }

    public byte[] getEncoded(String encoding) throws IOException {
        if (encoding.equals(ASN1Encoding.DER)) {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            new DEROutputStream(bOut).writeObject(this);
            return bOut.toByteArray();
        } else if (!encoding.equals(ASN1Encoding.DL)) {
            return getEncoded();
        } else {
            ByteArrayOutputStream bOut2 = new ByteArrayOutputStream();
            new DLOutputStream(bOut2).writeObject(this);
            return bOut2.toByteArray();
        }
    }

    public int hashCode() {
        return toASN1Primitive().hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ASN1Encodable)) {
            return false;
        }
        return toASN1Primitive().equals(((ASN1Encodable) o).toASN1Primitive());
    }

    public ASN1Primitive toASN1Object() {
        return toASN1Primitive();
    }

    protected static boolean hasEncodedTagValue(Object obj, int tagValue) {
        return (obj instanceof byte[]) && ((byte[]) obj)[0] == tagValue;
    }
}
