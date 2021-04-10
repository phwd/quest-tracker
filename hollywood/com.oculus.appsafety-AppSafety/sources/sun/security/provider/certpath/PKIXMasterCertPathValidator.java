package sun.security.provider.certpath;

import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXReason;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import sun.security.util.Debug;

class PKIXMasterCertPathValidator {
    private static final Debug debug = Debug.getInstance("certpath");

    PKIXMasterCertPathValidator() {
    }

    static void validate(CertPath cpOriginal, List<X509Certificate> reversedCertList, List<PKIXCertPathChecker> certPathCheckers) throws CertPathValidatorException {
        Set<String> unresCritExts;
        int cpSize = reversedCertList.size();
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("--------------------------------------------------------------");
            debug.println("Executing PKIX certification path validation algorithm.");
        }
        for (int i = 0; i < cpSize; i++) {
            X509Certificate currCert = reversedCertList.get(i);
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("Checking cert" + (i + 1) + " - Subject: " + ((Object) currCert.getSubjectX500Principal()));
            }
            Set<String> unresCritExts2 = currCert.getCriticalExtensionOIDs();
            if (unresCritExts2 == null) {
                unresCritExts = Collections.emptySet();
            } else {
                unresCritExts = unresCritExts2;
            }
            if (debug != null && !unresCritExts.isEmpty()) {
                StringJoiner joiner = new StringJoiner(", ", "{", "}");
                for (String oid : unresCritExts) {
                    joiner.add(oid);
                }
                debug.println("Set of critical extensions: " + joiner.toString());
            }
            for (int j = 0; j < certPathCheckers.size(); j++) {
                PKIXCertPathChecker currChecker = certPathCheckers.get(j);
                Debug debug4 = debug;
                if (debug4 != null) {
                    debug4.println("-Using checker" + (j + 1) + " ... [" + currChecker.getClass().getName() + "]");
                }
                if (i == 0) {
                    currChecker.init(false);
                }
                try {
                    currChecker.check(currCert, unresCritExts);
                    if (debug != null) {
                        debug.println("-checker" + (j + 1) + " validation succeeded");
                    }
                } catch (CertPathValidatorException cpve) {
                    throw new CertPathValidatorException(cpve.getMessage(), cpve.getCause() != null ? cpve.getCause() : cpve, cpOriginal, cpSize - (i + 1), cpve.getReason());
                }
            }
            if (unresCritExts.isEmpty()) {
                Debug debug5 = debug;
                if (debug5 != null) {
                    debug5.println("\ncert" + (i + 1) + " validation succeeded.\n");
                }
            } else {
                throw new CertPathValidatorException("unrecognized critical extension(s)", null, cpOriginal, cpSize - (i + 1), PKIXReason.UNRECOGNIZED_CRIT_EXT);
            }
        }
        Debug debug6 = debug;
        if (debug6 != null) {
            debug6.println("Cert path validation succeeded. (PKIX validation algorithm)");
            debug.println("--------------------------------------------------------------");
        }
    }
}
