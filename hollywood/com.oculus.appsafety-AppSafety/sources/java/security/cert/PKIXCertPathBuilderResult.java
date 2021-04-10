package java.security.cert;

import java.security.PublicKey;

public class PKIXCertPathBuilderResult extends PKIXCertPathValidatorResult implements CertPathBuilderResult {
    private CertPath certPath;

    public PKIXCertPathBuilderResult(CertPath certPath2, TrustAnchor trustAnchor, PolicyNode policyTree, PublicKey subjectPublicKey) {
        super(trustAnchor, policyTree, subjectPublicKey);
        if (certPath2 != null) {
            this.certPath = certPath2;
            return;
        }
        throw new NullPointerException("certPath must be non-null");
    }

    @Override // java.security.cert.CertPathBuilderResult
    public CertPath getCertPath() {
        return this.certPath;
    }

    @Override // java.security.cert.PKIXCertPathValidatorResult
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("PKIXCertPathBuilderResult: [\n");
        sb.append("  Certification Path: " + ((Object) this.certPath) + "\n");
        sb.append("  Trust Anchor: " + getTrustAnchor().toString() + "\n");
        sb.append("  Policy Tree: " + String.valueOf(getPolicyTree()) + "\n");
        sb.append("  Subject Public Key: " + ((Object) getPublicKey()) + "\n");
        sb.append("]");
        return sb.toString();
    }
}
