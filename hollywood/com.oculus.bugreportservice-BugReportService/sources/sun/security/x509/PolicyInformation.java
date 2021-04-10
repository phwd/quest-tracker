package sun.security.x509;

import sun.security.util.DerOutputStream;

public class PolicyInformation {
    private CertificatePolicyId policyIdentifier;

    public boolean equals(Object obj) {
        if (!(obj instanceof PolicyInformation)) {
            return false;
        }
        this.policyIdentifier.equals(((PolicyInformation) obj).getPolicyIdentifier());
        throw null;
    }

    public int hashCode() {
        this.policyIdentifier.hashCode();
        throw null;
    }

    public CertificatePolicyId getPolicyIdentifier() {
        return this.policyIdentifier;
    }

    public String toString() {
        new StringBuilder().append("  [");
        this.policyIdentifier.toString();
        throw null;
    }

    public void encode(DerOutputStream derOutputStream) {
        this.policyIdentifier.encode(new DerOutputStream());
        throw null;
    }
}
