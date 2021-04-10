package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class SubjectKeyIdentifierExtension extends Extension implements CertAttrSet<String> {
    public static final String IDENT = "x509.info.extensions.SubjectKeyIdentifier";
    public static final String KEY_ID = "key_id";
    public static final String NAME = "SubjectKeyIdentifier";
    private KeyIdentifier id = null;

    private void encodeThis() throws IOException {
        if (this.id == null) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream os = new DerOutputStream();
        this.id.encode(os);
        this.extensionValue = os.toByteArray();
    }

    public SubjectKeyIdentifierExtension(byte[] octetString) throws IOException {
        this.id = new KeyIdentifier(octetString);
        this.extensionId = PKIXExtensions.SubjectKey_Id;
        this.critical = false;
        encodeThis();
    }

    public SubjectKeyIdentifierExtension(Boolean critical, Object value) throws IOException {
        this.extensionId = PKIXExtensions.SubjectKey_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        this.id = new KeyIdentifier(new DerValue(this.extensionValue));
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public String toString() {
        return super.toString() + "SubjectKeyIdentifier [\n" + String.valueOf(this.id) + "]\n";
    }

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.SubjectKey_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (!name.equalsIgnoreCase("key_id")) {
            throw new IOException("Attribute name not recognized by CertAttrSet:SubjectKeyIdentifierExtension.");
        } else if (obj instanceof KeyIdentifier) {
            this.id = (KeyIdentifier) obj;
            encodeThis();
        } else {
            throw new IOException("Attribute value should be of type KeyIdentifier.");
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public KeyIdentifier get(String name) throws IOException {
        if (name.equalsIgnoreCase("key_id")) {
            return this.id;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:SubjectKeyIdentifierExtension.");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase("key_id")) {
            this.id = null;
            encodeThis();
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:SubjectKeyIdentifierExtension.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement("key_id");
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return NAME;
    }
}
