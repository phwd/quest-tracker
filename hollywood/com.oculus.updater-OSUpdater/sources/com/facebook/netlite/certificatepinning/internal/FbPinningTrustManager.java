package com.facebook.netlite.certificatepinning.internal;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.facebook.annotations.OkToExtend;
import java.nio.ByteBuffer;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.thoughtcrime.ssl.pinning.CertificateChainCleaner;
import org.thoughtcrime.ssl.pinning.SystemKeyStore;

@OkToExtend
public class FbPinningTrustManager implements X509TrustManager {
    private final long mEnforceUntilTimestampMillis;
    @Nullable
    private SystemKeyStore mKeyStore;
    private final boolean mPinTimeoutSet;
    private final Set<ByteBuffer> mPins;
    protected final X509TrustManager mSystemTrustManager;

    public FbPinningTrustManager(long j) {
        this(j, SystemKeyStore.getInstance());
    }

    FbPinningTrustManager(long j, @Nullable SystemKeyStore systemKeyStore) {
        this.mPins = new HashSet();
        this.mKeyStore = systemKeyStore;
        this.mSystemTrustManager = initializeSystemTrustManager();
        this.mPinTimeoutSet = j > 0;
        this.mEnforceUntilTimestampMillis = j + 31536000000L;
        for (String str : CertificatePinningData.FB_CERT_SHA256_PINS) {
            this.mPins.add(ByteBuffer.wrap(Base64.decode(str, 0)));
        }
    }

    private static X509TrustManager initializeSystemTrustManager() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init((KeyStore) null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new CertificateException("Unable to create system TrustManger");
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new IllegalStateException("Failure initializing TrustManager", e);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        checkSystemTrust(x509CertificateArr, str);
        checkPinTrust(x509CertificateArr);
    }

    private void checkSystemTrust(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.mSystemTrustManager.checkServerTrusted(x509CertificateArr, str);
    }

    /* access modifiers changed from: protected */
    public void checkPinTrust(X509Certificate[] x509CertificateArr) throws CertificateException {
        SystemKeyStore systemKeyStore = this.mKeyStore;
        if (systemKeyStore != null) {
            checkPinTrustWithCleanChain(Arrays.asList(CertificateChainCleaner.getCleanChain(x509CertificateArr, systemKeyStore)));
            return;
        }
        throw new CertificateException("SystemKeystore is not intialized.");
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public void checkPinTrustWithCleanChain(List<X509Certificate> list) throws CertificateException {
        if (this.mPinTimeoutSet && System.currentTimeMillis() > this.mEnforceUntilTimestampMillis) {
            return;
        }
        if (!list.isEmpty()) {
            for (X509Certificate x509Certificate : list) {
                if (isValidPin(x509Certificate)) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("pinning error, trusted chain: ");
            for (X509Certificate x509Certificate2 : list) {
                sb.append(Base64.encodeToString(x509Certificate2.getEncoded(), 0));
                sb.append("\n");
            }
            throw new CertificateException(sb.toString());
        }
        throw new CertificateException("pinning error: certificate chain empty");
    }

    private boolean isValidPin(X509Certificate x509Certificate) throws CertificateException {
        try {
            return this.mPins.contains(ByteBuffer.wrap(MessageDigest.getInstance("SHA-256").digest(x509Certificate.getPublicKey().getEncoded())));
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(e);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.mSystemTrustManager.getAcceptedIssuers();
    }
}
