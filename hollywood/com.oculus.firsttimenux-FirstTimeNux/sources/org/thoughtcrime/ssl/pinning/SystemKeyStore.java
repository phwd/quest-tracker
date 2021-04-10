package org.thoughtcrime.ssl.pinning;

import android.annotation.TargetApi;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;

public class SystemKeyStore {
    private static final int CACERTS_FILE_SIZE = 143360;
    private static SystemKeyStore instance;
    private final HashMap<Principal, X509Certificate> trustRoots;
    public final KeyStore trustStore;

    public static synchronized SystemKeyStore getInstance() {
        SystemKeyStore systemKeyStore;
        synchronized (SystemKeyStore.class) {
            if (instance == null) {
                instance = new SystemKeyStore();
            }
            systemKeyStore = instance;
        }
        return systemKeyStore;
    }

    private SystemKeyStore() {
        KeyStore trustStore2 = getTrustStore();
        this.trustRoots = initializeTrustedRoots(trustStore2);
        this.trustStore = trustStore2;
    }

    public boolean isTrustRoot(X509Certificate certificate) {
        X509Certificate trustRoot = this.trustRoots.get(certificate.getSubjectX500Principal());
        return trustRoot != null && trustRoot.getPublicKey().equals(certificate.getPublicKey());
    }

    public X509Certificate getTrustRootFor(X509Certificate certificate) {
        X509Certificate trustRoot = this.trustRoots.get(certificate.getIssuerX500Principal());
        if (trustRoot == null) {
            return null;
        }
        if (trustRoot.getSubjectX500Principal().equals(certificate.getSubjectX500Principal())) {
            return null;
        }
        try {
            certificate.verify(trustRoot.getPublicKey());
            return trustRoot;
        } catch (GeneralSecurityException e) {
            return null;
        }
    }

    private HashMap<Principal, X509Certificate> initializeTrustedRoots(KeyStore trustStore2) {
        try {
            HashMap<Principal, X509Certificate> trusted = new HashMap<>();
            Enumeration<String> aliases = trustStore2.aliases();
            while (aliases.hasMoreElements()) {
                X509Certificate cert = (X509Certificate) trustStore2.getCertificate(aliases.nextElement());
                if (cert != null) {
                    trusted.put(cert.getSubjectX500Principal(), cert);
                }
            }
            return trusted;
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        }
    }

    @TargetApi(14)
    private KeyStore getTrustStore() {
        try {
            KeyStore trustStore2 = KeyStore.getInstance("AndroidCAStore");
            trustStore2.load(null, null);
            return trustStore2;
        } catch (NoSuchAlgorithmException nsae) {
            throw new AssertionError(nsae);
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        } catch (CertificateException e2) {
            throw new AssertionError(e2);
        } catch (FileNotFoundException e3) {
            throw new AssertionError(e3);
        } catch (IOException e4) {
            throw new AssertionError(e4);
        }
    }
}
