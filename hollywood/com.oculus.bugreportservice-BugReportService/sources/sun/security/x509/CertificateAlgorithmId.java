package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;

public class CertificateAlgorithmId implements CertAttrSet {
    private AlgorithmId algId;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "algorithmID";
    }

    public CertificateAlgorithmId(DerInputStream derInputStream) {
        this.algId = AlgorithmId.parse(derInputStream.getDerValue());
    }

    public String toString() {
        if (this.algId == null) {
            return "";
        }
        return this.algId.toString() + ", OID = " + this.algId.getOID().toString() + "\n";
    }

    @Override // sun.security.x509.CertAttrSet
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        this.algId.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    public AlgorithmId get(String str) {
        if (str.equalsIgnoreCase("algorithm")) {
            return this.algId;
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:CertificateAlgorithmId.");
    }
}
