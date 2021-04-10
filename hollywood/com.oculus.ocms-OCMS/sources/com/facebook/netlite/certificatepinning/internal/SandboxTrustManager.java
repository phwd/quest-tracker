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

    public SandboxTrustManager(X509TrustManager x509TrustManager) {
        this.mSystemTrustManager = x509TrustManager;
    }

    private static InputStream getInputStream(String str) {
        return new Base64InputStream(new ByteArrayInputStream(str.getBytes()), 0);
    }

    private static void loadStore(KeyStore keyStore, String str) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        keyStore.load(getInputStream(str), "password".toCharArray());
    }

    private static TrustManager[] buildSandboxTrustManagers() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            loadStore(instance, FACEBOOK_CAS);
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            return instance2.getTrustManagers();
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
            throw new IllegalStateException("Failure initialising sandbox TrustManager");
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.mSystemTrustManager.checkClientTrusted(x509CertificateArr, str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|(2:4|(2:6|7)(1:12))|(1:13)(1:11)) */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r3 = r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0006 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0019 A[EDGE_INSN: B:12:0x0019->B:10:0x0019 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x000d A[Catch:{ CertificateException -> 0x0017 }] */
    @Override // javax.net.ssl.X509TrustManager
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkServerTrusted(java.security.cert.X509Certificate[] r5, java.lang.String r6) throws java.security.cert.CertificateException {
        /*
            r4 = this;
            javax.net.ssl.X509TrustManager r0 = r4.mSystemTrustManager     // Catch:{ CertificateException -> 0x0006 }
            r0.checkServerTrusted(r5, r6)     // Catch:{ CertificateException -> 0x0006 }
            goto L_0x001b
        L_0x0006:
            javax.net.ssl.TrustManager[] r0 = r4.mTrustManagers     // Catch:{ CertificateException -> 0x0017 }
            int r1 = r0.length     // Catch:{ CertificateException -> 0x0017 }
            r2 = 0
        L_0x000a:
            r3 = 0
            if (r2 >= r1) goto L_0x0019
            r3 = r0[r2]     // Catch:{ CertificateException -> 0x0017 }
            javax.net.ssl.X509TrustManager r3 = (javax.net.ssl.X509TrustManager) r3     // Catch:{ CertificateException -> 0x0017 }
            r3.checkServerTrusted(r5, r6)     // Catch:{ CertificateException -> 0x0017 }
            int r2 = r2 + 1
            goto L_0x000a
        L_0x0017:
            r5 = move-exception
            r3 = r5
        L_0x0019:
            if (r3 != 0) goto L_0x001c
        L_0x001b:
            return
        L_0x001c:
            goto L_0x001e
        L_0x001d:
            throw r3
        L_0x001e:
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.netlite.certificatepinning.internal.SandboxTrustManager.checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String):void");
    }

    public X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] acceptedIssuers = this.mSystemTrustManager.getAcceptedIssuers();
        X509Certificate[] x509CertificateArr = acceptedIssuers;
        for (TrustManager trustManager : this.mTrustManagers) {
            X509Certificate[] acceptedIssuers2 = ((X509TrustManager) trustManager).getAcceptedIssuers();
            int length = x509CertificateArr.length;
            x509CertificateArr = (X509Certificate[]) Arrays.copyOf(x509CertificateArr, acceptedIssuers2.length + length);
            System.arraycopy(acceptedIssuers2, 0, x509CertificateArr, length, acceptedIssuers2.length);
        }
        return x509CertificateArr;
    }
}
