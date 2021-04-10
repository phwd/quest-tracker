package com.facebook.netlite.certificatepinning.internal;

import android.annotation.TargetApi;
import com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import org.thoughtcrime.ssl.pinning.SystemKeyStore;

@TargetApi(24)
public class FbHostnameAwareExtendedTrustManager extends X509ExtendedTrustManager implements HostnameAwareX509TrustManager {
    private final FbHostnameAwarePinningTrustManager fbHostnameAwarePinningTrustManager;

    public FbHostnameAwareExtendedTrustManager(long appBuildTimeInMs) {
        this(appBuildTimeInMs, SystemKeyStore.getInstance());
    }

    FbHostnameAwareExtendedTrustManager(long appBuildTimeInMs, SystemKeyStore keyStore) {
        this.fbHostnameAwarePinningTrustManager = new FbHostnameAwarePinningTrustManager(appBuildTimeInMs, keyStore);
    }

    @Override // com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager
    public void checkServerTrustedUsingPeerHostName(X509Certificate[] chain, String authType, String host) throws CertificateException {
        this.fbHostnameAwarePinningTrustManager.checkServerTrustedUsingPeerHostName(chain, authType, host);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
        ((X509ExtendedTrustManager) this.fbHostnameAwarePinningTrustManager.mSystemTrustManager).checkServerTrusted(chain, authType, socket);
        this.fbHostnameAwarePinningTrustManager.checkPinTrust(chain);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {
        ((X509ExtendedTrustManager) this.fbHostnameAwarePinningTrustManager.mSystemTrustManager).checkServerTrusted(chain, authType, engine);
        this.fbHostnameAwarePinningTrustManager.checkPinTrust(chain);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        this.fbHostnameAwarePinningTrustManager.checkServerTrusted(chain, authType);
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.fbHostnameAwarePinningTrustManager.getAcceptedIssuers();
    }
}
