package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificateIssuerExtension extends Extension implements CertAttrSet<String> {
    public static final String ISSUER = "issuer";
    public static final String NAME = "CertificateIssuer";
    private GeneralNames names;

    private void encodeThis() throws IOException {
        GeneralNames generalNames = this.names;
        if (generalNames == null || generalNames.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream os = new DerOutputStream();
        this.names.encode(os);
        this.extensionValue = os.toByteArray();
    }

    public CertificateIssuerExtension(GeneralNames issuer) throws IOException {
        this.extensionId = PKIXExtensions.CertificateIssuer_Id;
        this.critical = true;
        this.names = issuer;
        encodeThis();
    }

    public CertificateIssuerExtension(Boolean critical, Object value) throws IOException {
        this.extensionId = PKIXExtensions.CertificateIssuer_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        this.names = new GeneralNames(new DerValue(this.extensionValue));
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (!name.equalsIgnoreCase("issuer")) {
            throw new IOException("Attribute name not recognized by CertAttrSet:CertificateIssuer");
        } else if (obj instanceof GeneralNames) {
            this.names = (GeneralNames) obj;
            encodeThis();
        } else {
            throw new IOException("Attribute value must be of type GeneralNames");
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public GeneralNames get(String name) throws IOException {
        if (name.equalsIgnoreCase("issuer")) {
            return this.names;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateIssuer");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase("issuer")) {
            this.names = null;
            encodeThis();
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateIssuer");
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public String toString() {
        return super.toString() + "Certificate Issuer [\n" + String.valueOf(this.names) + "]\n";
    }

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.CertificateIssuer_Id;
            this.critical = true;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement("issuer");
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return NAME;
    }
}
