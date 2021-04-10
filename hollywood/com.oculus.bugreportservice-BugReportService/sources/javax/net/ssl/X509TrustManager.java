package javax.net.ssl;

import java.security.cert.X509Certificate;

public interface X509TrustManager extends TrustManager {
    X509Certificate[] getAcceptedIssuers();
}
