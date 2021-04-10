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
    private final Hashtable<ObjectIdentifier, PKCS9Attribute> attributes;
    private final byte[] derEncoding;
    private boolean ignoreUnsupportedAttributes;
    private final Hashtable<ObjectIdentifier, ObjectIdentifier> permittedAttributes;

    public PKCS9Attributes(ObjectIdentifier[] permittedAttributes2, DerInputStream in) throws IOException {
        this.attributes = new Hashtable<>(3);
        this.ignoreUnsupportedAttributes = false;
        if (permittedAttributes2 != null) {
            this.permittedAttributes = new Hashtable<>(permittedAttributes2.length);
            for (int i = 0; i < permittedAttributes2.length; i++) {
                this.permittedAttributes.put(permittedAttributes2[i], permittedAttributes2[i]);
            }
        } else {
            this.permittedAttributes = null;
        }
        this.derEncoding = decode(in);
    }

    public PKCS9Attributes(DerInputStream in) throws IOException {
        this(in, false);
    }

    public PKCS9Attributes(DerInputStream in, boolean ignoreUnsupportedAttributes2) throws IOException {
        this.attributes = new Hashtable<>(3);
        this.ignoreUnsupportedAttributes = false;
        this.ignoreUnsupportedAttributes = ignoreUnsupportedAttributes2;
        this.derEncoding = decode(in);
        this.permittedAttributes = null;
    }

    public PKCS9Attributes(PKCS9Attribute[] attribs) throws IllegalArgumentException, IOException {
        this.attributes = new Hashtable<>(3);
        this.ignoreUnsupportedAttributes = false;
        for (int i = 0; i < attribs.length; i++) {
            ObjectIdentifier oid = attribs[i].getOID();
            if (!this.attributes.containsKey(oid)) {
                this.attributes.put(oid, attribs[i]);
            } else {
                throw new IllegalArgumentException("PKCSAttribute " + ((Object) attribs[i].getOID()) + " duplicated while constructing PKCS9Attributes.");
            }
        }
        this.derEncoding = generateDerEncoding();
        this.permittedAttributes = null;
    }

    private byte[] decode(DerInputStream in) throws IOException {
        byte[] derEncoding2 = in.getDerValue().toByteArray();
        derEncoding2[0] = 49;
        DerValue[] derVals = new DerInputStream(derEncoding2).getSet(3, true);
        boolean reuseEncoding = true;
        for (int i = 0; i < derVals.length; i++) {
            try {
                PKCS9Attribute attrib = new PKCS9Attribute(derVals[i]);
                ObjectIdentifier oid = attrib.getOID();
                if (this.attributes.get(oid) == null) {
                    Hashtable<ObjectIdentifier, ObjectIdentifier> hashtable = this.permittedAttributes;
                    if (hashtable == null || hashtable.containsKey(oid)) {
                        this.attributes.put(oid, attrib);
                    } else {
                        throw new IOException("Attribute " + ((Object) oid) + " not permitted in this attribute set");
                    }
                } else {
                    throw new IOException("Duplicate PKCS9 attribute: " + ((Object) oid));
                }
            } catch (ParsingException e) {
                if (this.ignoreUnsupportedAttributes) {
                    reuseEncoding = false;
                } else {
                    throw e;
                }
            }
        }
        return reuseEncoding ? derEncoding2 : generateDerEncoding();
    }

    public void encode(byte tag, OutputStream out) throws IOException {
        out.write(tag);
        byte[] bArr = this.derEncoding;
        out.write(bArr, 1, bArr.length - 1);
    }

    private byte[] generateDerEncoding() throws IOException {
        DerOutputStream out = new DerOutputStream();
        out.putOrderedSetOf((byte) 49, castToDerEncoder(this.attributes.values().toArray()));
        return out.toByteArray();
    }

    public byte[] getDerEncoding() throws IOException {
        return (byte[]) this.derEncoding.clone();
    }

    public PKCS9Attribute getAttribute(ObjectIdentifier oid) {
        return this.attributes.get(oid);
    }

    public PKCS9Attribute getAttribute(String name) {
        return this.attributes.get(PKCS9Attribute.getOID(name));
    }

    public PKCS9Attribute[] getAttributes() {
        PKCS9Attribute[] attribs = new PKCS9Attribute[this.attributes.size()];
        int j = 0;
        for (int i = 1; i < PKCS9Attribute.PKCS9_OIDS.length && j < attribs.length; i++) {
            attribs[j] = getAttribute(PKCS9Attribute.PKCS9_OIDS[i]);
            if (attribs[j] != null) {
                j++;
            }
        }
        return attribs;
    }

    public Object getAttributeValue(ObjectIdentifier oid) throws IOException {
        try {
            return getAttribute(oid).getValue();
        } catch (NullPointerException e) {
            throw new IOException("No value found for attribute " + ((Object) oid));
        }
    }

    public Object getAttributeValue(String name) throws IOException {
        ObjectIdentifier oid = PKCS9Attribute.getOID(name);
        if (oid != null) {
            return getAttributeValue(oid);
        }
        throw new IOException("Attribute name " + name + " not recognized or not supported.");
    }

    public String toString() {
        StringBuffer buf = new StringBuffer(200);
        buf.append("PKCS9 Attributes: [\n\t");
        boolean first = true;
        for (int i = 1; i < PKCS9Attribute.PKCS9_OIDS.length; i++) {
            PKCS9Attribute value = getAttribute(PKCS9Attribute.PKCS9_OIDS[i]);
            if (value != null) {
                if (first) {
                    first = false;
                } else {
                    buf.append(";\n\t");
                }
                buf.append(value.toString());
            }
        }
        buf.append("\n\t] (end PKCS9 Attributes)");
        return buf.toString();
    }

    static DerEncoder[] castToDerEncoder(Object[] objs) {
        DerEncoder[] encoders = new DerEncoder[objs.length];
        for (int i = 0; i < encoders.length; i++) {
            encoders[i] = (DerEncoder) objs[i];
        }
        return encoders;
    }
}
