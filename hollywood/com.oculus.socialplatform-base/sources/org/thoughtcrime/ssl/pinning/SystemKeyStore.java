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
    public static final int CACERTS_FILE_SIZE = 143360;
    public static SystemKeyStore instance;
    public final HashMap<Principal, X509Certificate> trustRoots;
    public final KeyStore trustStore;

    public static synchronized SystemKeyStore getInstance() {
        SystemKeyStore systemKeyStore;
        synchronized (SystemKeyStore.class) {
            systemKeyStore = instance;
            if (systemKeyStore == null) {
                systemKeyStore = new SystemKeyStore();
                instance = systemKeyStore;
            }
        }
        return systemKeyStore;
    }

    @TargetApi(14)
    private KeyStore getTrustStore() {
        try {
            KeyStore instance2 = KeyStore.getInstance("AndroidCAStore");
            instance2.load(null, null);
            return instance2;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        } catch (CertificateException e3) {
            throw new AssertionError(e3);
        } catch (FileNotFoundException e4) {
            throw new AssertionError(e4);
        } catch (IOException e5) {
            throw new AssertionError(e5);
        }
    }

    private HashMap<Principal, X509Certificate> initializeTrustedRoots(KeyStore keyStore) {
        try {
            HashMap<Principal, X509Certificate> hashMap = new HashMap<>();
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(aliases.nextElement());
                if (x509Certificate != null) {
                    hashMap.put(x509Certificate.getSubjectX500Principal(), x509Certificate);
                }
            }
            return hashMap;
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        }
    }

    public X509Certificate getTrustRootFor(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = this.trustRoots.get(x509Certificate.getIssuerX500Principal());
        if (x509Certificate2 == null || x509Certificate2.getSubjectX500Principal().equals(x509Certificate.getSubjectX500Principal())) {
            return null;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return x509Certificate2;
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    public boolean isTrustRoot(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = this.trustRoots.get(x509Certificate.getSubjectX500Principal());
        if (x509Certificate2 == null || !x509Certificate2.getPublicKey().equals(x509Certificate.getPublicKey())) {
            return false;
        }
        return true;
    }

    public SystemKeyStore() {
        KeyStore trustStore2 = getTrustStore();
        this.trustRoots = initializeTrustedRoots(trustStore2);
        this.trustStore = trustStore2;
    }
}
