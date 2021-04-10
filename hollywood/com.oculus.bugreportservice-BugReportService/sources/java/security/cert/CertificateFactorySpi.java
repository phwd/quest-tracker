package java.security.cert;

import java.io.InputStream;
import java.util.List;

public abstract class CertificateFactorySpi {
    public abstract CRL engineGenerateCRL(InputStream inputStream);

    public abstract Certificate engineGenerateCertificate(InputStream inputStream);

    public CertPath engineGenerateCertPath(InputStream inputStream) {
        throw new UnsupportedOperationException();
    }

    public CertPath engineGenerateCertPath(List list) {
        throw new UnsupportedOperationException();
    }
}
