package sun.security.provider.certpath;

import java.io.IOException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXReason;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import sun.security.util.Debug;
import sun.security.x509.NameConstraintsExtension;
import sun.security.x509.PKIXExtensions;
import sun.security.x509.X509CertImpl;

class ConstraintsChecker extends PKIXCertPathChecker {
    private static final Debug debug = Debug.getInstance("certpath");
    private final int certPathLength;
    private int i;
    private int maxPathLength;
    private NameConstraintsExtension prevNC;
    private Set<String> supportedExts;

    ConstraintsChecker(int certPathLength2) {
        this.certPathLength = certPathLength2;
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
    public void init(boolean forward) throws CertPathValidatorException {
        if (!forward) {
            this.i = 0;
            this.maxPathLength = this.certPathLength;
            this.prevNC = null;
            return;
        }
        throw new CertPathValidatorException("forward checking not supported");
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
    public boolean isForwardCheckingSupported() {
        return false;
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public Set<String> getSupportedExtensions() {
        if (this.supportedExts == null) {
            this.supportedExts = new HashSet(2);
            this.supportedExts.add(PKIXExtensions.BasicConstraints_Id.toString());
            this.supportedExts.add(PKIXExtensions.NameConstraints_Id.toString());
            this.supportedExts = Collections.unmodifiableSet(this.supportedExts);
        }
        return this.supportedExts;
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public void check(Certificate cert, Collection<String> unresCritExts) throws CertPathValidatorException {
        X509Certificate currCert = (X509Certificate) cert;
        this.i++;
        checkBasicConstraints(currCert);
        verifyNameConstraints(currCert);
        if (unresCritExts != null && !unresCritExts.isEmpty()) {
            unresCritExts.remove(PKIXExtensions.BasicConstraints_Id.toString());
            unresCritExts.remove(PKIXExtensions.NameConstraints_Id.toString());
        }
    }

    private void verifyNameConstraints(X509Certificate currCert) throws CertPathValidatorException {
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("---checking " + "name constraints" + "...");
        }
        if (this.prevNC != null && (this.i == this.certPathLength || !X509CertImpl.isSelfIssued(currCert))) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("prevNC = " + ((Object) this.prevNC) + ", currDN = " + ((Object) currCert.getSubjectX500Principal()));
            }
            try {
                if (!this.prevNC.verify(currCert)) {
                    throw new CertPathValidatorException("name constraints" + " check failed", null, null, -1, PKIXReason.INVALID_NAME);
                }
            } catch (IOException ioe) {
                throw new CertPathValidatorException(ioe);
            }
        }
        this.prevNC = mergeNameConstraints(currCert, this.prevNC);
        Debug debug4 = debug;
        if (debug4 != null) {
            debug4.println("name constraints" + " verified.");
        }
    }

    static NameConstraintsExtension mergeNameConstraints(X509Certificate currCert, NameConstraintsExtension prevNC2) throws CertPathValidatorException {
        try {
            NameConstraintsExtension newConstraints = X509CertImpl.toImpl(currCert).getNameConstraintsExtension();
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("prevNC = " + ((Object) prevNC2) + ", newNC = " + String.valueOf(newConstraints));
            }
            if (prevNC2 == null) {
                Debug debug3 = debug;
                if (debug3 != null) {
                    debug3.println("mergedNC = " + String.valueOf(newConstraints));
                }
                if (newConstraints == null) {
                    return newConstraints;
                }
                return (NameConstraintsExtension) newConstraints.clone();
            }
            try {
                prevNC2.merge(newConstraints);
                Debug debug4 = debug;
                if (debug4 != null) {
                    debug4.println("mergedNC = " + ((Object) prevNC2));
                }
                return prevNC2;
            } catch (IOException ioe) {
                throw new CertPathValidatorException(ioe);
            }
        } catch (CertificateException ce) {
            throw new CertPathValidatorException(ce);
        }
    }

    private void checkBasicConstraints(X509Certificate currCert) throws CertPathValidatorException {
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("---checking " + "basic constraints" + "...");
            Debug debug3 = debug;
            debug3.println("i = " + this.i + ", maxPathLength = " + this.maxPathLength);
        }
        if (this.i < this.certPathLength) {
            int pathLenConstraint = -1;
            if (currCert.getVersion() >= 3) {
                pathLenConstraint = currCert.getBasicConstraints();
            } else if (this.i == 1 && X509CertImpl.isSelfIssued(currCert)) {
                pathLenConstraint = Integer.MAX_VALUE;
            }
            if (pathLenConstraint != -1) {
                if (!X509CertImpl.isSelfIssued(currCert)) {
                    int i2 = this.maxPathLength;
                    if (i2 > 0) {
                        this.maxPathLength = i2 - 1;
                    } else {
                        throw new CertPathValidatorException("basic constraints" + " check failed: pathLenConstraint violated - this cert must be the last cert in the certification path", null, null, -1, PKIXReason.PATH_TOO_LONG);
                    }
                }
                if (pathLenConstraint < this.maxPathLength) {
                    this.maxPathLength = pathLenConstraint;
                }
            } else {
                throw new CertPathValidatorException("basic constraints" + " check failed: this is not a CA certificate", null, null, -1, PKIXReason.NOT_CA_CERT);
            }
        }
        Debug debug4 = debug;
        if (debug4 != null) {
            debug4.println("after processing, maxPathLength = " + this.maxPathLength);
            Debug debug5 = debug;
            debug5.println("basic constraints" + " verified.");
        }
    }

    static int mergeBasicConstraints(X509Certificate cert, int maxPathLength2) {
        int pathLenConstraint = cert.getBasicConstraints();
        if (!X509CertImpl.isSelfIssued(cert)) {
            maxPathLength2--;
        }
        return pathLenConstraint < maxPathLength2 ? pathLenConstraint : maxPathLength2;
    }
}
