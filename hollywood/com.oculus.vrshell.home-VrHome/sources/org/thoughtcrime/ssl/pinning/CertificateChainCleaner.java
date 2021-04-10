package org.thoughtcrime.ssl.pinning;

import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;

public class CertificateChainCleaner {
    private CertificateChainCleaner() {
    }

    public static X509Certificate[] getCleanChain(Certificate[] chain, SystemKeyStore systemKeyStore) throws CertificateException {
        LinkedList<X509Certificate> cleanChain = new LinkedList<>();
        boolean trustedChain = false;
        X509Certificate rootCert = (X509Certificate) chain[0];
        if (systemKeyStore.isTrustRoot(rootCert)) {
            trustedChain = true;
        }
        cleanChain.add(rootCert);
        int i = 1;
        while (i < chain.length) {
            X509Certificate cert = (X509Certificate) chain[i];
            X509Certificate prevCert = (X509Certificate) chain[i - 1];
            if (systemKeyStore.isTrustRoot(cert)) {
                trustedChain = true;
            }
            if (!isValidLink(cert, prevCert)) {
                break;
            }
            cleanChain.add(cert);
            i++;
        }
        X509Certificate trustRoot = systemKeyStore.getTrustRootFor((X509Certificate) chain[i - 1]);
        if (trustRoot != null) {
            cleanChain.add(trustRoot);
            trustedChain = true;
        }
        if (trustedChain) {
            return (X509Certificate[]) cleanChain.toArray(new X509Certificate[cleanChain.size()]);
        }
        throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
    }

    private static boolean isValidLink(X509Certificate parent, X509Certificate child) {
        if (!parent.getSubjectX500Principal().equals(child.getIssuerX500Principal())) {
            return false;
        }
        try {
            child.verify(parent.getPublicKey());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }
}
