package com.facebook.netlite.certificatepinning;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public interface HostnameAwareX509TrustManager extends X509TrustManager {
    void checkServerTrustedUsingPeerHostName(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException;
}
