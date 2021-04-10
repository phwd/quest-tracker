package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.platform.Platform;

public abstract class CertificateChainCleaner {
    public abstract List<Certificate> clean(List<Certificate> list, String str) throws SSLPeerUnverifiedException;

    public static CertificateChainCleaner get(X509TrustManager trustManager) {
        return Platform.get().buildCertificateChainCleaner(trustManager);
    }
}
