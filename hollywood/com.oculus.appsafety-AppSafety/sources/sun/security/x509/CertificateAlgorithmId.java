package sun.security.x509;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificateAlgorithmId implements CertAttrSet<String> {
    public static final String ALGORITHM = "algorithm";
    public static final String IDENT = "x509.info.algorithmID";
    public static final String NAME = "algorithmID";
    private AlgorithmId algId;

    public CertificateAlgorithmId(AlgorithmId algId2) {
        this.algId = algId2;
    }

    public CertificateAlgorithmId(DerInputStream in) throws IOException {
        this.algId = AlgorithmId.parse(in.getDerValue());
    }

    public CertificateAlgorithmId(InputStream in) throws IOException {
        this.algId = AlgorithmId.parse(new DerValue(in));
    }

    @Override // sun.security.x509.CertAttrSet
    public String toString() {
        if (this.algId == null) {
            return "";
        }
        return this.algId.toString() + ", OID = " + this.algId.getOID().toString() + "\n";
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        this.algId.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (!(obj instanceof AlgorithmId)) {
            throw new IOException("Attribute must be of type AlgorithmId.");
        } else if (name.equalsIgnoreCase("algorithm")) {
            this.algId = (AlgorithmId) obj;
        } else {
            throw new IOException("Attribute name not recognized by CertAttrSet:CertificateAlgorithmId.");
        }
    }

    @Override // sun.security.x509.CertAttrSet
    public AlgorithmId get(String name) throws IOException {
        if (name.equalsIgnoreCase("algorithm")) {
            return this.algId;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateAlgorithmId.");
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase("algorithm")) {
            this.algId = null;
            return;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateAlgorithmId.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement("algorithm");
        return elements.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "algorithmID";
    }
}
