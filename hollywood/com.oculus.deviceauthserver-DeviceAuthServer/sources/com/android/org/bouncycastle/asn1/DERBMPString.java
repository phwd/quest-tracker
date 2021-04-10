package com.android.org.bouncycastle.asn1;

import com.android.org.bouncycastle.util.Arrays;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;

public class DERBMPString extends ASN1Primitive implements ASN1String {
    private final char[] string;

    public static DERBMPString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERBMPString)) {
            return (DERBMPString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERBMPString) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERBMPString getInstance(ASN1TaggedObject obj, boolean explicit) {
        ASN1Primitive o = obj.getObject();
        if (explicit || (o instanceof DERBMPString)) {
            return getInstance(o);
        }
        return new DERBMPString(ASN1OctetString.getInstance(o).getOctets());
    }

    DERBMPString(byte[] string2) {
        char[] cs = new char[(string2.length / 2)];
        for (int i = 0; i != cs.length; i++) {
            cs[i] = (char) ((string2[i * 2] << 8) | (string2[(i * 2) + 1] & UnsignedBytes.MAX_VALUE));
        }
        this.string = cs;
    }

    DERBMPString(char[] string2) {
        this.string = string2;
    }

    public DERBMPString(String string2) {
        this.string = string2.toCharArray();
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1String
    public String getString() {
        return new String(this.string);
    }

    public String toString() {
        return getString();
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1Object, com.android.org.bouncycastle.asn1.ASN1Primitive
    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof DERBMPString)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((DERBMPString) o).string);
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length * 2) + 1 + (this.string.length * 2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream out) throws IOException {
        out.write(30);
        out.writeLength(this.string.length * 2);
        int i = 0;
        while (true) {
            char[] cArr = this.string;
            if (i != cArr.length) {
                char c = cArr[i];
                out.write((byte) (c >> '\b'));
                out.write((byte) c);
                i++;
            } else {
                return;
            }
        }
    }
}
