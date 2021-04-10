package javax.net.ssl;

import java.security.cert.Certificate;

public interface SSLSession {
    String getCipherSuite();

    Certificate[] getLocalCertificates();

    Certificate[] getPeerCertificates();
}
