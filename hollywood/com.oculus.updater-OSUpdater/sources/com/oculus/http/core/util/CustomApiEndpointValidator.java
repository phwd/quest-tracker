package com.oculus.http.core.util;

import com.facebook.debug.log.BLog;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;

public class CustomApiEndpointValidator {
    private static final Pattern DISABLE_CERT_CHECK_HOSTNAME_PATTERN = Pattern.compile("^graph[.]((\\w+[.]sb)|((svcscm[.])?\\d+[.]od)|dev)[.]oculus[.]com$");
    private static final Pattern DISABLE_CERT_CHECK_URI_PATTERN = Pattern.compile("^https://graph[.]((\\w+[.]sb)|((svcscm[.])?\\d+[.]od)|dev)[.]oculus[.]com$");
    private static final Pattern SAFE_URI_PATTERN = Pattern.compile("^https://graph.*[.]oculus[.]com$");
    private static final String TAG = "com.oculus.http.core.util.CustomApiEndpointValidator";

    public static boolean isDisableHostNameValid(String str) {
        return DISABLE_CERT_CHECK_HOSTNAME_PATTERN.matcher(str).matches();
    }

    public static boolean isDisabledUriValid(String str) {
        return DISABLE_CERT_CHECK_URI_PATTERN.matcher(str).matches();
    }

    public static void disableCertCheckForHttpClient(OkHttpClient.Builder builder) {
        builder.hostnameVerifier(new HostnameVerifier() {
            /* class com.oculus.http.core.util.CustomApiEndpointValidator.AnonymousClass1 */

            public boolean verify(String str, SSLSession sSLSession) {
                return CustomApiEndpointValidator.isDisableHostNameValid(str);
            }
        });
        AnonymousClass2 r0 = new X509TrustManager() {
            /* class com.oculus.http.core.util.CustomApiEndpointValidator.AnonymousClass2 */

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            SSLContext instance = SSLContext.getInstance("TLSv1.2");
            instance.init(null, new TrustManager[]{r0}, new SecureRandom());
            builder.sslSocketFactory(instance.getSocketFactory(), r0);
        } catch (NoSuchAlgorithmException e) {
            BLog.e(TAG, e, "NoSuchAlgorithmException when trying to initialize SSL Context.");
            throw new RuntimeException(e);
        } catch (KeyManagementException e2) {
            BLog.e(TAG, e2, "KeyManagementException when trying to initialize SSL Context.");
            throw new RuntimeException(e2);
        }
    }
}
