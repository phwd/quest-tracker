package org.thoughtcrime.ssl.pinning;

import android.util.Log;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class PinningTrustManager implements X509TrustManager {
    public final Set<X509Certificate> cache = Collections.synchronizedSet(new HashSet());
    public final long enforceUntilTimestampMillis;
    public final List<byte[]> pins = new LinkedList();
    public final SystemKeyStore systemKeyStore;
    public final TrustManager[] systemTrustManagers;

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    private void checkPinTrust(X509Certificate[] x509CertificateArr) throws CertificateException {
        long j = this.enforceUntilTimestampMillis;
        if (j == 0 || System.currentTimeMillis() <= j) {
            for (X509Certificate x509Certificate : CertificateChainCleaner.getCleanChain(x509CertificateArr, this.systemKeyStore)) {
                if (isValidPin(x509Certificate)) {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        Log.w("PinningTrustManager", "Certificate pins are stale, falling back to system trust.");
    }

    private void checkSystemTrust(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        for (TrustManager trustManager : this.systemTrustManagers) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
    }

    private TrustManager[] initializeSystemTrustManagers(SystemKeyStore systemKeyStore2) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init(systemKeyStore2.trustStore);
            return instance.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        } catch (KeyStoreException e2) {
            throw new AssertionError(e2);
        }
    }

    private boolean isValidPin(X509Certificate x509Certificate) throws CertificateException {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] bArr : this.pins) {
                if (Arrays.equals(bArr, digest)) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(e);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (!this.cache.contains(x509CertificateArr[0])) {
            checkSystemTrust(x509CertificateArr, str);
            checkPinTrust(x509CertificateArr);
            this.cache.add(x509CertificateArr[0]);
        }
    }

    public void clearCache() {
        this.cache.clear();
    }

    public PinningTrustManager(SystemKeyStore systemKeyStore2, String[] strArr, long j) {
        this.systemTrustManagers = initializeSystemTrustManagers(systemKeyStore2);
        this.systemKeyStore = systemKeyStore2;
        this.enforceUntilTimestampMillis = j;
        for (String str : strArr) {
            this.pins.add(hexStringToByteArray(str));
        }
    }

    private byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length >> 1)];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
