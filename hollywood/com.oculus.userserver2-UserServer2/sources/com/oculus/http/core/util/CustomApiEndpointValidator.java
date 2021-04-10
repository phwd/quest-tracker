package com.oculus.http.core.util;

import X.Mi;
import X.WV;
import X.XQ;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class CustomApiEndpointValidator {
    public static final String DISABLE_CERT_CHECK_EXPRESSION = "[.]((\\w+[.]sb)|((svcscm[.])?\\d+[.]od)|dev)";
    public static final String DISABLE_CERT_CHECK_HOSTNAME = "^graph[.]((\\w+[.]sb)|((svcscm[.])?\\d+[.]od)|dev)[.]oculus[.]com$";
    public static final Pattern DISABLE_CERT_CHECK_HOSTNAME_PATTERN = Pattern.compile(DISABLE_CERT_CHECK_HOSTNAME);
    public static final String DISABLE_CERT_CHECK_URI = "^https://graph[.]((\\w+[.]sb)|((svcscm[.])?\\d+[.]od)|dev)[.]oculus[.]com$";
    public static final Pattern DISABLE_CERT_CHECK_URI_PATTERN = Pattern.compile(DISABLE_CERT_CHECK_URI);
    public static final String GRAPH_SUBDOMAIN = "^graph";
    public static final String OCULUS_DOMAIN_NAME = "[.]oculus[.]com$";
    public static final String SAFE_URI = "^https://graph.*[.]oculus[.]com$";
    public static final Pattern SAFE_URI_PATTERN = Pattern.compile(SAFE_URI);
    public static final String SECURED_GRAPH_SCHEME = "^https://graph";
    public static final String TAG = "com.oculus.http.core.util.CustomApiEndpointValidator";

    public static void A00(XQ xq) {
        xq.A0I = new HostnameVerifier() {
            /* class com.oculus.http.core.util.CustomApiEndpointValidator.AnonymousClass1 */

            public final boolean verify(String str, SSLSession sSLSession) {
                return CustomApiEndpointValidator.DISABLE_CERT_CHECK_HOSTNAME_PATTERN.matcher(str).matches();
            }
        };
        AnonymousClass2 r4 = new X509TrustManager() {
            /* class com.oculus.http.core.util.CustomApiEndpointValidator.AnonymousClass2 */

            @Override // javax.net.ssl.X509TrustManager
            public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public final X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            SSLContext instance = SSLContext.getInstance("TLSv1.2");
            instance.init(null, new TrustManager[]{r4}, new SecureRandom());
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            if (socketFactory != null) {
                xq.A0J = socketFactory;
                xq.A0L = WV.A01.A01(r4);
                return;
            }
            throw new NullPointerException("sslSocketFactory == null");
        } catch (NoSuchAlgorithmException e) {
            Mi.A06(TAG, e, "NoSuchAlgorithmException when trying to initialize SSL Context.");
            throw new RuntimeException(e);
        } catch (KeyManagementException e2) {
            Mi.A06(TAG, e2, "KeyManagementException when trying to initialize SSL Context.");
            throw new RuntimeException(e2);
        }
    }
}
