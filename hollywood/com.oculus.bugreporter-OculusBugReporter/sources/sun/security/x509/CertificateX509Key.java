package sun.security.x509;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.util.Enumeration;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificateX509Key implements CertAttrSet<String> {
    public static final String IDENT = "x509.info.key";
    public static final String KEY = "value";
    public static final String NAME = "key";
    private PublicKey key;

    public CertificateX509Key(PublicKey key2) {
        this.key = key2;
    }

    public CertificateX509Key(DerInputStream in) throws IOException {
        this.key = X509Key.parse(in.getDerValue());
    }

    public CertificateX509Key(InputStream in) throws IOException {
        this.key = X509Key.parse(new DerValue(in));
    }

    @Override // sun.security.x509.CertAttrSet
    public String toString() {
        PublicKey publicKey = this.key;
        if (publicKey == null) {
            return "";
        }
        return publicKey.toString();
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        tmp.write(this.key.getEncoded());
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (name.equalsIgnoreCase("value")) {
            this.key = (PublicKey) obj;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateX509Key.");
    }

    @Override // sun.security.x509.CertAttrSet
    public PublicKey get(String name) throws IOException {
        if (name.equalsIgnoreCase("value")) {
            return this.key;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateX509Key.");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase("value")) {
            this.key = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet: CertificateX509Key.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement("value");
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "key";
    }
}
