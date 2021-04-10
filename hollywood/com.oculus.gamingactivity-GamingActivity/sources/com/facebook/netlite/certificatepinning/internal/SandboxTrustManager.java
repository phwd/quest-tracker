package com.facebook.netlite.certificatepinning.internal;

import android.util.Base64InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class SandboxTrustManager implements X509TrustManager {
    private static final String FACEBOOK_CAS = "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";
    private static final String KEYSTORE_PASSWORD = "password";
    private final X509TrustManager mSystemTrustManager;
    protected final TrustManager[] mTrustManagers = buildSandboxTrustManagers();

    public SandboxTrustManager(X509TrustManager systemTrustManager) {
        this.mSystemTrustManager = systemTrustManager;
    }

    private static InputStream getInputStream(String ca) {
        return new Base64InputStream(new ByteArrayInputStream(ca.getBytes()), 0);
    }

    private static void loadStore(KeyStore store, String ca) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        store.load(getInputStream(ca), KEYSTORE_PASSWORD.toCharArray());
    }

    private static TrustManager[] buildSandboxTrustManagers() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            loadStore(keyStore, FACEBOOK_CAS);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            return trustManagerFactory.getTrustManagers();
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new IllegalStateException("Failure initialising sandbox TrustManager");
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        this.mSystemTrustManager.checkClientTrusted(chain, authType);
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        try {
            this.mSystemTrustManager.checkServerTrusted(chain, authType);
        } catch (CertificateException e) {
            CertificateException inner = null;
            try {
                for (TrustManager trustManager : this.mTrustManagers) {
                    ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
                    inner = null;
                }
            } catch (CertificateException innerEx) {
                inner = innerEx;
            }
            if (inner != null) {
                throw inner;
            }
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] results = this.mSystemTrustManager.getAcceptedIssuers();
        for (TrustManager trustManager : this.mTrustManagers) {
            X509Certificate[] certs = ((X509TrustManager) trustManager).getAcceptedIssuers();
            int existingLength = results.length;
            results = (X509Certificate[]) Arrays.copyOf(results, certs.length + existingLength);
            System.arraycopy(certs, 0, results, existingLength, certs.length);
        }
        return results;
    }
}
