package javax.net.ssl;

import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpsURLConnection extends HttpURLConnection {
    private static SSLSocketFactory defaultSSLSocketFactory;
    private SSLSocketFactory sslSocketFactory = getDefaultSSLSocketFactory();

    protected HttpsURLConnection(URL url) {
        super(url);
    }

    private static class NoPreloadHolder {
        public static HostnameVerifier defaultHostnameVerifier;
        public static final Class originalDefaultHostnameVerifierClass;

        static {
            try {
                defaultHostnameVerifier = (HostnameVerifier) Class.forName("com.android.okhttp.internal.tls.OkHostnameVerifier").getField("INSTANCE").get(null);
                originalDefaultHostnameVerifierClass = defaultHostnameVerifier.getClass();
            } catch (Exception e) {
                throw new AssertionError("Failed to obtain okhttp HostnameVerifier", e);
            }
        }
    }

    public static HostnameVerifier getDefaultHostnameVerifier() {
        return NoPreloadHolder.defaultHostnameVerifier;
    }

    public static SSLSocketFactory getDefaultSSLSocketFactory() {
        if (defaultSSLSocketFactory == null) {
            defaultSSLSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        }
        return defaultSSLSocketFactory;
    }
}
