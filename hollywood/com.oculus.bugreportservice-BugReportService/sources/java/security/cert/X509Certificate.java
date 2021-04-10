package java.security.cert;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Collection;
import javax.security.auth.x500.X500Principal;
import sun.security.x509.X509CertImpl;

public abstract class X509Certificate extends Certificate implements X509Extension {
    private static final long serialVersionUID = -2491127588187038216L;
    private transient X500Principal issuerX500Principal;
    private transient X500Principal subjectX500Principal;

    public abstract Principal getIssuerDN();

    public abstract boolean[] getKeyUsage();

    public abstract BigInteger getSerialNumber();

    public abstract Principal getSubjectDN();

    public abstract byte[] getTBSCertificate();

    protected X509Certificate() {
        super("X.509");
    }

    public X500Principal getIssuerX500Principal() {
        if (this.issuerX500Principal == null) {
            this.issuerX500Principal = X509CertImpl.getIssuerX500Principal(this);
        }
        return this.issuerX500Principal;
    }

    public X500Principal getSubjectX500Principal() {
        if (this.subjectX500Principal == null) {
            this.subjectX500Principal = X509CertImpl.getSubjectX500Principal(this);
        }
        return this.subjectX500Principal;
    }

    public Collection getSubjectAlternativeNames() {
        return X509CertImpl.getSubjectAlternativeNames(this);
    }
}
