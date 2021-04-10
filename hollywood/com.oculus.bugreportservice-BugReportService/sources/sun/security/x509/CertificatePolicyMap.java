package sun.security.x509;

import sun.security.util.DerOutputStream;

public class CertificatePolicyMap {
    private CertificatePolicyId issuerDomain;

    public String toString() {
        new StringBuilder().append("CertificatePolicyMap: [\nIssuerDomain:");
        this.issuerDomain.toString();
        throw null;
    }

    public void encode(DerOutputStream derOutputStream) {
        this.issuerDomain.encode(new DerOutputStream());
        throw null;
    }
}
