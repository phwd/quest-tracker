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

    public FbPinningTrustManager(long appBuildTimeInMs) {
        this(appBuildTimeInMs, SystemKeyStore.getInstance());
    }

    FbPinningTrustManager(long appBuildTimeInMs, @Nullable SystemKeyStore keyStore) {
        this.mPins = new HashSet();
        this.mKeyStore = keyStore;
        this.mSystemTrustManager = initializeSystemTrustManager();
        this.mPinTimeoutSet = appBuildTimeInMs > 0;
        this.mEnforceUntilTimestampMillis = 31536000000L + appBuildTimeInMs;
        for (String pin : CertificatePinningData.FB_CERT_SHA256_PINS) {
            this.mPins.add(ByteBuffer.wrap(Base64.decode(pin, 0)));
        }
    }

    private static X509TrustManager initializeSystemTrustManager() {
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance("X509");
            factory.init((KeyStore) null);
            TrustManager[] trustManagers = factory.getTrustManagers();
            if (trustManagers.length == 1) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new CertificateException("Unable to create system TrustManger");
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new IllegalStateException("Failure initializing TrustManager", e);
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        checkSystemTrust(chain, authType);
        checkPinTrust(chain);
    }

    private void checkSystemTrust(X509Certificate[] chain, String authType) throws CertificateException {
        this.mSystemTrustManager.checkServerTrusted(chain, authType);
    }

    /* access modifiers changed from: protected */
    public void checkPinTrust(X509Certificate[] chain) throws CertificateException {
        if (this.mKeyStore == null) {
            throw new CertificateException("SystemKeystore is not intialized.");
        }
        checkPinTrustWithCleanChain(Arrays.asList(CertificateChainCleaner.getCleanChain(chain, this.mKeyStore)));
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public void checkPinTrustWithCleanChain(List<X509Certificate> chain) throws CertificateException {
        if (this.mPinTimeoutSet && System.currentTimeMillis() > this.mEnforceUntilTimestampMillis) {
            return;
        }
        if (chain.isEmpty()) {
            throw new CertificateException("pinning error: certificate chain empty");
        }
        for (X509Certificate certificate : chain) {
            if (isValidPin(certificate)) {
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("pinning error, trusted chain: ");
        for (X509Certificate certificate2 : chain) {
            sb.append(Base64.encodeToString(certificate2.getEncoded(), 0)).append("\n");
        }
        throw new CertificateException(sb.toString());
    }

    private boolean isValidPin(X509Certificate certificate) throws CertificateException {
        try {
            return this.mPins.contains(ByteBuffer.wrap(MessageDigest.getInstance("SHA-256").digest(certificate.getPublicKey().getEncoded())));
        } catch (NoSuchAlgorithmException nsae) {
            throw new CertificateException(nsae);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.mSystemTrustManager.getAcceptedIssuers();
    }
}
