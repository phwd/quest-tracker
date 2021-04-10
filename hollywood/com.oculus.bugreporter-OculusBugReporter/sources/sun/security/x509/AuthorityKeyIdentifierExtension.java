package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class AuthorityKeyIdentifierExtension extends Extension implements CertAttrSet<String> {
    public static final String AUTH_NAME = "auth_name";
    public static final String IDENT = "x509.info.extensions.AuthorityKeyIdentifier";
    public static final String KEY_ID = "key_id";
    public static final String NAME = "AuthorityKeyIdentifier";
    public static final String SERIAL_NUMBER = "serial_number";
    private static final byte TAG_ID = 0;
    private static final byte TAG_NAMES = 1;
    private static final byte TAG_SERIAL_NUM = 2;
    private KeyIdentifier id = null;
    private GeneralNames names = null;
    private SerialNumber serialNum = null;

    private void encodeThis() throws IOException {
        if (this.id == null && this.names == null && this.serialNum == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream seq = new DerOutputStream();
        DerOutputStream tmp = new DerOutputStream();
        if (this.id != null) {
            DerOutputStream tmp1 = new DerOutputStream();
            this.id.encode(tmp1);
            tmp.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 0), tmp1);
        }
        try {
            if (this.names != null) {
                DerOutputStream tmp12 = new DerOutputStream();
                this.names.encode(tmp12);
                tmp.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 1), tmp12);
            }
            if (this.serialNum != null) {
                DerOutputStream tmp13 = new DerOutputStream();
                this.serialNum.encode(tmp13);
                tmp.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 2), tmp13);
            }
            seq.write((byte) 48, tmp);
            this.extensionValue = seq.toByteArray();
        } catch (Exception e) {
            throw new IOException(e.toString());
        }
    }

    public AuthorityKeyIdentifierExtension(KeyIdentifier kid, GeneralNames name, SerialNumber sn) throws IOException {
        this.id = kid;
        this.names = name;
        this.serialNum = sn;
        this.extensionId = PKIXExtensions.AuthorityKey_Id;
        this.critical = false;
        encodeThis();
    }

    public AuthorityKeyIdentifierExtension(Boolean critical, Object value) throws IOException {
        this.extensionId = PKIXExtensions.AuthorityKey_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        DerValue val = new DerValue(this.extensionValue);
        if (val.tag == 48) {
            while (val.data != null && val.data.available() != 0) {
                DerValue opt = val.data.getDerValue();
                if (!opt.isContextSpecific((byte) 0) || opt.isConstructed()) {
                    if (!opt.isContextSpecific((byte) 1) || !opt.isConstructed()) {
                        if (!opt.isContextSpecific((byte) 2) || opt.isConstructed()) {
                            throw new IOException("Invalid encoding of AuthorityKeyIdentifierExtension.");
                        } else if (this.serialNum == null) {
                            opt.resetTag((byte) 2);
                            this.serialNum = new SerialNumber(opt);
                        } else {
                            throw new IOException("Duplicate SerialNumber in AuthorityKeyIdentifier.");
                        }
                    } else if (this.names == null) {
                        opt.resetTag((byte) 48);
                        this.names = new GeneralNames(opt);
                    } else {
                        throw new IOException("Duplicate GeneralNames in AuthorityKeyIdentifier.");
                    }
                } else if (this.id == null) {
                    opt.resetTag((byte) 4);
                    this.id = new KeyIdentifier(opt);
                } else {
                    throw new IOException("Duplicate KeyIdentifier in AuthorityKeyIdentifier.");
                }
            }
            return;
        }
        throw new IOException("Invalid encoding for AuthorityKeyIdentifierExtension.");
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public String toString() {
        String s = super.toString() + "AuthorityKeyIdentifier [\n";
        if (this.id != null) {
            s = s + this.id.toString();
        }
        if (this.names != null) {
            s = s + this.names.toString() + "\n";
        }
        if (this.serialNum != null) {
            s = s + this.serialNum.toString() + "\n";
        }
        return s + "]\n";
    }

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.AuthorityKey_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (name.equalsIgnoreCase("key_id")) {
            if (obj instanceof KeyIdentifier) {
                this.id = (KeyIdentifier) obj;
            } else {
                throw new IOException("Attribute value should be of type KeyIdentifier.");
            }
        } else if (name.equalsIgnoreCase(AUTH_NAME)) {
            if (obj instanceof GeneralNames) {
                this.names = (GeneralNames) obj;
            } else {
                throw new IOException("Attribute value should be of type GeneralNames.");
            }
        } else if (!name.equalsIgnoreCase(SERIAL_NUMBER)) {
            throw new IOException("Attribute name not recognized by CertAttrSet:AuthorityKeyIdentifier.");
        } else if (obj instanceof SerialNumber) {
            this.serialNum = (SerialNumber) obj;
        } else {
            throw new IOException("Attribute value should be of type SerialNumber.");
        }
        encodeThis();
    }

    @Override // sun.security.x509.CertAttrSet
    public Object get(String name) throws IOException {
        if (name.equalsIgnoreCase("key_id")) {
            return this.id;
        }
        if (name.equalsIgnoreCase(AUTH_NAME)) {
            return this.names;
        }
        if (name.equalsIgnoreCase(SERIAL_NUMBER)) {
            return this.serialNum;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:AuthorityKeyIdentifier.");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase("key_id")) {
            this.id = null;
        } else if (name.equalsIgnoreCase(AUTH_NAME)) {
            this.names = null;
        } else if (name.equalsIgnoreCase(SERIAL_NUMBER)) {
            this.serialNum = null;
        } else {
            throw new IOException("Attribute name not recognized by CertAttrSet:AuthorityKeyIdentifier.");
        }
        encodeThis();
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement("key_id");
        elements.addElement(AUTH_NAME);
        elements.addElement(SERIAL_NUMBER);
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return NAME;
    }

    public byte[] getEncodedKeyIdentifier() throws IOException {
        if (this.id == null) {
            return null;
        }
        DerOutputStream derOut = new DerOutputStream();
        this.id.encode(derOut);
        return derOut.toByteArray();
    }
}
