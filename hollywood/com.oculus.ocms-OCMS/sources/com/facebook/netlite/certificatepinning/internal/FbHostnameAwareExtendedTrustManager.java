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

    public FbHostnameAwareExtendedTrustManager(long j) {
        this(j, SystemKeyStore.getInstance());
    }

    FbHostnameAwareExtendedTrustManager(long j, SystemKeyStore systemKeyStore) {
        this.fbHostnameAwarePinningTrustManager = new FbHostnameAwarePinningTrustManager(j, systemKeyStore);
    }

    @Override // com.facebook.netlite.certificatepinning.HostnameAwareX509TrustManager
    public void checkServerTrustedUsingPeerHostName(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
        this.fbHostnameAwarePinningTrustManager.checkServerTrustedUsingPeerHostName(x509CertificateArr, str, str2);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        ((X509ExtendedTrustManager) this.fbHostnameAwarePinningTrustManager.mSystemTrustManager).checkServerTrusted(x509CertificateArr, str, socket);
        this.fbHostnameAwarePinningTrustManager.checkPinTrust(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        ((X509ExtendedTrustManager) this.fbHostnameAwarePinningTrustManager.mSystemTrustManager).checkServerTrusted(x509CertificateArr, str, sSLEngine);
        this.fbHostnameAwarePinningTrustManager.checkPinTrust(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.fbHostnameAwarePinningTrustManager.checkServerTrusted(x509CertificateArr, str);
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.fbHostnameAwarePinningTrustManager.getAcceptedIssuers();
    }
}
