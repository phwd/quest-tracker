package sun.security.pkcs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import sun.security.util.DerEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class PKCS9Attributes {
    private final Hashtable attributes;
    private final byte[] derEncoding;
    private boolean ignoreUnsupportedAttributes;
    private final Hashtable permittedAttributes;

    public PKCS9Attributes(DerInputStream derInputStream) {
        this(derInputStream, false);
    }

    public PKCS9Attributes(DerInputStream derInputStream, boolean z) {
        this.attributes = new Hashtable(3);
        this.ignoreUnsupportedAttributes = false;
        this.ignoreUnsupportedAttributes = z;
        this.derEncoding = decode(derInputStream);
        this.permittedAttributes = null;
    }

    private byte[] decode(DerInputStream derInputStream) {
        byte[] byteArray = derInputStream.getDerValue().toByteArray();
        byteArray[0] = 49;
        DerValue[] set = new DerInputStream(byteArray).getSet(3, true);
        boolean z = true;
        for (int i = 0; i < set.length; i++) {
            try {
                PKCS9Attribute pKCS9Attribute = new PKCS9Attribute(set[i]);
                ObjectIdentifier oid = pKCS9Attribute.getOID();
                if (this.attributes.get(oid) == null) {
                    Hashtable hashtable = this.permittedAttributes;
                    if (hashtable == null || hashtable.containsKey(oid)) {
                        this.attributes.put(oid, pKCS9Attribute);
                    } else {
                        throw new IOException("Attribute " + oid + " not permitted in this attribute set");
                    }
                } else {
                    throw new IOException("Duplicate PKCS9 attribute: " + oid);
                }
            } catch (ParsingException e) {
                if (this.ignoreUnsupportedAttributes) {
                    z = false;
                } else {
                    throw e;
                }
            }
        }
        return z ? byteArray : generateDerEncoding();
    }

    public void encode(byte b, OutputStream outputStream) {
        outputStream.write(b);
        byte[] bArr = this.derEncoding;
        outputStream.write(bArr, 1, bArr.length - 1);
    }

    private byte[] generateDerEncoding() {
        DerOutputStream derOutputStream = new DerOutputStream();
        derOutputStream.putOrderedSetOf((byte) 49, castToDerEncoder(this.attributes.values().toArray()));
        return derOutputStream.toByteArray();
    }

    public byte[] getDerEncoding() {
        return (byte[]) this.derEncoding.clone();
    }

    public PKCS9Attribute getAttribute(ObjectIdentifier objectIdentifier) {
        return (PKCS9Attribute) this.attributes.get(objectIdentifier);
    }

    public Object getAttributeValue(ObjectIdentifier objectIdentifier) {
        try {
            return getAttribute(objectIdentifier).getValue();
        } catch (NullPointerException unused) {
            throw new IOException("No value found for attribute " + objectIdentifier);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(200);
        stringBuffer.append("PKCS9 Attributes: [\n\t");
        int i = 1;
        boolean z = true;
        while (true) {
            ObjectIdentifier[] objectIdentifierArr = PKCS9Attribute.PKCS9_OIDS;
            if (i < objectIdentifierArr.length) {
                PKCS9Attribute attribute = getAttribute(objectIdentifierArr[i]);
                if (attribute != null) {
                    if (z) {
                        z = false;
                    } else {
                        stringBuffer.append(";\n\t");
                    }
                    stringBuffer.append(attribute.toString());
                }
                i++;
            } else {
                stringBuffer.append("\n\t] (end PKCS9 Attributes)");
                return stringBuffer.toString();
            }
        }
    }

    static DerEncoder[] castToDerEncoder(Object[] objArr) {
        DerEncoder[] derEncoderArr = new DerEncoder[objArr.length];
        for (int i = 0; i < derEncoderArr.length; i++) {
            derEncoderArr[i] = (DerEncoder) objArr[i];
        }
        return derEncoderArr;
    }
}
