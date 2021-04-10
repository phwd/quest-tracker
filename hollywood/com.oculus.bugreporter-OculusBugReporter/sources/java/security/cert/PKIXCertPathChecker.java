package java.security.cert;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public abstract class PKIXCertPathChecker implements CertPathChecker, Cloneable {
    public abstract void check(Certificate certificate, Collection<String> collection) throws CertPathValidatorException;

    public abstract Set<String> getSupportedExtensions();

    @Override // java.security.cert.CertPathChecker
    public abstract void init(boolean z) throws CertPathValidatorException;

    @Override // java.security.cert.CertPathChecker
    public abstract boolean isForwardCheckingSupported();

    protected PKIXCertPathChecker() {
    }

    @Override // java.security.cert.CertPathChecker
    public void check(Certificate cert) throws CertPathValidatorException {
        check(cert, Collections.emptySet());
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString(), e);
        }
    }
}
