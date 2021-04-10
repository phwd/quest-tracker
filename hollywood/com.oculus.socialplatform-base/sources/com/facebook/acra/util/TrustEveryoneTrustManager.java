package com.facebook.acra.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.annotation.Nullable;
import javax.net.ssl.X509TrustManager;

public class TrustEveryoneTrustManager implements X509TrustManager {
    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    @Nullable
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
