package org.thoughtcrime.ssl.pinning;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;

public class CertificateChainCleaner {
    private CertificateChainCleaner() {
    }

    public static X509Certificate[] getCleanChain(Certificate[] certificateArr, SystemKeyStore systemKeyStore) throws CertificateException {
        LinkedList linkedList = new LinkedList();
        X509Certificate x509Certificate = (X509Certificate) certificateArr[0];
        boolean isTrustRoot = systemKeyStore.isTrustRoot(x509Certificate);
        linkedList.add(x509Certificate);
        boolean z = true;
        boolean z2 = isTrustRoot;
        int i = 1;
        while (i < certificateArr.length) {
            X509Certificate x509Certificate2 = (X509Certificate) certificateArr[i];
            X509Certificate x509Certificate3 = (X509Certificate) certificateArr[i - 1];
            if (systemKeyStore.isTrustRoot(x509Certificate2)) {
                z2 = true;
            }
            if (!isValidLink(x509Certificate2, x509Certificate3)) {
                break;
            }
            linkedList.add(x509Certificate2);
            i++;
        }
        X509Certificate trustRootFor = systemKeyStore.getTrustRootFor((X509Certificate) certificateArr[i - 1]);
        if (trustRootFor != null) {
            linkedList.add(trustRootFor);
        } else {
            z = z2;
        }
        if (z) {
            return (X509Certificate[]) linkedList.toArray(new X509Certificate[linkedList.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
    }

    private static boolean isValidLink(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (!x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getIssuerX500Principal())) {
            return false;
        }
        try {
            x509Certificate2.verify(x509Certificate.getPublicKey());
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }
}
