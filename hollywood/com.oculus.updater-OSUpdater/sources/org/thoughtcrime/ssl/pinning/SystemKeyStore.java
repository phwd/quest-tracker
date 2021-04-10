package org.thoughtcrime.ssl.pinning;

import android.annotation.TargetApi;
import com.facebook.ultralight.UL;
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

    public boolean isTrustRoot(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = this.trustRoots.get(x509Certificate.getSubjectX500Principal());
        return x509Certificate2 != null && x509Certificate2.getPublicKey().equals(x509Certificate.getPublicKey());
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

    @TargetApi(UL.id._UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID)
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
}
