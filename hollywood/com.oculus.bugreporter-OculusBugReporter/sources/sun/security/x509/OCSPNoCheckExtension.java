package sun.security.x509;

import java.io.IOException;
import java.util.Enumeration;

public class OCSPNoCheckExtension extends Extension implements CertAttrSet<String> {
    public static final String IDENT = "x509.info.extensions.OCSPNoCheck";
    public static final String NAME = "OCSPNoCheck";

    public OCSPNoCheckExtension() throws IOException {
        this.extensionId = PKIXExtensions.OCSPNoCheck_Id;
        this.critical = false;
        this.extensionValue = new byte[0];
    }

    public OCSPNoCheckExtension(Boolean critical, Object value) throws IOException {
        this.extensionId = PKIXExtensions.OCSPNoCheck_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = new byte[0];
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        throw new IOException("No attribute is allowed by CertAttrSet:OCSPNoCheckExtension.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Object get(String name) throws IOException {
        throw new IOException("No attribute is allowed by CertAttrSet:OCSPNoCheckExtension.");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        throw new IOException("No attribute is allowed by CertAttrSet:OCSPNoCheckExtension.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        return new AttributeNameEnumeration().elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return NAME;
    }
}
