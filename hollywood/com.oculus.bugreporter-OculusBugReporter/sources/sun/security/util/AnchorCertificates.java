package sun.security.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import sun.security.x509.X509CertImpl;

public class AnchorCertificates {
    private static final String HASH = "SHA-256";
    private static HashSet<String> certs;
    private static final Debug debug = Debug.getInstance("certpath");

    static {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class sun.security.util.AnchorCertificates.AnonymousClass1 */

            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0058, code lost:
                r5 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
                r3.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x005d, code lost:
                r6 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x005e, code lost:
                r4.addSuppressed(r6);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
                throw r5;
             */
            @Override // java.security.PrivilegedAction
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void run() {
                /*
                // Method dump skipped, instructions count: 118
                */
                throw new UnsupportedOperationException("Method not decompiled: sun.security.util.AnchorCertificates.AnonymousClass1.run():java.lang.Void");
            }
        });
    }

    public static boolean contains(X509Certificate cert) {
        Debug debug2;
        boolean result = certs.contains(X509CertImpl.getFingerprint(HASH, cert));
        if (result && (debug2 = debug) != null) {
            debug2.println("AnchorCertificate.contains: matched " + ((Object) cert.getSubjectDN()));
        }
        return result;
    }

    private AnchorCertificates() {
    }
}
