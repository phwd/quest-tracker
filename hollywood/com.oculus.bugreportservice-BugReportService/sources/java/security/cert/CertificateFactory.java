package java.security.cert;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import sun.security.jca.GetInstance;

public class CertificateFactory {
    private CertificateFactorySpi certFacSpi;
    private Provider provider;
    private String type;

    protected CertificateFactory(CertificateFactorySpi certificateFactorySpi, Provider provider2, String str) {
        this.certFacSpi = certificateFactorySpi;
        this.provider = provider2;
        this.type = str;
    }

    public static final CertificateFactory getInstance(String str) {
        try {
            GetInstance.Instance instance = GetInstance.getInstance("CertificateFactory", CertificateFactorySpi.class, str);
            return new CertificateFactory((CertificateFactorySpi) instance.impl, instance.provider, str);
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(str + " not found", e);
        }
    }

    public final Certificate generateCertificate(InputStream inputStream) {
        return this.certFacSpi.engineGenerateCertificate(inputStream);
    }

    public final CertPath generateCertPath(InputStream inputStream) {
        return this.certFacSpi.engineGenerateCertPath(inputStream);
    }

    public final CertPath generateCertPath(List list) {
        return this.certFacSpi.engineGenerateCertPath(list);
    }

    public final CRL generateCRL(InputStream inputStream) {
        return this.certFacSpi.engineGenerateCRL(inputStream);
    }
}
